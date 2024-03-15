package com.example.demorestapi.services;

import com.example.demorestapi.controllers.UserController;
import com.example.demorestapi.interfaces.IJwtService;
import com.example.demorestapi.interfaces.IUserService;
import com.example.demorestapi.models.User;
import com.example.demorestapi.repositories.UserRepository;
import com.example.demorestapi.viewModels.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class UserService implements IUserService {
    private final IJwtService jwtService;
    private final HttpServletRequest httpServletRequest;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;
    @Autowired
    public UserService(IJwtService jwtService, HttpServletRequest httpServletRequest, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.httpServletRequest = httpServletRequest;
        this.userRepository = userRepository;
    }

    public Claims getJwtClaims() throws JwtException {
        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer ".length()).trim();
            return jwtService.decodeJwt(token);
        }
        return null;
    }

    @Override
    public List<UserVM> getAllData() {
        List<User> list = userRepository.findAll();
        return list.stream()
                .map(x -> new UserVM(
                        x.getId(), x.getUserName(), x.getEmail(), x.getNumberPhone()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public UserVM getDataWithToken() {
        Claims claims = getJwtClaims();

        if (claims != null) {
            Optional<User> user = Optional.ofNullable(userRepository.findById(Integer.parseInt(claims.getSubject())));

            if (user.isPresent()) {
                User getUser = user.get();
                return new UserVM(
                        getUser.getId(),
                        getUser.getUserName(),
                        getUser.getEmail(),
                        getUser.getNumberPhone()
                );
            } else {
                throw new RuntimeException("user not found");
            }
        } else {
            throw new RuntimeException("Invalid or missing token");
        }
    }

    @Override
    @Transactional
    public CompletableFuture<Integer> CreateNewUser(CreateUserRequest request) {
        User checkUser = userRepository.findByUserName(request.getUserName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (checkUser != null) {
            throw new RuntimeException("tên người dùng này đã tồn tại!");
        }
        String passwordHash = passwordEncoder.encode(request.getPassword());
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setNumberPhone(request.getNumberPhone());
        user.setPassword(passwordHash);
        userRepository.save(user);
        return CompletableFuture.completedFuture(user.getId());
    }

    @Override
    public CompletableFuture<Boolean> DeleteUser(int id) {
        Optional<User> checkUserOptional = Optional.ofNullable(userRepository.findById(id));
        if (checkUserOptional.isEmpty()) {
            throw new RuntimeException("Không tồn tại người dùng!");
        }
        User checkUser = checkUserOptional.get();
        userRepository.delete(checkUser);
        return CompletableFuture.completedFuture(true);
    }

    @Override
    public CompletableFuture<Boolean> UpdateUser(UpdateUserRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            User user = userRepository.findById(request.getId());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (user == null) {
                throw new RuntimeException("Không tồn tại người dùng!");
            }
            String passwordHash = passwordEncoder.encode(request.getPassword());
            user.setPassword(passwordHash);
            user.setEmail(request.getEmail());
            user.setNumberPhone(request.getNumberPhone());
            userRepository.save(user);
            return true;
        });
    }

    @Override
    public CompletableFuture<UserLoginVM> UserLogin(UserLoginRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            User user = userRepository.findByUserName(request.getUserName());
            if (user == null) {
                throw new RuntimeException("Tên đăng nhập không tồn tại!");
            }
            boolean isCorrect = new BCryptPasswordEncoder().matches(request.getPassword(), user.getPassword());
            if (!isCorrect) {
                throw new RuntimeException("Mật khẩu không hợp lệ!");
            }
            GenerateJwtRequest jwtRequest = new GenerateJwtRequest(
                    user.getId(),
                    user.getUserName()
            );
            String token = jwtService.generateJwt(jwtRequest);
            return new UserLoginVM(token);
        });
    }
//    @Scheduled(cron = "0/1 * * * * ?", zone = "GMT-7")
//    public void DemoScheduledCron() {
//        logger.info("DemoCron");
//    }
//    @Scheduled(cron = Scheduled.CRON_DISABLED)
//    public void CronDisabled(){
//        logger.info("CronDisabled");
//    }
//    @Scheduled(fixedDelayString = "1000", timeUnit = TimeUnit.SECONDS)
//    public void delayString(){
//        logger.info("Fixed Delay String");
//    }
//    @Scheduled(fixedRate = 1000)
//    public void DemoScheduledFixedRate(){
//        logger.info("DemoScheduledFixedRate");
//    }
//    @Scheduled(fixedDelay = 1000)
//    public void DemoScheduledFixedDelay(){
//        logger.info("DemoScheduledFixedDelay");
//    }
}
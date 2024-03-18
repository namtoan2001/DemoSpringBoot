package com.example.demorestapi.interceptors;

import com.example.demorestapi.models.LogCount;
import com.example.demorestapi.repositories.LogCountRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Date;

@Component
public class LogCountInterceptors implements HandlerInterceptor {
    private final LogCountRepository logCountRepository;

    public LogCountInterceptors(LogCountRepository logCountRepository) {
        this.logCountRepository = logCountRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler){
        boolean isTransaction = request.getParameter("isTransaction") != null && request.getParameter("isTransaction").equals("true");
        Date currentDate = new Date();
        LogCount logCount = logCountRepository.findByDate(currentDate)
                .orElseGet(() -> {
                    LogCount newLogCount = new LogCount();
                    newLogCount.setDate(currentDate);
                    return newLogCount;
                });
        logCount.setRequestCount(logCount.getRequestCount() + 1);
        if (isTransaction) {
            logCount.setTransactionCount(logCount.getTransactionCount() + 1);
        }
        logCountRepository.save(logCount);
        return true;
    }
}

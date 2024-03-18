package com.example.demorestapi.services;

import com.example.demorestapi.interfaces.ILogCountService;
import com.example.demorestapi.models.LogCount;
import com.example.demorestapi.repositories.LogCountRepository;
import com.example.demorestapi.viewModels.LogCountVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogCountService implements ILogCountService {
    private final LogCountRepository logCountRepository;
    public LogCountService(LogCountRepository logCountRepository) {
        this.logCountRepository = logCountRepository;
    }

    @Override
    public List<LogCountVM> GetLogCount() {
        List<LogCount> list = logCountRepository.findAll();
        return list.stream()
                .map(x -> new LogCountVM(
                        x.getRequestCount(), x.getTransactionCount(), x.getDate()
                ))
                .collect(Collectors.toList());
    }
}

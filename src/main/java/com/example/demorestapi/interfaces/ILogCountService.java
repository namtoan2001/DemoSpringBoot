package com.example.demorestapi.interfaces;

import com.example.demorestapi.viewModels.LogCountVM;
import java.util.List;

public interface ILogCountService {
    List<LogCountVM> GetLogCount();
}

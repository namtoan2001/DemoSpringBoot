package com.example.demorestapi.viewModels;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LogCountVM {
    public int requestCount;
    public int transactionCount;
    public Date date;

    public LogCountVM(int requestCount, int transactionCount, Date date) {
        this.requestCount = requestCount;
        this.transactionCount = transactionCount;
        this.date = date;
    }
}

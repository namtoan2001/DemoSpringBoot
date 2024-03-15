package com.example.demorestapi.interfaces;

import org.springframework.http.ResponseEntity;

public interface IExportCSVService {
    ResponseEntity<byte[]> ExportCSV();
}

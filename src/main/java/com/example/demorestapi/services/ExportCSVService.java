package com.example.demorestapi.services;

import com.example.demorestapi.interfaces.IExportCSVService;
import com.example.demorestapi.models.User;
import com.example.demorestapi.repositories.UserRepository;
import com.opencsv.CSVWriter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

import java.util.List;

@Service
public class ExportCSVService implements IExportCSVService {
    public final UserRepository userRepository;

    public ExportCSVService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public ResponseEntity<byte[]> ExportCSV() {
        try{
            List<User> userList = userRepository.findAll();
            StringWriter stringWriter = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(stringWriter);

            csvWriter.writeNext(new String[]{"User Name","Email","Number Phone"});
            for(User user : userList){
                csvWriter.writeNext(new String[]{user.getUserName(), user.getEmail(), "\"" + user.getNumberPhone() + "\""});
            }
            csvWriter.close();
            String csvContent = stringWriter.toString();
            byte[] csvBytes = csvContent.getBytes();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("text/csv"));
            headers.setContentDispositionFormData("attachment", "users.csv");
            return new ResponseEntity<>(csvBytes, headers, org.springframework.http.HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

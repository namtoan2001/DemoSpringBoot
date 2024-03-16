package com.example.demorestapi.controllers;

import com.example.demorestapi.services.ExportCSVService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@CrossOrigin(origins = {"https://demospringboot-production-7e99.up.railway.app","http://demospringboot-production-7e99.up.railway.app"}, allowCredentials = "true")
@RequestMapping("/api")
public class ExportCSVController {
    private final ExportCSVService exportCSVService;

    public ExportCSVController(ExportCSVService exportCSVService) {
        this.exportCSVService = exportCSVService;
    }

    @GetMapping("/exportCSV")
    public ResponseEntity<byte[]> exportCSV(){
        ResponseEntity<byte[]> result;
        result = exportCSVService.ExportCSV();
        return result;
    }
    @GetMapping("/genFile")
    public void generateFile() {
        try {
            String filePath = "D:\\CCSP_001.mml";
            File file = new File(filePath);

            if (!file.exists()) {
                // Nếu tệp tin chưa tồn tại, thì tạo nó
                if (file.createNewFile()) {
                    System.out.println("Tạo tệp tin mới thành công.");
                } else {
                    System.err.println("Không thể tạo tệp tin mới.");
                    return;
                }
            }

//            Thread.sleep(500);

            FileWriter writer = new FileWriter(file, true);

            writer.write("$BEGIN$ 457072_12\n" +
                    "\n" +
                    "SOAP Request:\n" +
                    "<?xml version=\"1.0\" encoding=\"utf-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><ns2:receiverServiceReq xmlns:ns2=\"http://object.app.telsoft/\"><ISDN>902196923</ISDN><ServiceCode>99918</ServiceCode><CommandCode>DK Q5 NOT EM</CommandCode><PackageCode>Q5</PackageCode><SourceCode>CP</SourceCode><User>FLOWONE</User><Password>KTMB@1405</Password><Description>DK Q5|SMS</Description></ns2:receiverServiceReq></soapenv:Body></soapenv:Envelope>\n" +
                    "SOAP Response:\n" +
                    "<?xml version=\"1.0\" encoding=\"utf-8\"?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><ns2:receiverServiceReqResponse xmlns:ns2=\"http://object.app.telsoft/\"><return>Q5_DK_SUCC_NOTEM: </return></ns2:receiverServiceReqResponse></S:Body></S:Envelope>\n" +
                    "$END$ 457072_12\n");

            writer.close();
            System.out.println("Đã ghi nội dung vào tệp tin thành công.");
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi vào tệp tin: " + e.getMessage());
        }
    }
}

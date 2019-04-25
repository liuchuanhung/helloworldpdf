package com.liuchuanhung.helloworldpdf.controllers;

import com.liuchuanhung.helloworldpdf.dtos.UserDto;
import com.liuchuanhung.helloworldpdf.utils.PdfUtil;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/pdf")
public class PdfController {

    @PostMapping("/generate/hello-world/{date}")
    public ResponseEntity<InputStreamResource> generateHelloWorldPDF(@PathVariable("date") String date, @RequestBody List<UserDto> users) {

        ByteArrayInputStream inputStream = PdfUtil.helloWorldReport(users);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.parse("attachment;filename=HelloWorldReport_" + date + ".pdf"));
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
    }
}
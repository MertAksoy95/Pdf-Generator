package com.bulutmd.pdfGenerator.controller;

import com.bulutmd.pdfGenerator.service.PDFService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @PostMapping
    public ResponseEntity generate(@RequestParam String name) throws IOException, DocumentException {
        return new ResponseEntity<>(pdfService.generate(name), HttpStatus.OK);
    }
}

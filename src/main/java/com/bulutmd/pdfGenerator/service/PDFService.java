package com.bulutmd.pdfGenerator.service;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

@Service
public class PDFService {

    @Autowired
    private TemplateEngine htmlTemplateEngine;

    public ResponseEntity generate(String user) throws IOException, DocumentException {
        Context ctx = new Context();
        ctx.setVariable("to", user);
        return generatePdfFromHtml(htmlTemplateEngine.process("thymeleaf_template", ctx));
    }

    public ResponseEntity generatePdfFromHtml(String html) throws DocumentException, IOException {
        String outputFolder = Paths.get(".").normalize().toAbsolutePath().toString() + "/uploadedPdf/thymeleaf.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();

        return new ResponseEntity("SUCCESS", HttpStatus.OK);
    }


}

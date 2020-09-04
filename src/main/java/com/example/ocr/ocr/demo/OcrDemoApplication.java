package com.example.ocr.ocr.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@RestController
@SpringBootApplication
public class OcrDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(OcrDemoApplication.class, args);
  }

  @PostMapping("/ocr")
  public List<String> getOCR() throws TesseractException {

    File image1 = new File("src/main/resources/example.jpeg");
    File imageBorrosa = new File("src/main/resources/cedula-borrosa.PNG");
    Tesseract tesseract = new Tesseract();

    tesseract.setDatapath("src/main/resources/tessdata");
    tesseract.setLanguage("spa");
    tesseract.setPageSegMode(1);
    tesseract.setOcrEngineMode(1);

    List<String> words = new ArrayList<>();

    words.add(tesseract.doOCR(image1));
    words.add(tesseract.doOCR(imageBorrosa));

    return words;

  }

}

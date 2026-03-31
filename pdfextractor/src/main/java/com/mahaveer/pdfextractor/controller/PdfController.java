package com.mahaveer.pdfextractor.controller;

import com.mahaveer.pdfextractor.dto.QuestionDTO;
import com.mahaveer.pdfextractor.service.AiTopicService;
import com.mahaveer.pdfextractor.service.PdfService;
import com.mahaveer.pdfextractor.util.AdvancedQuestionExtractorUtil;
import com.mahaveer.pdfextractor.util.QuestionExtractorUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "http://localhost:3000")
public class PdfController {

    private final PdfService pdfService;
    private final AiTopicService aiTopicService;

    public PdfController(PdfService pdfService, AiTopicService aiTopicService) {
        this.pdfService = pdfService;
        this.aiTopicService = aiTopicService;
    }

        @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public List<QuestionDTO> uploadPdf(@RequestParam("file") MultipartFile file) throws IOException {

        String rawText = pdfService.extractText(file);

        return QuestionExtractorUtil.extractQuestions(rawText);
    }


//    @PostMapping(value = "/upload", consumes = "multipart/form-data")
//    public List<QuestionDTO> uploadPdf(@RequestParam("file") MultipartFile file) throws IOException {
//
//        String rawText = pdfService.extractText(file);
//
//        List<QuestionDTO> questions = QuestionExtractorUtil.extractQuestions(rawText);
//
//        // 🔹 Topic classify karo
//        for (QuestionDTO q : questions) {
//            String topic = aiTopicService.classifyTopic(q.getQuestionText());
//            q.setTopic(topic);
//        }
//
//        return questions;
//    }

    @PostMapping(value = "/upload-v2", consumes = "multipart/form-data")
    public List<QuestionDTO> uploadPdfV2(@RequestParam("file") MultipartFile file) throws IOException {

        String rawText = pdfService.extractText(file);

        return AdvancedQuestionExtractorUtil.extractQuestions(rawText);
    }
}
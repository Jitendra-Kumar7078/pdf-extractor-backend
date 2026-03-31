package com.mahaveer.pdfextractor.util;

import com.mahaveer.pdfextractor.dto.QuestionDTO;

import java.util.*;
import java.util.regex.*;

public class AdvancedQuestionExtractorUtil {

    public static List<QuestionDTO> extractQuestions(String text) {

        List<QuestionDTO> questions = new ArrayList<>();

        // 🔹 Step 1: Clean text
        text = cleanText(text);

        // 🔹 Step 2: Detect question blocks
        Pattern questionPattern = Pattern.compile("(Q\\.\\d+)(.*?)(?=Q\\.\\d+|$)", Pattern.DOTALL);
        Matcher matcher = questionPattern.matcher(text);

        while (matcher.find()) {

            String questionNumber = matcher.group(1).replace("Q.", "").trim();
            String block = matcher.group(2).trim();

            QuestionDTO dto = new QuestionDTO();
            dto.setQuestionNumber(questionNumber);

            // 🔹 Step 3: Extract options (strict line based)
            List<String> options = new ArrayList<>();

            Pattern optionPattern = Pattern.compile("(?m)^\\s*(?:\\d+\\.|[a-dA-D]\\))\\s*(.+)$");
            Matcher optionMatcher = optionPattern.matcher(block);

            while (optionMatcher.find()) {
                options.add(optionMatcher.group(1).trim());
            }

            // 🔹 Step 4: Remove options from question text
            String questionText = optionPattern.matcher(block).replaceAll("").trim();

            // 🔹 Validation layer (VERY IMPORTANT)
            if (!questionText.isBlank() && options.size() >= 2) {
                dto.setQuestionText(questionText);
                dto.setOptions(options);
                questions.add(dto);
            }
        }

        return questions;
    }

    private static String cleanText(String text) {

        // Remove ads
        text = text.replaceAll("FOR DSSSB.*?PUBLICATION\\.COM", "");

        // Remove Question ID blocks
        text = text.replaceAll("Question ID.*?Option 4 ID.*?\\d+", "");

        // Remove Ans garbage block
        text = text.replaceAll("Ans\\s*\\d+\\.?.*?(?=Q\\.|$)", "");

        // Remove standalone large numbers (like 224, 5186)
        text = text.replaceAll("(?m)^\\s*\\d{3,}\\s*$", "");

        // Fix units
        text = text.replaceAll("cm2", "cm²");
        text = text.replaceAll("cm3", "cm³");
        text = text.replaceAll("m2", "m²");
        text = text.replaceAll("m3", "m³");

        return text;
    }
}
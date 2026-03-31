package com.mahaveer.pdfextractor.util;


import com.mahaveer.pdfextractor.dto.QuestionDTO;
import java.util.regex.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class QuestionExtractorUtil {

    public static List<QuestionDTO> extractQuestions(String text) {

        List<QuestionDTO> questions = new ArrayList<>();
        String[] lines = text.split("\\r?\\n");

        Pattern questionStart = Pattern.compile("^Q\\.(\\d+)");
        Pattern firstOptionPattern = Pattern.compile("^Ans\\s*1\\.\\s*(.*)");
        Pattern otherOptionPattern = Pattern.compile("^(\\d+)\\.\\s*(.*)");

        String questionNumber = null;
        StringBuilder questionText = new StringBuilder();
        List<String> options = new ArrayList<>();

        boolean readingOptions = false;

        for (String line : lines) {

            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;

            // Skip unwanted lines
            if (trimmed.startsWith("Question ID") ||
                    trimmed.startsWith("Option") ||
                    trimmed.contains("FOR DSSSB") ||
                    trimmed.contains("TELEGRAM") ||
                    trimmed.startsWith("Section")) {
                continue;
            }

            Matcher qMatcher = questionStart.matcher(trimmed);

            // New Question
            if (qMatcher.find()) {

                if (questionNumber != null) {
                    questions.add(new QuestionDTO(
                            questionNumber,
                            questionText.toString().trim(),
                            options
                    ));
                }

                questionNumber = qMatcher.group(1);
                questionText = new StringBuilder();
                options = new ArrayList<>();
                readingOptions = false;

                // Add question line without Q.X
                questionText.append(trimmed.replaceFirst("^Q\\.\\d+\\s*", "")).append(" ");
                continue;
            }

            // First Option (Ans 1.)
            Matcher firstOptionMatcher = firstOptionPattern.matcher(trimmed);
            if (firstOptionMatcher.find()) {
                readingOptions = true;
                options.add(firstOptionMatcher.group(1));
                continue;
            }

            // Other Options (2. 3. 4.)
            Matcher otherOptionMatcher = otherOptionPattern.matcher(trimmed);
            if (readingOptions && otherOptionMatcher.find()) {
                options.add(otherOptionMatcher.group(2));
                continue;
            }

            // If not option, it's question text
            if (!readingOptions) {
                questionText.append(trimmed).append(" ");
            }
        }

        // Add last question
        if (questionNumber != null) {
            questions.add(new QuestionDTO(
                    questionNumber,
                    questionText.toString().trim(),
                    options
            ));
        }

        return questions;
    }
}
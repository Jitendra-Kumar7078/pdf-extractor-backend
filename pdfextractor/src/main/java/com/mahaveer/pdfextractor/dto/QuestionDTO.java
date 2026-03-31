package com.mahaveer.pdfextractor.dto;

import java.util.List;

public class QuestionDTO {

    private String questionNumber;
    private String questionText;
    private List<String> options;
    private String topic;

    // 🔹 Default constructor (VERY IMPORTANT)
    public QuestionDTO() {
    }

    // 🔹 Existing constructor (optional but useful)
    public QuestionDTO(String questionNumber, String questionText, List<String> options) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.options = options;
    }

    // 🔹 Getters & Setters
    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
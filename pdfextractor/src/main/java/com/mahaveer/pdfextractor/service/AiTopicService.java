package com.mahaveer.pdfextractor.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiTopicService {

    private final ChatClient chatClient;

    public AiTopicService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String classifyTopic(String questionText) {

        if (questionText == null || questionText.isBlank()) {
            return "Unknown";
        }

        String prompt = """
        Classify the following question into ONLY ONE topic from:
        Arithmetic, Algebra, Mensuration, Geometry, Time & Distance, Statistics, Reasoning, English, General Knowledge.

        Respond with ONLY the topic name. No explanation.

        Question:
        """ + questionText;

        try {
            String response = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            System.out.println("AI RAW RESPONSE: " + response);

            return response.trim();

        } catch (Exception e) {
            return "Unknown";
        }

    }
}
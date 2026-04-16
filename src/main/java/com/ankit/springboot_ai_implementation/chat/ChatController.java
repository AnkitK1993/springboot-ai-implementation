package com.ankit.springboot_ai_implementation.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final OpenAiChatModel openAiChatModel;
    private final ChatClient chatClient;

    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    public ChatController(OpenAiChatModel openAiChatModel, ChatClient.Builder builder) {
        this.openAiChatModel = openAiChatModel;
        this.chatClient = builder.build();
    }

    @GetMapping("/test")
    public String chat(){
        try {
            return openAiChatModel.call("Who are you");
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/openai")
    public String getOpenAIChatMessage(@RequestParam("query") String query){
        return openAiChatModel.call(query);
    }

    @GetMapping("/chat")
    public String getChatClientMessage(){
        return chatClient.prompt()
                .user("Hi")
                .call()
                .content();
    }

    @GetMapping("/hi")
    public String hi(){
        return "Hello";
    }

    @GetMapping("/apikey")
    public String getApiKey(){
        return apiKey;
    }

}

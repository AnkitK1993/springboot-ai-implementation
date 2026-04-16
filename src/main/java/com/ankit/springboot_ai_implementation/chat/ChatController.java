package com.ankit.springboot_ai_implementation.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;
    private final OpenAiChatModel openAiChatModel;

    public ChatController(ChatClient.Builder builder, OpenAiChatModel openAiChatModel){
        this.chatClient = builder.build();
        this.openAiChatModel = openAiChatModel;
    }

    @GetMapping("/test")
    public String chat(){
        return chatClient.prompt()
                .user("Hi")
                .call()
                .content();
    }

    @GetMapping("/chat")
    public String getOpenAIChatMessage(@RequestParam("query") String query){
        return openAiChatModel.call(query);
    }

    @GetMapping("/hi")
    public String hi(){
        return "Hello";
    }

}

package com.ankit.springboot_ai_implementation.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ChatController {

    private final OpenAiChatModel openAiChatModel;
    private final ChatClient chatClient;

    public ChatController(OpenAiChatModel openAiChatModel, ChatClient.Builder chatClientBuilder) {
        this.openAiChatModel = openAiChatModel;
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/hi")
    public String hi(){
        return "Hello";
    }

    @GetMapping("/open-ai")
    public String getOpenAIChatMessage(@RequestParam("query") String query){
        return openAiChatModel.call(query);
    }

    @GetMapping("/chat-client")
    public String getChatClientMessage(){
        return chatClient.prompt()
                .user("Hi")
                .call()
                .content();
    }

    @GetMapping("/stream")
    public Flux<String> stream(){
        return chatClient.prompt()
                .user("10 places to visit in Mumbai")
                .stream()
                .content();
    }

    @GetMapping("/joke")
    public ChatResponse joke(){
        return chatClient.prompt()
                .user("Tell me Dad joke about Java Developer")
                .call()
                .chatResponse();
    }

}

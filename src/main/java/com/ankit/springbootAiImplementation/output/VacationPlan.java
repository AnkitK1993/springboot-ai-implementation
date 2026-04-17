package com.ankit.springbootAiImplementation.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacation")
public class VacationPlan {

    private final ChatClient chatClient;

    public VacationPlan(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/unstructured")
    public String unstructured(){
        return chatClient.prompt()
                .user("I want to plan a trip to Thailand. Give me a list of things of things to do.")
                .call()
                .content();
    }

    @GetMapping("/structured")
    public Itinerary structured(){
        return chatClient.prompt()
                .user("I want to plan a trip to Thailand. Give me a list of things of things to do.")
                .call()
                .entity(Itinerary.class);
    }
}

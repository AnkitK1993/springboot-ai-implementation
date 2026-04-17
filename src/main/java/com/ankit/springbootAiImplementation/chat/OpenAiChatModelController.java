package com.ankit.springbootAiImplementation.chat;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("open-ai")
public class OpenAiChatModelController {

    private final OpenAiChatModel openAiChatModel;

    public OpenAiChatModelController(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
    }

    @GetMapping("/ask")
    public String getOpenAIChatMessage(@RequestParam("query") String query){
        return openAiChatModel.call(query);
    }
}

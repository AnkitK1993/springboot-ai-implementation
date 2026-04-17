package com.ankit.springbootAiImplementation.multimodal.image;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image-detection")
public class ImageDetection {

    private final ChatClient chatClient;

    @Value("classpath:/images/wallpaper.jpg")
    Resource sampleImage;

    public ImageDetection(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/image-to-text")
    public String imageToText() {
        return chatClient.prompt()
                .user(u -> {
                    u.text("Describe the image");
                    u.media(MimeTypeUtils.IMAGE_JPEG,sampleImage);
                })
                .call()
                .content();
    }

}

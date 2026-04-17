package com.ankit.springbootAiImplementation.multimodal.image;

import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/image-generation")
public class ImageGeneration {

    private final OpenAiImageModel openAiImageModel;

    public ImageGeneration(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    @GetMapping("/generate-image")
    public ResponseEntity<Map<String,String>> generateImage(@RequestParam(defaultValue = "A beautiful sunset over mountains") String prompt) {
        ImageOptions options = OpenAiImageOptions.builder()
                .withModel("dall-e-3")
                .withWidth(1024)
                .withHeight(1024)
                .withQuality("hd")
                .withStyle("vivid")
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(prompt,options);
        ImageResponse imageResponse = openAiImageModel.call(imagePrompt);

        String url = imageResponse.getResult().getOutput().getUrl();

        return ResponseEntity.ok(Map.of(
                "prompt", prompt,
                "imageUrl", url
        ));
    }
}

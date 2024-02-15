package com.ssafy.tosi.gptconversation.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "chatgpt")
public class ChatgptProperties {

    private String apiKey = "OPENAI_TOKEN=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";

    private String url = "https://api.openai.com/v1/chat/completions";

    private String model = "gpt-3.5-turbo-1106";

    private Integer maxTokens = 100;

    private Double temperature = 1.0;

    private Double topP = 1.0;

    private MultiChatProperties multi;

    public ChatgptProperties() {
        this.multi = new MultiChatProperties();
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTopP() {
        return topP;
    }

    public void setTopP(Double topP) {
        this.topP = topP;
    }

    public MultiChatProperties getMulti() {
        return multi;
    }

    public void setMulti(MultiChatProperties multi) {
        this.multi = multi;
    }
}
package com.hq.springbootchatbot.util;

import com.hq.springbootchatbot.exception.ModelNotSupportException;
import com.hq.springbootchatbot.model.ChatMessage;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatChoice;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.entity.common.Choice;
import com.unfbx.chatgpt.entity.completions.Completion;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ChatUtil {
    @Value("${openai.apikey}")
    private String token;

    @Value("${openai.connect-timeout}")
    private int connectTimeout;

    @Value("${openai.write-timeout}")
    private int writeTimeout;

    @Value("${openai.read-timeout}")
    private int readTimeout;

    @Value("${openai.max-token}")
    private int maxToken;

    public String ask(ChatMessage chatMessage) {
        StringBuilder result = new StringBuilder();
        String modelName = chatMessage.getModel();
        if (!isGpt3Plus(modelName) && !isLegacy(modelName)) {
            throw new ModelNotSupportException("GPT Model not support.");
        }

        try {
            OkHttpClient okHttpClient = new OkHttpClient
                    .Builder()
                    .proxy(null)
                    .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                    .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                    .readTimeout(readTimeout, TimeUnit.SECONDS)
                    .build();
            OpenAiClient openAiClient = OpenAiClient.builder().apiKey(Arrays.asList(token)).okHttpClient(okHttpClient).build();
            if (isGpt3Plus(modelName)) {
                Message message = Message.builder().role(Message.Role.USER).content(chatMessage.getText()).build();
                ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
                List<ChatChoice> resultList = openAiClient.chatCompletion(chatCompletion).getChoices();
                for (int i = 0; i < resultList.size(); i++) {
                    result.append(resultList.get(i).getMessage().getContent());
                }
            } else {
                // https://platform.openai.com/docs/models/model-endpoint-compatibility
                // text-davinci-003/text-ada-003
                Completion completion = Completion.builder()
                        .prompt(chatMessage.getText())
                        .model(chatMessage.getModel())
                        .maxTokens(maxToken)
                        .temperature(0)
                        .echo(false)
                        .build();
                Choice[] resultList = openAiClient.completions(completion).getChoices();
                for (Choice choice : resultList) {
                    result.append(choice.getText());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.append("chatbot can't help you with this.");
        }

        return result.toString();
    }

    private boolean isGpt3Plus(String modelName) {
        if (modelName == null || modelName.isEmpty()) {
            return false;
        }

        if (modelName.equals(ChatCompletion.Model.GPT_3_5_TURBO.getName())
                || modelName.equals(ChatCompletion.Model.GPT_4.getName())) {
            return true;
        }

        return false;
    }

    private boolean isLegacy(String modelName) {
        if (modelName == null || modelName.isEmpty()) {
            return false;
        }

        for (Completion.Model m : Completion.Model.values()) {
            if (m.getName().equals(modelName)) {
                return true;
            }
        }
        return false;
    }
}

package dev.isakov.mathexpertai;

import dev.isakov.mathexpertai.bot.BotService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class MathExpertAiApplication {
    public static void main(String[] args) throws TelegramApiException {
        ApplicationContext context = SpringApplication.run(MathExpertAiApplication.class, args);
        // Get the BotService bean from the application context
        BotService botService = context.getBean(BotService.class);
        try {
            // Register the BotService with TelegramBotsApi
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(botService);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

package dev.isakov.mathexpertai.bot;

import dev.isakov.mathexpertai.entity.UserCredentials;
import dev.isakov.mathexpertai.service.OpenAiService;
import dev.isakov.mathexpertai.service.UserService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BotService extends TelegramLongPollingBot {

    private final UserService userService;
    private final OpenAiService openAiService;
    private static final Logger logger = LoggerFactory.getLogger(BotService.class);
    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.username}")
    private String botUserName;

    public BotService(UserService userService, OpenAiService openAiService) {
        this.userService = userService;
        this.openAiService = openAiService;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            handleMessage(update.getMessage());
        }
    }

    private void handleMessage(Message message) {
        User targetUser = message.getFrom();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());

        UserCredentials userCredentials = new UserCredentials(
                targetUser.getId(),
                targetUser.getFirstName(),
                targetUser.getUserName(),
                message.getText());
        userService.save(userCredentials);
        String curie = openAiService.getResponseFromOpenAI(message.getText() + " "
                + "Answer like a math teacher, if it is not math related " +
                "say you can't answer this question", "curie");
        sendMessage.setText(curie);
        try {
            execute(sendMessage);
            logger.info("Sent message \"{}\" to {}", message.getText(), message.getChatId());
        } catch (
                TelegramApiException e) {
            logger.error("Failed to send message \"{}\" to {} due to error: {}", message.getText(), "text", e.getMessage());
        }
    }

    @PostConstruct
    public void start() {
        logger.info("username: {}, token: {}", botUserName, botToken);
    }
}

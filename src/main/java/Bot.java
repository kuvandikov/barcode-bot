import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;


public class Bot extends TelegramLongPollingBot {

    Message message;
//    Scanner scanner=new Scanner(System.in);
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            message = update.getMessage();
            sendMsg(message);
        }
    }

    public synchronized void sendMsg(Message message) {

        SendPhoto sendPhoto=new SendPhoto();
        Long chat_id=message.getChatId();
        sendPhoto.setChatId(chat_id);
        sendPhoto.setCaption("This is example");
        File file=new File ("photo.png");
        sendPhoto.setPhoto(file);

        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return BotConfig.USER;
    }

    @Override
    public String getBotToken() {
        return BotConfig.TOKEN;
    }
}

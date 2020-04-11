import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


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
        GenerateQRCode.exute("https://github.com/AnvarbekKuvandikov",message.getChatId()+".png");
       SendPhoto sendPhoto=new SendPhoto();
       sendPhoto.setChatId(message.getChatId());
       sendPhoto.setCaption("");
        try {
            sendPhoto.setPhoto("This is example",new FileInputStream(message.getChatId()+".png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

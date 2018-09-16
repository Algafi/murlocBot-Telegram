package murlocs_bot;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.telegram.telegrambots.api.methods.send.SendAudio;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Murlocs_bot extends TelegramLongPollingBot{
	
	List<String> murlocText = Arrays.asList("�Mgrglmrgl!","�Heh keh-keh-keh kah!","�Geeshewha?","!Grglrglrgl!","�Pyehhhh!");
	
	@Override
    public String getBotUsername() {
        return "murlocs_bot";
    }
	
	@Override
    public String getBotToken() {
        return "secretbottoken :D";
    }
	
	@Override
	public void onUpdateReceived(Update update) {
	    // We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	    	
	    	String textMissatge = update.getMessage().getText();
	    	SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
	    	
	    	if(textMissatge.equals("murlochelp")){
	    		message.setChatId(update.getMessage().getChatId());
	    		message.setText("Comandos:\n  -murlocnude\n  -murlocsound\n  -murlochelp\n  -<murloc>");
	    		try {
		            sendMessage(message);
		        } catch (TelegramApiException e) {
		            e.printStackTrace();
		        }
	    	}else if(textMissatge.equals("murloclog")){
	    		message.setChatId(update.getMessage().getChatId());
	    		message.setText("Changelog:\n -Foto <murlocnude>\n -Audio <murlocsound>\n -Ayuda <murlochelp>");
	    		try {
		            sendMessage(message);
		        } catch (TelegramApiException e) {
		            e.printStackTrace();
		        }
	    	}else if(textMissatge.equals("murlocnude")){
	    		SendPhoto murlocNude = new SendPhoto();
	    		murlocNude.setChatId(update.getMessage().getChatId());
	    		murlocNude.setNewPhoto(new File("murloc.jpg"));
	    		try{
	    			sendPhoto(murlocNude);
	    		} catch (TelegramApiException e){
	    			e.printStackTrace();
	    		}
	    	}else if(textMissatge.equals("murlocsound")){
	    		SendAudio murlocAudio = new SendAudio();
	    		murlocAudio.setChatId(update.getMessage().getChatId());
	    		murlocAudio.setNewAudio(new File("murlocAudio.ogg"));
	    		try{
	    			sendAudio(murlocAudio);
	    		} catch (TelegramApiException e) {
	    			e.printStackTrace();
	    		}
	    	}else if(textMissatge.toLowerCase().contains("murloc")){
		        message.setChatId(update.getMessage().getChatId());
		        Random ran = new Random();
		        message.setText(murlocText.get(ran.nextInt(murlocText.size())));
		        message.setReplyToMessageId(update.getMessage().getMessageId());
		        try {
		            sendMessage(message); // Call method to send the message
		        } catch (TelegramApiException e) {
		            e.printStackTrace();
		        }
	    	}
	    }
	}
}

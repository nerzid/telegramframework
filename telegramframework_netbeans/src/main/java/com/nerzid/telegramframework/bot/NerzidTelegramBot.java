/*
 * The MIT License
 *
 * Copyright 2016 nerzid.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.nerzid.telegramframework.bot;


import com.nerzid.telegramframework.cmd.Cmd;
import com.nerzid.telegramframework.cmd.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.time.StopWatch;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author nerzid
 */
public class NerzidTelegramBot extends TelegramLongPollingBot {

    private List<Long> chatIDs = new ArrayList<>();
    private StopWatch stopwatch;
    private Timer timer;

    public NerzidTelegramBot() {
        Cmd.init();
        stopwatch = new StopWatch();
        timer = new Timer();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            String text = update.getMessage().getText();
            Long id = update.getMessage().getChatId();

            Response response = Cmd.findThenRunTheCommand(text, id, this);

            switch (response.getType()) {
                case USER:
                    sendMessage(response.getText(), id);
                    break;
                case ALL:
                    sendMessageToAll(response.getText());
                    break;
                case LOG:
                    System.out.println(response.toString());
                    break;
                case NONE:
                    break;
                default:
                    break;
            }
        } catch (TelegramApiException ex) {
            Logger.getLogger(NerzidTelegramBot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String text, Long id) throws TelegramApiException {
        sendMessage(new SendMessage()
                .setChatId(id)
                .setText(text));
    }

    public void sendMessageToAll(String text) throws TelegramApiException {
        for (Long id : chatIDs) {
            sendMessage(text, id);
        }
    }
    
    public String getElapsedTime(){
        return stopwatch.toString();
    }

    @Override
    public String getBotToken() {
        return "<put_your_bot_token_here>";
    }

    @Override
    public String getBotUsername() {
        return "<put_your_bot_username_here>";
    }

    public List<Long> getChatIDs() {
        return chatIDs;
    }

    public void setChatIDs(List<Long> chatIDs) {
        this.chatIDs = chatIDs;
    }

    public StopWatch getStopwatch() {
        return stopwatch;
    }

    public void setStopwatch(StopWatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
}

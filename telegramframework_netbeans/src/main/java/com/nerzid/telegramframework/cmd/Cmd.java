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
package com.nerzid.telegramframework.cmd;

import com.nerzid.telegramframework.prerequisite.Prerequisite;
import com.nerzid.telegramframework.bot.NerzidTelegramBot;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nerzid
 */
public abstract class Cmd {

    public static ArrayList<Cmd> commands = new ArrayList<>();
    public HashMap<Long, Prerequisite> prerequisites_of_chats = new HashMap<>();

    protected String name;
    protected String abbreviation;
    protected String successMessage;
    protected String failedMessage;

    protected Cmd() {
        setAbbreviation();
        setName();
        setSuccessMessage();
        setFailedMessage();
    }

    /**
     * Call this method to setup commands for your telegram bot
     */
    public static void init() {
        // Add Commands here
        commands.add(new CmdSubscribe());
    }

    /**
     * This method is the main method of the commands to be able to what they
     * want.
     *
     * @param id Chat's id
     * @param bot
     * @return Response
     */
    protected abstract Response run(Long id, NerzidTelegramBot bot);

    protected String getName() {
        return name;
    }

    protected String getAbbreviation() {
        return abbreviation;
    }

    protected String getFailedMessage() {
        return failedMessage;
    }

    protected String getSuccessMessage() {
        return successMessage;
    }

    protected void addPrerequisite(Long id, Prerequisite prerequisite) {
        prerequisites_of_chats.put(id, prerequisite);
    }

    /**
     * Call this method to find the command from the given text. This method
     * looks for an abbreviation for the first word of the text.
     *
     * @param text The text message of what user send to bot
     * @param id Chat's id
     * @param bot
     *
     * @return Response
     */
    public static Response findThenRunTheCommand(String text, Long id, NerzidTelegramBot bot) {
        if (text.isEmpty() || text == null) {
            return new Response("You have given empty or null text", ResponseType.USER, true);
        }
        String command = text.split(" ")[0];

        for (Cmd cmd : commands) {
            if (cmd.getAbbreviation().equalsIgnoreCase(command)) {
                return cmd.run(id, bot);
            }
        }
        return new Response("No command avaliable like " + command, ResponseType.USER, true);
    }

    protected abstract void setName();

    protected abstract void setAbbreviation();

    protected abstract void setSuccessMessage();

    protected abstract void setFailedMessage();

}

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

import com.nerzid.telegramframework.bot.NerzidTelegramBot;

/**
 *
 * @author nerzid
 */
public final class CmdSubscribe extends Cmd{

    @Override
    protected Response run(Long id, NerzidTelegramBot bot){
        if (bot.getChatIDs().contains(id)) {
            return new Response(getFailedMessage(), ResponseType.USER, true);
        } else {
            bot.getChatIDs().add(id);
            return new Response(getSuccessMessage(), ResponseType.USER, true);
        }
    }

    @Override
    public void setName() {
        name = "subscribe";
    }
    
    @Override
    public void setAbbreviation() {
        abbreviation = "sub";
    }

    @Override
    public void setSuccessMessage() {
        successMessage = "Yes you subscribed";
    }

    @Override
    public void setFailedMessage() {
        failedMessage = "You'r already subscribed";
    }

}

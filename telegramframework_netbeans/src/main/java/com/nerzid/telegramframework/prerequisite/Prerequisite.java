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
package com.nerzid.telegramframework.prerequisite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nerzid
 */
public class Prerequisite {
    private Long chat_id;
    private String text;
    private boolean isSet;
    private List<Prerequisite> prerequisites;

    public Prerequisite(Long chat_id, String text) {
        this.chat_id = chat_id;
        this.text = text;
        isSet = false;
        prerequisites = new ArrayList<>();
    }

    public Prerequisite(Long chat_id, String text, boolean isSet) {
        this.chat_id = chat_id;
        this.text = text;
        this.isSet = isSet;
        prerequisites = new ArrayList<>();
    }

    public Prerequisite(Long chat_id, String text, boolean isSet, List<Prerequisite> prerequisites) {
        this.chat_id = chat_id;
        this.text = text;
        this.isSet = isSet;
        this.prerequisites = prerequisites;
    }

    public Long getChat_id() {
        return chat_id;
    }

    public void setChat_id(Long chat_id) {
        this.chat_id = chat_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isIsSet() {
        return isSet;
    }

    public void setIsSet(boolean isSet) {
        this.isSet = isSet;
    }

    public List<Prerequisite> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Prerequisite> prerequisites) {
        this.prerequisites = prerequisites;
    }
   
}

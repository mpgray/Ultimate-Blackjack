package blackjack;

import blackjack.views.MessageGUI;

import javax.swing.*;

public class Message {


    private String message;

    public MessageGUI messageGUI = new MessageGUI();

    public JLabel popupLBL(){
        return messageGUI.getLBL();
    }

    public void popup(String content) {
        messageGUI.popup(content);
    }

    public void clear() {
        this.messageGUI.hideLbl();
    }

    public void setMessage(String message) {
        this.messageGUI.popup(message);;
    }

    @Override
    public String toString() {
        return message.toString();
    }
}



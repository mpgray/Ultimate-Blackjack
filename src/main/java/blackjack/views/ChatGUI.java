package blackjack.views;

import blackjack.Dealer;
import blackjack.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatGUI extends JFrame implements ActionListener {
    private JTextArea chatFieldTXT;
    private JTextField submitFieldTXT;
    private JScrollPane scrollChatTxt;
    private JButton sendButton;
    private JLabel chatLBL = new JLabel();

    public JLabel chat(){

        chatLBL.setBounds(5, 515, 880, 140);

        chatFieldTXT = new JTextArea(20, 75);
        chatFieldTXT.setEditable(false);
        chatFieldTXT.setLineWrap(true);

        scrollChatTxt = new JScrollPane(chatFieldTXT,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollChatTxt.setBounds(0,0,880,110);

        submitFieldTXT = new JTextField(75);
        submitFieldTXT.setBounds(0, 112, 795, 25);
        submitFieldTXT.addActionListener(this);

        sendButton = new JButton("Send");
        sendButton.setEnabled(true);
        sendButton.setBounds(795, 112, 84, 23);
        sendButton.addActionListener(this);

        chatLBL.add(scrollChatTxt);
        chatLBL.add(submitFieldTXT);
        chatLBL.add(sendButton);

        return chatLBL;
    }

    public void systemText(Player player) {
        this.print("[" + player.getUserName() + " Has]: " + player.toString());
    }

    public void systemText(Dealer dealer) {
        this.print("[Dealer Has]: " + dealer.toString());
    }

    public void print(String message){
        chatFieldTXT.append("***" + message + "\n");
        chatFieldTXT.setCaretPosition(chatFieldTXT.getDocument().getLength());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = submitFieldTXT.getText();
        chatFieldTXT.append("[Chat]: " + text + "\n");
        submitFieldTXT.selectAll();
        chatFieldTXT.setCaretPosition(chatFieldTXT.getDocument().getLength());
    }
}

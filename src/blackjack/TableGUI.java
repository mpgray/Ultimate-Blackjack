package blackjack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableGUI extends JFrame implements ActionListener {
    private JLayeredPane contentPane;
    private JTextArea chatFieldTXT;
    private JTextField submitFieldTXT;
    private JButton sendButton;
    private JScrollPane scrollChatTxt;

    public TableGUI(){
        create();
        chat();

        this.setTitle("Ultimate Blackjack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, 905, 710);
       // this.setIconImage((createImageIcon("dragonicon.png")).getImage());
        this.setContentPane(contentPane);
    }


    private void create(){
        contentPane = new JLayeredPane();
        contentPane.setBorder(new EmptyBorder(0, 5, 5, 5));
        contentPane.setBackground(new Color(50, 100, 50));
        contentPane.setLayout(null);
        contentPane.setOpaque(true);
    }

    private void chat(){
        chatFieldTXT = new JTextArea(20, 75);
        chatFieldTXT.setEditable(false);

        scrollChatTxt = new JScrollPane(chatFieldTXT,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollChatTxt.setBounds(5,515,880,110);

        submitFieldTXT = new JTextField(75);
        submitFieldTXT.setBounds(5, 627, 795, 25);
        submitFieldTXT.addActionListener(this);

        sendButton = new JButton("Send");
        sendButton.setEnabled(true);
        sendButton.setBounds(800, 627, 84, 23);
        sendButton.addActionListener(this);

        contentPane.add(scrollChatTxt,JLayeredPane.MODAL_LAYER);
        contentPane.add(submitFieldTXT,JLayeredPane.MODAL_LAYER);
        contentPane.add(sendButton,JLayeredPane.MODAL_LAYER);
    }

    public void print(String message){
        chatFieldTXT.append("[Dealer]: " + message + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = submitFieldTXT.getText();
        chatFieldTXT.append("[System]: " + text + "\n");
        submitFieldTXT.selectAll();
        chatFieldTXT.setCaretPosition(chatFieldTXT.getDocument().getLength());
    }
}


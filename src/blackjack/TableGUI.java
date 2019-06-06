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
    private JButton sendButton, hitButton, dealButton, stayButton, doubleButton, splitButton, insuranceButton, readyButton;
    private JScrollPane scrollChatTxt;

    private Game blackjack = new Game();

    public TableGUI(){
        create();
        chat();
        move();

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

    private void move(){
        dealButton = new JButton(" Deal ");
        dealButton.setBounds(15, 35, 100, 25);
        dealButton.addActionListener(evt -> {
            blackjack.start();
            this.print("[Dealer]: " + blackjack.dealer.toString());
            this.print("[Player]: " + blackjack.player.toString());
            dealButton.setVisible(false);
        });

        hitButton = new JButton(" Hit ");
        hitButton.setBounds(15, 60, 100, 25);
        hitButton.addActionListener(evt -> {
            blackjack.hit();
            this.print(blackjack.player.toString());
        });

        stayButton = new JButton(" Stay ");
        stayButton.setBounds(15, 85, 100, 25);
        stayButton.addActionListener(evt -> {
            blackjack.stay();
            this.print(blackjack.player.toString());
        });

        doubleButton = new JButton(" Double ");
        doubleButton.setBounds(15, 110, 100, 25);
        doubleButton.addActionListener(evt -> {
            blackjack.onecard();
            this.print(blackjack.player.toString());
        });

        splitButton = new JButton(" Split ");
        splitButton.setBounds(15, 135, 100, 25);
        splitButton.addActionListener(evt -> {
            blackjack.split();
            this.print(blackjack.player.toString());
        });

        insuranceButton = new JButton(" Insurance ");
        insuranceButton.setBounds(15, 160, 140, 25);
        insuranceButton.addActionListener(evt -> {
            blackjack.insurance();
            this.print(blackjack.player.toString());
        });

        readyButton = new JButton(" Ready ");
        readyButton.setBounds(15, 185, 100, 25);
        readyButton.addActionListener(evt -> {
            this.print(blackjack.results());
            this.print("Dealer: " + blackjack.dealer.toString());
            dealButton.setVisible(true);
        });

        contentPane.add(dealButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(hitButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(stayButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(doubleButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(splitButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(insuranceButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(readyButton,JLayeredPane.MODAL_LAYER);
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

    private void print(String message){
        chatFieldTXT.append("[[System]]" + message + "\n");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String text = submitFieldTXT.getText();
        chatFieldTXT.append("[System]: " + text + "\n");
        submitFieldTXT.selectAll();
        chatFieldTXT.setCaretPosition(chatFieldTXT.getDocument().getLength());
    }
}


package blackjack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TableGUI extends JFrame implements ActionListener {
    private JLayeredPane contentPane;
    private JTextArea chatFieldTXT;
    private JTextField submitFieldTXT;
    private JButton sendButton, hitButton, dealButton, stayButton, doubleButton, splitButton, insuranceButton;
    private JScrollPane scrollChatTxt;
    private Game blackjack;


    public TableGUI(Game blackjack){

        this.blackjack = blackjack;
        this.create();
        this.chat();
        this.move();

        this.setTitle("Ultimate Blackjack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, 905, 710);
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
            this.systemText();
            this.beginGame();
            this.hand();
        });

        hitButton = new JButton(" Hit ");
        hitButton.setBounds(15, 60, 100, 25);
        hitButton.addActionListener(evt -> {
            if(!blackjack.hit()) {
                this.endTurn();
            }

            this.systemText();
        });

        stayButton = new JButton(" Stay ");
        stayButton.setBounds(15, 85, 100, 25);
        stayButton.addActionListener(evt -> {
            if(!blackjack.stay()) {
                this.endTurn();
            }
            this.systemText();
        });

        doubleButton = new JButton(" Double ");
        doubleButton.setBounds(15, 110, 100, 25);
        doubleButton.addActionListener(evt -> {
            if(!blackjack.onecard()){
                this.endTurn();
            }
            this.systemText();
        });

        splitButton = new JButton(" Split ");
        splitButton.setBounds(15, 135, 100, 25);
        splitButton.addActionListener(evt -> {
            if(!blackjack.split()){
                this.endTurn();
            }
            this.systemText();
        });

        insuranceButton = new JButton(" Insurance ");
        insuranceButton.setBounds(15, 160, 140, 25);
        insuranceButton.addActionListener(evt -> {
            if(!blackjack.insurance()) {
                this.endTurn();
            }
            this.systemText();
        });

        hitButton.setVisible(false);
        stayButton.setVisible(false);
        doubleButton.setVisible(false);
        splitButton.setVisible(false);
        insuranceButton.setVisible(false);


        contentPane.add(dealButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(hitButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(stayButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(doubleButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(splitButton,JLayeredPane.MODAL_LAYER);
        contentPane.add(insuranceButton,JLayeredPane.MODAL_LAYER);
    }

    private void beginGame() {
        dealButton.setVisible(false);
        hitButton.setVisible(true);
        stayButton.setVisible(true);
        doubleButton.setVisible(true);

        if (blackjack.player.hand.hand.get(0).value == blackjack.player.hand.hand.get(1).value) {
            splitButton.setVisible(true);
        }

        if (blackjack.dealer.hand.hand.get(1).face == Card.Face.ACE) {
            insuranceButton.setVisible(true);
        }
    }

    private void endTurn(){
        dealButton.setVisible(true);
        hitButton.setVisible(false);
        stayButton.setVisible(false);
        doubleButton.setVisible(false);
        splitButton.setVisible(false);
        insuranceButton.setVisible(false);
        this.print(blackjack.results());
    }

    private void hand() {
        List<JLabel> handLBLs = blackjack.handLBL();
        int size = handLBLs.size();
        System.out.println(handLBLs);
        for(int i = 0; i < size; i++) {
            System.out.println(i);
            contentPane.add(handLBLs.get(i));
        }

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

    private void systemText() {
        this.print("---------------------------------------------");
        this.print("[Dealer Has]: " + blackjack.dealer.toString());
        this.print("[Player Has]: " + blackjack.player.toString());

    }

    private void print(String message){
        chatFieldTXT.append("[System]" + message + "\n");
        chatFieldTXT.setCaretPosition(chatFieldTXT.getDocument().getLength());
    }

    public void run() {


        Runnable gameOn = () -> {


        }; new Thread(gameOn).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = submitFieldTXT.getText();
        chatFieldTXT.append("[Chat]: " + text + "\n");
        submitFieldTXT.selectAll();
        chatFieldTXT.setCaretPosition(chatFieldTXT.getDocument().getLength());
    }
}


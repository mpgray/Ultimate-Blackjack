package blackjack.views;

import blackjack.Card;
import blackjack.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TableGUI extends JFrame {

    private JLayeredPane contentPane;


    private Game blackjack;



    private ChatGUI chatGUI;
    private PlayGUI playGUI;
    private BetGUI betGUI;


    public TableGUI(Game blackjack, PlayGUI playGUI, BetGUI betGUI){
        this.blackjack = blackjack;
        this.contentPane = this.blackjack.getTable();
        this.playGUI = playGUI;
        this.betGUI = betGUI;

        this.chatGUI = this.blackjack.getChatGUI();

        this.create();

        this.setTitle("Ultimate Blackjack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, 905, 710);
        this.setContentPane(contentPane);
    }


    private void create(){

        contentPane.setBorder(new EmptyBorder(0, 5, 5, 5));
        contentPane.setBackground(new Color(50, 100, 50));
        contentPane.setLayout(null);
        contentPane.setOpaque(true);


        contentPane.add(this.blackjack.message.popupLBL(), JLayeredPane.POPUP_LAYER);
        contentPane.add(chatGUI.chat(), JLayeredPane.MODAL_LAYER);
        contentPane.add(playGUI.sidePanel(),JLayeredPane.PALETTE_LAYER);
        contentPane.add(betGUI.betPanel(),JLayeredPane.PALETTE_LAYER);
    }




    public Game getBlackjack() {
        return blackjack;
    }

    public void run() {


        Runnable gameOn = () -> {


        }; new Thread(gameOn).start();
    }
}


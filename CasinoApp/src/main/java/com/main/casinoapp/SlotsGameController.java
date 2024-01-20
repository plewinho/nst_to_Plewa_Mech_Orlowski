package com.main.casinoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class SlotsGameController implements Initializable {
    int coins = User.getCurrentUser().getBalance();

    @FXML
    private TextField tfAmount;
    @FXML
    private Circle btnClose;

    @FXML
    private Label lblBal;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;

    private  void init() {
        lblBal.setText(String.valueOf(coins));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }
    @FXML
    private void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnClose) {
            System.exit(0);
        }
    }

    @FXML
    private void handleSpinEvent(ActionEvent evt) {
        int amount = Integer.valueOf(tfAmount.getText());

        if((tfAmount.getText() != null) && (amount != 0)) {
            int slot1 = (int)(Math.random() * 9) + 1;
            int slot2 = (int)(Math.random() * 9) + 1;
            int slot3 = (int)(Math.random() * 9) + 1;

            lbl1.setText(String.valueOf(slot1));
            lbl2.setText(String.valueOf(slot2));
            lbl3.setText(String.valueOf(slot3));

            if (slot1 == 7 && slot2 == 7 && slot3 == 7){
                System.out.println("Trafiles trzy 7!!!");
                coins += 4 * amount;
            }

            else if (slot1 == 7 && slot2 == 7 || slot1 == 7 && slot3 == 7 || slot2 == 7 && slot3 == 7 ){
                System.out.println("Trafiles dwie 7!");
                coins += 2 * amount;
            }

            else if (slot1 == slot2 && slot1 == slot3){
                System.out.println("Trafiles 3 takie same liczby!");
                coins += 3 * amount;
            }

            else {
                System.out.println("Nic nie trafiles!!! :(");
                coins += 0;
            }
        }

        coins -= amount;
        lblBal.setText(String.valueOf(coins));
        User.getCurrentUser().setBalance(coins);

        UserData.updateBalanceInDatabase(User.getCurrentUser().getUserId(), coins);
    }
}

package com.main.casinoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuPanelController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    int coins = User.getCurrentUser().getBalance();

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnMain;

    @FXML
    private AnchorPane apProfile;

    @FXML
    private AnchorPane apCredits;

    @FXML
    private AnchorPane apGames;

    @FXML
    private Button btnGames;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnCredits;

    @FXML
    private AnchorPane apMain;

    @FXML
    private AnchorPane apHistory;

    @FXML
    private Circle btnClose;

    @FXML
    private Button btnPlay1;
    @FXML
    private Button btnPlay2;
    @FXML
    private Button btnPlay3;
    @FXML
    private Button btnPlay4;
    @FXML
    private Button btnPlay5;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label lblNickname;
    @FXML
    private Label lblNickname2;
    @FXML
    private Label lblNickname3;
    @FXML
    private Label lblNickname4;
    @FXML
    private Label lblNickname5;
    @FXML
    private Label lblBal;
    @FXML
    private Label lblBal2;
    @FXML
    private Label lblBal3;
    @FXML
    private Label lblBal4;
    @FXML
    private Label lblBal5;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblAge;
    @FXML
    private Label lblProfBalance;
    @FXML
    private TextField tfDeposit;
    @FXML
    private Button btnConfirm;
    @FXML
    private Label lblLogout;

    String logTxt = "Log in again to complete the transaction.";

    private void init() {
        lblBal.setText(String.valueOf(coins));
        lblNickname.setText(User.getCurrentUser().getNickname());
        lblBal2.setText(String.valueOf(coins));
        lblNickname2.setText(User.getCurrentUser().getNickname());
        lblBal3.setText(String.valueOf(coins));
        lblNickname3.setText(User.getCurrentUser().getNickname());
        lblBal4.setText(String.valueOf(coins));
        lblNickname4.setText(User.getCurrentUser().getNickname());
        lblBal5.setText(String.valueOf(coins));
        lblNickname5.setText(User.getCurrentUser().getNickname());
        lblUsername.setText(User.getCurrentUser().getNickname());
        lblEmail.setText(User.getCurrentUser().getEmail());
        int age = User.getCurrentUser().getAge();
        lblAge.setText(String.valueOf(age));
        lblProfBalance.setText(String.valueOf(coins));
        lblLogout.setText(" ");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    @FXML
    private void handleButtonEvent(ActionEvent event) throws IOException, InterruptedException {

        if (event.getSource().equals(btnMain)) {
            apMain.toFront();
        }
        if (event.getSource().equals(btnProfile)) {
            apProfile.toFront();
        }
        if (event.getSource().equals(btnGames)) {
            apGames.toFront();
        }
        if (event.getSource().equals(btnHistory)) {
            apHistory.toFront();
        }
        if (event.getSource().equals(btnCredits)) {
            apCredits.toFront();
        }
        if(event.getSource().equals(btnPlay1)) {
            closeCurrentStage();
            openSlotsView();
        }
        if(event.getSource().equals(btnPlay2)) {
            closeCurrentStage();
            openSlotsView();
        }
        if(event.getSource().equals(btnPlay3)) {
            closeCurrentStage();
            openSlotsView();
        }
        if(event.getSource().equals(btnPlay4)) {
            closeCurrentStage();
            openSlotsView();
        }
        if(event.getSource().equals(btnPlay5)) {
            closeCurrentStage();
            openSlotsView();
        }
        if(event.getSource().equals(btnConfirm)) {
            int depositAmount = Integer.valueOf(tfDeposit.getText());
            if(depositAmount != 0) {
                UserData.updateBalanceInDatabase(User.getCurrentUser().getUserId(),
                                        User.getCurrentUser().getBalance() + depositAmount);
                closeCurrentStage();
                openLoginView();
            }
        }
    }

    @FXML
    private void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnClose) {
            System.exit(0);
        }
    }

    private void closeCurrentStage() {
        Stage currentStage = (Stage) rootPane.getScene().getWindow();
        currentStage.close();
    }

    private void openSlotsView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("slots-view.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.TRANSPARENT);

        setDraggable(root, stage);

        stage.show();
    }

    private void openLoginView() throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root2 = fxmlLoader2.load();

        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root2));
        stage2.initStyle(StageStyle.TRANSPARENT);

        setDraggable(root2, stage2);

        stage2.show();
    }
    private void setDraggable(Parent root, Stage stage) {
        root.setOnMousePressed((MouseEvent eve) -> {
            xOffset = eve.getSceneX();
            yOffset = eve.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent eve) -> {
            stage.setX(eve.getScreenX() - xOffset);
            stage.setY(eve.getScreenY() - yOffset);
        });
    }
}

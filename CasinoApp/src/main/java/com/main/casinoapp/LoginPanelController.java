package com.main.casinoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;


public class LoginPanelController  {
    private double xOffset = 0;
    private double yOffset = 0;
        @FXML
        private AnchorPane rootPane;

        @FXML
        private ImageView btnBack;

        @FXML
        private Circle btnClose;

        @FXML
        private TextField tfEmailReg;

        @FXML
        private TextField tfEmail;

        @FXML
        private Pane pnlSignUp;

        @FXML
        private PasswordField tfPassReg;

        @FXML
        private Button btnRegister;

        @FXML
        private TextField tfBalance;

        @FXML
        private TextField tfNickname;

        @FXML
        private PasswordField tfPass;

        @FXML
        private Button btnSignUp;

        @FXML
        private Button btnSignIn;

        @FXML
        private Pane pnlSignIn;

        @FXML
        private TextField tfAge;


        Connection con = db.mycon();
    @FXML
        private void handleButtonAction(ActionEvent event) {
            if (event.getSource().equals(btnSignUp)) {
                pnlSignUp.toFront();
            }

            if (event.getSource().equals(btnSignIn)) {

                String em = tfEmail.getText();
                String ps = tfPass.getText();

                try {
                    User authenticatedUser = UserData.getAuthenticatedUser(em, ps, con);

                    if (authenticatedUser != null) {
                        User.setCurrentUser(authenticatedUser);

                        closeCurrentStage();
                        openMenuView();
                    } else {
                        System.exit(0);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if (event.getSource().equals(btnRegister)) {
                String nickname = tfNickname.getText();
                String email = tfEmailReg.getText();
                String pass = tfPassReg.getText();
                String age = tfAge.getText();
                String bal = tfBalance.getText();


                try {
                    java.sql.Statement s = db.mycon().createStatement();
                    s.executeUpdate(" INSERT INTO user (user_email,user_password,user_nickname,user_age,user_balance) "
                            + "VALUES ('" + email + "','" + pass + "','" + nickname + "','" + age + "','" + bal + "') ");

                    tfNickname.clear();
                    tfEmailReg.clear();
                    tfPassReg.clear();
                    tfAge.clear();
                    tfBalance.clear();
                    pnlSignIn.toFront();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }


        @FXML
        private void handleMouseEvent(MouseEvent event) {
            if (event.getSource() == btnClose) {
                System.exit(0);
            }
            if (event.getSource().equals(btnBack)) {
                pnlSignIn.toFront();
            }
        }

    private void closeCurrentStage() {
        Stage currentStage = (Stage) rootPane.getScene().getWindow();
        currentStage.close();
    }

    private void openMenuView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.TRANSPARENT);

        setDraggable(root, stage);

        stage.show();
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

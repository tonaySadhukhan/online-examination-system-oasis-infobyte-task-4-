package org.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;


public class HelloController {
    Alert a=new Alert(Alert.AlertType.NONE);
    @FXML
    private Label welcomeText;

    public Button submit;

    public TextField name;
    public TextField email;
    public  TextField password;
    public TextField con_pass;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    public void Loginpage()throws Exception{
        System.out.println("jgffhjg");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginform.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public void login(ActionEvent actionEvent)throws Exception{
       Loginpage();

    }
    public void onsubmit(ActionEvent actionEvent) throws Exception{
        if(password.getText().equals(con_pass.getText())) {
            Data d1 = new Data(name.getText(), password.getText(), email.getText());
            a.setAlertType(Alert.AlertType.CONFIRMATION);
            a.setContentText("Sign in successfull");
            a.show();
            Loginpage();
        }else{
            a.setAlertType(Alert.AlertType.WARNING);
            a.setTitle("Password mismatch");
            a.show();
        }
    }
}
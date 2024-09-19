package org.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Loginfromcontroller  {

    public TextField uname;
    public TextField password;

    public Button logsubmit;
    public void submit(ActionEvent actionEvent) throws Exception{
        if(uname.getText().equals(Data.name) && password.getText().equals(Data.password)){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Exampage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage=new Stage();
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
        else{
            Alert a=new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.WARNING);
            a.setTitle("Wrong username or password");
            a.show();
        }
    }

}

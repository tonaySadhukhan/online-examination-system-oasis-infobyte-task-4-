package org.example.demo1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.text.DecimalFormat;

public class Endpagecontroller {
    @FXML
    private ImageView Image;
    @FXML
    private Label name,sub,marks,percent;
    @FXML
    private Button exit;
    @FXML
    private Label grade;

    @FXML
    private void initialize(){
        DecimalFormat df=new DecimalFormat("0.00");
        name.setText(""+Data.name);
        sub.setText(""+Data.sub);
        marks.setText(""+Data.marks);
       double perc=((double) Data.marks /Data.q_no)*100;

       percent.setText(""+df.format(perc)+"%");
       if(perc>80){
           grade.setText("A");
       } else if (perc>60) {
           grade.setText("B");
       }
       else if(perc>40){
           grade.setText("C");
       }
       else{
           grade.setText("D");
       }

    }
    @FXML
    private void Exit(ActionEvent actionEvent){
        Platform.exit();
    }
}

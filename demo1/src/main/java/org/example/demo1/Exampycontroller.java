package org.example.demo1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Exampycontroller {

    @FXML
    private Label Question;
    @FXML
    private RadioButton option1;
    @FXML
    private RadioButton option2;
    @FXML
    private RadioButton option3;
    @FXML
    private RadioButton option4;
    @FXML
    private Button next;
    @FXML
    private Label timer, qno;
    @FXML
    private ProgressBar pg;

    private int score = 0;
    private String ans;
    private int index = 0;
    private Timeline timeline;
    private int time = 30; // 30 seconds for each question

    private String[][] q = {
            {"What is the extension of Python?", ".python", ".p", ".py", ".pt", ".py"},
            {"Which one of the following is not a keyword in Python?", "for", "in", "pass", "void", "void"},
            {"What is the output of print(2 ** 3)?", "6", "8", "9", "12", "8"},
            {"Which of the following is a mutable data type in Python?", "tuple", "list", "string", "int", "list"},
            {"How do you create a dictionary in Python?", "{key: value}", "[key: value]", "(key: value)", "<key: value>", "{key: value}"},
            {"Which method is used to remove an item from a set?", "delete()", "remove()", "pop()", "discard()", "remove()"},
            {"What does the len() function do in Python?", "Returns the length of a string", "Returns the type of an object", "Returns the number of elements in a list", "Both 1 and 3", "Both 1 and 3"},
            {"How do you start a comment in Python?", "//", "#", "/*", "<!--", "#"},
            {"What is the purpose of the 'pass' statement in Python?", "It terminates a loop", "It is a null statement", "It skips the current iteration of a loop", "It raises an error", "It is a null statement"},
            {"What is the default value of the end parameter in the print() function?", "newline ('\\n')", "space (' ')", "None", "comma (',')", "newline ('\\n')"}
    };

    private String[][] q1 = {
            {"Which component is used to compile java code", "JVM", "JDK", "JRE", "JIT", "JDK"},
            {"Extension of compiled java class", ".java", ".exe", ".bak", ".class", ".class"},
            {"Which environment variable is used to set the java path", "CLASSPATH", "JAVA_HOME", "JAVAPATH", "JAVA", "JAVA_HOME"},
            {"Which of the method in string class used to find out the character at a specific index", "chart()", "Charat()", "charat()", "char At()", "char At()"},
            {"Which is not an access specifier", "static", "Public", "protected", "private", "static"},
            {"Which type of inheritance does not support in java", "Multilevel", "Multiple", "Hierarchial", "Single", "Multiple"},
            {"Which keyword is used to access methods of a package", "export", "package", "import", "extends", "import"},
            {"Which of the following is not a Java keyword?", "static", "Boolean", "extends", "volatile", "Boolean"},
            {"What is the size of an int in Java?", "8 bits", "16 bits", "32 bits", "64 bits", "32 bits"},
            {"Which method is used to start a thread in Java?", "run()", "start()", "begin()", "init()", "start()"}
    };

    @FXML
    public void initialize() {
        System.out.println("Initialize called");

        if (Question == null) {
            System.out.println("Question Label is not initialized");
        } else {
            System.out.println("Question Label is initialized");
        }

        Loadquestions(index);
        if (Data.sub.equals("python"))
            Data.q_no = q.length;
        else if (Data.sub.equals("java")) {
            Data.q_no = q1.length;
        }
        startTimer();
    }

    private void Loadquestions(int index) {
        resetTimer(); // Reset the timer for each new question
        if (index < q.length && Data.sub.equals("python")) {
            String[] a;
            a = q[index];

            Question.setText(a[0]);
            option1.setText(a[1]);
            option2.setText(a[2]);
            option3.setText(a[3]);
            option4.setText(a[4]);
            ans = a[5];
        } else {
            String[] a;
            a = q1[index];

            Question.setText(a[0]);
            option1.setText(a[1]);
            option2.setText(a[2]);
            option3.setText(a[3]);
            option4.setText(a[4]);
            ans = a[5];
        }
        // Reset selection of RadioButtons
        option1.setSelected(false);
        option2.setSelected(false);
        option3.setSelected(false);
        option4.setSelected(false);
    }

    @FXML
    private void setnext(ActionEvent actionEvent) throws Exception {
        checkAnswer();
        moveToNextQuestion();
    }

    private void checkAnswer() {
        if (option1.isSelected() && option1.getText().equals(ans) ||
                option2.isSelected() && option2.getText().equals(ans) ||
                option3.isSelected() && option3.getText().equals(ans) ||
                option4.isSelected() && option4.getText().equals(ans)) {
            score++;
        }
        Data.marks = score;
    }

    private void moveToNextQuestion() {
        index++;
        if (Data.sub.equals("python") && index < q.length) {
            qno.setText((index + 1) + "/" + q.length);
            Loadquestions(index);
        } else if (Data.sub.equals("java") && index < q1.length) {
            qno.setText((index + 1) + "/" + q1.length);
            Loadquestions(index);
        } else {
            // Handle end of quiz scenario
            Data.marks = score;
            System.out.println("Quiz finished. Final score: " + score);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Endpage.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Quiz Finished");
                stage.setScene(scene);
                stage.show();

                // Close or hide the current window
                Stage currentStage = (Stage) next.getScene().getWindow();
                currentStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            time--;
            timer.setText("Time left: " + time + "s");

            // Update the progress bar according to the remaining time
            double progress = (double) time / 30; // Calculate progress as a fraction of the total time
            pg.setProgress(progress);

            if (time <= 0) {
                try {
                    moveToNextQuestion(); // Automatically move to next question
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void resetTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        time = 30; // Reset the timer to 30 seconds
        pg.setProgress(1.0); // Reset the progress bar to full
        startTimer();
    }


    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        pg.setStyle("-fx-accent: yellow");
        pg.setProgress(0.5);
    }
}

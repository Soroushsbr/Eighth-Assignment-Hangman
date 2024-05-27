package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MenuController {
    private String[] words = {"Water" , "Fire" , "Test"};
    @FXML
    Rectangle backgroundChoseDiff;
    @FXML
    Pane choseDiffPane;
    public void play(){
        backgroundChoseDiff.setVisible(true);
        choseDiffPane.setVisible(true);
    }

    public void exitPlay(){
        backgroundChoseDiff.setVisible(false);
        choseDiffPane.setVisible(false);
    }

    public void leaderboard(){

    }

    public void exit(){
        System.exit(0);
    }
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    TextField nameField;
    public void easyDiff(ActionEvent event) throws IOException {
        if(nameField.getText() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
            root = loader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            HangmanController hangmanController = loader.getController();
            hangmanController.set("Easy" , nameField.getText());
        }
    }

    public void medDiff(ActionEvent event) throws IOException {
        if(nameField.getText() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
            root = loader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            HangmanController hangmanController = loader.getController();
            hangmanController.set("Medium" , nameField.getText());
        }
    }

    public void hardDiff(ActionEvent event) throws IOException {
        if(nameField.getText() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
            root = loader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            HangmanController hangmanController = loader.getController();
            hangmanController.set("Hard" , nameField.getText());
        }
    }
}

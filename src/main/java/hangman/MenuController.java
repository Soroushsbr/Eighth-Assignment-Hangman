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
import java.util.Objects;

public class MenuController {
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
    public void animalBtn(ActionEvent event) throws IOException {
        if(!Objects.equals(nameField.getText(), "")) {
            RandWord randWord = new RandWord();
            System.out.println(nameField.getText());
            String rand = randWord.getWord("animals");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
            root = loader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            HangmanController hangmanController = loader.getController();
            hangmanController.set(rand , nameField.getText());
        }
    }

    public void colorBtn(ActionEvent event) throws IOException {
        if(!Objects.equals(nameField.getText(), "")) {
            RandWord randWord = new RandWord();
            String rand = randWord.getWord("colors");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
            root = loader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            HangmanController hangmanController = loader.getController();
            hangmanController.set(rand , nameField.getText());
        }
    }

    public void foodBtn(ActionEvent event) throws IOException {
        if(!Objects.equals(nameField.getText(), "")) {
            RandWord randWord = new RandWord();
            String rand = randWord.getWord("foods");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
            root = loader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            HangmanController hangmanController = loader.getController();
            hangmanController.set(rand , nameField.getText());
        }
    }

    public void clothesBtn(ActionEvent event) throws IOException {
        if(!Objects.equals(nameField.getText(), "")) {
            RandWord randWord = new RandWord();
            String rand = randWord.getWord("clothes");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
            root = loader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            HangmanController hangmanController = loader.getController();
            hangmanController.set(rand , nameField.getText());
        }
    }

    public void randomBtn(ActionEvent event) throws IOException {
        if(!Objects.equals(nameField.getText(), "")) {
            RandWord randWord = new RandWord();
            String rand = randWord.giveWord();
            System.out.println(rand);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
            root = loader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            HangmanController hangmanController = loader.getController();
            hangmanController.set(rand , nameField.getText());
        }
    }
}

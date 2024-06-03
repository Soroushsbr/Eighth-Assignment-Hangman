package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    Rectangle backgroundChoseDiff;
    @FXML
    Pane choseDiffPane;
    @FXML
    Account account;
    @FXML
    Pane back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media media = new Media(new File("C:\\Users\\Lenovo\\OneDrive\\Desktop\\Java Projects\\Eighth-Assignment-Hangman\\src\\main\\resources\\background.mp4").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(mediaPlayer.getStartTime());
            mediaPlayer.play();
        });
        ((Pane) back.getChildren().get(0)).getChildren().add(mediaView);
        mediaPlayer.play();
    }
    public void set(Account account){
        this.account = account;
    }
    public void play(){
        backgroundChoseDiff.setVisible(true);
        choseDiffPane.setVisible(true);
    }

    public void exitPlay(){
        backgroundChoseDiff.setVisible(false);
        choseDiffPane.setVisible(false);
    }
    @FXML
    Pane leaderboardPane;
    @FXML
    VBox leaderboardBox;

    public void leaderboard(){
        backgroundChoseDiff.setVisible(true);
        leaderboardPane.setVisible(true);
        leaderboardBox.getChildren().clear();

        DatabaseManager databaseManager = new DatabaseManager();
        ArrayList<Game> gamesList = databaseManager.getLeaderboard();
        String state;
        for(Game game : gamesList){
            if(game.getWin()){
                state = "Won";
            }else {
                state = "Lost";
            }
            Label label = new Label(game.getUsername() + "   ||   " + game.getWord() + "   ||   " + game.getWrongGuesses() + "   ||   " + game.getTime() + "   ||   " + state);
            label.setStyle("-fx-font-size: 16; -fx-background-color: transparent; -fx-border-radius: 10; -fx-border-color: white");
            leaderboardBox.getChildren().add(label);
        }
    }

    public void exitLeaderboard(){
        backgroundChoseDiff.setVisible(false);
        leaderboardPane.setVisible(false);
    }

    public void exit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginMenu.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    TextField nameField;
    public void animalBtn(ActionEvent event) throws IOException {
        RandWord randWord = new RandWord();
        String rand = randWord.getWord("animals");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HangmanController hangmanController = loader.getController();
        hangmanController.set(rand , account);
    }

    public void colorBtn(ActionEvent event) throws IOException {
        RandWord randWord = new RandWord();
        String rand = randWord.getWord("colors");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HangmanController hangmanController = loader.getController();
        hangmanController.set(rand , account);
    }

    public void foodBtn(ActionEvent event) throws IOException {
        RandWord randWord = new RandWord();
        String rand = randWord.getWord("foods");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HangmanController hangmanController = loader.getController();
        hangmanController.set(rand , account);
    }

    public void clothesBtn(ActionEvent event) throws IOException {
        RandWord randWord = new RandWord();
        String rand = randWord.getWord("clothes");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman-view.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HangmanController hangmanController = loader.getController();
        hangmanController.set(rand , account);
    }

    public void randomBtn(ActionEvent event) throws IOException {
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
        hangmanController.set(rand , account);
    }

}

package hangman;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HangmanController extends Application {
    private ArrayList<String> guessed = new ArrayList<>();
    private String word;
    private int HP;
    private int rightGuesses = 0;
    private Game game = new Game();
    private String name;
    private Thread thread;
    private Account account;
    @FXML
    Label timeLabel;
    public void set(String word , Account account){
        this.word = word.toUpperCase();
        this.name = account.getUsername();
        this.account = account;
        this.HP = 10;
        TimeCal timeCal = new TimeCal(timeLabel);
        thread = new Thread(timeCal);
        thread.start();
        setWords();
    }
    @FXML
    VBox vBox;
    @FXML
    HBox hBoxtop;
    @FXML
    HBox hBoxBottom;
    public void setWords(){
        try {
            vBox.getChildren().clear();
            hBoxtop.getChildren().clear();
            hBoxBottom.getChildren().clear();
            for (int i = 0; i < word.length(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("WordBox.fxml"));
                AnchorPane wordPane = loader.load();
                if( i < 6){
                    if(guessed.contains(String.valueOf(word.charAt(i)))) {
                        ((Label) wordPane.getChildren().get(0)).setText(String.valueOf(word.charAt(i)));
                    }
                    hBoxtop.getChildren().add(wordPane);
                }else{
                    if(guessed.contains(String.valueOf(word.charAt(i)))) {
                        ((Label) wordPane.getChildren().get(0)).setText(String.valueOf(word.charAt(i)));
                    }
                    hBoxBottom.getChildren().add(wordPane);
                }
            }
            vBox.getChildren().add(hBoxtop);
            vBox.getChildren().add(hBoxBottom);
        }catch (IOException ignored){
        }
    }
    @FXML
    TextField wordField;
    @FXML
    Label scoreLabel;
    private int score = 0;
    public void selectAWord(){
        String guess = wordField.getText().toUpperCase();
        char[] ch = guess.toCharArray();
        //user only can type letters
        boolean match = (ch[0] >= 'A' && ch[0] <= 'Z');
        if(word.contains(guess)){
            scoreLabel.setText(String.valueOf(score));
            vBox.getChildren().clear();
            hBoxtop.getChildren().clear();
            hBoxBottom.getChildren().clear();
            if(!guessed.contains(guess) && match) {
                rightGuesses++;
                guessed.add(guess);
                setLetters();
                score += 100;
            }
            wordField.clear();
            winChecker();
            setWords();
        }else {
            wordField.clear();
            if(!guessed.contains(guess) && match) {
                guessed.add(guess);
                score -= 10;
                scoreLabel.setText(String.valueOf(score));
                setLetters();
                switch (HP){
                    case 10:
                        animeYwood();
                        HP--;
                        break;
                    case 9:
                       animeXwood();
                       HP--;
                       break;
                    case 8:
                        animeSideWood();
                        HP--;
                        break;
                    case 7:
                        animeRope();
                        HP--;
                        break;
                    case 6:
                        animeHead();
                        HP--;
                        break;
                    case 5:
                        animeBody();
                        HP--;
                        break;
                    case 4:
                        animeRightH();
                        HP--;
                        break;
                    case 3:
                        animeLeftH();
                        HP--;
                        break;
                    case 2:
                        animeRightL();
                        HP--;
                        break;
                    case 1:
                        animeLeftL();
                        HP--;
                        statue(false);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void winChecker(){
        boolean flag = true;
        char[] letters = word.toCharArray();
        for(char letter : letters){
            if(!guessed.contains(String.valueOf(letter))){
                flag = false;
            }
        }
        if(flag){
            statue(true);
        }
    }

    @FXML
    Pane statuePane;
    @FXML
    Label stateLabel;
    @FXML
    Label stateWord;
    @FXML
    Label stateTime;
    @FXML
    Rectangle background;
    public void statue(boolean state){
        thread.interrupt();
        if(state) {
            stateLabel.setText("YOU WON");
        }else{
            stateLabel.setText("YOU LOST");
        }

        //set game info
        game.setUsername(name);
        game.setTime(timeLabel.getText());
        game.setWord(word);
        game.setWrongGuesses(guessed.size() - rightGuesses);
        game.setWin(state);
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.insertGame(game);

        //set labels
        stateWord.setText(word);
        stateTime.setText(timeLabel.getText());
        background.setVisible(true);
        statuePane.setVisible(true);
    }

    public void getBackToMenu(ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        MenuController menuController = loader.getController();
        menuController.set(account);
    }
    @FXML
    Label guessLabel;
    public void setLetters(){
        String guesses ="";
        int i = 0;
        for(String guess : guessed){
            guesses = guesses + " " + guess + " ";
            i++;
            if(i == 30){
                guesses = guesses + "\n";
            }
        }
        guessLabel.setText(guesses);
    }


    //--------------------------------animation----------------------------------
    @FXML
    Line Ywood;
    public void animeYwood(){
        Ywood.setVisible(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(Ywood.endYProperty(), 415.0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(Ywood.endYProperty(), -10.0 ))
        );
        timeline.play();
    }
    @FXML
    Line Xwood;
    public void animeXwood(){
        Xwood.setVisible(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(Xwood.endXProperty(), -55.0)),
                new KeyFrame(Duration.seconds(0.7), new KeyValue(Xwood.endXProperty(), 100.0 ))
        );
        timeline.play();
    }

    @FXML
    Line sideWood;
    public void animeSideWood(){
        sideWood.setVisible(true);
        Timeline timelineX = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(sideWood.endXProperty(), -55.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(sideWood.endXProperty(), -14.0 ))
        );
        timelineX.play();
        Timeline timelineY = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(sideWood.endYProperty(), 60.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(sideWood.endYProperty(), -9.0 ))
        );
        timelineY.play();
    }
    @FXML
    Line rope;
    public void animeRope(){
        rope.setVisible(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rope.endYProperty(), -53.0)),
                new KeyFrame(Duration.seconds(0.7), new KeyValue(rope.endYProperty(), 83.0 ))
        );
        timeline.play();
    }

    @FXML
    Circle head;
    public void animeHead(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(head.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(head.opacityProperty(), 1.0 ))
        );
        timeline.play();
    }

    @FXML
    Line body;

    public void animeBody(){
        body.setVisible(true);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(body.endYProperty(), -10.0)),
                new KeyFrame(Duration.seconds(0.7), new KeyValue(body.endYProperty(), 120.0 ))
        );
        timeline.play();
    }

    @FXML
    Line rightH;
    public void animeRightH(){
        rightH.setVisible(true);
        Timeline timelineX = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rightH.endXProperty(), -100.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(rightH.endXProperty(), -60 ))
        );
        timelineX.play();
        Timeline timelineY = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rightH.endYProperty(), 0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(rightH.endYProperty(), 45 ))
        );
        timelineY.play();
    }

    @FXML
    Line leftH;
    public void animeLeftH(){
        leftH.setVisible(true);
        Timeline timelineX = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(leftH.endXProperty(), 100)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(leftH.endXProperty(), 60.0 ))
        );
        timelineX.play();
        Timeline timelineY = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(leftH.endYProperty(), 0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(leftH.endYProperty(), 50 ))
        );
        timelineY.play();
    }

    @FXML
    Line rightL;
    public void animeRightL(){
        rightL.setVisible(true);
        Timeline timelineX = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rightL.endXProperty(), -100.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(rightL.endXProperty(), -60.0 ))
        );
        timelineX.play();
        Timeline timelineY = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rightL.endYProperty(), 0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(rightL.endYProperty(), 50 ))
        );
        timelineY.play();
    }
    @FXML
    Line leftL;
    public void animeLeftL(){
        leftL.setVisible(true);
        Timeline timelineX = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(leftL.endXProperty(), 100)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(leftL.endXProperty(), 60.0 ))
        );
        timelineX.play();
        Timeline timelineY = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(leftL.endYProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(leftL.endYProperty(), 50 ))
        );
        timelineY.play();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void stop() throws Exception {
    }
    //--------------------------------animation----------------------------------
}
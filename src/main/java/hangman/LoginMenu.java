package hangman;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoginMenu {
    @FXML
    Hyperlink switchLink;
    @FXML
    Pane switchPane;

    public void switchScene(){
        String name = switchLink.getText();
        if(name.equals("Log in")){
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(switchPane.layoutXProperty(), 0)),
                    new KeyFrame(Duration.seconds(0.2), new KeyValue(switchPane.layoutXProperty(), 322))
            );
            timeline.play();
            switchLink.setText("Sign up");
        }else{
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(switchPane.layoutXProperty(), 322)),
                    new KeyFrame(Duration.seconds(0.2), new KeyValue(switchPane.layoutXProperty(), 0))
            );
            timeline.play();
            switchLink.setText("Log in");
        }
    }
    @FXML
    TextField logUser;
    @FXML
    PasswordField logPass;

    public void login(ActionEvent event) throws IOException{
        String username = logUser.getText();
        String password = logPass.getText();
        DatabaseManager databaseManager = new DatabaseManager();
        Account account = databaseManager.verifyAcc(username , password);
        if(account != null){
            changeScene(event, account);
        }
    }

    private void changeScene(ActionEvent event, Account account) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root;
        Stage stage;
        Scene scene;

        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        MenuController menuController = loader.getController();
        menuController.set(account);
    }

    @FXML
    TextField signUser;
    @FXML
    TextField signName;
    @FXML
    PasswordField signPass;
    public void signUp(ActionEvent event) throws IOException {
        DatabaseManager databaseManager = new DatabaseManager();
        String name = signName.getText();
        String username = signUser.getText();
        String password = signPass.getText();
        //made a new account in database
        Account account = new Account(name , username , password);
        //add to database
        databaseManager.newAccount(account);
        changeScene(event, account);
    }

}

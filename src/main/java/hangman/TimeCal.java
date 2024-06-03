package hangman;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static java.lang.Thread.sleep;

public class TimeCal implements Runnable {
    public TimeCal(Label timeLabel){
        this.timeLabel = timeLabel;
    }
    @FXML
    private Label timeLabel;
    private static int sec = 0;
    @Override
    public void run() {
        sec = 0;
        while (!Thread.currentThread().isInterrupted()){
            sec++;
            try {
                sleep(1000);
                Platform.runLater(() -> {
                    int min = sec / 60;
                    String Sec;
                    if(sec % 60 < 10){
                        Sec = "0" + sec % 60;
                    }else {
                        Sec = String.valueOf(sec % 60);
                    }
                    timeLabel.setText(min + ":" + Sec);
                });
            } catch (InterruptedException e) {
                System.out.println("Time Stopped");
                //when the game end the thread should be terminated
                break;
            }
        }
    }

}

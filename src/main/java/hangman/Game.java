package hangman;

import java.util.UUID;

public class Game {
    private UUID gameID;
    private String username;
    private String word;
    private int wrongGuesses;
    private String time;
    private Boolean win;
    public Game(){
        gameID = UUID.randomUUID();
    }

    public UUID getGameID() {
        return gameID;
    }

    public String getUsername() {
        return username;
    }

    public String getWord() {
        return word;
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public String getTime() {
        return time;
    }

    public Boolean getWin() {
        return win;
    }

    public void setGameID(UUID gameID) {
        this.gameID = gameID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setWrongGuesses(int wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }
}

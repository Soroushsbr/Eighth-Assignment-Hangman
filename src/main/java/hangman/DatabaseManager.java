package hangman;

import java.sql.*;
import java.util.ArrayList;

// Use JDBC to connect to your database and run queries

public class DatabaseManager {

    public boolean verify(Account account){
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Hangman";
            String sqlUsername = "postgres";
            String sqlPassword = "1383";
            //connect to server
            Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String query = "SELECT * FROM \"UserInfo\" WHERE \"Username\" = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1 , account.getUsername());
            ResultSet result = statement.executeQuery();
            if(result.next()){
                //username exist
                connection.setAutoCommit(false);
                connection.commit();
                // Close resources
                statement.close();
                connection.close();
                return false;
            }else {
                connection.setAutoCommit(false);
                connection.commit();
                // Close resources
                statement.close();
                connection.close();
                return true;
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean newAccount(Account account) {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Hangman";
            String username = "postgres";
            String password = "1383";
            if(verify(account)) {
                //connect to server
                Connection connection = DriverManager.getConnection(url, username, password);
                String query = "INSERT INTO public.\"UserInfo\"(\"Username\", \"Name\", \"Password\") VALUES (?, ?, ?);";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, account.getUsername());
                statement.setString(2, account.getName());
                statement.setString(3, account.getPassword());
                statement.executeUpdate();
                connection.setAutoCommit(false);
                connection.commit();

                // Close resources
                statement.close();
                connection.close();
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Account verifyAcc(String username , String password){
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Hangman";
            String sqlUsername = "postgres";
            String sqlPassword = "1383";
            //connect to server
            Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String query = "SELECT * FROM \"UserInfo\" WHERE \"Username\" = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1 , username);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                if(result.getString("Password").equals(password)){
                    Account account = new Account(result.getString("Name") ,result.getString("Username"), result.getString("Password") );
                    return account;
                }
            }
            connection.setAutoCommit(false);
            connection.commit();

            // Close resources
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Game> getLeaderboard(){
        ArrayList<Game> gamesList = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Hangman";
            String sqlUsername = "postgres";
            String sqlPassword = "1383";
            //connect to server
            Connection connection = DriverManager.getConnection(url, sqlUsername, sqlPassword);
            String query = "SELECT * FROM \"GameInfo\"";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Game game = new Game();
                game.setUsername(result.getString("Username"));
                game.setWord(result.getString("Word"));
                game.setTime(result.getString("Time"));
                game.setWin(result.getBoolean("Win"));
                game.setWrongGuesses(result.getInt("WrongGuesses"));
                gamesList.add(game);
            }
            connection.setAutoCommit(false);
            connection.commit();

            // Close resources
            statement.close();
            connection.close();
            return gamesList;
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void insertGame(Game game){
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Hangman";
            String username = "postgres";
            String password = "1383";
            //connect to server
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO public.\"GameInfo\"(\"Game_ID\", \"Username\", \"Word\" , \"WrongGuesses\", \"Time\" ,\"Win\") VALUES (?, ?, ?, ? , ? ,?);";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1 , game.getGameID().toString());
            statement.setString(2 , game.getUsername());
            statement.setString(3, game.getWord());
            statement.setInt(4, game.getWrongGuesses());
            statement.setString(5, game.getTime());
            statement.setBoolean(6, game.getWin());

            statement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();

            // Close resources
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
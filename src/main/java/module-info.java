module hangman {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires json;
    requires javafx.media;
    requires org.postgresql.jdbc;
    opens hangman to javafx.fxml;
    exports hangman;

}
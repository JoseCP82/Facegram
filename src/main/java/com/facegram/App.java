package com.facegram;

import com.facegram.model.DAO.UserDAO;
import com.facegram.model.dataobject.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        //launch();
        User u = new User("Miguel","12345");
        UserDAO uDAO = new UserDAO(u);
        uDAO.insert(u);

    }
}
package com.facegram;

import com.facegram.controllers.FeedController;
import com.facegram.model.DAO.UserDAO;
import com.facegram.model.dataobject.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        User user = new UserDAO().get(1);
        FeedController fc =new FeedController(user); //modificar


        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("feed.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("register.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 320, 480);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
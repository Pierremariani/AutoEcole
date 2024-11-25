package com.example.autoecole;

import com.example.autoecole.controllers.UserController;
import com.example.autoecole.repositories.UserRepository;
import com.example.autoecole.services.UserService;
import com.example.autoecole.tools.DataSourceProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    UserController userController;
    UserRepository userRepository;
    UserService userService;

    DataSourceProvider connexionBDD;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connexionBDD = new DataSourceProvider();
            userController = new UserController();
            userRepository = new UserRepository();
            userService = new UserService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onHelloButtonClick() throws SQLException {
        welcomeText.setText("Welcome to JavaFX Application!");

        //userController.create("teub","paul",0);
        System.out.println(userController.getAll());
        System.out.println(userController.getLogin());
        System.out.println(userController.verifyLoginMdp("zebi","paul"));
    }
}
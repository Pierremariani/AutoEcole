package com.example.autoecole;

import com.example.autoecole.controllers.EleveController;
import com.example.autoecole.controllers.MoniteurController;
import com.example.autoecole.controllers.UserController;
import com.example.autoecole.repositories.UserRepository;
import com.example.autoecole.services.UserService;
import com.example.autoecole.tools.DataSourceProvider;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GraphicMoniteur implements Initializable {

    UserController userController;
    UserRepository userRepository;
    UserService userService;

    EleveController eleveController;

    MoniteurController moniteurController;

    DataSourceProvider connexionBDD;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connexionBDD = new DataSourceProvider();
            userController = new UserController();
            userRepository = new UserRepository();
            userService = new UserService();
            eleveController = new EleveController();
            moniteurController = new MoniteurController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.example.autoecole;

import com.example.autoecole.controllers.EleveController;
import com.example.autoecole.controllers.UserController;
import com.example.autoecole.repositories.UserRepository;
import com.example.autoecole.services.UserService;
import com.example.autoecole.tools.DataSourceProvider;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    UserController userController;
    UserRepository userRepository;
    UserService userService;

    EleveController eleveController;

    DataSourceProvider connexionBDD;
    @FXML
    private TextField tflog;
    @FXML
    private TextField tfmdp;
    @FXML
    private Button btninscr;
    @FXML
    private ImageView voitute;
    @FXML
    private TextField tfville;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfsexe;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfpostal;
    @FXML
    private TextField tftel;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfdate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        voitute.setVisible(true);
        try {

            connexionBDD = new DataSourceProvider();
            userController = new UserController();
            userRepository = new UserRepository();
            userService = new UserService();
            eleveController = new EleveController();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onInscriptionButtonClicked() throws SQLException {
        //userController.create("teub","paul",0);
       // System.out.println(userController.getAll());
        //System.out.println(userController.getLogin());
        //System.out.println(userController.verifyLoginMdp("zebi","paul"));
       // userController.create(tflog.getText(),tfmdp.getText(),0);
        //System.out.println(eleveController.GenerateCodeEleve());
        eleveController.createEleve(eleveController.GenerateCodeEleve(), tfnom.getText(),tfprenom.getText(),Integer.parseInt(tfsexe.getText()),tfdate.getText(),tfadresse.getText(),Integer.parseInt(tfpostal.getText()),tfville.getText(),Integer.parseInt(tftel.getText()));
    }

    @FXML
    public void onConnextionButtonClicked(Event event) {
    }
}
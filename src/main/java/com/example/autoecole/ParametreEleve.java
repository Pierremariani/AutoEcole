package com.example.autoecole;

import com.example.autoecole.controllers.*;
import com.example.autoecole.models.Eleve;
import com.example.autoecole.models.Global;
import com.example.autoecole.repositories.UserRepository;
import com.example.autoecole.services.UserService;
import com.example.autoecole.tools.DataSourceProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class ParametreEleve implements Initializable {

    UserController userController;
    UserRepository userRepository;
    UserService userService;

    EleveController eleveController;

    CategorieController categorieController;

    LeconController leconController;

    VehiculeController vehiculeController;

    DataSourceProvider connexionBDD;

    Alert a;

    @javafx.fxml.FXML
    private TextField modiftfmail;
    @javafx.fxml.FXML
    private TextField modiftfville;
    @javafx.fxml.FXML
    private TextField modiftfadresse;
    @javafx.fxml.FXML
    private TextField modiftfcp;
    @javafx.fxml.FXML
    private TextField modiftftel;
    @javafx.fxml.FXML
    private Button btnmodif;
    @javafx.fxml.FXML
    private TextField modiftfmdp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connexionBDD = new DataSourceProvider();
            userController = new UserController();
            userRepository = new UserRepository();
            userService = new UserService();
            eleveController = new EleveController();
            leconController = new LeconController();
            categorieController = new CategorieController();
            vehiculeController = new VehiculeController();

            //modiftfmdp.setText(userController.getMdp(Global.currentEleve.getNumCompte()));
            modiftfmail.setText(userController.getlogin(Global.currentEleve.getNumCompte()));

            a = new Alert(Alert.AlertType.CONFIRMATION);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        modiftfadresse.setText(Global.currentEleve.getAdresse());
        modiftfcp.setText(String.valueOf(Global.currentEleve.getPostal()));
        modiftftel.setText(String.valueOf(Global.currentEleve.getTel()));
        modiftfville.setText(Global.currentEleve.getVille());
    }

    @javafx.fxml.FXML
    public void modifclicked(ActionEvent actionEvent) throws SQLException {
        eleveController.update(Global.currentEleve.getNom(), Global.currentEleve.getPrenom(), Global.currentEleve.getSexe(), Global.currentEleve.getDatenaissance(),
                modiftfadresse.getText(), Integer.parseInt(modiftfcp.getText()), modiftfville.getText(),
                Integer.parseInt(modiftftel.getText()), modiftfmail.getText(), Global.currentEleve.getNumCompte());

        if (Objects.equals(modiftfmdp.getText(), "")) {
            userController.updatelogin(Global.currentEleve.getNumCompte(), modiftfmail.getText());
        }
        else {
            userController.update(Global.currentEleve.getNumCompte(), modiftfmail.getText(), modiftfmdp.getText());
        }
        Global.currentEleve = new Eleve(Global.currentEleve.getCode(),Global.currentEleve.getNom(), Global.currentEleve.getPrenom(), Global.currentEleve.getSexe(), Global.currentEleve.getDatenaissance(),
                modiftfadresse.getText(), Integer.parseInt(modiftfcp.getText()), modiftfville.getText(),
                Integer.parseInt(modiftftel.getText()), modiftfmail.getText(), Global.currentEleve.getNumCompte());
        a.setTitle("Succès");
        a.setHeaderText(null);
        a.setContentText("Changements enregistrés");
        a.showAndWait();
    }

    @javafx.fxml.FXML
    public void retourclicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard_etudiant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Start & go Dashboard Eleve");
        stage.setScene(scene);
        stage.show();
    }
}

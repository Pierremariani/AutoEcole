package com.example.autoecole;

import com.example.autoecole.controllers.*;
import com.example.autoecole.models.Eleve;
import com.example.autoecole.models.Global;
import com.example.autoecole.models.Moniteur;
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

public class ParametreMoni implements Initializable {


    UserController userController;
    UserRepository userRepository;
    UserService userService;

    EleveController eleveController;

    CategorieController categorieController;

    LeconController leconController;

    VehiculeController vehiculeController;

    DataSourceProvider connexionBDD;

    MoniteurController moniteurController;

    Alert a;

    @javafx.fxml.FXML
    private TextField monimodiftftel;
    @javafx.fxml.FXML
    private TextField monimodiftfmdp;
    @javafx.fxml.FXML
    private DatePicker monimodifdate;
    @javafx.fxml.FXML
    private TextField monimodiftfsexe;
    @javafx.fxml.FXML
    private TextField monimodiftfprenom;
    @javafx.fxml.FXML
    private TextField monimodiftfcp;
    @javafx.fxml.FXML
    private Button monibtnmodif;
    @javafx.fxml.FXML
    private TextField modiftfmailmoni;
    @javafx.fxml.FXML
    private TextField monimodiftfville;
    @javafx.fxml.FXML
    private TextField monimodiftfadresse;
    @javafx.fxml.FXML
    private TextField monimodiftfnom;

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
            moniteurController = new MoniteurController();
            vehiculeController = new VehiculeController();

            a = new Alert(Alert.AlertType.CONFIRMATION);
            //t
            //monimodiftfmdp.setText(userController.getMdp(Global.currentMoniteur.getNumCompte()));
            modiftfmailmoni.setText(userController.getlogin(Global.currentMoniteur.getNumCompte()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        monimodiftfadresse.setText(Global.currentMoniteur.getAdresse());
        monimodiftfcp.setText(String.valueOf(Global.currentMoniteur.getCodePostal()));
        monimodiftfnom.setText(Global.currentMoniteur.getNom());
        monimodiftfprenom.setText(Global.currentMoniteur.getPrenom());
        monimodiftfsexe.setText(Global.currentMoniteur.getSexe());
        monimodiftftel.setText(String.valueOf(Global.currentMoniteur.getTel()));
        monimodiftfville.setText(Global.currentMoniteur.getVille());
        monimodifdate.setValue(LocalDate.parse(Global.currentMoniteur.getDatedenaissancen()));

    }

    @javafx.fxml.FXML
    public void monimodifclicked(ActionEvent actionEvent) throws SQLException {
        moniteurController.update(monimodiftfnom.getText(), monimodiftfprenom.getText(), monimodiftfsexe.getText(), String.valueOf(monimodifdate.getValue()),
                monimodiftfadresse.getText(), Integer.parseInt(monimodiftfcp.getText()), monimodiftfville.getText(),
                Integer.parseInt(monimodiftftel.getText()), Global.currentMoniteur.getNumCompte());

        if (Objects.equals(monimodiftfmdp.getText(), "")) {
            userController.updatelogin(Global.currentMoniteur.getNumCompte(), modiftfmailmoni.getText());
        }
        else {
            userController.update(Global.currentMoniteur.getNumCompte(), modiftfmailmoni.getText(), monimodiftfmdp.getText());
        }
        Global.currentMoniteur = new Moniteur(monimodiftfnom.getText(), monimodiftfprenom.getText(), monimodiftfsexe.getText(), String.valueOf(monimodifdate.getValue()),
                monimodiftfadresse.getText(), Integer.parseInt(monimodiftfcp.getText()), monimodiftfville.getText(),
                Integer.parseInt(monimodiftftel.getText()), Global.currentMoniteur.getNumCompte());
        a.setTitle("Succès");
        a.setHeaderText(null);
        a.setContentText("Changements enregistrés");
        a.showAndWait();
    }

    @javafx.fxml.FXML
    public void moniretourclicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard_moniteur.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Start & go Dashboard Moniteur");
        stage.setScene(scene);
        stage.show();
    }
}

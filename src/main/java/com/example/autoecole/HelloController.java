package com.example.autoecole;

import com.example.autoecole.controllers.EleveController;
import com.example.autoecole.controllers.UserController;
import com.example.autoecole.models.Eleve;
import com.example.autoecole.models.Global;
import com.example.autoecole.models.Users;
import com.example.autoecole.repositories.UserRepository;
import com.example.autoecole.services.UserService;
import com.example.autoecole.tools.DataSourceProvider;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    UserController userController;
    UserRepository userRepository;
    UserService userService;

    EleveController eleveController;

    DataSourceProvider connexionBDD;

    Alert a;

    @FXML
    private TextField txtField_login_mdp;
    @FXML
    private TextField txtField_login_user;
    @FXML
    private Label btn_inscription;
    @FXML
    private TextField txtField_inscrVille;
    @FXML
    private TextField txtField_inscrPrenom;
    @FXML
    private TextField txtField_inscrTel;
    @FXML
    private Button btn_inscrValide;
    @FXML
    private Button btn_connexion;
    @FXML
    private TextField txtField_inscrNom;
    @FXML
    private DatePicker datePicker_naissance;
    @FXML
    private TextField txtField_inscrAdresse;
    @FXML
    private TextField txtField_inscrCP;
    @FXML
    private ComboBox cbo_sexe;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfmdpinscription;
    @FXML
    private AnchorPane paneConnexion;
    @FXML
    private AnchorPane paneInscription;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            connexionBDD = new DataSourceProvider();
            userController = new UserController();
            userRepository = new UserRepository();
            userService = new UserService();
            eleveController = new EleveController();
            a = new Alert(Alert.AlertType.ERROR);
            cbo_sexe.getItems().addAll("Homme","Femme");
            cbo_sexe.getSelectionModel().selectFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void onInscriptionSubmitButtonClicked(Event event) throws SQLException, IOException {
        if (verifInscription()) {
            userController.create(tfmail.getText(),tfmdpinscription.getText(),0);
            eleveController.createEleve(eleveController.GenerateCodeEleve(), txtField_inscrNom.getText(), txtField_inscrPrenom.getText(), cbo_sexe.getSelectionModel().getSelectedItem().toString(), String.valueOf(datePicker_naissance.getValue()), txtField_inscrAdresse.getText(), Integer.parseInt(txtField_inscrCP.getText()), txtField_inscrVille.getText(), Integer.parseInt(txtField_inscrTel.getText()), tfmail.getText(), userController.getNumCompte(tfmail.getText()));
            Global.idUser = userController.getNumCompte(tfmail.getText());
            Global.currentEleve = eleveController.setCurrentEleve(Global.idUser);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard_etudiant.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Auto-école/Dashboard étudiant");
            stage.setScene(scene);
            stage.show();
        }
        else {
            a.setTitle("Erreur de saisie");
            a.setHeaderText(null);
            a.setContentText("Veuillez compléter tous les champs");
            a.showAndWait();
        }
    }
    @FXML
    public void onConnextionButtonClicked(Event event) throws SQLException, IOException {
        if (userController.verifyLoginMdp(txtField_login_user.getText(),txtField_login_mdp.getText())) {
            Global.idUser = userController.getNumCompte(txtField_login_user.getText());
            Global.currentEleve = eleveController.setCurrentEleve(Global.idUser);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard_etudiant.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Auto-école/Dashboard étudiant");
            stage.setScene(scene);
            stage.show();
        }
        else {
            a.setTitle("Erreur de saisie");
            a.setHeaderText(null);
            a.setContentText("Utilisateur ou mot de passe incorrect");
            a.showAndWait();
        }
    }

    public boolean verifInscription() {
        if (!Objects.equals(txtField_inscrNom.getText(), "") && !Objects.equals(txtField_inscrPrenom.getText(), "") && !Objects.equals( String.valueOf(datePicker_naissance.getValue()), "") && !Objects.equals(txtField_inscrAdresse.getText(), "") && !Objects.equals(txtField_inscrCP.getText(), "") && !Objects.equals(txtField_inscrVille.getText(), "") && !Objects.equals(txtField_inscrTel.getText(), "") && !Objects.equals(tfmail.getText(), "") && !Objects.equals(tfmdpinscription.getText(), "")) {
            return true;
        }
        return false;
    }


    @FXML
    public void connexionpaneclicked(Event event) {
        paneConnexion.setVisible(true);
    }

    @FXML
    public void onInscriptionPaneClicked(Event event) {
        paneConnexion.setVisible(false);
    }
}
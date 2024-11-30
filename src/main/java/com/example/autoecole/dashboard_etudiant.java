package com.example.autoecole;

import com.example.autoecole.controllers.EleveController;
import com.example.autoecole.controllers.LeconController;
import com.example.autoecole.controllers.UserController;
import com.example.autoecole.models.Global;
import com.example.autoecole.repositories.UserRepository;
import com.example.autoecole.services.UserService;
import com.example.autoecole.tools.DataSourceProvider;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.Objects;

public class dashboard_etudiant
{
    UserController userController;
    UserRepository userRepository;
    UserService userService;

    EleveController eleveController;

    LeconController leconController;

    DataSourceProvider connexionBDD;

    private int idEtudiant;

    Alert a;
    @javafx.fxml.FXML
    private Button ap_cours_eleve;
    @javafx.fxml.FXML
    private Button ap_planning_eleve;
    @javafx.fxml.FXML
    private AnchorPane ap_planning_eleve2;
    @javafx.fxml.FXML
    private AnchorPane ap_cours_eleve2;
    @javafx.fxml.FXML
    private Button btn_param_eleve;
    @javafx.fxml.FXML
    private Button ap_dashboard_eleve;
    @javafx.fxml.FXML
    private AnchorPane ap_dashboard_eleve2;
    @javafx.fxml.FXML
    private Label LabNomEtu;
    @javafx.fxml.FXML
    private Label labnextdate;

    @javafx.fxml.FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        try {
            connexionBDD = new DataSourceProvider();
            userController = new UserController();
            userRepository = new UserRepository();
            userService = new UserService();
            eleveController = new EleveController();
            leconController = new LeconController();
            a = new Alert(Alert.AlertType.ERROR);
            // Afficher le prenom de l'eleve
            LabNomEtu.setText(Global.currentEleve.getPrenom());
            // On affiche la Date de la prochaine leçon si elle existe
            if (leconController.nextLeconEleve(Global.currentEleve.getCode()) != null) {
                labnextdate.setText(leconController.nextLeconEleve(Global.currentEleve.getCode()));
            }
            // Sinon on affiche un message d'erreur
            else  {
                labnextdate.setText("Aucune leçon prévue");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Deprecated
    public void OnClicked_deconnexion_eleve(Event event) {
    }

    @javafx.fxml.FXML
    public void Onclicked_dashboard_eleve(Event event) {
    }

    @javafx.fxml.FXML
    public void Onclicked_cours_eleve(Event event) {
    }

    @javafx.fxml.FXML
    public void Onclicked_parametre_eleve(Event event) {
    }

    @javafx.fxml.FXML
    public void Onclicked_planning_eleve(Event event) {
    }

    public void connexionpaneclicked(MouseEvent mouseEvent) {
    }

    public void onInscriptionSubmitButtonClicked(MouseEvent mouseEvent) {
    }
}
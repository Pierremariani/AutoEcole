package com.example.autoecole;

import com.example.autoecole.controllers.CategorieController;
import com.example.autoecole.controllers.EleveController;
import com.example.autoecole.controllers.LeconController;
import com.example.autoecole.controllers.UserController;
import com.example.autoecole.models.Eleve;
import com.example.autoecole.models.Global;
import com.example.autoecole.models.Moniteur;
import com.example.autoecole.repositories.UserRepository;
import com.example.autoecole.services.UserService;
import com.example.autoecole.tools.DataSourceProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class dashboard_etudiant
{
    UserController userController;
    UserRepository userRepository;
    UserService userService;

    EleveController eleveController;

    CategorieController categorieController;

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
    private Label labtotallecon;
    @javafx.fxml.FXML
    private Label labresteapayer;
    @javafx.fxml.FXML
    private TableView<Moniteur> tvMoniteurs;
    @javafx.fxml.FXML
    private TableColumn<Moniteur, String> prenomColumn;
    @javafx.fxml.FXML
    private TableColumn<Moniteur, String> nomColumn;
    @javafx.fxml.FXML
    private Label labtotalheures;
    @javafx.fxml.FXML
    private Label labheuresrestantes;

    @javafx.fxml.FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        try {
            connexionBDD = new DataSourceProvider();
            userController = new UserController();
            userRepository = new UserRepository();
            userService = new UserService();
            eleveController = new EleveController();
            leconController = new LeconController();
            categorieController = new CategorieController();
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
            // On affiche le prix total de toutes les leçons
            labtotallecon.setText(getTotalLeconPrice(Global.currentEleve.getCode()));
            //On affiche le prix total qu'il reste à payer
            labresteapayer.setText(getNonPayer(Global.currentEleve.getCode()));
            // Création TableView Moniteur
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            prenomColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
            ObservableList<Moniteur> moniteurs = FXCollections.observableArrayList(eleveController.getAllMoniteurByEleve(Global.currentEleve.getCode()));
            tvMoniteurs.setItems(moniteurs);
            labtotalheures.setText(eleveController.getAllHeures(Global.currentEleve.getCode())+" heures ");
            labheuresrestantes.setText(leconController.getAllHoursToDo(Global.currentEleve.getCode())+" heures ");
            for (int i = 0; i < categorieController.getAllCategorieEleve(Global.currentEleve.getCode()).size();i++){
                System.out.println(categorieController.getCategorie(categorieController.getAllCategorieEleve(Global.currentEleve.getCode()).get(i)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTotalLeconPrice(int CodeEleve) throws SQLException {
        int total = 0;
        for (int i = 0; i < leconController.getAllLeconByEleve(CodeEleve).size();i++) {
            total+= 55*leconController.getAllLeconByEleve(CodeEleve).get(i).getDuree();
        }
        return String.valueOf(total)+ "€";
    }

    public String getNonPayer(int CodeEleve) throws SQLException {
        int total = 0;
        for (int i = 0; i < leconController.getAllLeconByEleve(CodeEleve).size();i++) {
            if (!leconController.getAllLeconByEleve(CodeEleve).get(i).isReglee()) {
                total += 55*leconController.getAllLeconByEleve(CodeEleve).get(i).getDuree();
            }
        }
        return String.valueOf(total)+ "€";
    }



    @javafx.fxml.FXML
    public void OnClicked_deconnexion_eleve(Event event) throws IOException {
        Global.currentEleve = new Eleve();
        Global.idUser = 9999;
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Start & go Connexion / Inscription");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void Onclicked_dashboard_eleve(Event event) {
    }

    @javafx.fxml.FXML
    public void Onclicked_cours_eleve(Event event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cours_etudiant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Start & go Prise de leçon");
        stage.setScene(scene);
        stage.show();
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
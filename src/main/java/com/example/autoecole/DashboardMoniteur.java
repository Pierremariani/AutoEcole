package com.example.autoecole;

import com.example.autoecole.controllers.*;
import com.example.autoecole.models.*;
import com.example.autoecole.repositories.UserRepository;
import com.example.autoecole.services.UserService;
import com.example.autoecole.tools.DataSourceProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardMoniteur implements Initializable {


    UserController userController;
    UserRepository userRepository;
    UserService userService;

    EleveController eleveController;

    CategorieController categorieController;

    LeconController leconController;

    VehiculeController vehiculeController;

    LicenceController licenceController;

    DataSourceProvider connexionBDD;

    @javafx.fxml.FXML
    private TableColumn columnheuremoni;
    @javafx.fxml.FXML
    private TableColumn columndureemoni;
    @javafx.fxml.FXML
    private TableView tvmonilecons;
    @javafx.fxml.FXML
    private TableColumn columndatemoni;
    @javafx.fxml.FXML
    private Label labnbtrimestre;
    @javafx.fxml.FXML
    private Label labnbsemaine;
    @javafx.fxml.FXML
    private Label labnbmois;
    @javafx.fxml.FXML
    private Label labnbjours;
    @javafx.fxml.FXML
    private Label labbenefsemaine;
    @javafx.fxml.FXML
    private Label labbenefjour;
    @javafx.fxml.FXML
    private Label labbenefmois;
    @javafx.fxml.FXML
    private Label labbeneftrimestre;
    @javafx.fxml.FXML
    private Button btnlicence;
    @javafx.fxml.FXML
    private ComboBox cbolicence;

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
            licenceController = new LicenceController();

            ObservableList<Lecon> lecons = FXCollections.observableArrayList(leconController.getAllLeconComingByMoniteur(Global.currentMoniteur.getCode()));
            columndatemoni.setCellValueFactory(new PropertyValueFactory<>("Date"));
            columnheuremoni.setCellValueFactory(new PropertyValueFactory<>("Heure"));
            columndureemoni.setCellValueFactory(new PropertyValueFactory<>("Duree"));
            tvmonilecons.setItems(lecons);

            labnbjours.setText(String.valueOf(leconController.nbleconjour(Global.currentMoniteur.getCode())));
            labnbsemaine.setText(String.valueOf(leconController.nbleconsemaine(Global.currentMoniteur.getCode())));
            labnbmois.setText(String.valueOf(leconController.nbleconmois(Global.currentMoniteur.getCode())));
            labnbtrimestre.setText(String.valueOf(leconController.nblecontrimestre(Global.currentMoniteur.getCode())));
            labbenefjour.setText(getBenefJour());
            labbenefsemaine.setText(getBenefSemaine());
            labbenefmois.setText(getBenefMois());
            labbeneftrimestre.setText(getBenefTrimestre());

            cbolicence.setItems(FXCollections.observableArrayList(licenceController.getLicenceManquante(Global.currentMoniteur.getCode())));
            cbolicence.getSelectionModel().selectFirst();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public String getBenefJour() throws SQLException {
        double total = 0;
        for (int i = 0; i < leconController.getAllLeconComingByMoniteurjour(Global.currentMoniteur.getCode()).size();i++) {
            total+= vehiculeController.getPrixbyImmatriculation(leconController.getAllLeconComingByMoniteurjour(Global.currentMoniteur.getCode()).get(i).getImmatriculation())*leconController.getAllLeconComingByMoniteurjour(Global.currentMoniteur.getCode()).get(i).getDuree();
        }
        return String.format("%.2f", total)+ "€";
    }

    public String getBenefSemaine() throws SQLException {
        double total = 0;
        for (int i = 0; i < leconController.getAllLeconComingByMoniteursemaine(Global.currentMoniteur.getCode()).size();i++) {
            total+= vehiculeController.getPrixbyImmatriculation(leconController.getAllLeconComingByMoniteursemaine(Global.currentMoniteur.getCode()).get(i).getImmatriculation())*leconController.getAllLeconComingByMoniteursemaine(Global.currentMoniteur.getCode()).get(i).getDuree();
        }
        return String.format("%.2f", total)+ "€";
    }

    public String getBenefMois() throws SQLException {
        double total = 0;
        for (int i = 0; i < leconController.getAllLeconComingByMoniteurmois(Global.currentMoniteur.getCode()).size();i++) {
            total+= vehiculeController.getPrixbyImmatriculation(leconController.getAllLeconComingByMoniteurmois(Global.currentMoniteur.getCode()).get(i).getImmatriculation())*leconController.getAllLeconComingByMoniteurmois(Global.currentMoniteur.getCode()).get(i).getDuree();
        }
        return String.format("%.2f", total)+ "€";
    }

    public String getBenefTrimestre() throws SQLException {
        double total = 0;
        for (int i = 0; i < leconController.getAllLeconComingByMoniteurtrimestre(Global.currentMoniteur.getCode()).size();i++) {
            total+= vehiculeController.getPrixbyImmatriculation(leconController.getAllLeconComingByMoniteurtrimestre(Global.currentMoniteur.getCode()).get(i).getImmatriculation())*leconController.getAllLeconComingByMoniteurtrimestre(Global.currentMoniteur.getCode()).get(i).getDuree();
        }
        return String.format("%.2f", total)+ "€";
    }

    @javafx.fxml.FXML
    public void decomoniclicked(ActionEvent actionEvent) throws IOException {
        Global.currentMoniteur = new Moniteur();
        Global.idUser = 9999;
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Start & go Connexion / Inscription");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void onaddlicenceclicked(ActionEvent actionEvent) throws SQLException {
        Licence l = (Licence) cbolicence.getSelectionModel().getSelectedItem();
        licenceController.addLicence(Global.currentMoniteur.getCode(),l.getCodeCategorie(), licenceController.GenerateLicenceCode());
    }

    @javafx.fxml.FXML
    public void onparamclicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("parametre_moni.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Start & go Parametre");
        stage.setScene(scene);
        stage.show();
    }
}

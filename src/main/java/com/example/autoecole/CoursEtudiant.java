package com.example.autoecole;

import com.example.autoecole.controllers.*;
import com.example.autoecole.models.Categorie;
import com.example.autoecole.models.Global;
import com.example.autoecole.models.Moniteur;
import com.example.autoecole.repositories.UserRepository;
import com.example.autoecole.services.UserService;
import com.example.autoecole.tools.DataSourceProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CoursEtudiant implements Initializable {

    Alert a;

    UserController userController;
    UserRepository userRepository;
    UserService userService;

    EleveController eleveController;

    CategorieController categorieController;

    LeconController leconController;

    MoniteurController moniteurController;

    VehiculeController vehiculeController;

    DataSourceProvider connexionBDD;
    @javafx.fxml.FXML
    private ComboBox cboforfait;
    @javafx.fxml.FXML
    private ComboBox cboenseignant;
    @javafx.fxml.FXML
    private DatePicker datereserve;
    @javafx.fxml.FXML
    private ComboBox cbohours;
    @javafx.fxml.FXML
    private ComboBox cbovehicule;
    @javafx.fxml.FXML
    private ComboBox cbodureetakelecon;
    @javafx.fxml.FXML
    private Label labenseignant;
    @javafx.fxml.FXML
    private Button btnreserve;
    @javafx.fxml.FXML
    private Label labvehicule;


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
            a = new Alert(Alert.AlertType.ERROR);
            int h = 8;
            int i = 0;
            String s;
            ArrayList<String> horaires = new ArrayList<>();
            while (h < 18) {
                i+=1;
                if (i % 2  == 0 && i != 0) {
                    h+=1;
                    s=h+":00:00";
                }
                else {
                    s=h+":30:00";
                }
                horaires.add(s);
            }
            cbohours.setItems(FXCollections.observableArrayList(horaires));
            cbohours.getSelectionModel().selectFirst();

            ArrayList<String> durees = new ArrayList<>();
            durees.add("1");
            durees.add("2");
            cbodureetakelecon.setItems(FXCollections.observableArrayList(durees));
            cbodureetakelecon.getSelectionModel().selectFirst();


            cboforfait.setItems(FXCollections.observableArrayList(categorieController.getAll()));
            cboforfait.getSelectionModel().selectFirst();
        }catch (
        SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onRechercherClicked(Event event) throws SQLException {
        Categorie c = (Categorie) cboforfait.getSelectionModel().getSelectedItem();
        int codeSelect = c.getCode();
        if(datereserve.getValue() == null) {
            a.setTitle("Erreur de saisie");
            a.setHeaderText(null);
            a.setContentText("Veuillez sélectionner une date");
            a.showAndWait();
        }
        else {
            labenseignant.setDisable(false);
            labvehicule.setDisable(false);
            cbovehicule.setDisable(false);
            cboenseignant.setDisable(false);
            btnreserve.setDisable(false);
            updateMoniteur(codeSelect);
        }
    }

    public void updateMoniteur(int codeSelect) throws SQLException {
        cboenseignant.setItems(FXCollections.observableArrayList(moniteurController.getMoniteur(codeSelect)));
        cboenseignant.getSelectionModel().selectFirst();
        cbovehicule.setItems(FXCollections.observableArrayList(vehiculeController.getByCodeCategorie(codeSelect,String.valueOf(datereserve.getValue()),String.valueOf(cbohours.getSelectionModel().getSelectedItem()), Integer.parseInt(String.valueOf(cbodureetakelecon.getSelectionModel().getSelectedItem())))));
        cbovehicule.getSelectionModel().selectFirst();
    }

    @javafx.fxml.FXML
    public void ReservedClicked(Event event) throws SQLException {

        if(datereserve.getValue() == null) {
            a.setTitle("Erreur de saisie");
            a.setHeaderText(null);
            a.setContentText("Veuillez remplir tous les champs");
            a.showAndWait();
        }
        else if (leconController.isDateAvailable(String.valueOf(datereserve.getValue()),String.valueOf(cbohours.getSelectionModel().getSelectedItem()))) {
            Moniteur m = (Moniteur) cboenseignant.getSelectionModel().getSelectedItem();
            int codeMoniteur = m.getCode();
            leconController.create(leconController.GenerateCodeLecon(), String.valueOf(datereserve.getValue()),
                    String.valueOf(cbohours.getSelectionModel().getSelectedItem()),
                    codeMoniteur,
                    Global.currentEleve.getCode(),
                    "567 EF 21",
                    false,
                    Integer.parseInt(String.valueOf(cbodureetakelecon.getSelectionModel().getSelectedItem())));
        }
        else {
            a.setTitle("Erreur disponibilité");
            a.setHeaderText(null);
            a.setContentText("Date ou heure déjà réservé");
            a.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void BackClicked(Event event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard_etudiant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Start & go Dashboard Eleve");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void dateclicked(ActionEvent actionEvent) {
    }
}

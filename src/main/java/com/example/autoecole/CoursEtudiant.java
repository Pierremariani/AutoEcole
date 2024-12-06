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
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
            Categorie c = (Categorie) cboforfait.getSelectionModel().getSelectedItem();
            int codeSelect = c.getCode();
            updateMoniteur(codeSelect);

            System.out.println(String.valueOf(cbohours.getSelectionModel().getSelectedItem()));

        }catch (
        SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void onModifierClicked(Event event) throws SQLException {
        Categorie c = (Categorie) cboforfait.getSelectionModel().getSelectedItem();
        int codeSelect = c.getCode();
        updateMoniteur(codeSelect);
    }

    public void updateMoniteur(int codeSelect) throws SQLException {
        cboenseignant.setItems(FXCollections.observableArrayList(moniteurController.getMoniteur(codeSelect)));
        cboenseignant.getSelectionModel().selectFirst();
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
}

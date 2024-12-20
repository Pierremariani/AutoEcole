package com.example.autoecole.controllers;

import com.example.autoecole.models.Lecon;
import com.example.autoecole.services.EleveService;
import com.example.autoecole.services.LeconService;

import java.sql.SQLException;
import java.util.ArrayList;

public class LeconController {

    private LeconService leconService;

    public LeconController() {
        leconService = new LeconService();
    }

    public ArrayList<Lecon> getAllLeconByEleve(int codeEleve) throws SQLException {
        return leconService.getAllLeconByEleve(codeEleve);
    }

    public String nextLeconEleve(int codeEleve) throws SQLException {
        return leconService.nextLeconEleve(codeEleve);
    }

    public void create(int CodeLecon, String Date, String Heure, int CodeMoniteur, int CodeEleve, String Immatriculation, boolean Reglee, int duree) throws SQLException {
        leconService.create(CodeLecon, Date, Heure, CodeMoniteur, CodeEleve, Immatriculation, Reglee, duree);
    }

    public int getAllHoursToDo(int CodeEleve) throws SQLException {
        return leconService.getAllHoursToDo(CodeEleve);
    }

    public boolean isDateAvailable(String Date) throws SQLException {
        return leconService.isDateAvailable(Date);
    }

    public int GenerateCodeLecon() throws SQLException {
        return leconService.GenerateCodeLecon();
    }

    public ArrayList<Lecon> getAllLeconComingByEleve(int codeEleve) throws SQLException {
        return  leconService.getAllLeconComingByEleve(codeEleve);
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteur(int codeMoniteur) throws SQLException {
        return leconService.getAllLeconComingByMoniteur(codeMoniteur);
    }

    public int nbleconjour(int CodeMoniteur) throws SQLException {
        return leconService.nbleconjour(CodeMoniteur);
    }

    public int nbleconsemaine(int CodeMoniteur) throws SQLException {
        return  leconService.nbleconsemaine(CodeMoniteur);
    }

    public int nbleconmois(int CodeMoniteur) throws SQLException {
        return leconService.nbleconmois(CodeMoniteur);
    }

    public int nblecontrimestre(int CodeMoniteur) throws SQLException {
        return  leconService.nblecontrimestre(CodeMoniteur);
    }
    public ArrayList<Lecon> getAllLeconComingByMoniteurtrimestre(int codeMoniteur) throws SQLException {
        return leconService.getAllLeconComingByMoniteurtrimestre(codeMoniteur);
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteurjour(int codeMoniteur) throws SQLException {
        return leconService.getAllLeconComingByMoniteurjour(codeMoniteur);
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteursemaine(int codeMoniteur) throws SQLException {
        return leconService.getAllLeconComingByMoniteursemaine(codeMoniteur);
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteurmois(int codeMoniteur) throws SQLException {
        return leconService.getAllLeconComingByMoniteurmois(codeMoniteur);
    }

}

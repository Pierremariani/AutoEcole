package com.example.autoecole.services;

import com.example.autoecole.models.Lecon;
import com.example.autoecole.repositories.EleveRepository;
import com.example.autoecole.repositories.LeconRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class LeconService {

    private LeconRepository leconRepository;

    public LeconService() {
        leconRepository = new LeconRepository();
    }

    public ArrayList<Lecon> getAllLeconByEleve(int codeEleve) throws SQLException {
        return leconRepository.getAllLeconByEleve(codeEleve);
    }

    public String nextLeconEleve(int codeEleve) throws SQLException {
        return leconRepository.nextLeconEleve(codeEleve);
    }

    public void create(int CodeLecon, String Date, String Heure, int CodeMoniteur, int CodeEleve, String Immatriculation, boolean Reglee, int duree) throws SQLException {
        Lecon lecon = new Lecon(CodeLecon, Heure, CodeMoniteur, CodeEleve, Reglee, Date, Immatriculation, duree);
        leconRepository.create(lecon);
    }

    public int getAllHoursToDo(int CodeEleve) throws SQLException {
        return leconRepository.getAllHoursToDo(CodeEleve);
    }

    public boolean isDateAvailable(String Date,String horaires) throws SQLException {
        return leconRepository.isDateAvailable(Date,horaires);
    }

    public int GenerateCodeLecon() throws SQLException {
        return leconRepository.GenerateCodeLecon();
    }

    public ArrayList<Lecon> getAllLeconComingByEleve(int codeEleve) throws SQLException {
        return  leconRepository.getAllLeconComingByEleve(codeEleve);
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteur(int codeMoniteur) throws SQLException {
        return leconRepository.getAllLeconComingByMoniteur(codeMoniteur);
    }

    public int nbleconjour(int CodeMoniteur) throws SQLException {
        return leconRepository.nbleconjour(CodeMoniteur);
    }

    public int nbleconsemaine(int CodeMoniteur) throws SQLException {
        return  leconRepository.nbleconsemaine(CodeMoniteur);
    }

    public int nbleconmois(int CodeMoniteur) throws SQLException {
        return leconRepository.nbleconmois(CodeMoniteur);
    }

    public int nblecontrimestre(int CodeMoniteur) throws SQLException {
        return  leconRepository.nblecontrimestre(CodeMoniteur);
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteurtrimestre(int codeMoniteur) throws SQLException {
        return leconRepository.getAllLeconComingByMoniteurtrimestre(codeMoniteur);
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteurjour(int codeMoniteur) throws SQLException {
        return leconRepository.getAllLeconComingByMoniteurjour(codeMoniteur);
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteursemaine(int codeMoniteur) throws SQLException {
        return leconRepository.getAllLeconComingByMoniteursemaine(codeMoniteur);
    }

    public ArrayList<Lecon> getAllLeconComingByMoniteurmois(int codeMoniteur) throws SQLException {
        return leconRepository.getAllLeconComingByMoniteurmois(codeMoniteur);
    }
}

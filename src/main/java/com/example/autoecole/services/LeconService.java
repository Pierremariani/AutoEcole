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

    public ArrayList<Lecon> getAllLeconByEleve(int codeEleve)throws SQLException {
        return leconRepository.getAllLeconByEleve(codeEleve);
    }

    public String nextLeconEleve (int codeEleve) throws SQLException {
        return leconRepository.nextLeconEleve(codeEleve);
    }
}

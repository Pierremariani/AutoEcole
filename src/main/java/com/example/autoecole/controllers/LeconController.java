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

    public ArrayList<Lecon> getAllLeconByEleve(int codeEleve)throws SQLException {
        return leconService.getAllLeconByEleve(codeEleve);
    }

    public String nextLeconEleve (int codeEleve) throws SQLException {
        return leconService.nextLeconEleve(codeEleve);
    }

}

package com.example.autoecole.controllers;

import com.example.autoecole.models.Licence;
import com.example.autoecole.services.LeconService;
import com.example.autoecole.services.LicenceService;

import java.sql.SQLException;
import java.util.ArrayList;

public class LicenceController {

    private LicenceService licenceService;

    public LicenceController() {
        licenceService = new LicenceService();
    }

    public int GenerateLicenceCode() throws SQLException {
        return licenceService.GenerateLicenceCode();
    }

    public void addLicence(int CodeMoniteur,int CodeCategorie,int CodeLicence) throws SQLException {
        licenceService.addLicence(CodeMoniteur,CodeCategorie,CodeLicence);
    }

    public ArrayList<Licence> getLicenceManquante(int CodeMoniteur) throws SQLException {
        return licenceService.getLicenceManquante(CodeMoniteur);
    }
}

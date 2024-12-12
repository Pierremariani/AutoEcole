package com.example.autoecole.services;

import com.example.autoecole.models.Licence;
import com.example.autoecole.repositories.LeconRepository;
import com.example.autoecole.repositories.licenceRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class LicenceService {

    private licenceRepository licenceRepository;

    public LicenceService() {
        licenceRepository = new licenceRepository();
    }
    public int GenerateLicenceCode() throws SQLException {
        return licenceRepository.GenerateLicenceCode();
    }

    public void addLicence(int CodeMoniteur,int CodeCategorie,int CodeLicence) throws SQLException {
        licenceRepository.addLicence(CodeMoniteur,CodeCategorie,CodeLicence);
    }

    public ArrayList<Licence> getLicenceManquante(int CodeMoniteur) throws SQLException {
        return licenceRepository.getLicenceManquante(CodeMoniteur);
    }
}

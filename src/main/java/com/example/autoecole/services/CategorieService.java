package com.example.autoecole.services;

import com.example.autoecole.models.Categorie;
import com.example.autoecole.repositories.CategorieRepository;
import com.example.autoecole.repositories.EleveRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieService {

    private CategorieRepository categorieRepository;
    public CategorieService() {
        categorieRepository = new CategorieRepository();
    }

    public  Categorie getCategorie(int CodeCategorie) throws SQLException {
        return categorieRepository.getCategorie(CodeCategorie);
    }

    public ArrayList<Integer> getAllCategorieEleve(int CodeEleve) throws SQLException {
        return categorieRepository.getAllCategorieEleve(CodeEleve);
    }

    public ArrayList<Categorie> getAll() throws SQLException {
        return categorieRepository.getAll();
    }

}

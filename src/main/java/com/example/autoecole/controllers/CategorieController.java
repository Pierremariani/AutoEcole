package com.example.autoecole.controllers;

import com.example.autoecole.models.Categorie;
import com.example.autoecole.services.CategorieService;
import com.example.autoecole.services.EleveService;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieController {

    private CategorieService categorieService;

    public CategorieController() {
        categorieService = new CategorieService();
    }

    public Categorie getCategorie(int CodeCategorie) throws SQLException {
        return categorieService.getCategorie(CodeCategorie);
    }

    public ArrayList<Integer> getAllCategorieEleve(int CodeEleve) throws SQLException {
        return categorieService.getAllCategorieEleve(CodeEleve);
    }

    public ArrayList<Categorie> getAll() throws SQLException {
        return categorieService.getAll();
    }
}

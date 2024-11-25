package com.example.autoecole.repositories;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepositoryInterface<T,ID>
{
    ArrayList<T> getAll() throws SQLException;

    void create(T t) throws SQLException;

}

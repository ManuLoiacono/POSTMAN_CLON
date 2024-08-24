package org.example.Repository;

import org.example.exceptions.DAOException;

import java.util.List;

public interface ICrudRepository {
    public void guardar(Object o) throws DAOException;
    public void eliminar(int id) throws DAOException;
    public void modificar(Object o) throws DAOException;
    public Object encontrarPorId(int id) throws DAOException;
    public List<Object> listarTodos() throws DAOException;


}

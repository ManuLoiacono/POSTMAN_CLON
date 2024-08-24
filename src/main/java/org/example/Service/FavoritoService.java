package org.example.Service;

import org.example.Clases.Favorito;
import org.example.Repository.ICrudRepository;
import org.example.exceptions.DAOException;
import org.example.exceptions.ServiceException;

import java.util.List;

public class FavoritoService {

    private ICrudRepository favoritoCRUD;

    public FavoritoService() {
        favoritoCRUD = new Favorito();
    }

    public void guardar(Favorito fav)throws ServiceException {

        try {
            favoritoCRUD.guardar(fav);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("FavoritoService.Guardar: " + e.getMessage());
        }

    }

    public void eliminar(int id)throws ServiceException{

        try {
            favoritoCRUD.eliminar(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("FavoritoService.Eliminar: " + e.getMessage());
        }

    }

    public void modificar(Favorito favorito) throws ServiceException{

        try {
            favoritoCRUD.modificar(favorito);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("FavoritoService.Modificar: " + e.getMessage());
        }
    }

    public Object recuperar(int id)throws ServiceException{

        try {
            return favoritoCRUD.encontrarPorId(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("FavoritoService.recuperar: " + e.getMessage());
        }
    }

    public List<Object> recuperarTodos()throws ServiceException{
        try {
            return favoritoCRUD.listarTodos();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("FavoritoService.recuperarTodos: " + e.getMessage());
        }
    }

}

package org.example.Clases;

import org.example.Repository.ICrudRepository;
import org.example.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Favorito implements ICrudRepository {


    private int idFav;
    private String nameFav;
    private String urlFav;
    private String methodFav;
    private String bodyFav;


    public Favorito() {
    }

    public Favorito(int idFav, String nameFav, String urlFav, String methodFav, String bodyFav) {
        this.idFav = idFav;
        this.nameFav = nameFav;
        this.urlFav = urlFav;
        this.methodFav = methodFav;
        this.bodyFav = bodyFav;
    }

    public int getIdFav() {
        return idFav;
    }

    public void setIdFav(int idFav) {
        this.idFav = idFav;
    }

    public String getNameFav() {
        return nameFav;
    }

    public void setNameFav(String nameFav) {
        this.nameFav = nameFav;
    }

    public String getUrlFav() {
        return urlFav;
    }

    public void setUrlFav(String urlFav) {
        this.urlFav = urlFav;
    }

    public String getMethodFav() {
        return methodFav;
    }

    public void setMethodFav(String methodFav) {
        this.methodFav = methodFav;
    }

    public String getBodyFav() {
        return bodyFav;
    }

    public void setBodyFav(String bodyFav) {
        this.bodyFav = bodyFav;
    }


    @Override
    public void guardar(Object o) throws DAOException {
        Favorito fav = (Favorito) o;

        //INSERTAR (INSERT)
        try{
            //1.-Levantar el driver de la base de datos
            Class.forName("org.h2.Driver");

            //2.- Conectar a la base de datos
            Connection connection = DriverManager.getConnection("jdbc:h2:~/TP3", "sa", "");

            //3.- Operar sobre la base de datos. Ya sea consultar o modificar datos
            Statement statement = connection.createStatement();

            //4.- Ingresar datos
            int cantRegistros = statement.executeUpdate("INSERT INTO FAVORITO VALUES(" + fav.getIdFav() + ", '" + fav.getNameFav() + "', '" + fav.getUrlFav() + "', '" + fav.getMethodFav() + "', '"  + fav.getBodyFav() + "')");


            //5.- Evaluar resultado
            if(cantRegistros > 0)
                System.out.println("Se ha guardado una nueva Request favorita");

            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("FavoritoDAO.Guardar: " + e.getMessage());
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("FavoritoDAO.Guardar: " + throwables.getMessage());
        }
    }

    @Override
    public void eliminar(int id) throws DAOException {
        //DELETE
        try {
            //1.- Levantar el driver
            Class.forName("org.h2.Driver");

            //2.- Crear una conextion
            Connection con4 = DriverManager.getConnection("jdbc:h2:~/TP3", "sa", "");

            //3.- Crear sentencia SQL
            Statement statement4 = con4.createStatement();

            //4.- Ejecutamos el SQL
            int cantRegistrosEliminados = statement4.executeUpdate("DELETE FROM FAVORITO WHERE ID=" + id);

            if(cantRegistrosEliminados > 0)
                System.out.println("Se ha eliminado el registro");

            con4.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("FavoritoDAO.Eliminar: " + e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("FavoritoDAO.Eliminar: " + throwables.getMessage());
        }
    }

    @Override
    public void modificar(Object o) throws DAOException {
        Favorito fav = (Favorito) o;

        //UPDATE
        //1.- Cargar el driver
        try {
            Class.forName("org.h2.Driver");

            //2.- Nos conectamos a la base de datos
            Connection con3 = DriverManager.getConnection("jdbc:h2:~/TP3", "sa","");

            //3.- Creamos una sentencia SQL
            Statement statement3 = con3.createStatement();

            //4.- Ejecutamos consulta
            int cantRegistros2 = statement3.executeUpdate("UPDATE FAVORITO SET NOMBRE = '" + fav.getNameFav() + "', URL = '" + fav.getUrlFav() + "', METODO = '" + fav.getMethodFav() + "', BODY = '" + fav.getBodyFav() + "' WHERE ID=" + fav.getIdFav());

            //5.-Evaluamos resultado
            if(cantRegistros2 > 0)
                System.out.println("Se modificó el FAVORITO");

            con3.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("FavoritoDAO.Modificar: " + e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("FavoritoDAO.Modificar: " + throwables.getMessage());
        }
    }

    @Override
    public Object encontrarPorId(int id) throws DAOException {
        Favorito fav = new Favorito();

        //Select *
        try {
            //1.- Levantar el drive
            Class.forName("org.h2.Driver");

            //2.- conectarnos a la base de datos
            Connection con5 = DriverManager.getConnection("jdbc:h2:~/TP3", "sa", "");

            //3.- Creamos una sentencia sql
            Statement statement5 = con5.createStatement();

            //4.- Ejecutamos la sentencia sql
            ResultSet resultSet2 = statement5.executeQuery("SELECT * FROM FAVORITO WHERE ID=" + id);


            while(resultSet2.next())
            {
                fav.setIdFav(resultSet2.getInt("ID"));
                fav.setNameFav(resultSet2.getString("NOMBRE"));
                fav.setUrlFav(resultSet2.getString("URL"));
                fav.setMethodFav(resultSet2.getString("METODO"));
                fav.setBodyFav(resultSet2.getString("BODY"));

            }
            con5.close();


        } catch (ClassNotFoundException e) {
            throw new DAOException("FavoritoDAO.Recuperar: " + e.getMessage());
        } catch (SQLException throwables) {
            throw new DAOException("FavoritoDAO.Recuperar: " + throwables.getMessage());
        }

        return fav;    }

    @Override
    public List<Object> listarTodos() throws DAOException {
        List<Object> favs = new ArrayList<Object>();

        //SELECT
        //1.- Levantar el driver
        try {
            Class.forName("org.h2.Driver");
            //2.- Conectarnos a la base de datos
            Connection con2 = null;

            con2 = DriverManager.getConnection("jdbc:h2:~/TP3", "sa", "");

            //3.- Crear una sentencia SQL
            Statement statement2 = con2.createStatement();
            //4.- Obtener los resultados de la consulta
            ResultSet resultSet = statement2.executeQuery("SELECT * FROM FAVORITO");
            //5.- EValuar resultado

            int id = 0;
            String nombre = "";
            String apellido = "";
            String tel = "";
            while(resultSet.next())
            {
                Favorito fav = new Favorito();
                fav.setIdFav(resultSet.getInt("ID"));
                fav.setNameFav(resultSet.getString("NOMBRE"));
                fav.setUrlFav(resultSet.getString("URL"));
                fav.setMethodFav(resultSet.getString("METODO"));
                fav.setBodyFav(resultSet.getString("BODY"));

                favs.add(fav);
            }

            con2.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("FavoritoDAO.RecuperarTodos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DAOException("FavoritoDAO.RecuperarTodos: " + e.getMessage());
        }

        return favs;
    }
}

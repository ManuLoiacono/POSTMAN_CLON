package org.example;

import com.sun.net.httpserver.Headers;
import org.apache.http.Header;
import org.example.Clases.Favorito;
import org.example.Clases.Request;
import org.example.Service.FavoritoService;
import org.example.exceptions.DAOException;
import org.example.exceptions.RequestException;
import org.example.exceptions.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Crear el marco (ventana) principal de la aplicación
        JFrame frame = new JFrame("CLON POSTMAN");
        // Configuramos el comportamiento de cierre de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Establecemos el tamaño de la ventana
        frame.setSize(1200, 900);

        // Crear un panel con un layout de GridBagLayout para una colocación flexible de los componentes
        JPanel panel = new JPanel(new GridBagLayout());
        // Crear un objeto GridBagConstraints para gestionar las restricciones de los componentes
        GridBagConstraints constraints = new GridBagConstraints();
        // Establecemos márgenes entre los componentes
        constraints.insets = new Insets(5, 5, 5, 5);



        JToolBar toolBar = new JToolBar();
        JButton main = new JButton("Inicio");
        JButton favoritosBar = new JButton("Favoritos");

        toolBar.add(main);
        toolBar.add(favoritosBar);

        frame.add(toolBar);




        // METODO

        // Crear una etiqueta para el METODO
        JLabel methodLabel = new JLabel("Metodo:");
        // Configuramos la posición de la etiqueta en la cuadrícula (columna 0, fila 2)
        constraints.gridx = 0;
        constraints.gridy = 0;
        // Añadir la etiqueta al panel con las restricciones establecidas
        panel.add(methodLabel, constraints);

        // Crear un combo box para seleccionar el género con las opciones "GET", "POST", "PUT" y "DELETE"
        String[] methodField = {"GET", "POST", "PUT", "DELETE"};
        JComboBox<String> methodComboBox = new JComboBox<>(methodField);
        // Configuramos la posición del combo box en la cuadrícula (columna 1, fila 2)
        constraints.gridx = 1;
        constraints.gridy = 0;
        // Añadir el combo box al panel con las restricciones establecidas
        panel.add(methodComboBox, constraints);



        //URL

        // Crear una etiqueta para la URL
        JLabel nameLabel = new JLabel("URL:");
        // Configuramos la posición de la etiqueta en la cuadrícula (columna 0, fila 0)
        constraints.gridx = 0;
        constraints.gridy = 1;
        // Añadir la etiqueta al panel con las restricciones establecidas
        panel.add(nameLabel, constraints);

        // Crear un campo de texto para la URL con una longitud de 20 columnas
        JTextField urlField = new JTextField(80);
        // Configuramos la posición del campo de texto en la cuadrícula (columna 1, fila 0)
        constraints.gridx = 1;
        constraints.gridy = 1;
        // Añadir el campo de texto al panel con las restricciones establecidas
        panel.add(urlField, constraints);


        //Header name

        // Crear una etiqueta para la URL
        JLabel headNameLabel = new JLabel("Header Name:");
        // Configuramos la posición de la etiqueta en la cuadrícula (columna 0, fila 0)
        constraints.gridx = 0;
        constraints.gridy = 2;
        // Añadir la etiqueta al panel con las restricciones establecidas
        panel.add(headNameLabel, constraints);

        // Crear un campo de texto para la URL con una longitud de 20 columnas
        JTextField headNameField = new JTextField(80);
        // Configuramos la posición del campo de texto en la cuadrícula (columna 1, fila 0)
        constraints.gridx = 1;
        constraints.gridy = 2;
        // Añadir el campo de texto al panel con las restricciones establecidas
        panel.add(headNameField, constraints);


        //Header value

        // Crear una etiqueta para la URL
        JLabel headValueLabel = new JLabel("Header Value:");
        // Configuramos la posición de la etiqueta en la cuadrícula (columna 0, fila 0)
        constraints.gridx = 0;
        constraints.gridy = 3;
        // Añadir la etiqueta al panel con las restricciones establecidas
        panel.add(headValueLabel, constraints);

        // Crear un campo de texto para la URL con una longitud de 20 columnas
        JTextField headValueField = new JTextField(80);
        // Configuramos la posición del campo de texto en la cuadrícula (columna 1, fila 0)
        constraints.gridx = 1;
        constraints.gridy = 3;
        // Añadir el campo de texto al panel con las restricciones establecidas
        panel.add(headValueField, constraints);


        // BODY

        // Crear una etiqueta para el body
        JLabel bodyLabel = new JLabel("Body:");
        // Configuramos la posición de la etiqueta en la cuadrícula (columna 0, fila 3)
        constraints.gridx = 0;
        constraints.gridy = 4;
        // Añadir la etiqueta al panel con las restricciones establecidas
        panel.add(bodyLabel, constraints);

        // Crear un área de texto para el body con un tamaño de 5 filas y 20 columnas
        JTextArea bodyArea = new JTextArea(5, 20);
        // Configuramos la posición del área de texto en la cuadrícula (columna 1, fila 3)
        constraints.gridx = 1;
        constraints.gridy = 4;
        // Establecer el área de texto para que ocupe dos columnas y llenar ambas con la opción BOTH
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        // Añadir el área de texto al panel dentro de un JScrollPane para permitir el desplazamiento
        panel.add(new JScrollPane(bodyArea), constraints);


        // BOTON SUBMIT

        // Crear un botón para enviar la información
        JButton submitButton = new JButton("Enviar");
        // Configuramos la posición del botón en la cuadrícula (columna 0, fila 4)
        constraints.gridx = 1;
        constraints.gridy = 5;
        // Restablecer la configuración de gridwidth y fill para este componente
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        // Añadir el botón al panel con las restricciones establecidas
        panel.add(submitButton, constraints);


        // BOTON GUARDAR REQUEST

        // Crear un botón para enviar la información
        JButton submitButtonGuardar = new JButton("Guardar Request");
        // Configuramos la posición del botón en la cuadrícula (columna 0, fila 4)
        constraints.gridx = 1;
        constraints.gridy = 6;
        // Restablecer la configuración de gridwidth y fill para este componente
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        // Añadir el botón al panel con las restricciones establecidas
        panel.add(submitButtonGuardar, constraints);


        // RESPONSE

        // Crear una etiqueta para la response
        JLabel responseLabel = new JLabel("Response:");
        // Configuramos la posición de la etiqueta en la cuadrícula (columna 0, fila 3)
        constraints.gridx = 0;
        constraints.gridy = 7;
        // Añadir la etiqueta al panel con las restricciones establecidas
        panel.add(responseLabel, constraints);

        // Crear un área de texto para el body con un tamaño de 5 filas y 20 columnas
        JTextArea responseArea = new JTextArea(20, 80);
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        // Configuramos la posición del área de texto en la cuadrícula (columna 1, fila 3)
        constraints.gridx = 1;
        constraints.gridy = 7;
        // Establecer el área de texto para que ocupe dos columnas y llenar ambas con la opción BOTH
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        // Añadir el área de texto al panel dentro de un JScrollPane para permitir el desplazamiento
        panel.add(new JScrollPane(responseArea), constraints);


        favoritosBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String lista = "";

                FavoritoService favoritoService = new FavoritoService();
                List<Object> os = null;
                try {
                    os = favoritoService.recuperarTodos();
                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }

                List<Favorito> favs = new ArrayList<>();

                for (Object o : os) {
                    favs.add((Favorito) o);
                }



                JFrame frame = new JFrame("Favoritos");
                frame.setSize(800, 500);

                JPanel panelFavs = new JPanel(new GridBagLayout());
                GridBagConstraints constraintsFavs = new GridBagConstraints();
                constraintsFavs.insets = new Insets(5, 5, 5, 5);


                // Lista de favoritos

                JLabel listaLabel = new JLabel("Listado:");
                constraintsFavs.gridx = 0;
                constraintsFavs.gridy = 0;
                panelFavs.add(listaLabel, constraintsFavs);

                JTextArea listaArea = new JTextArea(10, 50);
                listaArea.setLineWrap(true);
                listaArea.setWrapStyleWord(true);
                constraintsFavs.gridx = 1;
                constraintsFavs.gridy = 1;
                constraintsFavs.gridwidth = 2;
                constraintsFavs.fill = GridBagConstraints.BOTH;
                panelFavs.add(new JScrollPane(listaArea), constraintsFavs);


                for(Favorito favorito: favs){

                    if(favorito.getBodyFav().isEmpty()) {
                        lista += "\n\nID: " + favorito.getIdFav() + "\nNombre:" + favorito.getNameFav() + "\nMetodo: " + favorito.getMethodFav() + "\nURL: " + favorito.getUrlFav();
                    }else{
                        lista += "\n\nID: " + favorito.getIdFav() + "\nNombre:" + favorito.getNameFav() + "\nMetodo: " + favorito.getMethodFav() + "\nURL: " + favorito.getUrlFav() + "\nBody: " + favorito.getBodyFav();

                    }
                }

                listaArea.setText(lista);


                // BOTON Eliminar favorito

                JButton submitButtonEliminar = new JButton("Eliminar");
                constraintsFavs.gridx = 1;
                constraintsFavs.gridy = 2;
                constraintsFavs.gridwidth = 1;
                constraintsFavs.fill = GridBagConstraints.NONE;
                panelFavs.add(submitButtonEliminar, constraintsFavs);


                // BOTON modificar favorito

                JButton submitButtonModificar = new JButton("Modificar");
                constraintsFavs.gridx = 1;
                constraintsFavs.gridy = 3;
                constraintsFavs.gridwidth = 1;
                constraintsFavs.fill = GridBagConstraints.NONE;
                panelFavs.add(submitButtonModificar, constraintsFavs);

                submitButtonEliminar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        JFrame frame = new JFrame("Favoritos");
                        frame.setSize(500, 300);

                        JPanel panelDelete = new JPanel(new GridBagLayout());
                        GridBagConstraints constraintsDelete = new GridBagConstraints();
                        constraintsDelete.insets = new Insets(5, 5, 5, 5);


                        //ID

                        JLabel idLabel = new JLabel("ID:");
                        constraintsDelete.gridx = 0;
                        constraintsDelete.gridy = 1;
                        panelDelete.add(idLabel, constraintsDelete);

                        JTextField idField = new JTextField(20);
                        constraintsDelete.gridx = 1;
                        constraintsDelete.gridy = 1;
                        panelDelete.add(idField, constraintsDelete);


                        // BOTON Eliminar favorito

                        JButton submitButtonEliminar2 = new JButton("Eliminar");
                        constraintsDelete.gridx = 1;
                        constraintsDelete.gridy = 2;
                        constraintsDelete.gridwidth = 1;
                        constraintsDelete.fill = GridBagConstraints.NONE;
                        panelDelete.add(submitButtonEliminar2, constraintsDelete);



                        submitButtonEliminar2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String idText = idField.getText();
                                int id = Integer.parseInt(idText);

                                try {
                                    favoritoService.eliminar(id);
                                } catch (ServiceException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        });


                        frame.add(panelDelete);
                        frame.setVisible(true);

                    }
                });
                submitButtonModificar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = new JFrame("Favoritos");
                        frame.setSize(800, 800);

                        JPanel panelUpdate = new JPanel(new GridBagLayout());
                        GridBagConstraints constraintsUpdate = new GridBagConstraints();
                        constraintsUpdate.insets = new Insets(5, 5, 5, 5);


                        //ID

                        JLabel idLabel = new JLabel("ID:");
                        constraintsUpdate.gridx = 0;
                        constraintsUpdate.gridy = 1;
                        panelUpdate.add(idLabel, constraintsUpdate);

                        JTextField idField = new JTextField(20);
                        constraintsUpdate.gridx = 1;
                        constraintsUpdate.gridy = 1;
                        panelUpdate.add(idField, constraintsUpdate);

                        //Nombre

                        JLabel nameLabel = new JLabel("Nombre:");
                        constraintsUpdate.gridx = 0;
                        constraintsUpdate.gridy = 2;
                        panelUpdate.add(nameLabel, constraintsUpdate);

                        JTextField nameField = new JTextField(20);
                        constraintsUpdate.gridx = 1;
                        constraintsUpdate.gridy = 2;
                        panelUpdate.add(nameField, constraintsUpdate);


                        //URL

                        JLabel urlLabel = new JLabel("URL:");
                        constraintsUpdate.gridx = 0;
                        constraintsUpdate.gridy = 3;
                        panelUpdate.add(urlLabel, constraintsUpdate);

                        JTextField urlField = new JTextField(20);
                        constraintsUpdate.gridx = 1;
                        constraintsUpdate.gridy = 3;
                        panelUpdate.add(urlField, constraintsUpdate);


                        //Metodo

                        JLabel metLabel = new JLabel("Metodo:");
                        constraintsUpdate.gridx = 0;
                        constraintsUpdate.gridy = 4;
                        panelUpdate.add(metLabel, constraintsUpdate);

                        JTextField metField = new JTextField(20);
                        constraintsUpdate.gridx = 1;
                        constraintsUpdate.gridy = 4;
                        panelUpdate.add(metField, constraintsUpdate);


                        // BODY

                        JLabel bodyLabel = new JLabel("Body:");
                        constraintsUpdate.gridx = 0;
                        constraintsUpdate.gridy = 5;
                        panelUpdate.add(bodyLabel, constraintsUpdate);

                        JTextArea bodyArea = new JTextArea(5, 50);
                        constraintsUpdate.gridx = 1;
                        constraintsUpdate.gridy = 5;
                        constraintsUpdate.gridwidth = 2;
                        constraintsUpdate.fill = GridBagConstraints.BOTH;
                        panelUpdate.add(new JScrollPane(bodyArea), constraintsUpdate);


                        // BOTON Modificar favorito

                        JButton submitButtonModificar2 = new JButton("Modificar");
                        constraintsUpdate.gridx = 1;
                        constraintsUpdate.gridy = 6;
                        constraintsUpdate.gridwidth = 1;
                        constraintsUpdate.fill = GridBagConstraints.NONE;
                        panelUpdate.add(submitButtonModificar2, constraintsUpdate);


                        submitButtonModificar2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String idText = idField.getText();
                                int id = Integer.parseInt(idText);
                                String name = nameField.getText();
                                String url = urlField.getText();
                                String method = metField.getText();
                                String body = bodyArea.getText();

                                Favorito fav = new Favorito(id, name, url, method, body);

                                try {
                                    favoritoService.modificar(fav);
                                } catch (ServiceException ex) {
                                    throw new RuntimeException(ex);
                                }


                            }
                        });


                        frame.add(panelUpdate);
                        frame.setVisible(true);
                    }
                });




                frame.add(panelFavs);
                frame.setVisible(true);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores ingresados por el usuario
                String method = (String) methodComboBox.getSelectedItem();
                String url = urlField.getText();
                String body = bodyArea.getText();
                String headerName = headNameField.getText();
                String headerValue = headValueField.getText();
                Headers header = new Headers();


                // Verificar si todos los campos están llenos
                if (method.isEmpty() || url.isEmpty()) {
                    // Mostrar un mensaje de error si algún campo está vacío
                    JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Mostrar un mensaje con la información ingresada
                    JOptionPane.showMessageDialog(frame,
                            "Metodo: " + method + "\nURL: " + url + "\nBody: " + body + "\nHeaderName: " + headerName + "\nHeaderValue: " + headerValue, "Información Enviada", JOptionPane.INFORMATION_MESSAGE);

                    Random random = new Random();
                    int id = random.nextInt(Integer.MAX_VALUE);

                    Request req = new Request(id , url, headerName, headerValue,  method, body);

                    try {

                        System.out.println("Metodo: " + method + "\nURL: " + url + "\nBody: " + body + "\nHeaderName: " + headerName + "\nHeaderValue: " + headerValue);
                        String response = req.send();
                        responseArea.setText(response);


                    } catch (RequestException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        submitButtonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Crear el marco (ventana) principal de la aplicación
                JFrame frame = new JFrame("Guardar");
                // Establecemos el tamaño de la ventana
                frame.setSize(500, 300);

                // Crear un panel con un layout de GridBagLayout para una colocación flexible de los componentes
                JPanel panel2 = new JPanel(new GridBagLayout());
                // Crear un objeto GridBagConstraints para gestionar las restricciones de los componentes
                GridBagConstraints constraints2 = new GridBagConstraints();
                // Establecemos márgenes entre los componentes
                constraints2.insets = new Insets(5, 5, 5, 5);


                // NOMBRE

                // Crear una etiqueta para el NOMBRE
                JLabel nameLabel = new JLabel("Ingrese el nombre con el que quier guardar la Request:");
                // Configuramos la posición de la etiqueta en la cuadrícula (columna 0, fila 0)
                constraints2.gridx = 0;
                constraints2.gridy = 0;
                // Añadir la etiqueta al panel con las restricciones establecidas
                panel2.add(nameLabel, constraints2);

                // Crear un campo de texto para la URL con una longitud de 20 columnas
                JTextField nombreField = new JTextField(20);
                // Configuramos la posición del campo de texto en la cuadrícula (columna 1, fila 0)
                constraints2.gridx = 0;
                constraints2.gridy = 1;
                // Añadir el campo de texto al panel con las restricciones establecidas
                panel2.add(nombreField, constraints2);


                // BOTON OK

                // Crear un botón para enviar la información
                JButton submitButton3 = new JButton("OK");
                // Configuramos la posición del botón en la cuadrícula (columna 0, fila 4)
                constraints2.gridx = 0;
                constraints2.gridy = 2;
                // Restablecer la configuración de gridwidth y fill para este componente
                constraints2.gridwidth = 1;
                constraints2.fill = GridBagConstraints.NONE;
                // Añadir el botón al panel con las restricciones establecidas
                panel2.add(submitButton3, constraints2);


                submitButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String method = (String) methodComboBox.getSelectedItem();
                        String url = urlField.getText();
                        String body = bodyArea.getText();
                        String name = nombreField.getText();

                        Random random = new Random();
                        int id = random.nextInt(Integer.MAX_VALUE);

                        Favorito fav = new Favorito(id, name, url,  method, body);
                        Favorito favG = new Favorito();
                        try {

                            System.out.println("Metodo: " + method + "\nURL: " + url + "\nBody: " + body + "Información Enviada");
                            favG.guardar(fav);

                        } catch (DAOException ex) {
                            throw new RuntimeException(ex);
                        }


                    }
                });

                frame.add(panel2);
                frame.setVisible(true);
            }
        });



        frame.add(toolBar, BorderLayout.NORTH);
        // Añadir el panel al marco
        frame.add(panel);

        // Hacer visible el marco para que el usuario pueda interactuar con la aplicación
        frame.setVisible(true);
    }
}
package cargasMasivas;

import elementos.Libros;
import clases.Main;
import Interfaces.PantallaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class CargaMasivaLibros extends JFrame implements ActionListener {

    private JButton b1, b2;
    private JTextArea ta1;
    private JScrollPane scroll;

    //Para carga masiva
    Long ID = null;
    String titulo = null;
    String autor = null;
    Long esTipo = null;
    String tipo = null;
    Long copias = null;
    Long disponibles = null;
    Long ocupados = null;
    

    public CargaMasivaLibros() {
        Font font = new Font("SansSerif", Font.BOLD, 20);
        //TextArea
        ta1 = new JTextArea();
        ta1.setFont(font);
        scroll = new JScrollPane(ta1);
        scroll.setBounds(90, 50, 800, 800);
        this.add(scroll);
        //Botones
        b1 = new JButton("Cargar libros");
        b1.setBounds(700, 900, 200, 40);
        b1.setFont(font);
        b1.setBackground(new Color(211, 207, 199));
        b1.setLayout(null);
        b1.addActionListener(this);
        this.add(b1);

        b2 = new JButton("Regresar");
        b2.setBounds(60, 900, 200, 40);
        b2.setFont(font);
        b2.setBackground(new Color(211, 207, 199));
        b2.setLayout(null);
        b2.addActionListener(this);
        this.add(b2);

        //frame
        Color color = new Color(232, 167, 28);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(color);
        this.setLayout(null);
        this.setTitle("LOGIN BIBLIOTECA USAC");
        this.setBounds(100, 100, 1000, 1000);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b2) {
            try {
                PantallaPrincipal principal = new PantallaPrincipal();
            } catch (ParseException ex) {
                
            }
            this.dispose();
            
        }
        if (e.getSource() == b1) {

            String texto = ta1.getText();

            Object jsonObteto = JSONValue.parse(texto);
            JSONObject obteto = (JSONObject) jsonObteto;

            Object jsonarrayoLibro = obteto.get("Libros");
            JSONArray arrayobjetoLibro = (JSONArray) jsonarrayoLibro;

            for (Object objeto_inarray : arrayobjetoLibro) {
                JSONObject objeto_value = (JSONObject) objeto_inarray;
                titulo = (String) objeto_value.get("Titulo");
                //ID =  Integer.parseInt((Integer) objeto_value.get("ID"));
                ID = (Long) objeto_value.get("ID");
                autor = (String) objeto_value.get("Autor");
                //esTipo = Integer.parseInt((String) objeto_value.get("Tipo"));
                esTipo = (Long) objeto_value.get("Tipo");
                if(esTipo == 1l){
                    tipo = "Libro";
                }else if(esTipo == 2l){
                    tipo = "Revista";
                }else if(esTipo == 3l){
                    tipo = "Libro electronico";
                }
                copias = (Long) objeto_value.get("Copias");
                disponibles = (Long) objeto_value.get("Disponibles");
                ocupados = (Long) objeto_value.get("Ocupados");
                
                //Se crea un nuevo libro
                Libros nuevoLibro = new Libros(ID, titulo, autor, tipo, copias, disponibles, ocupados);
                //Agregar libro
                Main.agregarLibro(nuevoLibro);
                //Cargar tablero
                //Guardar que tiplo de libro es
                Main.tipoLibros(tipo);
                
            }          
            JOptionPane.showMessageDialog(this, "Se ha agregado la carga masiva de libros");
            ta1.setText("");
            Main.leerLibros();
            
        }
    }

}

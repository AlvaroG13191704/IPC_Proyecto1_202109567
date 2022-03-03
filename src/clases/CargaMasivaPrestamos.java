package clases;

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

public class CargaMasivaPrestamos extends JFrame implements ActionListener {

    private JButton b1, b2;
    private JTextArea ta1;
    private JScrollPane scroll;
    //Variables
    private String nombreUsuario = null;
    private String nombreLibro = null;
    private String fechaEntrega = null;
    private Long IDUsuario;
    private Long IDLibro;
   
    
    public CargaMasivaPrestamos() {
        Font font = new Font("SansSerif", Font.BOLD, 20);
        //TextArea
        ta1 = new JTextArea();
        ta1.setFont(font);
        scroll = new JScrollPane(ta1);
        scroll.setBounds(90, 50, 800, 800);
        this.add(scroll);
        //Botones
        b1 = new JButton("Cargar de prestamos");
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

            Object jsonarrayoPrestamo = obteto.get("Prestamos");
            JSONArray arrayobjetoPrestamo = (JSONArray) jsonarrayoPrestamo;

            for (Object objeto_inarray : arrayobjetoPrestamo) {
                JSONObject objeto_value = (JSONObject) objeto_inarray;
                IDUsuario = (Long) objeto_value.get("IDUsuario");
                IDLibro = (Long) objeto_value.get("IDLibro");
                fechaEntrega = (String) objeto_value.get("FechaEntrega");

                //Transformar ese Long a el nombre que pertenece el ID
                for (int i = 0; i < Main.cUsers; i++) {
                    if (Main.users[i].getiD().equals(IDUsuario)) {
                        nombreUsuario = Main.users[i].getUsuario();
                    }

                }
                for (int i = 0; i < Main.cLibros; i++) {
                    if (Main.libros[i].getID().equals(IDLibro)) {
                        nombreLibro = Main.libros[i].getTitulo();
                    }

                }
                
                //Se crea un nuevo prestamo
                Prestamos nuevoPrestamo = null;
                try {
                    nuevoPrestamo = new Prestamos(nombreUsuario, nombreLibro, fechaEntrega,PantallaPrincipal.verificarFecha(fechaEntrega));
                } catch (ParseException ex) {
                    
                }
                //Agregar libro
                Main.agregarPrestamo(nuevoPrestamo);
                try {
                    //Administrar disponibles y ocupados en libros
                    //Posible error----------------------
                    
                    if(PantallaPrincipal.verificarFecha(fechaEntrega) == "Entregado"){
                        Main.administrarPrestamosEntregado(nombreLibro);
                    } else if(PantallaPrincipal.verificarFecha(fechaEntrega) == "Prestado"){
                        Main.administrarPrestamosPrestado(nombreLibro);
                    }
                } catch (ParseException ex) {
                    
                }
            }
            JOptionPane.showMessageDialog(this, "Se ha agregado la carga masiva de prestamos");
            ta1.setText("");
            Main.leerPrestamo();

        }
    }
}

package cargasMasivas;


import elementos.Usuarios;
import clases.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class CargaMasivaUsuario extends JFrame implements ActionListener {

    private JButton b1, b2;
    private JTextArea ta1;
    private JScrollPane scroll;

    //Para carga masiva
    Long iD = null;
    String usuario = null;
    String password = null;
    String facultad = null;
    String carrera = null;
    Long tipo = null;

    public CargaMasivaUsuario() {
        Font font = new Font("SansSerif", Font.BOLD, 20);
        //TextArea
        ta1 = new JTextArea();
        ta1.setFont(font);
        scroll = new JScrollPane(ta1);
        scroll.setBounds(90, 50, 800, 800);
        this.add(scroll);
        //Botones
        b1 = new JButton("Cargar usuarios");
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
            Main.login.setVisible(true);
            this.dispose();
        }
        if (e.getSource() == b1) {

            String texto = ta1.getText();

            Object jsonObteto = JSONValue.parse(texto);
            JSONObject obteto = (JSONObject) jsonObteto;

            Object jsonarrayoUsuario = obteto.get("Usuarios");
            JSONArray arrayobjetoUsuario = (JSONArray) jsonarrayoUsuario;

            for (Object objeto_inarray : arrayobjetoUsuario) {
                JSONObject objeto_value = (JSONObject) objeto_inarray;
                iD = (Long) objeto_value.get("ID");
                usuario = (String) objeto_value.get("Usuario");
                password = (String) objeto_value.get("Password");
                facultad = (String) objeto_value.get("Facultad");
                carrera = (String) objeto_value.get("Carrera");
                tipo = (Long) objeto_value.get("Tipo");
                
                //Sumar los contadores
                if(tipo == 1l){
                    Main.ctipoAdmin ++;
                }else{
                    Main.ctipoEstudiante ++;
                }
                //Se crea un nuevo usuario
                Usuarios UsuariosMasivos = new Usuarios(iD, usuario, password, facultad, carrera, tipo);
                //Usuario repetido
                Main.agregarUsuario(UsuariosMasivos);

            }          
            JOptionPane.showMessageDialog(this, "Se ha agregado la carga masiva de usuarios");
            ta1.setText("");
            Main.leerUsuarios();

        }
    }

}

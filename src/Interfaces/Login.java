package Interfaces;

import cargasMasivas.CargaMasivaUsuario;
import clases.Main;
import elementos.Usuarios;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    Color color;
    Font font;
    //Objetos de la ventana
    private JLabel lb1, lb2;
    private JButton b1, b2, b3;
    private JTextField tf1, tf2;

    //Variables
    String nombre, pass;

    public Login() {
        font = new Font("SansSerif", Font.BOLD, 20);

        //TextField
        tf1 = new JTextField();
        tf2 = new JTextField();

        tf1.setBounds(220, 135, 200, 30);
        tf1.setFont(font);
        this.add(tf1);

        tf2.setBounds(220, 255, 200, 30);
        tf2.setFont(font);
        this.add(tf2);

        //Buttones
        b1 = new JButton("Carga masiva");
        b2 = new JButton("Login");

        b1.setBounds(350, 500, 200, 40);
        b1.setFont(font);
        b1.setBackground(new Color(211, 207, 199));
        b1.setLayout(null);
        b1.addActionListener(this);
        this.add(b1);

        b2.setBounds(400, 400, 100, 40);
        b2.setLayout(null);
        b2.setFont(font);
        b2.setBackground(new Color(211, 207, 199));
        b2.addActionListener(this);
        this.add(b2);

        //Labels
        lb1 = new JLabel("Usuario:");
        lb2 = new JLabel("Contraseña:");

        lb1.setLayout(null);
        lb1.setBounds(70, 100, 100, 100);
        lb1.setFont(font);
        this.add(lb1);

        lb2.setLayout(null);
        lb2.setBounds(70, 220, 200, 100);
        lb2.setFont(font);
        this.add(lb2);

        //Frame
        color = new Color(232, 167, 28);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(color);
        this.setLayout(null);
        this.setTitle("LOGIN BIBLIOTECA USAC");
        this.setBounds(0, 0, 600, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Boton login
        if (e.getSource() == b2) {

            nombre = tf1.getText();
            pass = tf2.getText();
            Usuarios logeado = Main.verificarUser(nombre, pass);
            boolean verificarTipoUser = Main.verificarUserTIpo(nombre);

            if (verificarTipoUser) {
                if (logeado != null) {
                    System.out.println("Usted es admin");
                    JOptionPane.showMessageDialog(this, logeado.getUsuario() + " esta registrado");
                    Main.usuarioLogin(logeado);
                    System.out.println("El usuario que esta usando el sistema ahora es: " + Main.adminNow[0].getUsuario());
                    try {
                        PantallaPrincipal principal = new PantallaPrincipal();
                    } catch (ParseException ex) {
                        
                    }
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario no registrado");
                }
            } else {
                System.out.println("Usted no es admin");
                JOptionPane.showMessageDialog(this, "Tipo de usuario no admitido");
                
            }

        }
        //Boton carga masiva
        if (e.getSource() == b1) {

            //leerArchivo();
            CargaMasivaUsuario cargaUsuario = new CargaMasivaUsuario();
            this.setVisible(false);
        }
    }

}

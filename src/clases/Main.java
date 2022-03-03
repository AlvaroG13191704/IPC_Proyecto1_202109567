package clases;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Main {

    //VENTANAS
    static Login login;

    //AGREGAR TODOS LOS ARREGLOS QUE VAMOS A UTILIZAR
    public static Usuarios[] adminNow = new Usuarios[1]; //Persona que ingreso al programa con login y contraseña
    
    public static Usuarios[] users = new Usuarios[50]; //Arreglo de usuarios
    public static Libros[] libros = new Libros[100]; //Arreglo de libros
    public static Prestamos[] prestamos = new Prestamos[200];//Arreglo de prestamos
    public static Reportes[] reportes = new Reportes[100];//Arreglo de reportes
    public static int cUsers = 0; //Contador User
    public static int cLibros = 0; //Contador libros
    public static int cPrestamos = 0;//Contador prestamos
    public static int cReportes = 0;//Contador reportes

    public static void main(String[] args) throws ParseException {

        //Ventana login
        login = new Login();
        //Agregando un admin
        Usuarios admin = new Usuarios(0l, "admin", "123");
        Libros libroInicial = new Libros(1l, "Mate Aplicada 1", "Alvaro", "Revista", 3l, 3l, 0l);
        Usuarios usuarioInicial = new Usuarios(1l, "Alvaro", "123", "Ingenieria", "Ciencias y Sistemas", 1l);
        Main.agregarLibro(libroInicial);
        Main.agregarUsuario(usuarioInicial);
        Main.agregarUsuario(admin);

        Main.leerUsuarios();
        Main.leerLibros();

        //PantallaPrincipal principal = new PantallaPrincipal();

    }

    //METODOS PARA MANEJAR LA INFORMACIÓN
    //Pueden ser static para acceder desde la clase
    //Agregar admin
    public static void usuarioLogin(Usuarios admin){
        adminNow[0] = admin;
    }
    //Agregar usuario
    public static void agregarUsuario(Usuarios usuario) {
        if (cUsers < users.length) {
            users[cUsers] = usuario;
            cUsers += 1;
        } else {
            JOptionPane.showMessageDialog(new Login(), "Se llego al limite");
        }

    }

    //Agregar libro
    public static void agregarLibro(Libros librosIngresados) {
        if (cLibros < libros.length) {
            libros[cLibros] = librosIngresados;
            cLibros += 1;
        } else {
            JOptionPane.showMessageDialog(new Login(), "Se llego al limite de libros");
        }
    }

    //Agregar prestamo
    public static void agregarPrestamo(Prestamos prestamosIngresados) {
        if (cPrestamos < prestamos.length) {
            prestamos[cPrestamos] = prestamosIngresados;
            cPrestamos += 1;
        }
    }
    //Agregar Reporte
    public static void agregarReporte(Reportes reportesIngresados){
        if(cReportes < reportes.length){
            reportes[cReportes] = reportesIngresados;
            cReportes +=1;
        }
    }

    //Leer usuario
    public static void leerUsuarios() {
        System.out.println("Usuarios en el sistema");
        for (int i = 0; i < cUsers; i++) {
            System.out.println((i + 1) + " Nombre: " + users[i].getUsuario() + " ID: " + users[i].getiD());
        }
    }

    //Leer libros
    public static void leerLibros() {
        System.out.println("Libros en el sistema: ");
        for (int i = 0; i < cLibros; i++) {
            System.out.println((i + 1) + " Nombre: " + libros[i].getTitulo() + " ID: " + libros[i].getID() + " Tipo: " + libros[i].getTipo());
        }
    }

    //Leer prestamo
    public static void leerPrestamo() {
        System.out.println("Prestamos en el sistema");
        for (int i = 0; i < cPrestamos; i++) {
            System.out.println("ID Usuario: " + prestamos[i].getUsuarioID() + " ID Libro: " + prestamos[i].getLibroID() + " Fecha de entrega: " + prestamos[i].getFechaEntrega());
        }
    }
    //Leer reportes
    public static void leerReportes(){
        System.out.println("Reportes en el sistema");
        for (int i = 0; i < cReportes; i++) {
            System.out.println("Fecha de generación:" + reportes[i].getFechaGeneracion() + " Usuario: " + reportes[i].getUsuario() + " Tipo de reporte: "+ reportes[i].getTipoReporte());
        }
        
    }

    //VerificarUsuario
    public static Usuarios verificarUser(String usuario, String pass) {
        for (int i = 0; i < cUsers; i++) {
            if (users[i].getUsuario().equals(usuario) && users[i].getPassword().equals(pass)) {
                return users[i];
            }
        }
        return null;
    }

    //Verificar Usuario Tipo
    public static boolean verificarUserTIpo(String tipo) {
        for (int i = 0; i < cUsers; i++) {
            if (users[i].getUsuario().equals(tipo)) {
                if (users[i].getTipo().equals(1l)) { //Tipo administrador
                    return true;
                }

            }
        }
        return false;

    }

    //Verificar que exista ID en libros y usuario
    //---------------
    public static boolean verificarPrestamo(Long IDUsuario, Long IDLibro) {
        boolean iduser = false;
        boolean idlibro = false;
        for (int i = 0; i < cUsers; i++) {
            if (users[i].getiD().equals(IDUsuario)) {
                iduser = true;

            }

        }
        for (int i = 0; i < cLibros; i++) {
            if (libros[i].getID().equals(IDLibro)) {
                idlibro = true;

            }

        }
        return iduser == true && idlibro == true;
    }

    //Administración de disponibles y ocupados, si es es prestado, se resta a disponibles y se suma a ocupados
    public static void administrarPrestamosPrestado(String prestamoLibro) {
        for (int i = 0; i < cLibros; i++) {
            if (libros[i].getTitulo().equals(prestamoLibro)) {
                //Se actualiza disponibles y ocupados
                libros[i].setDisponibles(libros[i].getDisponibles() - 1);
                libros[i].setOcupados(libros[i].getOcupados() + 1);
            }
        }
    }
    //Administración de disponibles y ocupados, si es es entregado, se le suma a disponibles y se le resta a ocupados
    public static void administrarPrestamosEntregado(String prestamoLibro){
        for (int i = 0; i < cLibros; i++) {
            if (libros[i].getTitulo().equals(prestamoLibro)) {
                //Se actualiza disponibles y ocupados
                libros[i].setDisponibles(libros[i].getDisponibles() + 1);
                libros[i].setOcupados(libros[i].getOcupados() - 1);
            }
        }
    }

    //Administrar que disponibles llegue a 0
    public static boolean verificarDisponibles() {
        for (int i = 0; i < Main.cLibros; i++) {
            if (Main.libros[i].getDisponibles().equals(0l)) {
                return true;
            }
        }
        return false;
    }
    //Administrar que ocupados no se pase de copias que hay
    public static boolean verificarOcupadosMax(){
        for (int i = 0; i < Main.cLibros; i++) {
            if(Main.libros[i].getOcupados() > Main.libros[i].getCopias()){
                return true;
            }
        }
        return false;
    }
    //Administrar que ocupados este en 0, porque sino no se puede entragr un libro si no esta ocupado
    public static boolean verificarOcupados() {
        for (int i = 0; i < Main.cLibros; i++) {
            if (Main.libros[i].getOcupados().equals(0l)) {
                return true;
            }
        }
        return false;
    }
    //Tabla de libros
    public static Object[][] tablaLibros() {
        int filas = Main.cLibros;
        Object[][] arregloTabla = new Object[filas][7];
        for (int i = 0; i < filas; i++) {
            arregloTabla[i][0] = Main.libros[i].getID();
            arregloTabla[i][1] = Main.libros[i].getTitulo();
            arregloTabla[i][2] = Main.libros[i].getAutor();
            arregloTabla[i][3] = Main.libros[i].getTipo();
            arregloTabla[i][4] = Main.libros[i].getCopias();
            arregloTabla[i][5] = Main.libros[i].getDisponibles();
            arregloTabla[i][6] = Main.libros[i].getOcupados();
        }
        return arregloTabla;
    }

    //Tabla de prestamos
    public static Object[][] tablaPrestamos() throws ParseException {
        int filas = Main.cPrestamos;
        Object[][] arregloTabla2 = new Object[filas][4];
        for (int i = 0; i < filas; i++) {
            arregloTabla2[i][0] = Main.prestamos[i].getUsuarioID();
            arregloTabla2[i][1] = Main.prestamos[i].getLibroID();
            arregloTabla2[i][2] = Main.prestamos[i].getFechaEntrega();
            arregloTabla2[i][3] = Main.prestamos[i].getStatus();
        }
        return arregloTabla2;
    }
    //Tabla de reportes
    public static Object[][] tablaReportes(){
        int filas = Main.cReportes;
        Object[][] arregloTabla3 = new Object[filas][3];
        for (int i = 0; i < filas; i++) {
            arregloTabla3[i][0] = Main.reportes[i].getFechaGeneracion();
            arregloTabla3[i][1] = Main.reportes[i].getUsuario();
            arregloTabla3[i][2] = Main.reportes[i].getTipoReporte();
        }
        return arregloTabla3;
    }
    

}

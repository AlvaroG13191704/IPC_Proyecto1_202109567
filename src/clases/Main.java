package clases;



import elementos.Libros;
import elementos.Prestamos;
import elementos.Reportes;
import elementos.Usuarios;
import Interfaces.Login;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Main {

    //VENTANAS
    static public Login login;

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
    
    //Contador para las graficas
    public static int ctipoAdmin = 0; 
    public static int ctipoEstudiante = 0;
    //Contadores por los 12 meses que hay 
    public static int enero = 0;
    public static int febrero = 0;
    public static int marzo = 0;
    public static int abril = 0;
    public static int mayo = 0;
    public static int junio = 0;
    public static int julio = 0;
    public static int agosto = 0;
    public static int septiembre = 0;
    public static int octubre = 0;
    public static int noviembre = 0;
    public static int diciembre = 0;
    //Contador por el tipo de libros 
    public static int ctipoLibros = 0;
    public static int ctipoRevista = 0;
    public static int ctipoLibroElec = 0;
    

    public static void main(String[] args) throws ParseException {

        //Ventana login
        login = new Login();
        //Agregando un admin
        //Usuarios admin = new Usuarios(0l, "admin", "123");
//        Libros libroInicial = new Libros(1l, "Mate Aplicada 1", "Alvaro", "Revista", 3l, 3l, 0l);
        //Usuarios usuarioInicial = new Usuarios(1l, "Alvaro", "123", "Administrador", "Administrador", 1l);
        //Main.agregarLibro(libroInicial);
        //Main.agregarUsuario(usuarioInicial);
        //Main.agregarUsuario(admin);

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
    //Metodo que crea los pdf de usuarios
    public static void PDFUsuarios(String fechaActual) throws DocumentException, FileNotFoundException{
        Document documento = new Document(PageSize.LETTER);
        
        OutputStream archivo = new FileOutputStream("E:\\ReportesProyecto1\\Reportes_Usuarios\\reporteUsuarios_"+fechaActual + ".pdf");
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        Paragraph p = new Paragraph();
        Paragraph p2 = new Paragraph();
        p.add("REPORTES BIBLIOTECA USAC");
        p.setAlignment(1);
        p2.add("Registro de usuarios en el sistema");
        p2.setAlignment(1);
        //Creamos la tabla registro de libros
        PdfPTable tabla = new PdfPTable(5);
        tabla.setHorizontalAlignment(1);
        tabla.addCell("ID");
        tabla.addCell("Usuario");
        tabla.addCell("Facultad");
        tabla.addCell("Carrera");
        tabla.addCell("Tipo");
        for (int i = 0; i < Main.cUsers; i++){
            tabla.addCell(Main.users[i].getiD().toString());
            tabla.addCell(Main.users[i].getUsuario());
            tabla.addCell(Main.users[i].getFacultad());
            tabla.addCell(Main.users[i].getCarrera());
            tabla.addCell(Main.users[i].getTipo().toString());
        }
        //Agregar
        documento.add(p);
        documento.add(Chunk.NEWLINE);
        documento.add(p2);
        documento.add(Chunk.NEWLINE);
        documento.add(tabla);
        documento.close();
    }
    //Metodo que crea los pdf de libros
    public static void PDFLibro(String fechaActual) throws DocumentException, FileNotFoundException{
        Document documento = new Document(PageSize.LETTER);
        OutputStream archivo = new FileOutputStream("E:\\ReportesProyecto1\\Reportes_Libros\\reporteLibros_"+fechaActual + ".pdf");
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        Paragraph p = new Paragraph();
        Paragraph p1 = new Paragraph();
        Paragraph p2 = new Paragraph();
        p.add("REPORTES BIBLIOTECA USAC");
        p.setAlignment(1);
        p1.add("Registro de administrador");
        p1.setAlignment(1);
        //Tabla de usuario
        PdfPTable tablaAdmin = new PdfPTable(4);
        tablaAdmin.setHorizontalAlignment(1);
        tablaAdmin.addCell("ID");
        tablaAdmin.addCell("Nombre");
        tablaAdmin.addCell("Carrera");
        tablaAdmin.addCell("Tipo");
        tablaAdmin.addCell(Main.adminNow[0].getiD().toString());
        tablaAdmin.addCell(Main.adminNow[0].getUsuario());
        tablaAdmin.addCell(Main.adminNow[0].getCarrera());
        tablaAdmin.addCell(Main.adminNow[0].getTipo().toString());
        p2.add("Registro de libros en el sistema");
        p2.setAlignment(1);
        //Creamos la tabla registro de libros
        PdfPTable tabla = new PdfPTable(4);
        tabla.setHorizontalAlignment(1);
        tabla.addCell("ID");
        tabla.addCell("Titulo");
        tabla.addCell("Autor");
        tabla.addCell("Tipo");
        for (int i = 0; i < Main.cLibros; i++){
            tabla.addCell(Main.libros[i].getID().toString());
            tabla.addCell(Main.libros[i].getTitulo());
            tabla.addCell(Main.libros[i].getAutor());
            tabla.addCell(Main.libros[i].getTipo());
        }
        //Agregar
        documento.add(p);
        documento.add(Chunk.NEWLINE);
        documento.add(p1);
        documento.add(Chunk.NEWLINE);
        documento.add(tablaAdmin);
        documento.add(Chunk.NEWLINE);
        documento.add(p2);
        documento.add(Chunk.NEWLINE);
        documento.add(tabla);
        documento.close();
    }
    //Metodo que crea los pdf de los prestamos
    public static void PDFPrestamo(String fechaActual) throws DocumentException, FileNotFoundException{
        Document documento = new Document(PageSize.LETTER);
     
        OutputStream archivo = new FileOutputStream("E:\\ReportesProyecto1\\Reportes_Prestamos\\reportePrestamos_"+fechaActual + ".pdf");
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        Paragraph p = new Paragraph();
        Paragraph p1 = new Paragraph();
        Paragraph p2 = new Paragraph();
        p.add("REPORTES BIBLIOTECA USAC");
        p.setAlignment(1);
        p1.add("Registro de administrador");
        p1.setAlignment(1);
        //Tabla de usuario
        PdfPTable tablaAdmin = new PdfPTable(4);
        tablaAdmin.setHorizontalAlignment(1);
        tablaAdmin.addCell("ID");
        tablaAdmin.addCell("Nombre");
        tablaAdmin.addCell("Carrera");
        tablaAdmin.addCell("Tipo");
        tablaAdmin.addCell(Main.adminNow[0].getiD().toString());
        tablaAdmin.addCell(Main.adminNow[0].getUsuario());
        tablaAdmin.addCell(Main.adminNow[0].getCarrera());
        tablaAdmin.addCell(Main.adminNow[0].getTipo().toString());
        p2.add("Registro de prestamos en el sistema");
        p2.setAlignment(1);
        //Creamos la tabla registro de libros
        PdfPTable tabla = new PdfPTable(4);
        tabla.setHorizontalAlignment(1);
        tabla.addCell("Usuario");
        tabla.addCell("Libro");
        tabla.addCell("Fecha de entrega");
        tabla.addCell("Status");
        for (int i = 0; i < Main.cPrestamos; i++){
            tabla.addCell(Main.prestamos[i].getUsuarioID());
            tabla.addCell(Main.prestamos[i].getLibroID());
            tabla.addCell(Main.prestamos[i].getFechaEntrega());
            tabla.addCell(Main.prestamos[i].getStatus());
        }
        //Agregar
        documento.add(p);
        documento.add(Chunk.NEWLINE);
        documento.add(p1);
        documento.add(Chunk.NEWLINE);
        documento.add(tablaAdmin);
        documento.add(Chunk.NEWLINE);
        documento.add(p2);
        documento.add(Chunk.NEWLINE);
        documento.add(tabla);
        documento.close();
    }
    //Metodo que cuenta los meses de los prestamos
    public static void tipoMes(int numeroMes){
        switch(numeroMes){
            case 1:
                Main.enero ++;
                break;
            case 2:
                Main.febrero ++;
                break;
            case 3: 
                Main.marzo ++;
                break;
            case 4: 
                Main.abril ++;
                break;
            case 5: 
                Main.mayo ++;
                break;
            case 6: 
                Main.junio ++;
                break;
            case 7:
                Main.julio ++;
                break;
            case 8:
                Main.agosto ++;
                break;
            case 9:
                Main.septiembre ++;
                break;
            case 10:
                Main.octubre ++;
                break;
            case 11:
                Main.noviembre ++;
                break;
            case 12:
                Main.diciembre ++;
                break;
            default:
                System.out.println("Error");
                   
        }
    }
    //Metodo para la cuenta de tipos de libros
    public static void tipoLibros(String tipo){
        switch(tipo){
            case "Libro":
                Main.ctipoLibros ++;
                break;
            case "Revista":
                Main.ctipoRevista ++;
                break;
            case "Libro electronico":
                Main.ctipoLibroElec ++;
                break;
            default:
                System.out.println("Error");
        }
    }
    
    

}

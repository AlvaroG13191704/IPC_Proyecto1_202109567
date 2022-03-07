package Interfaces;

import cargasMasivas.CargaMasivaPrestamos;
import cargasMasivas.CargaMasivaLibros;
import elementos.Libros;
import clases.Main;
import elementos.Prestamos;
import elementos.Reportes;
import com.itextpdf.text.DocumentException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class PantallaPrincipal extends JFrame implements ActionListener {

    Font font, font2;
    private JTabbedPane pestañas;
    static private JPanel panelLibros, panelPrestamos, panelReportes, panelGraficos;
    //Primera pestaña
    static private JPanel p1, p2;
    private JLabel lb1, lb2, lb3, lb4, lb5;
    private JButton b1, b2;
    private JTextField tf1, tf2, tf3, tf4;
    private JComboBox com1;

    //Segunda pestaña
    static private JPanel p2_1, p2_2;
    private JLabel lb2_1, lb2_2, lb2_3;
    private JButton b2_1, b2_2;
    private JTextField tf2_1, tf2_2, tf2_3;

    //Tecera pestaña
    static private JPanel p3_1, p3_2;
    private JLabel lb3_1;
    private JButton b3_1;
    private JComboBox com3;

    //Variables de primera pestaña
    Long ID = null;
    String libro = null;
    String autor = null;
    Long copias = null;
    String tipo = "";
    //Variable de segunda pestaña
    Long IDUsuario = null;
    Long IDLibro = null;
    public static String fechaEntrega = null;
    String prestamoUsuario = null;
    String prestamoLibro = null;
    //Variables de tercera pestaña
    String fechaDeGeneracion = null;
    String usuarioEnUso = null;
    String tipodeReporte = null;
    //Tabla libros
    static JTable table1;
    static Object[][] librosDatos;
    static JScrollPane scrolltable;
    String[] columnas1;
    //Tabla prestamos
    static JTable table2;
    static Object[][] prestamosDatos;
    static JScrollPane scrolltable2;
    //Tabla reortes
    static JTable table3;
    static Object[][] reportesDatos;
    static JScrollPane scrolltable3;

    //Constructor
    public PantallaPrincipal() throws ParseException {
        font = new Font("SansSerif", Font.BOLD, 17);
        font2 = new Font("SansSerif", Font.BOLD, 16);

        System.out.println("Cantidad de usuarios admin: " + Main.ctipoAdmin);
        System.out.println("Cantidad de usuarios estudiantes: " + Main.ctipoEstudiante);

        //new Color(211, 207, 199) new Color(198, 135, 8 )
        //Pestañas
        pestañas = new JTabbedPane();
        pestañas.setBounds(0, 0, 1400, 750);
        pestañas.setFont(font);

        //PANELES DE LAS PESTAÑAS
        //PANEL LIBROS
        panelLibros = new JPanel();
        panelLibros.setBackground(new Color(198, 135, 8));
        //panelLibros.setBounds(1, 1, 1300, 700);
        panelLibros.setLayout(null);

        //Paneles
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(10, 10, 350, 550);
        p1.setBackground(new Color(232, 167, 28));

        //Botones
        b2 = new JButton("Carga masiva");
        b2.setLayout(null);
        b2.setBackground(new Color(211, 207, 199));
        b2.setBounds(1197, 620, 150, 35);
        b2.addActionListener(this);

        b1 = new JButton("Registrar libro");
        b1.setBackground(new Color(211, 207, 199));
        b1.setBounds(190, 500, 150, 30);
        b1.addActionListener(this);

        //Labels
        lb1 = new JLabel("ID libro");
        lb1.setBounds(10, 5, 60, 30);
        lb1.setFont(font2);
        lb2 = new JLabel("Libro");
        lb2.setBounds(10, 100, 60, 30);
        lb2.setFont(font2);
        lb3 = new JLabel("Autor");
        lb3.setBounds(10, 200, 60, 30);
        lb3.setFont(font2);
        lb4 = new JLabel("Copias");
        lb4.setBounds(10, 300, 60, 30);
        lb4.setFont(font2);
        lb5 = new JLabel("Tipo");
        lb5.setBounds(10, 400, 60, 30);
        lb5.setFont(font2);
        //textfield
        tf1 = new JTextField();
        tf1.setFont(font2);
        tf1.setBounds(90, 10, 170, 20);
        tf2 = new JTextField();
        tf2.setFont(font2);
        tf2.setBounds(90, 110, 170, 20);
        tf3 = new JTextField();
        tf3.setFont(font2);
        tf3.setBounds(90, 210, 170, 20);
        tf4 = new JTextField();
        tf4.setFont(font2);
        tf4.setBounds(90, 310, 170, 20);
        com1 = new JComboBox();
        com1.setBounds(90, 410, 170, 20);
        com1.addItem("1");
        com1.addItem("2");
        com1.addItem("3");

        //Aca van las tablas
        p2 = new JPanel();
        p2.setLayout(null);
        p2.setBackground(new Color(232, 167, 28));
        p2.setBounds(400, 10, 950, 550);
        PantallaPrincipal.tableroLibros();

        //PANEL PRESTAMOS
        panelPrestamos = new JPanel();
        panelPrestamos.setBackground(new Color(198, 135, 8));
        panelPrestamos.setLayout(null);
        //
        p2_1 = new JPanel();
        p2_1.setLayout(null);
        p2_1.setBounds(10, 10, 350, 550);
        p2_1.setBackground(new Color(232, 167, 28));
        //Botones
        b2_2 = new JButton("Carga masiva");
        b2_2.setLayout(null);
        b2_2.setBackground(new Color(211, 207, 199));
        b2_2.setBounds(1197, 620, 150, 35);
        b2_2.addActionListener(this);

        b2_1 = new JButton("Prestar libro");
        b2_1.setBackground(new Color(211, 207, 199));
        b2_1.setBounds(190, 500, 150, 30);
        b2_1.addActionListener(this);

        //Labels
        lb2_1 = new JLabel("Usuario ID");
        lb2_1.setBounds(10, 5, 100, 30);
        lb2_1.setFont(font2);
        lb2_2 = new JLabel("Libro ID");
        lb2_2.setBounds(10, 100, 60, 40);
        lb2_2.setFont(font2);
        lb2_3 = new JLabel("Fecha \nde entrega");
        lb2_3.setBounds(10, 200, 150, 100);
        lb2_3.setFont(font2);
        //textfield
        tf2_1 = new JTextField();
        tf2_1.setFont(font2);
        tf2_1.setBounds(120, 10, 170, 20);
        tf2_2 = new JTextField();
        tf2_2.setFont(font2);
        tf2_2.setBounds(120, 110, 170, 20);
        tf2_3 = new JTextField();
        tf2_3.setFont(font2);
        tf2_3.setBounds(120, 210, 170, 20);

        //Tablas
        p2_2 = new JPanel();
        p2_2.setLayout(null);
        p2_2.setBackground(new Color(198, 135, 8));
        p2_2.setBounds(400, 10, 950, 550);
        PantallaPrincipal.tableroPrestamos();

        //PANEL REPROTES
        panelReportes = new JPanel();
        panelReportes.setBackground(new Color(198, 135, 8));
        panelReportes.setLayout(null);
        //Paneles
        p3_1 = new JPanel();
        p3_1.setLayout(null);
        p3_1.setBounds(10, 10, 350, 350);
        p3_1.setBackground(new Color(232, 167, 28));
        //Boton
        b3_1 = new JButton("Generar");
        b3_1.setLayout(null);
        b3_1.setBackground(new Color(211, 207, 199));
        b3_1.setBounds(240, 300, 100, 35);
        b3_1.addActionListener(this);
        //Labels
        lb3_1 = new JLabel("Tipo de reporte");
        lb3_1.setBounds(10, 5, 150, 30);
        lb3_1.setFont(font2);
        //Comb
        com3 = new JComboBox();
        com3.setBounds(20, 60, 190, 30);
        com3.addItem("Tipo de reporte");
        com3.addItem("1");
        com3.addItem("2");
        com3.addItem("3");

        //Aca van las tablas
        p3_2 = new JPanel();
        p3_2.setLayout(null);
        p3_2.setBackground(new Color(232, 167, 28));
        p3_2.setBounds(400, 10, 950, 650);
        PantallaPrincipal.tableroReportes();
        //PANEL GRAFICOS
        panelGraficos = new JPanel();
        panelGraficos.setBackground(new Color(198, 135, 8));
        panelGraficos.setLayout(null);
        //Frame
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(0, 0, 1400, 750);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("PANTALLA PRINCIPAL");

        //add
        this.add(pestañas);
        pestañas.addTab("Libros", panelLibros);
        pestañas.addTab("Prestamos", panelPrestamos);
        pestañas.addTab("Reportes", panelReportes);
        pestañas.addTab("Gráficas", panelGraficos);

        //Primera pestaña
        panelLibros.add(p1);
        panelLibros.add(p2);
        panelLibros.add(b2);
        p1.add(lb1);
        p1.add(lb2);
        p1.add(lb3);
        p1.add(lb4);
        p1.add(lb5);
        p1.add(tf1);
        p1.add(tf2);
        p1.add(tf3);
        p1.add(tf4);
        p1.add(com1);
        p1.add(b1);

        //Segunda pestaña
        panelPrestamos.add(p2_1);
        panelPrestamos.add(p2_2);
        panelPrestamos.add(b2_2);
        p2_1.add(b2_1);
        p2_1.add(lb2_1);
        p2_1.add(lb2_2);
        p2_1.add(lb2_3);
        p2_1.add(tf2_1);
        p2_1.add(tf2_2);
        p2_1.add(tf2_3);

        //Tercera pestaña
        panelReportes.add(p3_1);
        panelReportes.add(p3_2);
        p3_1.add(b3_1);
        p3_1.add(lb3_1);
        p3_1.add(com3);

        //Agregar grafica
        grafica1();
        grafica2();
        grafica3();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1)
        {

            ID = Long.parseLong(tf1.getText());
            boolean validarID = verificarIDLibro();
            if (validarID)
            {
                JOptionPane.showMessageDialog(this, "EL ID del libro ya existe \n Ingrese nuevamente");
                tf1.setText("");

            } else
            {
                libro = tf2.getText();
                autor = tf3.getText();
                copias = Long.parseLong(tf4.getText());
                ID = Long.parseLong(tf1.getText());
                if (com1.getSelectedItem().toString() == "1")
                {
                    tipo = "Libro";
                } else if (com1.getSelectedItem().toString() == "2")
                {
                    tipo = "Revista";
                } else if (com1.getSelectedItem().toString() == "3")
                {
                    tipo = "Libro electronico";
                }
                

                //Creando el objeto libro
                Libros libroNuevo = new Libros(ID, libro, autor, tipo, copias, copias, 0l);
                Main.agregarLibro(libroNuevo);
                Main.leerLibros();
                //Guardar que tiplo de libro es
                Main.tipoLibros(tipo);
                //Se actualiza la tabla cada vez que se agrega un libro
                this.dispose();
                try
                {
                    PantallaPrincipal principal = new PantallaPrincipal();
                } catch (ParseException ex)
                {

                }

                JOptionPane.showMessageDialog(this, "Se ha agreado el libro con exito");

                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                Main.leerUsuarios();
                Main.leerLibros();
                Main.leerPrestamo();
            }

        }
        if (e.getSource() == b2)
        {
            //Creando la ventana
            CargaMasivaLibros cargaLibros = new CargaMasivaLibros();
            this.dispose();
        }
        //Crear un prestamo, se valida la existencia de ID y y se crea un objeto prestamos
        if (e.getSource() == b2_1)
        {
            IDUsuario = Long.parseLong(tf2_1.getText());
            IDLibro = Long.parseLong(tf2_2.getText());
            fechaEntrega = tf2_3.getText();

            try
            {
                if (PantallaPrincipal.verificarFecha(fechaEntrega).equals("Prestado"))
                {
                    //Metodo de contadores del mes
                    int numeroMes = PantallaPrincipal.obtenerMes(fechaEntrega);
                    Main.tipoMes(numeroMes);
                    System.out.println("Mes no. " + numeroMes);
                }

            } catch (ParseException ex)
            {
                Logger.getLogger(CargaMasivaPrestamos.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Verificar que este en el sistema el libro y usuario
            boolean prestamoEs = Main.verificarPrestamo(IDUsuario, IDLibro);
            //HACER MEJOR UN SWITCH JUSTO ACA

            if (prestamoEs)
            {
                try
                {
                    //Transformar ese Long a el nombre que pertenece el ID
                    for (int i = 0; i < Main.cUsers; i++)
                    {
                        if (Main.users[i].getiD().equals(IDUsuario))
                        {
                            prestamoUsuario = Main.users[i].getUsuario();
                        }

                    }
                    for (int i = 0; i < Main.cLibros; i++)
                    {
                        if (Main.libros[i].getID().equals(IDLibro))
                        {
                            prestamoLibro = Main.libros[i].getTitulo();
                        }

                    }
                    //Si disponibles y ocupados 
                    if (Main.verificarDisponibles())
                    {
                        JOptionPane.showMessageDialog(this, "Ya no hay libros disponibles para prestar");
                    } //Si ocupados es lo max de copias ya no se puede hacer prestados
                    else if (Main.verificarOcupadosMax())
                    {
                        JOptionPane.showMessageDialog(this, "Ya estan todas las copias ocupadas");
                    } //Si disponibles es diferente de cero, ocupados es igual a cero y la fecha es entregado, entonces solo se agrega el prestamo
                    else if (Main.verificarDisponibles() != true && Main.verificarOcupados() != true && verificarFecha(fechaEntrega) == "Entregado")
                    {
                        Prestamos prestamoNuevo = null;
                        try
                        {
                            prestamoNuevo = new Prestamos(prestamoUsuario, prestamoLibro, fechaEntrega, verificarFecha(fechaEntrega));
                        } catch (ParseException ex)
                        {

                        }
                        Main.agregarPrestamo(prestamoNuevo);
                        //Main.administrarPrestamosEntregado(prestamoLibro);
                        JOptionPane.showMessageDialog(this, "Se ha hecho un prestamo correctamente");
                        tf2_1.setText("");
                        tf2_2.setText("");
                        tf2_3.setText("");

                        //Actualizar
                        prestamoUsuario = null;
                        prestamoLibro = null;

                        //Leer
                        Main.leerUsuarios();
                        Main.leerLibros();
                        Main.leerPrestamo();
                        this.dispose();
                        try
                        {
                            PantallaPrincipal principal = new PantallaPrincipal();
                        } catch (ParseException ex)
                        {

                        }
                    } //Si disponibles es diferente de 0 y la fecha de entrega es prestado, entonces se le suma a ocupados y se le resta a disponibles
                    else if (Main.verificarDisponibles() != true && verificarFecha(fechaEntrega) == "Prestado")
                    {
                        Prestamos prestamoNuevo = null;
                        try
                        {
                            prestamoNuevo = new Prestamos(prestamoUsuario, prestamoLibro, fechaEntrega, verificarFecha(fechaEntrega));
                        } catch (ParseException ex)
                        {

                        }
                        Main.agregarPrestamo(prestamoNuevo);
                        Main.administrarPrestamosPrestado(prestamoLibro);
                        JOptionPane.showMessageDialog(this, "Se ha hecho un prestamo correctamente");
                        tf2_1.setText("");
                        tf2_2.setText("");
                        tf2_3.setText("");

                        //Actualizar
                        prestamoUsuario = null;
                        prestamoLibro = null;

                        //Leer
                        Main.leerUsuarios();
                        Main.leerLibros();
                        Main.leerPrestamo();
                        this.dispose();
                        try
                        {
                            PantallaPrincipal principal = new PantallaPrincipal();
                        } catch (ParseException ex)
                        {

                        }
                    } //Si disponibles es diferente de cero y la fecha es entregado, entonces se le suma a disponibles y se le resta a ocupados
                    else if (Main.verificarDisponibles() != true && Main.verificarOcupados() != true && verificarFecha(fechaEntrega) == "Entregado")
                    {
                        Prestamos prestamoNuevo = null;
                        try
                        {
                            prestamoNuevo = new Prestamos(prestamoUsuario, prestamoLibro, fechaEntrega, verificarFecha(fechaEntrega));
                        } catch (ParseException ex)
                        {

                        }
                        Main.agregarPrestamo(prestamoNuevo);
                        //Main.administrarPrestamosEntregado(prestamoLibro);
                        JOptionPane.showMessageDialog(this, "Se ha hecho un prestamo correctamente");
                        tf2_1.setText("");
                        tf2_2.setText("");
                        tf2_3.setText("");

                        //Actualizar
                        prestamoUsuario = null;
                        prestamoLibro = null;

                        //Leer
                        Main.leerUsuarios();
                        Main.leerLibros();
                        Main.leerPrestamo();
                        this.dispose();
                        try
                        {
                            PantallaPrincipal principal = new PantallaPrincipal();
                        } catch (ParseException ex)
                        {

                        }
                    } //Si disponibles es igual a cero, ocupados diferente de cero y la fecha es entregado, entonces se le suma uno a disponibles y se le resta a ocupados
                    else if (verificarFecha(fechaEntrega) == "Entregado")
                    {
                        Prestamos prestamoNuevo = null;
                        try
                        {
                            prestamoNuevo = new Prestamos(prestamoUsuario, prestamoLibro, fechaEntrega, verificarFecha(fechaEntrega));
                        } catch (ParseException ex)
                        {

                        }
                        Main.agregarPrestamo(prestamoNuevo);
                        //Main.administrarPrestamosEntregado(prestamoLibro);
                        JOptionPane.showMessageDialog(this, "Se ha hecho un prestamo correctamente");
                        tf2_1.setText("");
                        tf2_2.setText("");
                        tf2_3.setText("");

                        //Actualizar
                        prestamoUsuario = null;
                        prestamoLibro = null;

                        //Leer
                        Main.leerUsuarios();
                        Main.leerLibros();
                        Main.leerPrestamo();
                        this.dispose();
                        try
                        {
                            PantallaPrincipal principal = new PantallaPrincipal();
                        } catch (ParseException ex)
                        {

                        }
                    }

                } catch (ParseException ex)
                {

                }

            } else
            {
                JOptionPane.showMessageDialog(this, "Algún ID no existe");
                tf2_1.setText("");
                tf2_2.setText("");
            }

        }
        //Carga masiva de prestamos
        if (e.getSource() == b2_2)
        {
            System.out.println("Click carga masiva de prestamos");
            //Creando la nueva ventana
            CargaMasivaPrestamos cargaPrestamos = new CargaMasivaPrestamos();
            this.dispose();
        }
        if (e.getSource() == b3_1)
        {
            System.out.println("Click en generar reporte");
            //Poner que tipo es y dependiendo de eso genera el reporte
            fechaDeGeneracion = generarFecha();
            usuarioEnUso = Main.adminNow[0].getUsuario();
            if (com3.getSelectedItem().toString() == "1")
            {
                tipodeReporte = "Reporte de usuarios";

            } else if (com3.getSelectedItem().toString() == "2")
            {
                tipodeReporte = "Reporte de libros";
            } else if (com3.getSelectedItem().toString() == "3")
            {
                tipodeReporte = "Reporte de prestamos";
            }
            //Se crea el objeto
            Reportes reporteNuevo = new Reportes(fechaDeGeneracion, usuarioEnUso, tipodeReporte);
            Main.agregarReporte(reporteNuevo);
            JOptionPane.showMessageDialog(this, "Se ha generado un reporte correctamente");
            Main.leerReportes();
            this.dispose();
            try
            {
                PantallaPrincipal principal = new PantallaPrincipal();
            } catch (ParseException ex)
            {

            }

            try
            {
                if (com3.getSelectedItem().toString() == "1")
                {
                    Main.PDFUsuarios(fechaReportes());
                    JOptionPane.showMessageDialog(this, "Se ha generado un PDF de usuarios");

                } else if (com3.getSelectedItem().toString() == "2")
                {
                    //Generar reporte de libros

                    Main.PDFLibro(fechaReportes());
                    JOptionPane.showMessageDialog(this, "Se ha generado un PDF de libros");
                } else if (com3.getSelectedItem().toString() == "3")
                {

                    Main.PDFPrestamo(fechaReportes());
                    JOptionPane.showMessageDialog(this, "Se ha generado un PDF de prestamos");
                }
            } catch (DocumentException ex)
            {
                Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex)
            {
                Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    //Verificar la fecha de entrega de un prestamo
    public static String verificarFecha(String fecha) throws ParseException {
        try
        {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
            Calendar calendario = Calendar.getInstance();
            Date FechaActual = calendario.getTime();

            Date fechaIngresada = formatoFecha.parse(fecha);
            if (FechaActual.before(fechaIngresada))
            {
                return "Prestado";
            } else
            {
                return "Entregado";
            }
        } catch (ParseException ex)
        {
            System.out.println("ERORRRRRRESRJWEUJR");
        }
        return null;
    }

    //Obtener el mes del año
    public static int obtenerMes(String fecha) throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaActual = formatoFecha.parse(fecha);
        SimpleDateFormat formatoMes= new SimpleDateFormat("MM");
        String mes = formatoMes.format(fechaActual);
        int numeroMes = Integer.parseInt(mes);
        return numeroMes;
    }

    //Generar la fecha y hora de hoy
    public static String generarFecha() {
        String fechaActual = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        return fechaActual;
    }

    //Generar fecha para los reportes
    public static String fechaReportes() {
        String fechaActual = new SimpleDateFormat("ddMMyyyyHHmmss").format(Calendar.getInstance().getTime());
        return fechaActual;
    }

    //Tabla libros
    public static void tableroLibros() {

        librosDatos = Main.tablaLibros();
        String[] columnas1 =
        {
            "ID libro", "Nombre libro", "Autor", "Tipo", "Copias", "Disponibles", "Ocupados"
        };
        table1 = new JTable(librosDatos, columnas1);
        DefaultTableModel modelo = new DefaultTableModel(librosDatos,columnas1){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table1.setModel(modelo);
        scrolltable = new JScrollPane(table1);
        
        scrolltable.setBounds(0, 0, 950, 550);
        scrolltable.setVisible(true);
        p2.add(scrolltable);

    }

    //Imprimir tabla prestamos
    public static void tableroPrestamos() throws ParseException {
        prestamosDatos = Main.tablaPrestamos();
        String[] columnas2 =
        {
            "Nombre Usuario", "Libro", "Fechad de Entrega", "Status"
        };
        table2 = new JTable(prestamosDatos, columnas2);
        DefaultTableModel modelo2 = new DefaultTableModel(prestamosDatos,columnas2){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table2.setModel(modelo2);
        scrolltable2 = new JScrollPane(table2);
        scrolltable2.setBounds(0, 0, 950, 550);
        scrolltable2.setVisible(true);
        p2_2.add(scrolltable2);
    }

    //Imprimir tabla reportes
    public static void tableroReportes() {
        reportesDatos = Main.tablaReportes();
        String[] columnas3 =
        {
            "Fecha Generación", "Usuario", "Tipo de Reporte"
        };
        table3 = new JTable(reportesDatos, columnas3);
        DefaultTableModel modelo3 = new DefaultTableModel(reportesDatos,columnas3){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table3.setModel(modelo3);
        scrolltable3 = new JScrollPane(table3);
        scrolltable3.setBounds(0, 0, 950, 650);
        scrolltable3.setVisible(true);
        p3_2.add(scrolltable3);
    }

    //Verificar repiticon del boolean
    public boolean verificarIDLibro() {
        for (int i = 0; i < Main.cLibros; i++)
        {
            if (Main.libros[i].getID() == ID)
            {
                return true;
            }

        }
        return false;
    }

    //Grafica 1
    public static void grafica1() {
        DefaultPieDataset datos = new DefaultPieDataset();

        datos.setValue("Administradores", Main.ctipoAdmin);
        datos.setValue("Estudiantes", Main.ctipoEstudiante);

        JFreeChart grafica1 = ChartFactory.createPieChart("Gráfica de usuarios registrados", datos, true, true, false);

        ChartPanel panel = new ChartPanel(grafica1);
        panel.setBounds(10, 10, 450, 500);
        panelGraficos.add(panel);
    }
    //Grafica 2
    public static void grafica2(){
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        datos.addValue(Main.enero, "Libros Prestados", "Ene");
        datos.addValue(Main.febrero, "Libros Prestados", "Feb");
        datos.addValue(Main.marzo, "Libros Prestados", "Mar");
        datos.addValue(Main.abril, "Libros Prestados", "Abril");
        datos.addValue(Main.mayo, "Libros Prestados", "May");
        datos.addValue(Main.junio, "Libros Prestados", "Jun");
        datos.addValue(Main.julio, "Libros Prestados", "Jul");
        datos.addValue(Main.agosto, "Libros Prestados", "Ago");
        datos.addValue(Main.septiembre, "Libros Prestados", "Sep");
        datos.addValue(Main.octubre, "Libros Prestados", "Oct");
        datos.addValue(Main.noviembre, "Libros Prestados", "Nov");
        datos.addValue(Main.diciembre, "Libros Prestados", "Dic");
        
        JFreeChart graficoBarras = ChartFactory.createBarChart3D("Libros prestados 2022", "Meses", "Total de libros prestados", datos,PlotOrientation.VERTICAL,true,true,false);
        ChartPanel panel = new ChartPanel(graficoBarras);
        panel.setBounds(450, 10, 470, 500);
        panelGraficos.add(panel);     
        
    }
    //Grafica 3
    public static void grafica3() {
        DefaultPieDataset datos = new DefaultPieDataset();

        datos.setValue("Libros", Main.ctipoLibros);
        datos.setValue("Revistas", Main.ctipoRevista);
        datos.setValue("Libros electronicos", Main.ctipoLibroElec);

        JFreeChart grafica1 = ChartFactory.createPieChart("Gráfica tipos de libros", datos, true, true, false);

        ChartPanel panel = new ChartPanel(grafica1);
        panel.setBounds(920, 10, 450, 500);
        panelGraficos.add(panel);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paulo
 */
public class Administracion extends JFrame {

    ArrayList<ArrayList<Object>> empleados;
    JScrollPane tablePane;

    public Administracion() {
        super("Administracion");
        this.setSize(900, 480);
        this.setMinimumSize(new Dimension(900, 480));

        this.setResizable(true);
        this.setLayout(new BorderLayout());

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = this.getSize();
        this.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);

        JTable table = new JTable(new DefaultTableModel());
        DefaultTableModel tablaAvicola = (DefaultTableModel) table.getModel();
        tablaAvicola.addColumn("Rut");
        tablaAvicola.addColumn("Nombre");
        tablaAvicola.addColumn("Cargo");
        tablaAvicola.addColumn("Sueldo");
        tablaAvicola.addColumn("Dirección");
        tablaAvicola.addColumn("Teléfono");
        tablaAvicola.addColumn("Nacionalidad");
        tablePane = new JScrollPane(table);

        JMenuBar mb = new JMenuBar();
        JMenu inicio = new JMenu("Inicio");
        JMenu consultas = new JMenu("Consultas");
        JMenu solicitudes = new JMenu("Solicitudes");

        JMenuItem Inicio1 = new JMenu("Inicio 1");
        JMenuItem Inicio2 = new JMenu("Inicio 2");
        JMenuItem Inicio3 = new JMenu("Inicio 3");

        TrabajadoresCompra Consulta1 = new TrabajadoresCompra(this,tablePane);
        ClientesCompra Consulta2 = new ClientesCompra(this,tablePane);
        
        JMenuItem Consulta3 = new JMenuItem("Consulta 3");

        JMenuItem Solicitud1 = new JMenuItem("Solicitud 1");
        JMenuItem Solicitud2 = new JMenuItem("Solicitud 2");
        JMenuItem Solicitud3 = new JMenuItem("Solicitud 3");

        mb.add(inicio);
        mb.add(consultas);
        mb.add(solicitudes);

        inicio.add(Inicio1);
        inicio.add(Inicio2);
        inicio.add(Inicio3);

        consultas.add(Consulta1);
        consultas.add(Consulta2);
        consultas.add(Consulta3);

        solicitudes.add(Solicitud1);
        solicitudes.add(Solicitud2);
        solicitudes.add(Solicitud3);

        try {
            this.initInventario();
        } catch (SQLException ex) {
            Logger.getLogger(Avicola.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < empleados.size(); i++) {
            tablaAvicola.addRow(new Object[]{empleados.get(i).get(0), empleados.get(i).get(1), empleados.get(i).get(2), empleados.get(i).get(3), empleados.get(i).get(4), empleados.get(i).get(5), empleados.get(i).get(6)});
        }

        this.add(tablePane, BorderLayout.CENTER);
        this.setJMenuBar(mb);

        this.setVisible(true);
    }

    public void initInventario() throws SQLException {
        empleados = new ArrayList();
        String url = "jdbc:postgresql://plop.inf.udec.cl:5432/bdi2017t";
        Connection con = DriverManager.getConnection(url, "bdi2017t", "bdi2017t");
        con.setSchema("Agricola");
        Statement instruccionSQL = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultadosConsulta = instruccionSQL.executeQuery("select * from empleado order by nombre,cargo");
        while (resultadosConsulta.next()) {
            ArrayList aux = new ArrayList();
            aux.add(resultadosConsulta.getString(1));
            aux.add(resultadosConsulta.getString(2));
            aux.add(resultadosConsulta.getString(3));
            aux.add(resultadosConsulta.getString(4));
            aux.add(resultadosConsulta.getString(5));
            aux.add(resultadosConsulta.getString(6));
            aux.add(resultadosConsulta.getString(7));
            empleados.add(aux);
        }
        con.close();
    }
}

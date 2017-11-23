/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paulo
 */
public class BodegaVino extends JFrame {

    ArrayList<ArrayList<Object>> inventario;

    public BodegaVino() {
        super("Bodega de Vino");
        this.setSize(900, 480);
        this.setMinimumSize(new Dimension(900, 480));

        this.setResizable(true);
        this.setLayout(new BorderLayout());

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = this.getSize();
        this.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);

        JMenuBar mb = new JMenuBar();
        JMenu inicio = new JMenu("Inicio");
        JMenu consultas = new JMenu("Consultas");
        JMenu solicitudes = new JMenu("Solicitudes");

        JMenuItem Inicio1 = new JMenu("Inicio 1");
        JMenuItem Inicio2 = new JMenu("Inicio 2");
        JMenuItem Inicio3 = new JMenu("Inicio 3");

        JMenuItem Consulta1 = new JMenuItem("Consulta 1");
        JMenuItem Consulta2 = new JMenuItem("Consulta 2");
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

        JTable table = new JTable(new DefaultTableModel());
        DefaultTableModel tablaAvicola = (DefaultTableModel) table.getModel();
        tablaAvicola.addColumn("ID Bodega");
        tablaAvicola.addColumn("Año");
        tablaAvicola.addColumn("Tipo de vino");
        tablaAvicola.addColumn("Cantidad en litros");
        JScrollPane tablePane = new JScrollPane(table);

        try {
            this.initInventario();
        } catch (SQLException ex) {
            Logger.getLogger(Avicola.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < inventario.size(); i++) {
            tablaAvicola.addRow(new Object[]{inventario.get(i).get(0), inventario.get(i).get(1), inventario.get(i).get(2), inventario.get(i).get(3)});
        }

        this.add(tablePane);
        this.setJMenuBar(mb);
        this.setVisible(true);
    }

    public void initInventario() throws SQLException {
        inventario = new ArrayList();
        String url = "jdbc:postgresql://plop.inf.udec.cl:5432/bdi2017t";
        Connection con = DriverManager.getConnection(url, "bdi2017t", "bdi2017t");
        con.setSchema("Agricola");
        Statement instruccionSQL = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultadosConsulta = instruccionSQL.executeQuery("select * from bodega_vino");
        while (resultadosConsulta.next()) {
            ArrayList aux = new ArrayList();
            aux.add(resultadosConsulta.getString(1));
            aux.add(resultadosConsulta.getString(2));
            aux.add(resultadosConsulta.getString(3));
            aux.add(resultadosConsulta.getString(4));
            inventario.add(aux);
        }
        con.close();
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    DefaultTableModel tablaAvicola;

    public BodegaVino() {
        super("Bodega de Vino");
        this.setSize(640, 480);

        this.setResizable(true);
        this.setLayout(new BorderLayout());

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = this.getSize();
        this.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);

        JTable table = new JTable(new DefaultTableModel());
        tablaAvicola = (DefaultTableModel) table.getModel();
        tablaAvicola.addColumn("ID Bodega");
        tablaAvicola.addColumn("Año");
        tablaAvicola.addColumn("Tipo de vino");
        tablaAvicola.addColumn("Cantidad en litros");
        JScrollPane tablePane = new JScrollPane(table);

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

        SolicitarUvas Solicitud1 = new SolicitarUvas(this, tablePane);

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

        this.initInventario();
        this.rellenarTabla();
        this.add(tablePane, BorderLayout.CENTER);
        JButton jb = new JButton("Actualizar");
        jb.addActionListener(new Listener());
        this.add(jb, BorderLayout.SOUTH);

        this.setJMenuBar(mb);
        this.setVisible(true);
    }

    public void rellenarTabla() {
        this.initInventario();
        for (int i = 0; i < inventario.size(); i++) {
            tablaAvicola.addRow(new Object[]{inventario.get(i).get(0), inventario.get(i).get(1), inventario.get(i).get(2), inventario.get(i).get(3)});
        }
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            while (tablaAvicola.getRowCount() > 0) {
                tablaAvicola.removeRow(0);
            }
            initInventario();
            rellenarTabla();
        }
    }

    public void initInventario() {
        try {
            inventario = new ArrayList();
            String url = "jdbc:postgresql://plop.inf.udec.cl:5432/bdi2017t";
            Connection con = DriverManager.getConnection(url, "bdi2017t", "bdi2017t");
            con.setSchema("Agricola");
            Statement instruccionSQL = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultadosConsulta = instruccionSQL.executeQuery("select * from bodega_vino order by id_bodega");
            while (resultadosConsulta.next()) {
                ArrayList aux = new ArrayList();
                aux.add(resultadosConsulta.getString(1));
                aux.add(resultadosConsulta.getString(2));
                aux.add(resultadosConsulta.getString(3));
                aux.add(resultadosConsulta.getString(4));
                inventario.add(aux);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BodegaVino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

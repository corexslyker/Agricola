/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paulo
 */
public class SolicitarAlimentoGallina extends JMenuItem {

    JFrame _frame;
    JScrollPane _pane;

    public SolicitarAlimentoGallina(JFrame frame, JScrollPane pane) {
        super("Solicitar alimento de gallinas");
        _frame = frame;
        _pane = pane;
        this.addActionListener(new Listener());
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            this.listar();
        }

        public void listar() {
            try {

                String url = "jdbc:postgresql://plop.inf.udec.cl:5432/bdi2017t";
                Connection con = DriverManager.getConnection(url, "bdi2017t", "bdi2017t");
                con.setSchema("Agricola");
                Statement instruccionSQL = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultadosConsulta = instruccionSQL.executeQuery("select nombre_c,direccion_s from recibe, cliente, salaventas, genera where rut_c=rut_cr and id_boletar = id_boletag and id_salag=id_sala");

                con.close();
                //_pane.removeAll();
                //_frame.revalidate();
                //_frame.repaint();

            } catch (SQLException ex) {
                Logger.getLogger(TrabajadoresCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

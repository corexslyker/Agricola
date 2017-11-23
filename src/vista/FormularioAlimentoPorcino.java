/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import botones.BotonIngresar;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author pauol
 */
public class FormularioAlimentoPorcino {

    JButton bt1;
    JLabel jl1, jl2, jl3, jl4;
    JTextField jt1, jt2, jt3, jt4;
    JButton jb1, jb2;
    JFrame jf = new JFrame("Formulario de solicitud");
    Porcinos _avicola;
    JScrollPane _pane;

    public FormularioAlimentoPorcino(Porcinos avicola, JScrollPane pane) {
        _avicola = avicola;
        _pane = pane;
        jf.setResizable(true);
        jf.setAlwaysOnTop(true);
        jf.setSize(350, 200);
        jf.setLayout(new GridLayout(5, 2));
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = jf.getSize();
        jf.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        jl1 = new JLabel("ID sección solicitante: ");
        jl2 = new JLabel("ID sección a la que solicita: ");
        jl3 = new JLabel("Elemento que solicita: ");
        jl4 = new JLabel("Cantidad: ");

        jt1 = new JTextField();
        jt2 = new JTextField();
        jt3 = new JTextField();
        jt4 = new JTextField();

        jb1 = new JButton("Solicitar");
        jb1.addActionListener(new Listener());
        jb2 = new JButton("Cancelar");
        jb2.addActionListener(new ListenerClose());

        jf.add(jl1);
        jf.add(jt1);
        jf.add(jl2);
        jf.add(jt2);
        jf.add(jl3);
        jf.add(jt3);
        jf.add(jl4);
        jf.add(jt4);
        jf.add(jb1);
        jf.add(jb2);

        jf.setVisible(true);

    }

    private class Listener implements ActionListener {

        String ID1, ID2, elemento, cantidad;

        @Override

        public void actionPerformed(ActionEvent e) {
            ID1 = jt1.getText();
            ID2 = jt2.getText();
            elemento = jt3.getText();
            cantidad = jt4.getText();
            if (!this.tuplaInsertada()) {
                System.out.println("INSERTA");
                this.insertSolicitud();
            } else {

                System.out.println("ACTUALIZA");
                this.updateSolicitud();
            }

            _pane.repaint();
            jf.setVisible(false);

        }

        private boolean tuplaInsertada() {
            try {
                String url = "jdbc:postgresql://plop.inf.udec.cl:5432/bdi2017t";
                Connection con = DriverManager.getConnection(url, "bdi2017t", "bdi2017t");
                con.setSchema("Agricola");
                Statement instruccionSQL = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultadosConsulta = instruccionSQL.executeQuery("select * from alimenta_p where id_molinop ='" + ID2 + "' and id_cporcinosp = '" + ID1 + "'");
                con.close();
                return resultadosConsulta.first();

            } catch (SQLException ex) {
                Logger.getLogger(FormularioAlimentoPorcino.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        private void insertSolicitud() {
            try {
                String url = "jdbc:postgresql://plop.inf.udec.cl:5432/bdi2017t";
                Connection con = DriverManager.getConnection(url, "bdi2017t", "bdi2017t");
                con.setSchema("Agricola");
                Statement instruccionSQL = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultadosConsulta = instruccionSQL.executeQuery("INSERT INTO alimenta_p VALUES(" + ID2 + "," + ID1 + "," + cantidad + ")");
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FormularioAlimentoPorcino.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void updateSolicitud() {
            try {
                String url = "jdbc:postgresql://plop.inf.udec.cl:5432/bdi2017t";
                Connection con = DriverManager.getConnection(url, "bdi2017t", "bdi2017t");
                con.setSchema("Agricola");
                Statement instruccionSQL = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                instruccionSQL.executeUpdate("UPDATE alimenta_p SET cantidad = cantidad + " + cantidad + "  where id_molinop ='" + ID2 + "' and id_cporcinosp = '" + ID1 + "'");

                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FormularioAlimentoPorcino.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private class ListenerClose implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            jf.setVisible(false);
        }
    }
}

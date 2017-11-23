package vista;

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

public class SolicitarUvas extends JMenuItem {

    BodegaVino _frame;
    JScrollPane _pane;

    public SolicitarUvas(BodegaVino frame, JScrollPane pane) {
        super("Solicitar uvas");
        _frame = frame;
        _pane = pane;
        this.addActionListener(new Listener());
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FormularioUvas fs = new FormularioUvas(_frame, _pane);
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
            } catch (SQLException ex) {
                Logger.getLogger(TrabajadoresCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

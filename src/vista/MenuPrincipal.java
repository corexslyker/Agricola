/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import botones.PanelBotones;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;

/**
 *
 * @author ByPal
 */
public class MenuPrincipal extends JFrame {

    JPanel loginPanel;
    JTextField userText;
    JPasswordField passwordText;
    String _user;

    public MenuPrincipal(String user) throws SQLException {
        super("Agrícola Salesiana");
        this.setSize(800, 600);
        this.setResizable(true);
        this.setMinimumSize(new Dimension(800, 600));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        _user = user;

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = this.getSize();
        this.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);

        JLabel nombreUsuario = new JLabel(this.nombreUsuario());
        PanelFondo pf = new PanelFondo();
        PanelBotones pb = new PanelBotones();

        this.add(pf);
        this.add(nombreUsuario, BorderLayout.SOUTH);
        this.add(pb, BorderLayout.NORTH);
        this.setVisible(true);
    }

    public String nombreUsuario() throws SQLException {
        String url = "jdbc:postgresql://plop.inf.udec.cl:5432/bdi2017t";
        String str;
        try (Connection con = DriverManager.getConnection(url, "bdi2017t", "bdi2017t")) {
            con.setSchema("Agricola");
            Statement instruccionSQL = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultadosConsulta = instruccionSQL.executeQuery("SELECT nombre FROM empleado WHERE rut='" + _user + "'");
            resultadosConsulta.next();
            str = "Usuario: " + resultadosConsulta.getString(1);
        }
        return str;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pauloolivares
 */
public class TestGUI {

    public static void main(String[] args) {
        if (TestGUI.connect()) {
            Login v = new Login();
        }
    }

    public static boolean connect() {
        String url = "jdbc:postgresql://plop.inf.udec.cl:5432/bdi2017t";
        try {
            System.out.println(" Estableciendo la conexión");
            Connection con = DriverManager.getConnection(url, "bdi2017t", "bdi2017t");
            System.out.println("Obteniendo los metadatos de la base de datos");
            con.close();
            return true;
        } catch (SQLException e) {
            System.out.println(" Excepción al establecer la conexión " + e.getMessage());
        }
        return false;
    }
}

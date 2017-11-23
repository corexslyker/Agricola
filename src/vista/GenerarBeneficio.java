/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author ByPal
 */
public class GenerarBeneficio extends JFrame {

    ArrayList<String> cargas;
    ArrayList<String> medicamentos;
    ArrayList<String> medicos;
    ArrayList<String> examenes;
    String _user;

    public GenerarBeneficio(String user) {
        super("Generar Beneficio");
        this.setSize(640, 480);
        this.setResizable(true);
        this.setLayout(new BorderLayout());

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = this.getSize();
        this.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);

        this.setVisible(true);

    }
}

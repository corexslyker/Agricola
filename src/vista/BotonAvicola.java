/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author ByPal
 */
public class BotonAvicola extends JButton {

    public BotonAvicola() {
        super("Avícola");
        this.addActionListener(new Listener());
        
        this.setContentAreaFilled(false);
        this.setVerticalAlignment(TOP);
        this.setHorizontalAlignment(CENTER);
        this.setHorizontalTextPosition(CENTER);
        this.setVerticalTextPosition(BOTTOM);        
        
        URL urlBackground_image = getClass().getResource("/imágenes/avicola.png");
        ImageIcon icon = new ImageIcon(urlBackground_image);  
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);

        this.setIcon(icon);
        this.setBorderPainted(false);
        this.setBorder(null);
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Avicola sb = new Avicola();
        }
    }
}

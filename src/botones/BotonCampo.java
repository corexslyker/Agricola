/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botones;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import static javax.swing.SwingConstants.BOTTOM;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.TOP;
import vista.Campo;

/**
 *
 * @author Paulo
 */
public class BotonCampo  extends JButton {

    public BotonCampo() {
        super("Campo");
        this.addActionListener(new Listener());

       this.setContentAreaFilled(false);
        this.setVerticalAlignment(TOP);
        this.setHorizontalAlignment(CENTER);
        this.setHorizontalTextPosition(CENTER);
        this.setVerticalTextPosition(BOTTOM);        
        
        URL urlBackground_image = getClass().getResource("/imágenes/campo.png");
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
            Campo hd = new Campo();
        }
    }
    
}

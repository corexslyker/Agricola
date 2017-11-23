/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author ByPal
 */
public class PanelFondo extends JPanel {

    Image _image;

    public PanelFondo() {
        URL urlBackground_image = getClass().getResource("/imágenes/salesianos.png");
        this._image = new ImageIcon(urlBackground_image).getImage();
        
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(_image, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }
}

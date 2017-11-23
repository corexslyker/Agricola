/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botones;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author ByPal
 */
public class PanelBotones extends JPanel {

    Image _image;

    public PanelBotones() {
        URL urlBackground_image = getClass().getResource("/imágenes/banner.jpg");
        this._image = new ImageIcon(urlBackground_image).getImage();

        this.add(new BotonAvicola());
        this.add(new BotonBodegaVino());
        this.add(new BotonCampo());
        this.add(new BotonFrutales());
        this.add(new BotonLecheria());
        this.add(new BotonMolino());
        this.add(new BotonPorcinos());
        this.add(new BotonQueseria());
        this.add(new BotonSalaVentas());
        this.add(new BotonAdministracion());
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(_image, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }
}

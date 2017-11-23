
package botones;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import vista.Login;
import vista.MenuPrincipal;

public class BotonIngresar extends JButton {

    Login _v;

    public BotonIngresar(Login v) {
        super("Ingresar");
        _v = v;
        this.addActionListener(new Listener());
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String usuario = _v.userText.getText();
            String contraseña = new String(_v.passwordText.getPassword());
            System.out.println(contraseña);
            if (validarUsuario(usuario, contraseña)) {
                try {
                    MenuPrincipal p = new MenuPrincipal(usuario);
                } catch (SQLException ex) {
                    Logger.getLogger(BotonIngresar.class.getName()).log(Level.SEVERE, null, ex);
                }
                _v.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

        private boolean validarUsuario(String elUsr, String elPw) {
            try {
                String url = "jdbc:postgresql://plop.inf.udec.cl:5432/bdi2017t";
                Connection con = DriverManager.getConnection(url, "bdi2017t", "bdi2017t");
                con.setSchema("Agricola");
                Statement instruccionSQL = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultadosConsulta = instruccionSQL.executeQuery("SELECT rut,contraseña FROM empleado WHERE rut='" + elUsr + "' AND contraseña='" + elPw + "'");
                con.close();
                return resultadosConsulta.first();
            } catch (SQLException e) {
                return false;
            }
        }
    }
}

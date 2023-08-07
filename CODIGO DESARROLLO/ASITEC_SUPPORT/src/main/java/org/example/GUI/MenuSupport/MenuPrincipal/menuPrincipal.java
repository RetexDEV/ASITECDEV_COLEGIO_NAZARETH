package org.example.GUI.MenuSupport.MenuPrincipal;

import org.example.GUI.MenuSupport.MenuPrincipal.SubMenu.enviarIncidencia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;

public class menuPrincipal {
    private JPanel panelMenuPrincipal;
    private JPanel panelImagen;
    private JPanel panelBotones;
    private JLabel imagenHead;
    private JPanel panelRed;
    private JPanel panelDualmon;
    private JPanel panelCorreo;
    private JLabel redIcon;
    private JLabel mailIcon;
    private JLabel dualmonIcon;

    public menuPrincipal() {
        dualmonIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                abrirDualmonWeb("https://www.dualmon.com/assist/");

            }
        });
        mailIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                envioEmail("asitec@asitec.es");
            }
        });
        redIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                comprobarRed();
            }
        });
    }


    //METODOS

    private void abrirDualmonWeb(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void envioEmail(String emailAddress) {
        JFrame frame = new JFrame("TICKET | ASISTENCIA");
        frame.setContentPane(new enviarIncidencia().getPanelPrincipalEnviarIncidencia());
        ImageIcon icon = new ImageIcon(menuPrincipal.class.getResource("/ASITEC-MINIATURA.jpg"));
        frame.setIconImage(icon.getImage());
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }

    private void comprobarRed() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            String command;

            if (os.contains("win")) {
                command = "cmd /c start cmd.exe /k ping 8.8.8.8 -t";
            } else {
                command = "ping 8.8.8.8 -t";
            }

            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("ASITEC | SOPORTE | RMM");
        frame.setContentPane(new menuPrincipal().panelMenuPrincipal);
        ImageIcon icon = new ImageIcon(menuPrincipal.class.getResource("/ASITEC-MINIATURA.jpg"));
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        try {
            //Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            // handle exception
        }

    }
}

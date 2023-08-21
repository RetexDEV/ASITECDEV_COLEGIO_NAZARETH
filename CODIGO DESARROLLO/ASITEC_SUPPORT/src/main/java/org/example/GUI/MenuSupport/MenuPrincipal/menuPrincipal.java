package org.example.GUI.MenuSupport.MenuPrincipal;

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
    private JLabel incidenciasURL;
    private JLabel dualmonIcon;

    private String urlDuamon = "https://www.dualmon.com/assist/";

    private String urlIncidenciasAulas = "https://docs.google.com/forms/d/e/1FAIpQLSc6Y-2PP-4SXCStcK12RM5bFqVlowHURfyYjgoC1cjvG429wQ/viewform";

    public menuPrincipal() {

        dualmonIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                abrirDualmonWeb(urlDuamon);

            }
        });
        incidenciasURL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                abrirURLIncidencia(urlIncidenciasAulas);
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


    private void abrirURLIncidencia(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        JFrame frame = new JFrame("COLEGIO NAZARETH | ASISTENCIA TECNICA");
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

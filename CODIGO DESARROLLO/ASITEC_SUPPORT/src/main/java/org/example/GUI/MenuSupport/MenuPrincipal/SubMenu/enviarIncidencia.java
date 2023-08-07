package org.example.GUI.MenuSupport.MenuPrincipal.SubMenu;

import org.example.Objetos.CorreoElectronico;
import org.example.Objetos.Incidencia;

import javax.swing.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class enviarIncidencia {

    private JPanel panelPrincipalEnviarIncidencia;
    private JPanel panelBotones;
    private JButton enviarButton;
    private JButton LIMPIARButton;
    private JTextArea descripcionTextArea;
    private JTextField fechaIncidenciaTextField;
    private JTextField horaTextField;
    private JTextField nombreEmpresTextField;
    private JTextField nombreEmpleadoTextField;

    // VARIABLES RECOGIDA DATOS
    private String nombreEmpresa;
    private String empleadoPuesto;
    private String descripcionIncidencia;
    private String fecha;
    private String hora;

    // LISTAS
    private List<Incidencia> listaIncidencias = new ArrayList<>();

    // OBJETOS
    CorreoElectronico c1 = new CorreoElectronico();

    public enviarIncidencia() {

        fechaSystem();
        horaSystem();
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recogerIncidencia();
            }
        });
        LIMPIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
    }

    // METODOS

    private void recogerIncidencia() {
        if (nombreEmpresTextField.getText().isEmpty() || nombreEmpleadoTextField.getText().isEmpty() || descripcionTextArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos.", "#ERROR#", JOptionPane.ERROR_MESSAGE);
        } else {
            nombreEmpresa = nombreEmpresTextField.getText().toUpperCase();
            empleadoPuesto = nombreEmpleadoTextField.getText().toUpperCase();
            descripcionIncidencia = descripcionTextArea.getText();
            fecha = fechaIncidenciaTextField.getText();
            hora = horaTextField.getText();

            Incidencia id1 = new Incidencia(nombreEmpresa, empleadoPuesto, descripcionIncidencia, fecha, hora);
            listaIncidencias.add(id1);
            enviarCorreo();
            JOptionPane.showMessageDialog(null, "La incidencia ha sido registrada correctamente", "ENVIADO", JOptionPane.INFORMATION_MESSAGE);
            Window window = SwingUtilities.getWindowAncestor(panelPrincipalEnviarIncidencia);
            if (window instanceof JFrame || window instanceof JDialog) {
                window.dispose();
            }
        }
    }

    private void enviarCorreo() {
        try {
            String correoDestino = c1.getCorreoReceptor();
            String asunto = "SOLICITUD ASISTENCIA - " + nombreEmpresa;

            String cuerpoCorreo = "FECHA: " + fecha + "\n\n";
            cuerpoCorreo += "HORA: " + hora + "\n\n";
            cuerpoCorreo += "NOMBRE / PUESTO: " + empleadoPuesto + "\n\n";
            cuerpoCorreo += "DESCRIPCION: " + descripcionIncidencia;

            // Configurar las propiedades para la conexión al servidor de correo
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.serviciodecorreo.es");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            // Credenciales para autenticación
            final String usuario = c1.getCorreoEmisor();
            final String password = c1.getPassEmisor();

            // Crear una sesión de correo con autenticación
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuario, password);
                }
            });

            // Crear un objeto MimeMessage
            Message message = new MimeMessage(session);

            // Establecer el remitente
            message.setFrom(new InternetAddress(usuario));

            // Establecer el destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));

            // Establecer el asunto
            message.setSubject(asunto);

            // Establecer el cuerpo del correo
            message.setText(cuerpoCorreo);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Correo enviado exitosamente.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void fechaSystem() {
        // Obtener la fecha actual del sistema
        Date fechaActual = new Date();

        // Formatear la fecha en el formato deseado
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = dateFormat.format(fechaActual);

        // Establecer la fecha formateada en el campo fechaIncidenciaTextField
        fechaIncidenciaTextField.setText(fechaFormateada);
    }

    private void horaSystem() {
        // Obtener la hora actual del sistema
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);

        // Formatear la hora y minutos en el formato deseado
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        String horaFormateada = formatoHora.format(calendario.getTime());

        // Establecer la hora formateada en el campo horaTextField
        horaTextField.setText(horaFormateada);
    }

    private static String encodeURIComponent(String s) {
        String result;

        try {
            result = URLEncoder.encode(s, StandardCharsets.UTF_8.toString())
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        } catch (UnsupportedEncodingException e) {
            result = s;
        }

        return result;
    }

    private void reset() {
        nombreEmpleadoTextField.setText("");
        nombreEmpresTextField.setText("");
        fechaIncidenciaTextField.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Enviar Incidencia");
        frame.setContentPane(new enviarIncidencia().panelPrincipalEnviarIncidencia);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getPanelPrincipalEnviarIncidencia() {
        return panelPrincipalEnviarIncidencia;
    }
}


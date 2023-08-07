package org.example.Objetos;

public class CorreoElectronico {

    private String correoEmisor = "incidencias@asitec.es";

    private String correoReceptor = "jose@asitec.es";

    private String passEmisor = "(Inci)2023$";


    public CorreoElectronico() {
    }

    public String getCorreoEmisor() {
        return correoEmisor;
    }

    public String getCorreoReceptor() {
        return correoReceptor;
    }

    public String getPassEmisor() {
        return passEmisor;
    }
}

package org.example.Objetos;

import java.util.List;

public class Incidencia {


    private String nombreEmpresa;
    private String puestoTrabajo;

    private String descripcion;

    private String fecha;

    private String hora;

    public Incidencia(String nombreEmpresa, String puestoTrabajo, String descripcion, String fecha, String hora) {
        this.nombreEmpresa = nombreEmpresa;
        this.puestoTrabajo = puestoTrabajo;
        this.descripcion = descripcion;
        this.hora = hora;
        this.fecha = fecha;
    }



    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

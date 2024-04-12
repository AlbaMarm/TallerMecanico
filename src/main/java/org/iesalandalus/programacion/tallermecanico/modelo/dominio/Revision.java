package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Revision extends Trabajo {
    private static final float FACTOR_HORA = 35.0f;


    public Revision(Cliente cliente, Vehiculo vehiculo,LocalDate fechaInicio ) {
        super(fechaInicio, vehiculo, cliente);
    }

    @Override
    public float getPrecioEspecifico() {
        return getHoras() * FACTOR_HORA;
    }


    public Revision(Revision revision) {
        super(revision);
    }

    @Override
    public String toString() {
        return String.format("Revision[fechaInicio=%s, fechaFin=%s, horas=%s]", this.fechaInicio, this.fechaFin, this.horas);
    }
}

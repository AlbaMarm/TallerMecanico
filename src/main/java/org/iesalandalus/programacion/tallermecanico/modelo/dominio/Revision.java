package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30.0F;
    private static final float PRECIO_DIA = 10.0F;
    private static final float PRECIO_MATERIAL = 1.5F;
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private int horas;
    private float precioMaterial;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        fechaFin = null;
        horas = 0;
        precioMaterial = 0;
    }
    public Revision(Revision revision){
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        cliente = new Cliente(revision.cliente);
        vehiculo = revision.vehiculo;
        fechaInicio = revision.fechaInicio;
        fechaFin = revision.fechaFin;
        horas = revision.horas;
        precioMaterial = revision.precioMaterial;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }
    public Cliente getCliente() {
        return cliente;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaFin(LocalDate fechaFin){
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (fechaFin.isBefore(fechaInicio)){
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        } else if (fechaFin.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        this.fechaFin = fechaFin;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getHoras(){
        return horas;
    }
    private float getDias(){
        return (estaCerrada()) ? (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin) : 0;
    }
    public void anadirHoras(int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(horas, "Las horas no pueden ser nulas.");
        if (horas <= 0){
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        } else if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        this.horas += horas;
    }

    public float getPrecioMaterial(){
        return precioMaterial;
    }
    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if (precioMaterial <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        } else if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        this.precioMaterial += precioMaterial;
    }

    public boolean estaCerrada(){
        return fechaFin != null;
    }
    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        if (estaCerrada()) {
            throw new OperationNotSupportedException("La revisión ya está cerrada.");
        }
        setFechaFin(fechaFin);
    }

    public float getPrecio(){
        float precioHoras  = PRECIO_HORA * getHoras();
        float precioDias = PRECIO_DIA * getDias();
        float precioFijo = precioDias + precioHoras;
        float precioMaterial = getPrecioMaterial() * PRECIO_MATERIAL;
        return precioFijo + precioMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, cliente, vehiculo);
    }

    @Override
    public String toString() {
        String cadena;
        String fechaF = (this.fechaFin != null) ? this.fechaFin.format(FORMATO_FECHA) : "";
        if (!estaCerrada()){
            cadena = String.format("%s - %s: (%s - %s), %s horas, %.2f € en material", this.cliente, this.vehiculo, this.fechaInicio, fechaF, this.horas, this.precioMaterial);
        } else {
            cadena = String.format("%s - %s: (%s - %s), %s horas, %.2f € en material, %.2f € total", this.cliente, this.vehiculo, this.fechaInicio, fechaF, this.horas, this.precioMaterial, getPrecio());
        }
        return cadena;
    }
}

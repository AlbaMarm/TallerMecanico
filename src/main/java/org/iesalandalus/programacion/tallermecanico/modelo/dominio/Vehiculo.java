package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;
import java.util.regex.Pattern;

public record Vehiculo(String marca, String modelo, String matricula) {
    private static final String ER_MARCA = "[A-Z]+[a-z]*([ -]?[A-Z][a-z]+)*";
    private static final String ER_MATRICULA = "\\d{4}[^AEIOU]{3}";


    public Vehiculo{
        validarMarca(marca);
        validarModelo(modelo);
        validarMatricula(matricula);
    }

    public static Vehiculo get(String matricula) {
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula.");
        if (!Pattern.matches(ER_MATRICULA, matricula)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
        return new Vehiculo("Renault", "Megane", matricula);
    }


    public void validarMarca(String marca) {
        Objects.requireNonNull(marca, "La marca no puede ser nula.");
        if (!Pattern.matches(ER_MARCA, marca)) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
    }

    public String marca() {
        return marca;
    }

    public void validarModelo(String modelo) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        if (modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
    }

    public String modelo() {
        return modelo;
    }

    public void validarMatricula(String matricula) {
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula.");
        if (!Pattern.matches(ER_MATRICULA, matricula)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
    }
    public String matricula() {
        return matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(marca, vehiculo.marca) && Objects.equals(modelo, vehiculo.modelo) && Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marca, modelo, matricula);
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s", this.marca, this.modelo, this.matricula);
    }
}

package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Evento {
    INSERTAR_CLIENTE("Insertar cliente",1),
    BUSCAR_CLIENTE("Buscar cliente",2),
    BORRAR_CLIENTE("Borra cliente",3),
    LISTAR_CLIENTES("Listar clientes",4),
    MODIFICAR_CLIENTE("Modificar Clientes",5 ),
    INSERTAR_VEHICULO("Insertar vehiculo",6),
    BUSCAR_VEHICULO("Buscar vehiculo",7),
    BORRAR_VEHICULO("Insertar vehiculo",8),
    LISTAR_VEHICULOS("Listar vehiculos",9),
    INSERTAR_REVISION("Insertar trabajo",10),
    INSERTAR_MECANICO("Insertar mecánico", 11),
    BUSCAR_TRABAJO("Buscar trabajo",12),
    BORRAR_TRABAJO("Borrar trabajo",13),
    LISTAR_TRABAJOS("Listar trabajos",14),
    LISTAR_TRABAJOS_CLIENTES("Listar trabajos por clientes",15),
    LISTAR_TRABAJOS_VEHICULOS("Listar trabajos por vehiculos",16),
    ANADIR_HORAS_TRABAJO("Añadir horas trabajo ",17),
    ANADIR_PRECIO_MATERIAL_TRABAJO("Añadir precio material trabajo",18),
    CERRAR_TRABAJO("Cerrar trabajo",19),
    SALIR("Salir",20);
    private final String texto;
    private final Integer codigo;
    private static final Map<Integer, Evento> eventos = new HashMap<>();
    static {
        for (Evento evento:values()) {
            eventos.put(evento.codigo, evento);
        }
    }
    Evento(String nombre, Integer codigo) {
        this.texto = nombre;
        this.codigo = codigo;
    }
    public static boolean esValido(int numeroOpcion) {
        Objects.requireNonNull(numeroOpcion,"El numero no es valido");
        return eventos.containsKey(numeroOpcion);
    }

    public static Evento get(int numeroOpcion) {
        if (!esValido(numeroOpcion)) {
            throw new IllegalArgumentException("Opción inválida");
        }
        return eventos.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("Evento:%s. %s", this.texto, this.codigo);
    }
}

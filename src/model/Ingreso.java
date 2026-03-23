package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Ingreso {

    private String descripcion; // opcional
    private double importe;
    private LocalDate fecha;
    private String origen; // obligatorio

    public Ingreso(String descripcion, double importe, String fechaStr, String origen) {
        if (importe <= 0) {
            throw new IllegalArgumentException("El importe debe ser mayor que 0");
        }

        if (fechaStr == null || fechaStr.isBlank()) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }

        if (origen == null || origen.isBlank()) {
            throw new IllegalArgumentException("El origen es obligatorio");
        }

        this.descripcion = descripcion;
        this.importe = importe;

        try {
            this.fecha = LocalDate.parse(fechaStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha inválida. Formato correcto: YYYY-MM-DD");
        }

        this.origen = origen;
    }

    // Getter y setter del importe
    public double getImporte() { return importe; }
    public void setImporte(double importe) {
        if (importe <= 0) throw new IllegalArgumentException("El importe debe ser mayor que 0");
        this.importe = importe;
    }

    public String getDescripcion() { return descripcion; }
    public LocalDate getFecha() { return fecha; }
    public String getOrigen() { return origen; }

    @Override
    public String toString() {
        return (descripcion == null || descripcion.isEmpty() ? "" : descripcion + " | ") +
                importe + "€ | " + fecha + " | " + origen;
    }
}
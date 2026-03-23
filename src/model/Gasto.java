package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Gasto {

    public enum Categoria { ALIMENTACION, TRANSPORTE, OCIO, VIVIENDA, SALUD, OTROS }
    public enum MetodoPago { EFECTIVO, TARJETA, TRANSFERENCIA, BIZUM }

    private String descripcion; // opcional
    private Categoria categoria;
    private double importe;
    private LocalDate fecha;
    private MetodoPago metodoPago;

    public Gasto(String descripcion, String categoriaStr, double importe, String fechaStr, String metodoStr) {
        // Validaciones
        if (importe <= 0) {
            throw new IllegalArgumentException("El importe debe ser mayor que 0");
        }

        if (categoriaStr == null || categoriaStr.isBlank()) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }

        if (fechaStr == null || fechaStr.isBlank()) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }

        if (metodoStr == null || metodoStr.isBlank()) {
            throw new IllegalArgumentException("El método de pago es obligatorio");
        }

        // Parsear categoría
        try {
            this.categoria = Categoria.valueOf(categoriaStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoría inválida");
        }

        // Parsear fecha
        try {
            this.fecha = LocalDate.parse(fechaStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha inválida. Formato correcto: YYYY-MM-DD");
        }

        // Parsear método de pago
        try {
            this.metodoPago = MetodoPago.valueOf(metodoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Método de pago inválido");
        }

        this.descripcion = descripcion;
        this.importe = importe;
    }

    // Getter y setter del importe (solo se puede modificar esto)
    public double getImporte() { return importe; }
    public void setImporte(double importe) {
        if (importe <= 0) throw new IllegalArgumentException("El importe debe ser mayor que 0");
        this.importe = importe;
    }

    public Categoria getCategoria() { return categoria; }
    public MetodoPago getMetodoPago() { return metodoPago; }
    public LocalDate getFecha() { return fecha; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return (descripcion == null || descripcion.isEmpty() ? "" : descripcion + " | ") +
                categoria + " | " + importe + "€ | " + fecha + " | " + metodoPago;
    }
}

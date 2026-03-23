package ui;

import model.Gasto;
import model.Ingreso;
import service.FinanzasService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HelloConsole {

    private static Scanner scanner = new Scanner(System.in);
    private static FinanzasService service = new FinanzasService();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n---- MENU PRINCIPAL ----");
            System.out.println("1. Gestionar gastos");
            System.out.println("2. Gestionar ingresos");
            System.out.println("3. Ver resumen financiero");
            System.out.println("0. Salir");

            opcion = leerEntero("Seleccione una opción: ");

            switch(opcion){
                case 1 -> menuGastos();
                case 2 -> menuIngresos();
                case 3 -> mostrarResumen();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }

        } while(opcion != 0);
    }

    // ====================== MENÚ GASTOS ======================
    private static void menuGastos() {
        int opcion;
        do {
            System.out.println("\n--- MENU GASTOS ---");
            System.out.println("1. Añadir gasto");
            System.out.println("2. Listar gastos");
            System.out.println("3. Modificar importe de un gasto");
            System.out.println("4. Eliminar gasto");
            System.out.println("0. Volver al menú principal");

            opcion = leerEntero("Seleccione una opción: ");

            switch(opcion){
                case 1 -> añadirGasto();
                case 2 -> listarGastos();
                case 3 -> modificarGasto();
                case 4 -> eliminarGasto();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida");
            }

        } while(opcion != 0);
    }

    private static void añadirGasto() {
        try {
            System.out.print("Descripción (opcional): ");
            String desc = scanner.nextLine();

            System.out.print("Categoría (ALIMENTACION, TRANSPORTE, OCIO, VIVIENDA, SALUD, OTROS): ");
            String cat = scanner.nextLine();

            double importe = leerDouble("Importe: ");

            System.out.print("Fecha (YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine();

            System.out.print("Método de pago (EFECTIVO, TARJETA, TRANSFERENCIA, BIZUM): ");
            String metodo = scanner.nextLine();

            Gasto g = new Gasto(desc, cat, importe, fechaStr, metodo);
            service.agregarGasto(g);
            System.out.println("Gasto añadido correctamente.");

        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear el gasto: " + e.getMessage());
        }
    }

    private static void listarGastos() {
        List<Gasto> gastos = service.listarGastos();
        if(gastos.isEmpty()){
            System.out.println("No hay gastos registrados.");
            return;
        }
        System.out.println("\n--- LISTADO DE GASTOS ---");
        int index = 1;
        for(Gasto g : gastos){
            System.out.println(index + ". " + g);
            index++;
        }
    }

    private static void modificarGasto() {
        listarGastos();
        int index = leerEntero("Número del gasto a modificar: ") - 1;
        double nuevoImporte = leerDouble("Nuevo importe: ");
        try {
            service.modificarGastoImporte(index, nuevoImporte);
            System.out.println("Gasto actualizado correctamente.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Gasto no encontrado.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarGasto() {
        listarGastos();
        int index = leerEntero("Número del gasto a eliminar: ") - 1;
        try {
            service.eliminarGasto(index);
            System.out.println("Gasto eliminado correctamente.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Gasto no encontrado.");
        }
    }

    // ====================== MENÚ INGRESOS ======================
    private static void menuIngresos() {
        int opcion;
        do {
            System.out.println("\n--- MENU INGRESOS ---");
            System.out.println("1. Añadir ingreso");
            System.out.println("2. Listar ingresos");
            System.out.println("3. Modificar importe de un ingreso");
            System.out.println("4. Eliminar ingreso");
            System.out.println("0. Volver al menú principal");

            opcion = leerEntero("Seleccione una opción: ");

            switch(opcion){
                case 1 -> añadirIngreso();
                case 2 -> listarIngresos();
                case 3 -> modificarIngreso();
                case 4 -> eliminarIngreso();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida");
            }

        } while(opcion != 0);
    }

    private static void añadirIngreso() {
        try {
            System.out.print("Descripción (opcional): ");
            String desc = scanner.nextLine();

            double importe = leerDouble("Importe: ");

            System.out.print("Fecha (YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine();

            System.out.print("Origen: ");
            String origen = scanner.nextLine();

            Ingreso i = new Ingreso(desc, importe, fechaStr, origen);
            service.agregarIngreso(i);
            System.out.println("Ingreso añadido correctamente.");

        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear el ingreso: " + e.getMessage());
        }
    }

    private static void listarIngresos() {
        List<Ingreso> ingresos = service.listarIngresos();
        if(ingresos.isEmpty()){
            System.out.println("No hay ingresos registrados.");
            return;
        }
        System.out.println("\n--- LISTADO DE INGRESOS ---");
        int index = 1;
        for(Ingreso i : ingresos){
            System.out.println(index + ". " + i);
            index++;
        }
    }

    private static void modificarIngreso() {
        listarIngresos();
        int index = leerEntero("Número del ingreso a modificar: ") - 1;
        double nuevoImporte = leerDouble("Nuevo importe: ");
        try {
            service.modificarIngresoImporte(index, nuevoImporte);
            System.out.println("Ingreso actualizado correctamente.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ingreso no encontrado.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarIngreso() {
        listarIngresos();
        int index = leerEntero("Número del ingreso a eliminar: ") - 1;
        try {
            service.eliminarIngreso(index);
            System.out.println("Ingreso eliminado correctamente.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ingreso no encontrado.");
        }
    }

    // ====================== RESUMEN FINANCIERO ======================
    private static void mostrarResumen() {
        System.out.println("\n--- RESUMEN FINANCIERO ---");
        System.out.println("Saldo total: " + service.saldo() + "€");

        System.out.println("\nTotal de gastos por categoría:");
        Map<Gasto.Categoria, Double> totales = service.totalGastosPorCategoria();
        for(Gasto.Categoria cat : Gasto.Categoria.values()) {
            System.out.println(cat + ": " + totales.get(cat) + "€");
        }
    }

    // ====================== MÉTODOS AUXILIARES ======================
    private static int leerEntero(String mensaje) {
        int valor;
        while(true){
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un número entero.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        double valor;
        while(true){
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine());
                if(valor <= 0){
                    System.out.println("El importe debe ser mayor que 0.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un número decimal válido.");
            }
        }
    }
}

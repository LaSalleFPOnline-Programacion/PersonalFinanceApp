package service;

import model.Gasto;
import model.Ingreso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinanzasService {

    private List<Gasto> gastos = new ArrayList<>();
    private List<Ingreso> ingresos = new ArrayList<>();

    // --- GASTOS ---

    public void agregarGasto(Gasto gasto) {
        gastos.add(gasto);
    }

    public List<Gasto> listarGastos() {
        return gastos;
    }

    public void eliminarGasto(int index) {
        if(index >= 0 && index < gastos.size()) {
            gastos.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Gasto no encontrado");
        }
    }

    public void modificarGastoImporte(int index, double nuevoImporte) {
        if(index >= 0 && index < gastos.size()) {
            gastos.get(index).setImporte(nuevoImporte);
        } else {
            throw new IndexOutOfBoundsException("Gasto no encontrado");
        }
    }

    public double totalGastos() {
        double total = 0;
        for(Gasto g : gastos){
            total += g.getImporte();
        }
        return total;
    }

    public Map<Gasto.Categoria, Double> totalGastosPorCategoria() {
        Map<Gasto.Categoria, Double> totales = new HashMap<>();
        for(Gasto.Categoria cat : Gasto.Categoria.values()) {
            totales.put(cat, 0.0);
        }
        for(Gasto g : gastos){
            totales.put(g.getCategoria(), totales.get(g.getCategoria()) + g.getImporte());
        }
        return totales;
    }

    // --- INGRESOS ---

    public void agregarIngreso(Ingreso ingreso) {
        ingresos.add(ingreso);
    }

    public List<Ingreso> listarIngresos() {
        return ingresos;
    }

    public void eliminarIngreso(int index) {
        if(index >= 0 && index < ingresos.size()) {
            ingresos.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Ingreso no encontrado");
        }
    }

    public void modificarIngresoImporte(int index, double nuevoImporte) {
        if(index >= 0 && index < ingresos.size()) {
            ingresos.get(index).setImporte(nuevoImporte);
        } else {
            throw new IndexOutOfBoundsException("Ingreso no encontrado");
        }
    }

    public double totalIngresos() {
        double total = 0;
        for(Ingreso i : ingresos){
            total += i.getImporte();
        }
        return total;
    }

    // --- RESUMEN ---

    public double saldo() {
        return totalIngresos() - totalGastos();
    }
}
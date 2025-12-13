package modelo;

public class PlanAlimentacion {
    private String tipo;
    private double precio;
    private String descripcion;

    public PlanAlimentacion(String tipo, double precio, String descripcion) {
        this.tipo = tipo;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void mostrarInfo() {
        System.out.println("Plan " + tipo);
        System.out.println("Precio: $" + precio);
        System.out.println("Incluye: " + descripcion);
    }
}
package modelo;

public class Reserva {
    private Habitacion habitacion;
    private String fechaEntrada;
    private String fechaSalida;
    private PlanAlimentacion planAlimentacion;

    public Reserva(Cliente cliente, Habitacion habitacion, String fechaEntrada, String fechaSalida, PlanAlimentacion plan) {
        
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.planAlimentacion = plan;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public PlanAlimentacion getPlanAlimentacion() {
        return planAlimentacion;
    }

    public double getPrecioTotal() {
        double total = habitacion.getPrecio();
        if (planAlimentacion != null) {
            total += planAlimentacion.getPrecio();
        }
        return total;
    }
}
package modelo;

import java.util.ArrayList;

public class Cliente extends Usuario {
    private ArrayList<Reserva> reservas;

    public Cliente(String nombre, String dni, String correo) {
        super(nombre, dni, correo);
        this.reservas = new ArrayList<>();
    }

    public void reservarHabitacion(Habitacion habitacion, String fechaEntrada, String fechaSalida, PlanAlimentacion plan) {
        Reserva nuevaReserva = new Reserva(this, habitacion, fechaEntrada, fechaSalida, plan);
        reservas.add(nuevaReserva);
        habitacion.setDisponible(false);
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void cancelarReserva(int numeroHabitacion) {
        Reserva reservaAEliminar = null;
        for (Reserva r : reservas) {
            if (r.getHabitacion().getNumero() == numeroHabitacion) {
                reservaAEliminar = r;
                break;
            }
        }
        
        if (reservaAEliminar != null) {
            reservaAEliminar.getHabitacion().setDisponible(true);
            reservas.remove(reservaAEliminar);
        }
    }
}
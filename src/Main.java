import vista.HotelVista;
import controlador.HotelControlador;

public class Main {
    public static void main(String[] args) {
        HotelVista vista = new HotelVista();
        HotelControlador controlador = new HotelControlador(vista);
        controlador.iniciar();
        vista.cerrar();
    }
}
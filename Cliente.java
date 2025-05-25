package comercio;

public class Cliente extends Persona {

    private final String idCliente;

    public Cliente(String nombre, String email, int edad, String cedula, String idCliente) {
        super(nombre, email, edad, cedula);
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    @Override
    public String toString() {

        return String.format("%s ID del Comprador: %s.%n", super.toString(),this.idCliente);
    }
}

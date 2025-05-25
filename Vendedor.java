package comercio;

public class Vendedor extends Persona {

    private final String IdVendedor;

    public Vendedor(String nombre, String email, int edad, String cedula, String idVendedor) {
        super(nombre, email, edad, cedula);
        IdVendedor = idVendedor;
    }

    public String getIdVendedor() {
        return IdVendedor;
    }

    @Override
    public String toString() {

        return String.format("%s ID del Vendedor: %s.%n", super.toString(),this.IdVendedor);
    }
}

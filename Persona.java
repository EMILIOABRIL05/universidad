package comercio;

public abstract class Persona {

    protected final String nombre;
    protected String email;
    protected int edad;
    protected final String cedula;

    public Persona(String nombre, String email, int edad, String cedula) {
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCedula() {
        return cedula;
    }

    @Override
    public String toString() {

        return String.format("%s(%d). CI: %s. Email: %s.", this.nombre, this.email, this.edad, this.cedula);
    }
}

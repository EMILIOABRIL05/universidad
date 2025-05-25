package comercio;

import java.util.Objects;

public class Producto {

    private final String idProducto;
    private final String nombreProducto;
    private double precioProducto;
    private final double pesoProducto;
    private int stock;

    public Producto(String idProducto, String nombreProducto, double precioProducto, double pesoProducto, int stock) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.pesoProducto = pesoProducto;
        this.stock = stock;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public double getPesoProducto() {
        return pesoProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void reducirStock(int reduccion){

        this.stock -= reduccion; // this.stock = this.stock - reduccion;
    }

    @Override
    public boolean equals(Object obj){

        if (this.equals(obj)) {
            return true;
        }

        if(!(obj instanceof Producto)){
            return false;
        }

        Producto p = (Producto) obj;
        return this.idProducto.equals(p.idProducto) && this.pesoProducto == p.pesoProducto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idProducto, this.pesoProducto);
    }

    @Override
    public String toString() {

        return String.format("%s(%s), de %.2f gramos. Precio final: %.2f. Disponibles: %d", this.nombreProducto, this.idProducto, this.pesoProducto, this.precioProducto, this.stock);
    }
}

package comercio;

public class ItemOrden {

    private Producto producto;
    private int cantidad; // la creacion del item obliga que al inicio no venga vacio

    public ItemOrden(Producto producto, int cantidad) throws ExCantidadInvalida {
        if (cantidad <= 0) {
            throw new ExCantidadInvalida("No se puede crear un item con stock negativo");
        }

        if (cantidad > producto.getStock()) {
            throw new ExCantidadInvalida("No se puede crear un item de venta con una cantidad mayor a la existente");
        }

        this.producto = producto;
        this.cantidad = cantidad;

    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void procesarItem() {

        this.producto.reducirStock(this.cantidad);
    }

    public double subTotalItem() {

        return this.producto.getPrecioProducto() * this.cantidad;
    }
    @Override
    public String toString() {
        return String.format("%s - Cantidad: %d - Subtotal: %.2f",
                producto.toString(),
                cantidad,
                subTotalItem());
    }
}


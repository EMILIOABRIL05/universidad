package comercio;

public class Orden implements Descuentos, Pagos {
    private ItemOrden item1, item2, item3;
    private double montoPago;
    private Cliente cliente;
    private Vendedor vendedor;
    private static int idBase = 10000;
    private final int idOrden;

    public Orden(Cliente cliente, Vendedor vendedor) {
        this.idOrden = idBase++;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public ItemOrden getItem1() {
        return item1;
    }

    public void setItem1(ItemOrden item1) {
        this.item1 = item1;
    }

    public ItemOrden getItem2() {
        return item2;
    }

    public void setItem2(ItemOrden item2) {
        this.item2 = item2;
    }

    public ItemOrden getItem3() {
        return item3;
    }

    public void setItem3(ItemOrden item3) {
        this.item3 = item3;
    }

    public double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void agregarItem(Producto producto, int cantidad) throws ExCantidadInvalida {
        if (cantidad <= 0) {
            throw new ExCantidadInvalida("La cantidad debe ser mayor a 0");
        }

        if (cantidad > producto.getStock()) {
            throw new ExCantidadInvalida("No hay suficiente stock. Disponible: " + producto.getStock());
        }

        if (item1 == null) {
            item1 = new ItemOrden(producto, cantidad);
        } else if (item2 == null) {
            if (producto.getNombreProducto().equals(item1.getProducto().getNombreProducto())) {
                throw new ExCantidadInvalida("Producto duplicado: " + producto.getNombreProducto());
            }
            item2 = new ItemOrden(producto, cantidad);
        } else if (item3 == null) {
            if (producto.getNombreProducto().equals(item1.getProducto().getNombreProducto()) ||
                    producto.getNombreProducto().equals(item2.getProducto().getNombreProducto())) {
                throw new ExCantidadInvalida("Producto duplicado: " + producto.getNombreProducto());
            }
            item3 = new ItemOrden(producto, cantidad);
        } else {
            throw new ExCantidadInvalida("Solo se permiten 3 items por orden");
        }
    }

    @Override
    public double calculoTotal() {
        double total = 0;
        if (item1 != null) total += item1.subTotalItem();
        if (item2 != null) total += item2.subTotalItem();
        if (item3 != null) total += item3.subTotalItem();
        return total;
    }

    @Override
    public double aplicarDescuento(double total) {
        return total * 0.9; // 10% de descuento
    }

    @Override
    public void pago(double monto) throws ExPagos {
        double totalConDescuento = aplicarDescuento(calculoTotal());

        if (monto < totalConDescuento) {
            throw new ExPagos(String.format("El monto a pagar no es suficiente, falta: $%.2f",
                    (totalConDescuento - monto)));
        }

        this.montoPago = monto;

        // Procesar los items
        if (item1 != null) item1.procesarItem();
        if (item2 != null) item2.procesarItem();
        if (item3 != null) item3.procesarItem();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Pedido #%d%n", this.idOrden));
        sb.append(String.format("Cliente: %s%n", this.cliente.getNombre()));
        sb.append(String.format("Vendedor: %s%n", this.vendedor.getNombre()));
        sb.append("Items:%n");

        if (item1 != null) sb.append(String.format("- %s%n", item1.toString()));
        if (item2 != null) sb.append(String.format("- %s%n", item2.toString()));
        if (item3 != null) sb.append(String.format("- %s%n", item3.toString()));

        double subtotal = calculoTotal();
        double total = aplicarDescuento(subtotal);

        sb.append(String.format("Subtotal: $%.2f%n", subtotal));
        sb.append(String.format("Descuento: $%.2f%n", subtotal - total));
        sb.append(String.format("Total: $%.2f%n", total));

        if (montoPago > 0) {
            sb.append(String.format("Pagado: $%.2f%n", montoPago));
            if (montoPago > total) {
                sb.append(String.format("Cambio: $%.2f%n", montoPago - total));
            }
        }

        return sb.toString();
    }
}
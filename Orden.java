package comercio;

public class Orden implements Descuentos, Pagos{

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

    public void agregarItem(Producto producto, int cantidad) throws ExCantidadInvalida{

        if (item1 == null){

            item1 = new ItemOrden(producto, cantidad);
        }else if (item2 == null){
            item2 = new ItemOrden(producto, cantidad);
            if (producto.getNombreProducto().equals(item1.getProducto().getNombreProducto())){

                throw new ExCantidadInvalida("Producto duplicado: " + producto.getNombreProducto());
            }
        }else if (item3 == null){

            if (producto.getNombreProducto().equals(item1.getProducto().getNombreProducto()) || producto.getNombreProducto().equals(item2.getProducto().getNombreProducto())){

                throw new ExCantidadInvalida("Producto duplicado: " + producto.getNombreProducto());
            }

            item3 = new ItemOrden(producto, cantidad);
        }else {
            throw new ExCantidadInvalida("Solo se aceptan 3 items");
        }

    }

    @Override
    public double calculoTotal(){

        double total = 0;

        if (item1 != null){

            total += item1.subTotalItem();
        }

        if (item2 != null){

            total += item2.subTotalItem();
        }

        if (item3 != null){

            total += item3.subTotalItem();
        }

        return total;
    }

    @Override
    public double aplicarDescuento(double total){

        total *= 0.9;
        return total;
    }

    @Override
    public void pago(double monto) throws ExPagos{

        double total = aplicarDescuento(calculoTotal());

        if (montoPago < total){

            throw new ExPagos("El monto a pagar no es suficiente, falta: " + (total - montoPago));
        }
        this.montoPago = monto;
        if (item1 != null){

            item1.procesarItem();
        }

        if (item2 != null){

            item2.procesarItem();
        }

        if (item3 != null){

            item3.procesarItem();
        }
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        String s = String.format("Pedido # %d.%nCliente: %s%nVendedor: %s%nItems:%n.", this.idOrden, this.cliente.getNombre(), this.vendedor.getNombre());
        if(item1 != null){

            sb.append("- ").append(item1.toString()).append("\n");
        }
        if(item2 != null){

            sb.append("- ").append(item2.toString()).append("\n");
        }
        if(item3 != null){

            sb.append("- ").append(item3.toString()).append("\n");
        }
        sb.append(String.format("Descuento Aplicado: %.2f", aplicarDescuento(calculoTotal())));
        return s + sb.toString();
    }
}

package erp.modelo;

import java.util.ArrayList;

public class PedidoDeCompra extends Pedido {
	private Fabricante fabricante;
	
	public PedidoDeCompra(ArrayList<Produto> produtos, Fabricante fabricante, int id) {
		super(produtos, id);
		this.fabricante = fabricante;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Fabricante: " + this.fabricante.getNomeFantasia() + "\n";
	}
}

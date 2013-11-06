package erp.modelo;

import java.util.ArrayList;

public class PedidoDeVenda extends Pedido {
	
	private Cliente cliente;

	public PedidoDeVenda(ArrayList<Produto> produtos, Cliente cliente, int id) {
		super(produtos, id);
		this.cliente = cliente;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Cliente: " + this.cliente.getNomeFantasia() + "\n";
	}
}

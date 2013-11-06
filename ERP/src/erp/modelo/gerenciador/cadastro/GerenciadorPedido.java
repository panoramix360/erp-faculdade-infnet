package erp.modelo.gerenciador.cadastro;

import java.util.ArrayList;

import erp.modelo.Pedido;

public class GerenciadorPedido extends GerenciadorCadastro<Pedido> {
	
	private static volatile GerenciadorPedido instance;
	
	private ArrayList<Pedido> pedidos;
	
	private GerenciadorPedido() {
		pedidos = new ArrayList<Pedido>();
	}
			
	@Override
	public void cadastrar(Pedido pedido) {
		if( pedido != null ) {
			pedidos.add(pedido);
		}
	}

	@Override
	public ArrayList<Pedido> listar() {
		return this.pedidos;
	}
	
	public Pedido getPedido(int id) {
		return buscaPedidoID(id);
	}
	
	private Pedido buscaPedidoID(int id) {
		for( Pedido pedido : pedidos ) {
			if( pedido.getId() == id ) {
				return pedido;
			}
		}
		return null;
	}
	
	public static GerenciadorPedido getInstance() {
		if( instance == null ) {
			synchronized (GerenciadorPedido.class) {
				if( instance == null ) {
					instance = new GerenciadorPedido();
					System.out.println("Criado Gerenciador Pedido");
				}
			}
		}
		
		return instance;
	}

	public int getUltimoIdPedido() {
		if (pedidos.isEmpty())
			return 1;
			
		return pedidos.size() + 1;
	}
}
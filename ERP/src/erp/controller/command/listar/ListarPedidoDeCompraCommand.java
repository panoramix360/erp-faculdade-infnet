package erp.controller.command.listar;

import java.util.ArrayList;
import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Pedido;
import erp.modelo.PedidoDeCompra;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorPedido;

public class ListarPedidoDeCompraCommand implements Command {
	
	private String nomeMenu;
	
	public ListarPedidoDeCompraCommand() {
		this.nomeMenu = "Pedido de Compra";
	}
	
	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		GerenciadorPedido gerencPedido = 
				(GerenciadorPedido) factory.criarGerenciadorCadastro("pedido");
		ArrayList<Pedido> pedidos = gerencPedido.listar();
		
		
		System.out.println("\n\n\n\n\n\n****************************************");
		System.out.println("---------- Pedidos de Compra -----------");
		System.out.println("****************************************");
		
		System.out.println("\n\n========================================");
		System.out.println("---------- Pedidos Pendentes -----------");
		System.out.println("========================================");
		for( Pedido pedido : pedidos ) {
			if( pedido instanceof PedidoDeCompra && pedido.getStatus().equals("Pendente") ) {
				System.out.println(pedido);
			}
		}
		System.out.println("========================================");
		System.out.println("\n\n########################################");
		
		System.out.println("\n\n========================================");
		System.out.println("--------- Pedidos Finalizados ----------");
		System.out.println("========================================\n");
		for( Pedido pedido : pedidos ) {
			if( pedido instanceof PedidoDeCompra && pedido.getStatus().equals("Finalizado") ) {
				System.out.println(pedido);
			}
		}
		System.out.println("========================================");
		System.out.println("\nPressione Enter para continuar...");
		input.nextLine();
	}
	
	@Override
	public String getNomeMenu() {
		return this.nomeMenu;
	}
}

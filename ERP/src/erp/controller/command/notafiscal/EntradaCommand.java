package erp.controller.command.notafiscal;

import java.util.ArrayList;
import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Endereco;
import erp.modelo.Pedido;
import erp.modelo.PedidoDeCompra;
import erp.modelo.gerenciador.GerenciadorEstoque;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorPedido;

public class EntradaCommand implements Command {

	private String nomeMenu;

	public EntradaCommand() {
		this.nomeMenu = "Entrada";
	}
	
	/*
	 * Método execute - EntradaCommand
	 * Ele lista os pedidos de COMPRA para o Usuário
	 * Após o usuário escolher o pedido, os produtos deste pedido são estocados no estoque
	 * @return void @args Scanner e o Factory dos Gerenciadores
	 */
	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		System.out.println("\n\n\n\n=============================================================================");
		System.out.println("========= Favor selecione um pedido para dar entrada na nota fiscal =========");
		System.out.println("=============================================================================");
		// Listar pedidos cadastrados no sistema (se estiverem pendentes)
		GerenciadorPedido gerencPedido = (GerenciadorPedido) factory.criarGerenciadorCadastro("pedido");
		ArrayList<Pedido> pedidos = gerencPedido.listar();
		boolean existePendente = false;
		for( Pedido pedido : pedidos ) {
			if( pedido instanceof PedidoDeCompra && pedido.getStatus().equals("Pendente") ) {
				System.out.println(pedido);
				existePendente = true;
			}
		}
		
		if (!existePendente) {
			System.err.println("Não existem pedidos pendentes no sistema\n");
		
		} else {
			System.out.println("Digite o id do pedido de compra:");
			int id = input.nextInt();
			input.nextLine();
			
			//Recupera a referência do pedido que o usuário escolheu
			Pedido pedido = gerencPedido.getPedido(id);
			
			if( pedido != null && pedido.getStatus().equals("Pendente") ) {
				System.out.println("\n\n\n\n=========================");
				System.out.println("|   Pedido selecionado: |");
				System.out.println("=========================\n");
				System.out.println(pedido);
				
				System.out.println("Confirma?(s/n)");
				
				if( input.nextLine().equals("s") ) {
					
					// Pega o endereço que o usuário digitou
					System.out.println("Digite o endereço: (Rua / Coluna / Andar)");
					String[] endereco = input.nextLine().split("/");
					String rua = endereco[0].trim();
					String coluna = endereco[1].trim();
					String andar = endereco[2].trim();
					Endereco enderecoObj = new Endereco(rua, Integer.parseInt(coluna), Integer.parseInt(andar), pedido.getProdutos()); 
					
					GerenciadorEstoque gerencEstoque = factory.criarGerenciadorEstoque();
					
					//Altera o status do Pedido
					pedido.setStatus("Finalizado");
	
					gerencEstoque.estocarProduto(enderecoObj);
					
					System.out.println("Entrada efetuada com sucesso!");
				} else {
					System.err.println("Entrada cancelada");
				}
			} else {
				System.out.println("Pedido não encontrado.");
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

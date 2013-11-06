package erp.controller.command.cadastrar;

import java.util.ArrayList;
import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Cliente;
import erp.modelo.Endereco;
import erp.modelo.Pedido;
import erp.modelo.PedidoDeVenda;
import erp.modelo.Produto;
import erp.modelo.gerenciador.GerenciadorEstoque;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorCliente;
import erp.modelo.gerenciador.cadastro.GerenciadorPedido;
import erp.modelo.gerenciador.cadastro.GerenciadorProduto;

public class CadastrarPedidoDeVendaCommand implements Command {
	
	private String nomeMenu;
	
	public CadastrarPedidoDeVendaCommand() {
		this.nomeMenu = "Pedido de Venda";
	}

	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		Pedido pedidoCadastrado = null;
		ArrayList<Produto> itensDoPedido = new ArrayList<Produto>();
		GerenciadorEstoque gerencEstoque = (GerenciadorEstoque) factory.criarGerenciadorEstoque();
		GerenciadorProduto gerencProduto = (GerenciadorProduto) factory.criarGerenciadorCadastro("produto");
		
		// Clientes do sistema
		GerenciadorCliente gerencCliente = (GerenciadorCliente) factory.criarGerenciadorCadastro("cliente");
		ArrayList<Cliente> clientes = gerencCliente.listar();
		System.out.println("\n\n\n\n\n\n========================================");
		System.out.println("===== Cadastro de Pedido de Venda =====");
		System.out.println("========================================");
		
		System.out.println("\n\n--------------- Clientes cadastrados --------------");
		for( Cliente cliente : clientes ) {
			System.out.println(cliente);
		}
		System.out.println("------------------------------------------------------");
		
		System.out.println("\nDigite o CNPJ do cliente");
		String cnpjCliente = input.nextLine();
		if (!gerencCliente.isClienteCadastrado(cnpjCliente)) {
			System.out.println("Não existe Cliente com este CNPJ.");
		} else {
			
			ArrayList<Endereco> enderecosDoEstoque = gerencEstoque.listarEstoque();
			System.out.println("\n\n------- Produtos disponíveis no estoque --------");
			for( Endereco endereco : enderecosDoEstoque) {
				ArrayList<Produto> produtos = endereco.getProdutosArmazenados();
				ArrayList<Integer> quantidades = endereco.getQuantidade();
				
				if (produtos.isEmpty()) {
					System.err.println("Não existem produtos no estoque!\n\n");
				} else {
					for ( int i = 0; i < produtos.size(); i++ ) {
						System.out.println(produtos.get(i));
						System.out.println("Quantidade: " + quantidades.get(i));
						System.out.println("-----------------------------------------");
					}
				}
			}
			System.out.println("---------------------------------------------------");
			
			// Loop para a escolha de produtos
			int count = 1;
			do {
				System.out.println("\nPara parar concluir o pedido digite 0.");
				System.out.println("Digite o código do produto "+ count +":");
				int codigoDoProduto = input.nextInt();
				
				if( codigoDoProduto == 0 ) {
					break;
				} else if( gerencEstoque.isProdutoNoEstoque(codigoDoProduto) ) {
					Produto produtoEncontrado = gerencProduto.getProduto(codigoDoProduto);
					itensDoPedido.add(produtoEncontrado);
					count++;
				} else {
					System.err.println("\nProduto não existe no estoque.");
				}
			} while(true);
			input.nextLine();
			
			
			if (!itensDoPedido.isEmpty()) {
				Cliente cliente = gerencCliente.getCliente(cnpjCliente);
				GerenciadorPedido gerencPedido = (GerenciadorPedido) factory.criarGerenciadorCadastro("pedido");
				
				pedidoCadastrado = new PedidoDeVenda(itensDoPedido, cliente, gerencPedido.getUltimoIdPedido());
				
				if( pedidoCadastrado != null ) {
					
					// Adiciona o pedido no repositorio e reserva os itens
					if ( gerencEstoque.reservarProdutoDoPedido(pedidoCadastrado) ) {
						gerencPedido.cadastrar(pedidoCadastrado);
						System.out.println("\n\nPedido de Compra cadastrado com sucesso.");
					} else {
						System.err.println("Não existem produtos no estoque!\n\n");
					}
				}
			} else {
				System.err.println("\nPedido cancelado, não é possível cadastrar pedido sem itens.");
			}
		}
		System.out.println("\nPressione Enter para continuar...");
		input.nextLine();
	}

	@Override
	public String getNomeMenu() {
		return this.nomeMenu;
	}
}

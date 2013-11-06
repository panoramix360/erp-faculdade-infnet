package erp.controller.command.cadastrar;

import java.util.ArrayList;
import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Fabricante;
import erp.modelo.Pedido;
import erp.modelo.PedidoDeCompra;
import erp.modelo.Produto;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorFabricante;
import erp.modelo.gerenciador.cadastro.GerenciadorPedido;
import erp.modelo.gerenciador.cadastro.GerenciadorProduto;

public class CadastrarPedidoDeCompraCommand implements Command {

	private String nomeMenu;
	
	public CadastrarPedidoDeCompraCommand() {
		this.nomeMenu = "Pedido de Compra";
	}
	
	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		Pedido pedidoCadastrado = null;
		ArrayList<Produto> itensDoPedido = new ArrayList<Produto>();
		
		// Fabricantes do sistema
		GerenciadorFabricante gerencFabricante = (GerenciadorFabricante) factory.criarGerenciadorCadastro("fabricante");
		ArrayList<Fabricante> fabricantes = gerencFabricante.listar();
		System.out.println("\n\n\n\n\n\n========================================");
		System.out.println("===== Cadastro de Pedido de Compra =====");
		System.out.println("========================================");
		
		System.out.println("\n\n--------------- Fabricantes cadastrados --------------");
		for( Fabricante fabricante : fabricantes ) {
			System.out.println(fabricante);
		}
		System.out.println("------------------------------------------------------");
		
		System.out.println("\nDigite o CNPJ do fabricante");
		String cnpjFabricante = input.nextLine();
		if (!gerencFabricante.isFabricanteCadastrado(cnpjFabricante)) {
			System.out.println("Não existe Fabricante com este CNPJ.");
		} else {
			
			// Exibindo produtos por fabricante
			GerenciadorProduto gerencProduto = (GerenciadorProduto) factory.criarGerenciadorCadastro("produto");
			ArrayList<Produto> produtos = gerencProduto.listar();
			System.out.println("\n\n--------------- Produtos disponíveis --------------");
			for( Produto produto : produtos ) {
				if( produto.getFabricante().getCnpj().equals(cnpjFabricante) ) {
					System.out.println(produto);
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
				} else if (gerencProduto.isProdutoDoFabricante(codigoDoProduto, cnpjFabricante)) {
					Produto produtoEncontrado = gerencProduto.getProduto(codigoDoProduto);
					itensDoPedido.add(produtoEncontrado);
					count++;
				} else {
					System.err.println("\nProduto não pertence ao fabricante do pedido.");
				}
			} while(true);
			input.nextLine();
			
			if (!itensDoPedido.isEmpty()) {
				Fabricante fabricante = gerencFabricante.getFabricante(cnpjFabricante);
				GerenciadorPedido gerencPedido = (GerenciadorPedido) factory.criarGerenciadorCadastro("pedido");
				
				pedidoCadastrado = new PedidoDeCompra(itensDoPedido, fabricante, gerencPedido.getUltimoIdPedido());
				if( pedidoCadastrado != null ) {
					gerencPedido.cadastrar(pedidoCadastrado);
					System.out.println("\n\nPedido de Compra cadastrado com sucesso.");
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

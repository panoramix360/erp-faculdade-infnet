package erp.controller.command.listar;

import java.util.ArrayList;
import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Endereco;
import erp.modelo.Produto;
import erp.modelo.gerenciador.GerenciadorEstoque;
import erp.modelo.gerenciador.GerenciadorFactory;

public class ListarEstoqueCommand implements Command {
	
	private String nomeMenu;
	
	public ListarEstoqueCommand() {
		this.nomeMenu = "Estoque";
	}
	
	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		GerenciadorEstoque gerencEstoque = 
				(GerenciadorEstoque) factory.criarGerenciadorEstoque();
		
		ArrayList<Endereco> enderecosDoEstoque = gerencEstoque.listarEstoque();
		
		System.out.println("\n\n\n\n\n\n########################################");
		System.out.println("##              Estoque               ##");
		System.out.println("########################################");
		for( Endereco endereco : enderecosDoEstoque) {
			ArrayList<Produto> produtos = endereco.getProdutosArmazenados();
			ArrayList<Integer> quantidades = endereco.getQuantidade();
			
			System.out.println("\n=========================================");
			System.out.println("  Endereco - "+ endereco);
			System.out.println("=========================================\n");
			for ( int i = 0; i < produtos.size(); i++ ) {
				System.out.println(produtos.get(i));
				System.out.println("Quantidade: " + quantidades.get(i));
				System.out.println("-----------------------------------------");
			}
		}
		System.out.println("=========================================");
		System.out.println("\nPressione Enter para continuar...");
		input.nextLine();
	}
	
	@Override
	public String getNomeMenu() {
		return this.nomeMenu;
	}
}

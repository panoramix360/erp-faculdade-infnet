package erp.controller.command.listar;

import java.util.ArrayList;
import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Produto;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorProduto;

public class ListarProdutoCommand implements Command {
	
	private String nomeMenu;
	
	public ListarProdutoCommand() {
		this.nomeMenu = "Produto";
	}
	
	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		GerenciadorProduto gerencProduto = 
				(GerenciadorProduto) factory.criarGerenciadorCadastro(this.nomeMenu.toLowerCase());
		ArrayList<Produto> produtos = gerencProduto.listar();
		
		System.out.println("\n\n\n\n\n\n========================================");
		System.out.println("========= Produtos Cadastrados =========");
		System.out.println("========================================");
		for( Produto produto : produtos ) {
			System.out.println(produto);
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

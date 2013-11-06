package erp.controller.command.cadastrar;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Fabricante;
import erp.modelo.Produto;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorFabricante;
import erp.modelo.gerenciador.cadastro.GerenciadorProduto;

public class CadastrarProdutoCommand implements Command {

	private String nomeMenu;
	
	public CadastrarProdutoCommand() {
		this.nomeMenu = "Produto";
	}
	
	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		Produto produtoCadastrado = null;
		GerenciadorFabricante gerencFabricante = (GerenciadorFabricante) factory.criarGerenciadorCadastro("fabricante");
		
		System.out.println("\n\n\n\n\n\n========================================");
		System.out.println("========= Cadastro de Produtos =========");
		System.out.println("========================================");
		System.out.println("Digite o código do produto:");
		int codigoDoProduto = input.nextInt();
		input.nextLine();
		
		System.out.println("\nDigite o nome:");
		String nome = input.nextLine();
		
		System.out.println("\nDigite o descrição:");
		String descricao = input.nextLine();
		
		System.out.println("\nDigite o preço:");
		double preco;
		try {
			preco = input.nextDouble();
		} catch (InputMismatchException e) {
			System.err.println("\nSomente números são aceitos.\nTente novamente:");
			input.nextLine();
			preco = input.nextDouble();
		}
		input.nextLine();
		
		ArrayList<Fabricante> fabricantes = gerencFabricante.listar();
		
		System.out.println("\n\n========= Selecione o Fabricante do produto =========");
		for( Fabricante fabricante : fabricantes ) {
			System.out.println(fabricante);
		}
		System.out.println("-----------------------------------------------------");
		
		System.out.println("\nDigite o CNPJ do Fabricante:");
		String cnpjFabricante = input.nextLine();

		if (gerencFabricante.isFabricanteCadastrado(cnpjFabricante)) {
			Fabricante fabricante = gerencFabricante.getFabricante(cnpjFabricante);
			produtoCadastrado = new Produto(codigoDoProduto, nome, descricao, fabricante, preco);

			if (produtoCadastrado != null) {
				GerenciadorProduto gerencProduto = (GerenciadorProduto) factory
						.criarGerenciadorCadastro(this.nomeMenu.toLowerCase());

				gerencProduto.cadastrar(produtoCadastrado);
				System.out.println("\nProduto cadastrado com sucesso.");
			}

		} else {
			System.err.println("\nNão existe Fabricante com este CNPJ");
		}
		System.out.println("\nPressione Enter para continuar...");
		input.nextLine();
	}
	
	@Override
	public String getNomeMenu() {
		return this.nomeMenu;
	}
}

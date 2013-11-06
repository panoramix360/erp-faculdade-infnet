package erp.controller.command.cadastrar;

import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Fabricante;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorFabricante;

public class CadastrarFabricanteCommand implements Command {

	private String nomeMenu;
	
	public CadastrarFabricanteCommand() {
		this.nomeMenu = "Fabricante";
	}
	
	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		Fabricante fabricanteCadastrado = null;
		
		System.out.println("\n\n\n\n\n\n========================================");
		System.out.println("======== Cadastro de Fabricantes =======");
		System.out.println("========================================");
		System.out.println("Digite o cnpj:");
		String cnpj = input.nextLine();
		
		System.out.println("\nDigite o nome fantasia:");
		String nomeFantasia = input.nextLine();
		
		fabricanteCadastrado = new Fabricante(cnpj, nomeFantasia);
		if( fabricanteCadastrado != null ) {
			
			GerenciadorFabricante gerencFabricante = 
					(GerenciadorFabricante) factory.criarGerenciadorCadastro(this.nomeMenu.toLowerCase());
			
			gerencFabricante.cadastrar(fabricanteCadastrado);
			
			System.out.println("\nFabricante cadastrado com sucesso.");
		}
		System.out.println("\nPressione Enter para continuar...");
		input.nextLine();
	}
	
	@Override
	public String getNomeMenu() {
		return this.nomeMenu;
	}
}

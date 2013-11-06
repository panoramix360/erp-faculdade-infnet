package erp.controller.command.listar;

import java.util.ArrayList;
import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Fabricante;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorFabricante;

public class ListarFabricanteCommand implements Command {
	
	private String nomeMenu;
	
	public ListarFabricanteCommand() {
		this.nomeMenu = "Fabricante";
	}
	
	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		GerenciadorFabricante gerencFabricante = 
				(GerenciadorFabricante) factory.criarGerenciadorCadastro(this.nomeMenu.toLowerCase());
		ArrayList<Fabricante> fabricantes = gerencFabricante.listar();
		
		System.out.println("\n\n\n\n\n\n========================================");
		System.out.println("========= Clientes Cadastrados =========");
		System.out.println("========================================");
		for( Fabricante fabricante : fabricantes ) {
			System.out.println(fabricante);
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

package erp.controller.command.listar;

import java.util.ArrayList;
import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Cliente;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorCliente;

public class ListarClienteCommand implements Command {
	
	private String nomeMenu;
	
	public ListarClienteCommand() {
		this.nomeMenu = "Cliente";
	}
	
	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		GerenciadorCliente gerencCliente = 
				(GerenciadorCliente) factory.criarGerenciadorCadastro(this.nomeMenu.toLowerCase());
		ArrayList<Cliente> clientes = gerencCliente.listar();
		
		System.out.println("\n\n\n\n\n\n========================================");
		System.out.println("========= Clientes Cadastrados =========");
		System.out.println("========================================");
		for( Cliente cliente : clientes ) {
			System.out.println(cliente);
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

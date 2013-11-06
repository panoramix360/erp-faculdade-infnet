package erp.controller.command.cadastrar;

import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Cliente;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorCliente;

public class CadastrarClienteCommand implements Command {
	
	private String nomeMenu;
	
	public CadastrarClienteCommand() {
		this.nomeMenu = "Cliente";
	}
	
	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		Cliente clienteCadastrado = null;
		
		System.out.println("\n\n\n\n\n\n========================================");
		System.out.println("========= Cadastro de Clientes =========");
		System.out.println("========================================");
		System.out.println("Digite o cnpj:");
		String cnpj = input.nextLine();
		
		System.out.println("\nDigite o nome fantasia:");
		String nomeFantasia = input.nextLine();
		
		clienteCadastrado = new Cliente(cnpj, nomeFantasia);
		if( clienteCadastrado != null ) {
			GerenciadorCliente gerencCliente = 
					(GerenciadorCliente) factory.criarGerenciadorCadastro(this.nomeMenu.toLowerCase());
			
			gerencCliente.cadastrar(clienteCadastrado);
			System.out.println("\nCliente cadastrado com sucesso.");
		}
		System.out.println("\nPressione Enter para continuar...");
		input.nextLine();
	}
	
	@Override
	public String getNomeMenu() {
		return this.nomeMenu;
	}
}

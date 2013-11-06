package erp.controller.command.listar;

import java.util.ArrayList;
import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Usuario;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorUsuario;

public class ListarUsuarioCommand implements Command {
	
	private String nomeMenu;
	
	public ListarUsuarioCommand() {
		this.nomeMenu = "Usuario";
	}
	
	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		GerenciadorUsuario gerencUsuario = 
				(GerenciadorUsuario) factory.criarGerenciadorCadastro(this.nomeMenu.toLowerCase());
		ArrayList<Usuario> usuarios = gerencUsuario.listar();
		
		System.out.println("\n\n\n\n\n\n========================================");
		System.out.println("========= Usuarios Cadastrados =========");
		System.out.println("========================================");
		for( Usuario usuario : usuarios ) {
			System.out.println(usuario);
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

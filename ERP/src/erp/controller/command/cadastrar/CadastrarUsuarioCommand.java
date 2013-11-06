package erp.controller.command.cadastrar;

import java.util.Scanner;

import erp.controller.command.Command;
import erp.modelo.Administrador;
import erp.modelo.Funcionario;
import erp.modelo.Usuario;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorUsuario;

public class CadastrarUsuarioCommand implements Command {
	
	private String nomeMenu;
	
	public CadastrarUsuarioCommand() {
		this.nomeMenu = "Usuario";
	}

	@Override
	public void execute(Scanner input, GerenciadorFactory factory) {
		Usuario usuarioCadastrado = null;
		
		System.out.println("\n\n\n\n\n\n========================================");
		System.out.println("========= Cadastro de Usuários =========");
		System.out.println("========================================");
		System.out.println("Digite o nome:");
		String nome = input.nextLine();
		
		System.out.println("\nDigite o nome de usuário:");
		String nomeDeUsuario = input.nextLine();
		
		System.out.println("\nDigite o senha:");
		String senha = input.nextLine();
		
		System.out.println("\nDigite o tipo (1-admin ou 2-funcionario):");
		int tipo = input.nextInt();
		input.nextLine();
		if( tipo == 1 ) {
			usuarioCadastrado = new Administrador(nome, nomeDeUsuario, senha, "admin");
		} else if( tipo == 2 ) {
			usuarioCadastrado = new Funcionario(nome, nomeDeUsuario, senha, "funcionario");
		}
		
		if( usuarioCadastrado != null && (tipo == 1 || tipo == 2) ) {
			GerenciadorUsuario gerencUsuario = 
					(GerenciadorUsuario) factory.criarGerenciadorCadastro(this.nomeMenu.toLowerCase());
			gerencUsuario.cadastrar(usuarioCadastrado);
			System.out.println("\nUsuário cadastrado com sucesso.");
		} else {
			System.err.println("\nTipo informado inválido.\n");
		}
		System.out.println("\nPressione Enter para continuar...");
		input.nextLine();
	}

	@Override
	public String getNomeMenu() {
		return this.nomeMenu;
	}
}

package erp.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import erp.controller.command.*;
import erp.controller.command.cadastrar.*;
import erp.modelo.*;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.GerenciadorUsuario;

public class Main {
	
	private Hashtable<Integer, Command> comandosUsuario;
	private Hashtable<Integer, Command> comandosAdmin;
	
	private GerenciadorFactory factory;
	private Usuario usuarioAtivo;
	
	private Menu menuAtivo;
	
	private Scanner input;
	
	public static void main(String[] args) {
		new Main().runApp();
	}
	
	public Main() {
		this.input = new Scanner(System.in);
		this.menuAtivo = new Menu("Inicial");
	}
	
	public void runApp() {
		
		// Iniciando os commands
		this.initUsuarios();
		
//		boolean isLogado = false;
//
//		do {
//			System.out.println("========= Bem vindo ao ERP - Login =========");
//				
//			System.out.println("Digite o seu usuário:");
//			String nomeUsuario = this.input.nextLine();
//			
//			System.out.println("Digite a sua senha:");
//			String senha = this.input.nextLine();
//			
//			for( Usuario usuario : usuarios ) {
//				if( usuario.getNomeDeUsuario().equals(nomeUsuario)
//						&& usuario.getSenha().equals(senha) ) {
//					usuarioAtivo = usuario;
//					isLogado = true;
//				}
//			}
//			
//			if( isLogado == false ) {
//				System.err.print("Usuário/senha não encontrados.\n");
//				System.out.println();
//			}
//			
//		} while(!isLogado);
		
		
		
		do {
			System.out.println("========= ERP =========");
//			System.out.println("Bem vindo " + this.usuarioAtivo.getNomeDeUsuario());
			System.out.println("Digite um dos comandos para executar as ações:");
			
			this.exibirMenuPrincipal();
			
			int opcao = input.nextInt();
			
			this.exibirSubmenu(opcao);
			
			opcao = input.nextInt();
			
			this.menuAtivo.get(opcao).execute();
			
		} while(true);
		
		
	}
	
	public void initUsuarios() {
		factory = new GerenciadorFactory();
		
		GerenciadorUsuario gerenciadorUsuario = (GerenciadorUsuario) factory.criarGerenciador("usuario");
		
		Usuario func = new Funcionario("Joao Ricardo", "joao", "joao123", "2323232", "medeirosa@gmail.com", "Rua dos Veados, 24", "Ativo");
		Usuario admin = new Administrador("Lucas Oliveira", "admin", "admin", "323132131", "lucasreis@gmail.com", "Rua 24 de Maio", "Ativo");
		
		gerenciadorUsuario.cadastrar(func);
		gerenciadorUsuario.cadastrar(admin);
	}
	
	public void executarCommand(int opcao) {
		
	}
	
	public void exibirSubmenu(int opcao) {
		switch(opcao) {
			case 1:
				Menu menuAdminCadastrar = new Menu("Cadastrar");
				
				menuAdminCadastrar.add(new CadastrarUsuarioCommand());
				menuAdminCadastrar.add(new CadastrarClienteCommand());
				menuAdminCadastrar.add(new CadastrarFabricanteCommand());
				menuAdminCadastrar.add(new CadastrarProdutoCommand());
				menuAdminCadastrar.add(new CadastrarPedidoCommand());
				
				menuAdminCadastrar.exibirMenu();
				
				this.menuAtivo = menuAdminCadastrar;
				break;
//			case 2:
//				Menu menuAdminListar = new Menu("Listar");
//				
//				menuAdminListar.add(new ListarUsuarioCommand());
//				menuAdminListar.add(new ListarClienteCommand());
//				menuAdminListar.add(new ListarFabricanteCommand());
//				menuAdminListar.add(new ListarProdutoCommand());
//				menuAdminListar.add(new ListarPedidoCommand());
//				break;
//			case 3:
//				Menu menuAdminEditar = new Menu("Listar");
//				
//				menuAdminEditar.add(new EditarUsuarioCommand());
//				menuAdminEditar.add(new EditarClienteCommand());
//				menuAdminEditar.add(new EditarFabricanteCommand());
//				menuAdminEditar.add(new EditarProdutoCommand());
//				menuAdminEditar.add(new EditarPedidoCommand());
//				break;
//			case 4:
//				Menu menuAdminDeletar = new Menu("Listar");
//				
//				menuAdminDeletar.add(new DeletarUsuarioCommand());
//				menuAdminDeletar.add(new DeletarClienteCommand());
//				menuAdminDeletar.add(new DeletarFabricanteCommand());
//				menuAdminDeletar.add(new DeletarProdutoCommand());
//				menuAdminDeletar.add(new DeletarPedidoCommand());
//				break;
			default:
				return;
		}
	}
	
	public void exibirMenuPrincipal() {
		System.out.println("1- Cadastrar");
		System.out.println("2- Listar");
		System.out.println("3- Editar");
		System.out.println("4- Deletar");
		
//		if( isAdmin() ) {	
//			System.out.println("5- Gerar Relatório");
//		}
	}
	
	public boolean isAdmin() {
		return this.usuarioAtivo.getTipo().equals("admin");
	}
	
	public boolean isFuncionario() {
		return this.usuarioAtivo.getTipo().equals("funcionario");
	}
}

package erp.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import erp.controller.command.cadastrar.CadastrarClienteCommand;
import erp.controller.command.cadastrar.CadastrarFabricanteCommand;
import erp.controller.command.cadastrar.CadastrarPedidoDeCompraCommand;
import erp.controller.command.cadastrar.CadastrarPedidoDeVendaCommand;
import erp.controller.command.cadastrar.CadastrarProdutoCommand;
import erp.controller.command.cadastrar.CadastrarUsuarioCommand;
import erp.controller.command.listar.ListarClienteCommand;
import erp.controller.command.listar.ListarEstoqueCommand;
import erp.controller.command.listar.ListarFabricanteCommand;
import erp.controller.command.listar.ListarPedidoDeCompraCommand;
import erp.controller.command.listar.ListarPedidoDeVendaCommand;
import erp.controller.command.listar.ListarProdutoCommand;
import erp.controller.command.listar.ListarUsuarioCommand;
import erp.controller.command.notafiscal.EntradaCommand;
import erp.mock.Mock;
import erp.modelo.Menu;
import erp.modelo.Usuario;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorUsuario;

public class Main {
	
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
		this.factory = new GerenciadorFactory();
	}
	
	public void runApp() {
		
		GerenciadorUsuario gerencUsuario = null;
		// Iniciando os commands
		new Mock(factory);
		
		//Login do sistema
		boolean isLogado = false;
		do {
			try {
				System.out.println("\n##########################################");
				System.out.println("###       ERP - Login do sistema       ###");
				System.out.println("##########################################");
				
				System.out.println("Digite o seu usuário:");
				String nomeUsuario = this.input.nextLine();
				
				System.out.println("Digite a sua senha:");
				String senha = this.input.nextLine();
				
				gerencUsuario = (GerenciadorUsuario) factory.criarGerenciadorCadastro("usuario"); 
				for( Usuario usuario : gerencUsuario.listar() ) {
					if( usuario.getNomeDeUsuario().equals(nomeUsuario)
							&& usuario.getSenha().equals(senha) ) {
						usuarioAtivo = usuario;
						isLogado = true;
					}
				}
				
				if( isLogado == false ) {
					System.err.print("Usuário/senha não encontrados.\n\n\n\n\n");
					System.out.println();
				}
			} catch (InputMismatchException i) {
				System.err.println("\nValor informado inválido.\n");
			}
		} while(!isLogado);
		
		//Dentro do sistema com usuário logado
		int opcao;
		boolean desejaSair = false;
		do {
			try {
				System.out.println("\n\n\n\n\n\n\n\n\n################### ERP #####################");
				System.out.println("##            Bem vindo - " + this.usuarioAtivo.getNomeDeUsuario() + "           ###");
				System.out.println("#############################################");
				
				System.out.println("Digite um dos comandos para executar as ações:");
				this.exibirMenuPrincipal();
				opcao = input.nextInt();
				if (opcao == 0) {
					desejaSair = true;
					break;
				}
				input.nextLine();
				
				this.exibirSubmenu(opcao);
				
				opcao = input.nextInt();
				input.nextLine();
				if( opcao != 0 ) {
					this.menuAtivo.get(opcao).execute(input, factory);
				}
			} catch (InputMismatchException i) {
				System.err.println("Valor informado inválido.\nPressione Enter para continuar.");
				input.nextLine();
				input.nextLine();
			}
		} while(!desejaSair);
		System.out.println("\n\nAté mais! o/");
	}
	
	
	
	public void exibirSubmenu(int opcao) {
		switch(opcao) {
			case 1:
				Menu menuAdminCadastrar = new Menu("Cadastrar");
				
				menuAdminCadastrar.add(new CadastrarUsuarioCommand());
				menuAdminCadastrar.add(new CadastrarClienteCommand());
				menuAdminCadastrar.add(new CadastrarFabricanteCommand());
				menuAdminCadastrar.add(new CadastrarProdutoCommand());
				menuAdminCadastrar.add(new CadastrarPedidoDeCompraCommand());
				menuAdminCadastrar.add(new CadastrarPedidoDeVendaCommand());
				
				menuAdminCadastrar.exibirMenu();
				
				this.menuAtivo = menuAdminCadastrar;
				break;
			case 2:
				Menu menuAdminListar = new Menu("Listar");
				
				menuAdminListar.add(new ListarUsuarioCommand());
				menuAdminListar.add(new ListarClienteCommand());
				menuAdminListar.add(new ListarFabricanteCommand());
				menuAdminListar.add(new ListarProdutoCommand());
				menuAdminListar.add(new ListarPedidoDeCompraCommand());
				menuAdminListar.add(new ListarPedidoDeVendaCommand());
				menuAdminListar.add(new ListarEstoqueCommand());
				
				menuAdminListar.exibirMenu();
				
				this.menuAtivo = menuAdminListar;
				break;
			case 3:
				System.out.println("To be done!");
				break;
			case 4:
				System.out.println("To be done!");
				break;
			case 5:
				Menu menuAdminNotaFiscal = new Menu("Nota Fiscal");
				
				menuAdminNotaFiscal.add(new EntradaCommand());
				//menuAdminNotaFiscal.add(new FaturamentoCommand());
				
				menuAdminNotaFiscal.exibirMenu();
				
				this.menuAtivo = menuAdminNotaFiscal;
				break;
			default:
				return;
		}
	}
	
	public void exibirMenuPrincipal() {
		System.out.println("1- Cadastrar");
		System.out.println("2- Listar");
		System.out.println("3- Editar");
		System.out.println("4- Deletar");
		System.out.println("5- Nota Fiscal");
		System.out.println("0- Para sair do sistema");
	}
	
	public boolean isAdmin() {
		return this.usuarioAtivo.getTipo().equals("admin");
	}
	
	public boolean isFuncionario() {
		return this.usuarioAtivo.getTipo().equals("funcionario");
	}
}

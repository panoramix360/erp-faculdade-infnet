package erp.mock;

import java.util.ArrayList;

import erp.modelo.Administrador;
import erp.modelo.Cliente;
import erp.modelo.Fabricante;
import erp.modelo.Funcionario;
import erp.modelo.Pedido;
import erp.modelo.PedidoDeCompra;
import erp.modelo.Produto;
import erp.modelo.Usuario;
import erp.modelo.gerenciador.GerenciadorFactory;
import erp.modelo.gerenciador.cadastro.GerenciadorCliente;
import erp.modelo.gerenciador.cadastro.GerenciadorFabricante;
import erp.modelo.gerenciador.cadastro.GerenciadorPedido;
import erp.modelo.gerenciador.cadastro.GerenciadorProduto;
import erp.modelo.gerenciador.cadastro.GerenciadorUsuario;

public class Mock {
	
	private GerenciadorFactory factory;
	
	public Mock(GerenciadorFactory factory) {
		this.factory = factory;
		this.initUsuarios();
		this.initFabricantes();
		this.initClientes();
		this.initProdutos();
		this.initPedidosDeCompra();
	}

	private void initUsuarios() {
		GerenciadorUsuario gerencUsuario = (GerenciadorUsuario) factory.criarGerenciadorCadastro("usuario");
		
		Usuario func = new Funcionario("Joao Ricardo", "joao", "joao123", "2323232", "medeirosa@gmail.com", "Rua dos Veados, 24", "Ativo");
		Usuario admin = new Administrador("Lucas Oliveira", "admin", "admin", "323132131", "lucasreis@gmail.com", "Rua 24 de Maio", "Ativo");
		
		gerencUsuario.cadastrar(func);
		
		gerencUsuario.cadastrar(admin);
	}
	
	private void initFabricantes() {
		GerenciadorFabricante gerencFabricante = (GerenciadorFabricante) factory.criarGerenciadorCadastro("fabricante");
		
		Fabricante fabricante1 = new Fabricante("123", "Medley");
		Fabricante fabricante2 = new Fabricante("1234", "Neoquimica");
		
		gerencFabricante.cadastrar(fabricante1);
		
		gerencFabricante.cadastrar(fabricante2);
	}
	
	private void initClientes() {
		GerenciadorCliente gerencCliente = (GerenciadorCliente) factory.criarGerenciadorCadastro("cliente");
		
		Cliente cliente1 = new Cliente("123", "Drogaria Pacheco");
		Cliente cliente2 = new Cliente("987", "Drogasmil");
		
		gerencCliente.cadastrar(cliente1);
		
		gerencCliente.cadastrar(cliente2);
	}
	
	private void initProdutos() {
		GerenciadorProduto gerencProduto = (GerenciadorProduto) factory.criarGerenciadorCadastro("produto");
		GerenciadorFabricante gerencFabricante = (GerenciadorFabricante) factory.criarGerenciadorCadastro("fabricante");

		Fabricante fabricante1 = gerencFabricante.getFabricante("123");
		Fabricante fabricante2 = gerencFabricante.getFabricante("1234");
		
		Produto produto1 = new Produto(1, "Avamys", "Remedio1", fabricante1, 50.0);
		Produto produto2 = new Produto(2, "Neosaldina", "Remedio2", fabricante1, 100.0);
		Produto produto3 = new Produto(3, "Novalgina", "Remedio3", fabricante2, 20.0);
		Produto produto4 = new Produto(4, "Dipirona", "Remedio4", fabricante2, 35.0);
		
		gerencProduto.cadastrar(produto1);
		gerencProduto.cadastrar(produto2);
		gerencProduto.cadastrar(produto3);
		gerencProduto.cadastrar(produto4);
	}
	
	private void initPedidosDeCompra() {
		GerenciadorPedido gerencPedido = (GerenciadorPedido) factory.criarGerenciadorCadastro("pedido");
		GerenciadorProduto gerencProduto = (GerenciadorProduto) factory.criarGerenciadorCadastro("produto");
		GerenciadorFabricante gerencFabricante = (GerenciadorFabricante) factory.criarGerenciadorCadastro("fabricante");
		
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		
		Fabricante fabricante = gerencFabricante.getFabricante("123");
		
		Produto produto1 = gerencProduto.getProduto(1);
		Produto produto2 = gerencProduto.getProduto(2);
		
		produtos.add(produto1);
		produtos.add(produto2);
		
		Pedido pedido = new PedidoDeCompra(produtos, fabricante, gerencPedido.getUltimoIdPedido());
		
		gerencPedido.cadastrar(pedido);
	}
}

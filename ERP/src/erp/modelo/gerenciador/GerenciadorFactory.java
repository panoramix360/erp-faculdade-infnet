package erp.modelo.gerenciador;

import erp.modelo.gerenciador.cadastro.GerenciadorCadastro;
import erp.modelo.gerenciador.cadastro.GerenciadorCliente;
import erp.modelo.gerenciador.cadastro.GerenciadorFabricante;
import erp.modelo.gerenciador.cadastro.GerenciadorPedido;
import erp.modelo.gerenciador.cadastro.GerenciadorProduto;
import erp.modelo.gerenciador.cadastro.GerenciadorUsuario;

public class GerenciadorFactory {
	
	public GerenciadorFactory() { }
	
	public GerenciadorCadastro<?> criarGerenciadorCadastro(String tipoGerenc) {
		switch( tipoGerenc ) {
			case "usuario":
				return GerenciadorUsuario.getInstance();
			case "cliente":
				return GerenciadorCliente.getInstance();
			case "produto":
				return GerenciadorProduto.getInstance();
			case "fabricante":
				return GerenciadorFabricante.getInstance();
			case "pedido":
				return GerenciadorPedido.getInstance();
			default:
				return null;
		}
	}
	
	public GerenciadorEstoque criarGerenciadorEstoque() {
		return GerenciadorEstoque.getInstance();
	}
	
}

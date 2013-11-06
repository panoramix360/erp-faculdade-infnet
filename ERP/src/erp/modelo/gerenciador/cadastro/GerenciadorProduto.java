package erp.modelo.gerenciador.cadastro;

import java.util.ArrayList;

import erp.modelo.Produto;

public class GerenciadorProduto extends GerenciadorCadastro<Produto> {
	
	private static volatile GerenciadorProduto instance;
	
	private ArrayList<Produto> produtos;
	
	private GerenciadorProduto() {
		produtos = new ArrayList<Produto>();
	}
			
	@Override
	public void cadastrar(Produto produto) {
		if( produto != null ) {
			produtos.add(produto);
		}
	}

	@Override
	public ArrayList<Produto> listar() {
		return this.produtos;
	}
	
	public Produto getProduto(int codigoDoProduto) {
		Produto produtoEncontrado = null;
		for( Produto produto : produtos ) {
			if( produto.getCodigoDoProduto() == codigoDoProduto ) {
				produtoEncontrado = produto;
			}
		}
		
		return produtoEncontrado;
	}
	
	public static GerenciadorProduto getInstance() {
		if( instance == null ) {
			synchronized (GerenciadorProduto.class) {
				if( instance == null ) {
					instance = new GerenciadorProduto();
					System.out.println("Criado Gerenciador Produto");
				}
			}
		}
		return instance;
	}

	public boolean isProdutoDoFabricante(int codigoDoProduto,
			String cnpjFabricante) {
		for (Produto produto : produtos) {
			if (produto.getCodigoDoProduto() == codigoDoProduto) {
				if (produto.getFabricante().getCnpj().endsWith(cnpjFabricante)) {
					return true;
				}
			}
		}
		return false;
	}
}
package erp.modelo.gerenciador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import erp.modelo.Endereco;
import erp.modelo.Pedido;
import erp.modelo.Produto;

public class GerenciadorEstoque {
	
	private static volatile GerenciadorEstoque instance;
	private ArrayList<Endereco> estoque;
	
	public GerenciadorEstoque() {
		estoque = new ArrayList<Endereco>();
	}
	
	public void estocarProduto(Endereco enderecoParaEstocar) {
		if( estoque.isEmpty() ) {
			estoque.add(enderecoParaEstocar);
		} else {
			if( estoque.contains(enderecoParaEstocar) ) {
				int index = estoque.indexOf(enderecoParaEstocar);
				estoque.get(index).addProdutos(enderecoParaEstocar.getProdutosArmazenados());
			} else {
				estoque.add(enderecoParaEstocar);
			}
		}	
	}
	
	/*
	 * Varre os enderecos e traz todos os itens do estoque
	 */
	private HashMap<Produto, Integer> getEstoqueTotal(){
		HashMap<Produto, Integer> estoqueAtual = new HashMap<Produto, Integer>();
		
		for (Endereco endereco : estoque) {
			
			for( Produto produto : endereco.getProdutosArmazenados() ) {
				if( estoqueAtual.containsKey(produto) ) {
					estoqueAtual.put(produto, estoqueAtual.get(produto) + 1);
				} else {
					estoqueAtual.put(produto, 1);
				}
			}
		}
		return estoqueAtual;
	}
	/*
	 * Varre os enderecos e traz todos os itens RESERVADOS do estoque
	 */
	@SuppressWarnings("unused")
	private HashMap<Produto, Integer> getEstoqueReservado(){
		HashMap<Produto, Integer> estoqueReservado = new HashMap<Produto, Integer>();
		
		for (Endereco endereco : estoque) {
			
			for( Produto produto : endereco.getProdutosReservados() ) {
				if( estoqueReservado.containsKey(produto) ) {
					estoqueReservado.put(produto, estoqueReservado.get(produto) + 1);
				} else {
					estoqueReservado.put(produto, 1);
				}
			}
		}
		return estoqueReservado;
	}
	
	
	
	public boolean reservarProdutoDoPedido(Pedido pedido) {
		boolean maiorQueEstoque = false;
		HashMap<Produto, Integer> produtosDoPedido = new HashMap<Produto, Integer>();
		HashMap<Produto, Integer> estoqueTotal = new HashMap<Produto, Integer>();
		
		//Cria o HashMap dos itens do pedido.
		for( Produto produto : pedido.getProdutos() ) {
			if( produtosDoPedido.containsKey(produto) ) {
				produtosDoPedido.put(produto, produtosDoPedido.get(produto) + 1);
			} else {
				produtosDoPedido.put(produto, 1);
			}
		}
		
		estoqueTotal = getEstoqueTotal();
		outer:
		for (Entry<Produto, Integer> produtoPedido : produtosDoPedido.entrySet()) {
			for (Entry<Produto, Integer> produtoEstoque : estoqueTotal.entrySet()) {
				
				if (produtoPedido.getKey().equals(produtoEstoque.getKey())) {
					if ( produtoPedido.getValue() > produtoEstoque.getValue() ) {
						maiorQueEstoque = true;
						break outer;
					}
				}
				
			}
		}
		
		/*
		 * Entrando aqui realiza o processo de reserva dos produtos
		 */
		if (!maiorQueEstoque) {
			
			//Para cada produto do pedido vou removendo do estoque
			for (Entry<Produto, Integer> registroPedido : produtosDoPedido.entrySet()) {
				for (Endereco endereco : estoque) {
					
					for (Entry<Produto, Integer> registroEndereco : endereco.getHashProdutosArmazenados().entrySet()) {
						if (registroEndereco.getKey().equals(registroPedido.getKey())) {
							registroEndereco.setValue(registroEndereco.getValue() - registroPedido.getValue());
						}
					}
				}
			}
			
		}
		
		return !maiorQueEstoque;
	}
	
	public  ArrayList<Endereco> listarEstoque() {
		return this.estoque;
	}
	
	public static GerenciadorEstoque getInstance() {
		if( instance == null ) {
			synchronized (GerenciadorEstoque.class) {
				if( instance == null ) {
					instance = new GerenciadorEstoque();
					System.out.println("Criado Gerenciador Estoque");
				}
			}
		}
		return instance;
	}

	public ArrayList<Produto> listar() {
		ArrayList<Produto> produtosNoEstoque = new ArrayList<Produto>();
		
		for (Endereco endereco : estoque) {
			for (Produto produto : endereco.getProdutosArmazenados()) {
				produtosNoEstoque.add(produto);
			}
		}
		return produtosNoEstoque;
	}
	
	
	public boolean isProdutoNoEstoque(int codigoDoProduto) {
		for (Produto produto : listar()) {
			if (produto.getCodigoDoProduto() == codigoDoProduto) {
				return true;
			}
		}
		return false;
	}
	
	
}
package erp.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Endereco {
	private String rua;
	private int coluna;
	private int andar;
	private HashMap<Produto, Integer> produtosArmazenados;
	private HashMap<Produto, Integer> produtosReservados;
	
	public Endereco(String rua, int coluna, int andar, ArrayList<Produto> produtos) {
		this.rua = rua;
		this.coluna = coluna;
		this.andar = andar;
		this.produtosArmazenados = new HashMap<Produto, Integer>();
		this.produtosReservados = new HashMap<Produto, Integer>();
		this.addProdutos(produtos);
	}
	
	public void addProdutos(ArrayList<Produto> produtos) {
		for( Produto produto : produtos ) {
			if( produtosArmazenados.containsKey(produto) ) {
				produtosArmazenados.put(produto, produtosArmazenados.get(produto) + 1);
			} else {
				produtosArmazenados.put(produto, 1);
			}
		}
	}
	
	public HashMap<Produto, Integer> getHashProdutosReservados() {
		return produtosReservados;
	}
	
	public HashMap<Produto, Integer> getHashProdutosArmazenados() {
		return produtosArmazenados;
	}
	
	
	public ArrayList<Produto> getProdutosArmazenados() {
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		
		for (Entry<Produto, Integer> produto : produtosArmazenados.entrySet()) {
			produtos.add(produto.getKey());
		}
		
		return produtos;
	}
	
	public ArrayList<Produto> getProdutosReservados() {
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		
		for (Entry<Produto, Integer> produto : produtosArmazenados.entrySet()) {
			produtos.add(produto.getKey());
		}
		
		return produtos;
	}
	
	public ArrayList<Integer> getQuantidade() {
		ArrayList<Integer> quantidades = new ArrayList<Integer>();
		
		for (Entry<Produto, Integer> produto : produtosArmazenados.entrySet()) {
			quantidades.add(produto.getValue());
		}
		
		return quantidades;
	} 

	public void setProdutosArmazenados(ArrayList<Produto> produtosArmazenados) {
		this.addProdutos(produtosArmazenados);
	}
	
	public boolean reservarProduto(int codigoDoProduto) {
		boolean isReservado = false;
		for (Entry<Produto, Integer> produto : produtosArmazenados.entrySet()) {
			if( produto.getKey().getCodigoDoProduto() == codigoDoProduto
					&& produto.getValue() > 0) {
				Produto produtoReserva = produto.getKey();
				produtosArmazenados.put(produtoReserva, produtosArmazenados.get(produtoReserva) - 1);
				
				if( produtosReservados.containsKey(produtoReserva) ) {
					produtosArmazenados.put(produtoReserva, produtosArmazenados.get(produtoReserva) + 1);
				} else {
					produtosArmazenados.put(produtoReserva, 1);
				}
				
				isReservado = true;
			}
		}
		
		return isReservado;
	}
	
	public HashMap<Produto, Integer> getEstoque() {
		return produtosArmazenados;
	}
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int getAndar() {
		return andar;
	}

	public void setAndar(int andar) {
		this.andar = andar;
	}
	
	@Override
	public boolean equals(Object obj) {
		Endereco enderecoObj = (Endereco) obj;
		if( this.rua.equals(enderecoObj.getRua())
				&& this.coluna == enderecoObj.getColuna()
				&& this.andar == enderecoObj.getAndar() ) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Rua: " + this.rua + " Coluna: " + this.coluna + " Andar: " + this.andar;
	}
}

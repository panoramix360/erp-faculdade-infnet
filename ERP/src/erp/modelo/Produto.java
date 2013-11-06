package erp.modelo;

import java.text.DecimalFormat;

public class Produto {
	private int codigoDoProduto;
	private String nome;
	private String descricao;
	private Fabricante fabricante;
	private String principioAtivo;
	private long codigoDeBarra;
	private double preco;
	
	public Produto(int codigoDoProduto, String nome, String descricao,
			Fabricante fabricante, String principioAtivo, long codigoDeBarra,
			double preco) {
		this.codigoDoProduto = codigoDoProduto;
		this.nome = nome;
		this.descricao = descricao;
		this.fabricante = fabricante;
		this.principioAtivo = principioAtivo;
		this.codigoDeBarra = codigoDeBarra;
		this.preco = preco;
	}

	public Produto(int codigoDoProduto, String nome, String descricao,
			Fabricante fabricante, double preco) {
		this.codigoDoProduto = codigoDoProduto;
		this.nome = nome;
		this.descricao = descricao;
		this.fabricante = fabricante;
		this.preco = preco;
	}
	
	public int getCodigoDoProduto() {
		return codigoDoProduto;
	}

	public void setCodigoDoProduto(int codigoDoProduto) {
		this.codigoDoProduto = codigoDoProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}

	public long getCodigoDeBarra() {
		return codigoDeBarra;
	}

	public void setCodigoDeBarra(long codigoDeBarra) {
		this.codigoDeBarra = codigoDeBarra;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	@Override
	public boolean equals(Object obj) {
		Produto produtoObj = (Produto) obj;
		if( this.codigoDoProduto == produtoObj.getCodigoDoProduto() ) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		DecimalFormat formatador = new DecimalFormat("R$0.00");
		return "Código do Produto..: " + this.codigoDoProduto + "\n" +
			   "Nome...............: " + this.nome + "\n" +
			   "Descrição..........: " + this.descricao + "\n" + 
			   "Fabricante.........: " + this.fabricante.getNomeFantasia() + "\n" +
			   "Preço..............: " + formatador.format(this.preco) + "\n";
	}
}

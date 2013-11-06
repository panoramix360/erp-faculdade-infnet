package erp.modelo;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Pedido {
	
	private int id;
	private ArrayList<Produto> produtos;
	private double valorTotal;
	private Date dataDoPedido;
	private String status;
	private Date recebimento;
	private long notaFiscal;
	
	public Pedido(ArrayList<Produto> produtos, int id) {
		this.id = id;
		this.produtos = produtos;
		this.calcularTotal();
		this.dataDoPedido = new Date();
		this.status = "Pendente";
	}
	
	private void calcularTotal() {
		double total = 0;
		
		for( Produto produto : produtos ) {
			total += produto.getPreco();
		}
		
		this.valorTotal = total;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	private String getDataDoPedido() {
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		return formatter.format(this.dataDoPedido);
	}

	public void setDataDoPedido(Date dataDoPedido) {
		this.dataDoPedido = dataDoPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Date recebimento) {
		this.recebimento = recebimento;
	}

	public long getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(long notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		DecimalFormat formatador = new DecimalFormat("R$0.00");
		return "ID..................: " + this.id + "\n" +
			   "Total de Produtos...: " + this.produtos.size() + "\n" +
			   "Valor Total.........: " + formatador.format(this.valorTotal) + "\n" +
			   "Data do Pedido......: " + this.getDataDoPedido() + "\n" +
			   "Status..............: " + this.status + "\n";
	}
}

package erp.modelo;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
	
	private ArrayList<ItemDoPedido> itens;
	private float valorTotal;
	private Date dataDoPedido;
	private String status;
	private Date emissao;
	private Date recebimento;
	private long notaFiscal;
	
	public Pedido() {
		
	}

	public ArrayList<ItemDoPedido> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ItemDoPedido> itens) {
		this.itens = itens;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataDoPedido() {
		return dataDoPedido;
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

	public Date getEmissao() {
		return emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
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
}

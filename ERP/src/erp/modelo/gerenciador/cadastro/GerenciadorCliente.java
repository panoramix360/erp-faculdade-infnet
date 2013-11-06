package erp.modelo.gerenciador.cadastro;

import java.util.ArrayList;

import erp.modelo.Cliente;

public class GerenciadorCliente extends GerenciadorCadastro<Cliente> {
	
	private static volatile GerenciadorCliente instance;
	
	private ArrayList<Cliente> clientes;
	
	private GerenciadorCliente() {
		clientes = new ArrayList<Cliente>();
	}
			
	@Override
	public void cadastrar(Cliente cliente) {
		if( cliente != null ) {
			clientes.add(cliente);
		}
	}

	@Override
	public ArrayList<Cliente> listar() {
		return this.clientes;
	}
	
	public Cliente getCliente(String cnpj) {
		Cliente clienteEncontrado = null;
		for( Cliente cliente : clientes ) {
			if( cliente.getCnpj().equals(cnpj) ) {
				clienteEncontrado = cliente;
			}
		}
		
		return clienteEncontrado;
	}
	
	public boolean isClienteCadastrado(String cnpjCliente) {
		for (Cliente cliente : clientes) {
			if (cliente.getCnpj().equals(cnpjCliente))
				return true;
		}
		return false;
	}
	
	public static GerenciadorCliente getInstance() {
		if( instance == null ) {
			synchronized (GerenciadorCliente.class) {
				if( instance == null ) {
					instance = new GerenciadorCliente();
					System.out.println("Criado Gerenciador Cliente");
				}
			}
		}
		return instance;
	}
}
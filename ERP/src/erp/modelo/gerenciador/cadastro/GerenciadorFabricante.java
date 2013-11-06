package erp.modelo.gerenciador.cadastro;

import java.util.ArrayList;

import erp.modelo.Fabricante;

public class GerenciadorFabricante extends GerenciadorCadastro<Fabricante> {
	
	private static volatile GerenciadorFabricante instance;
	
	private ArrayList<Fabricante> fabricantes;
	
	private GerenciadorFabricante() {
		fabricantes = new ArrayList<Fabricante>();
	}
			
	@Override
	public void cadastrar(Fabricante fabricante) {
		if( fabricante != null ) {
			fabricantes.add(fabricante);
		}
	}

	@Override
	public ArrayList<Fabricante> listar() {
		return this.fabricantes;
	}
	
	public Fabricante getFabricante(String cnpj) {
		Fabricante fabricanteEncontrado = null;
		for( Fabricante fabricante : fabricantes ) {
			if( fabricante.getCnpj().equals(cnpj) ) {
				fabricanteEncontrado = fabricante;
			}
		}
		
		return fabricanteEncontrado;
	}
	
	public static GerenciadorFabricante getInstance() {
		if( instance == null ) {
			synchronized (GerenciadorFabricante.class) {
				if( instance == null ) {
					instance = new GerenciadorFabricante();
					System.out.println("Criado Gerenciador Fabricante");
				}
			}
		}
		
		return instance;
	}

	public boolean isFabricanteCadastrado(String cnpjFabricante) {
		for (Fabricante fabricante : fabricantes) {
			if (fabricante.getCnpj().equals(cnpjFabricante))
				return true;
		}
		return false;
	}
	
}
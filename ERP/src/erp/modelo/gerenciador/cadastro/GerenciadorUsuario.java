package erp.modelo.gerenciador.cadastro;

import java.util.ArrayList;

import erp.modelo.Usuario;

public class GerenciadorUsuario extends GerenciadorCadastro<Usuario> {
	
	private static volatile GerenciadorUsuario instance;
	
	private ArrayList<Usuario> usuarios;
	
	private GerenciadorUsuario() {
		usuarios = new ArrayList<Usuario>();
	}
			
	@Override
	public void cadastrar(Usuario usuario) {
		if( usuario != null ) {
			usuarios.add(usuario);
		}
	}

	@Override
	public ArrayList<Usuario> listar() {
		return this.usuarios;
	}
	
	public static GerenciadorUsuario getInstance() {
		if( instance == null ) {
			synchronized (GerenciadorUsuario.class) {
				if( instance == null ) {
					instance = new GerenciadorUsuario();
					System.out.println("Criado Gerenciador Usuario");
				}
			}
		}
		
		return instance;
	}
}
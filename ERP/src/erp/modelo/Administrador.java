package erp.modelo;

public class Administrador extends Usuario{

	public Administrador(String nome, String nomeDeUsuario, String senha, String telefone, String email, String endereco, String situacao) {
		super(nome, nomeDeUsuario, senha, telefone, email, endereco, situacao);
		
		super.setTipo("admin");
	}

}

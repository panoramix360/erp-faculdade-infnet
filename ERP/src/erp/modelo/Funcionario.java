package erp.modelo;

public class Funcionario extends Usuario{

	public Funcionario(String nome, String nomeDeUsuario, String senha, String telefone,
			String email, String endereco, String situacao) {
		super(nome, nomeDeUsuario, senha, telefone, email, endereco, situacao);
		// TODO Auto-generated constructor stub
		
		super.setTipo("funcionario");
	}

}

package erp.modelo;

public class Usuario {
	private String nome;
	private String nomeDeUsuario;
	private String senha;
	private String telefone;
	private String tipo;
	private String email;
	private String endereco;
	private String situacao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}
	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public Usuario(String nome, String nomeDeUsuario, String senha, String telefone, String email, String endereco, String situacao) {
		this.nome = nome;
		this.nomeDeUsuario = nomeDeUsuario;
		this.senha = senha;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.situacao = situacao;
	}
	
	@Override
	public boolean equals(Object obj) {
		if( this.nomeDeUsuario.equals( ((Usuario)obj).getNomeDeUsuario()) ) {
			return true;
		}
		return false;
	}
}

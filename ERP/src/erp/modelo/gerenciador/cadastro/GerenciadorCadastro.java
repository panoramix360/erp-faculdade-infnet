package erp.modelo.gerenciador.cadastro;

import java.util.ArrayList;

public abstract class GerenciadorCadastro<T> {
	
	public abstract void cadastrar(T t);
	public abstract ArrayList<T> listar();
}

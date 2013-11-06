package erp.controller.command;

import java.util.Scanner;

import erp.modelo.gerenciador.GerenciadorFactory;

public interface Command {
	String nomeMenu = "";
	
	void execute(Scanner input, GerenciadorFactory factory);
	String getNomeMenu();
}

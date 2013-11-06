package erp.modelo;

import java.util.ArrayList;
import java.util.Hashtable;

import erp.controller.command.Command;

public class Menu {
	
	private ArrayList<Menu> submenus;
	
	private Hashtable<Integer, Command> comandos;
	
	private int index;
	private String titulo;
	
	public Menu(String titulo) {
		this.titulo = titulo;
		this.submenus = new ArrayList<Menu>();
		
		this.comandos = new Hashtable<Integer, Command>();
		this.index = 1;
	}
	
	public void exibirMenu() {
		System.out.println("========="+ this.titulo +"=========");
		for( int i = 1; i <= this.index - 1; i++ ) {
			System.out.println(i + "- " + comandos.get(i).getNomeMenu());
		}
	}
	
	public void exibirMenu(int opcao) {
		this.submenus.get(opcao-1).exibirMenu();
	}
	
	public void exibirSubmenu() {
		for( Menu submenu : submenus ) {
			submenu.exibirMenu();
		}
	}
	
	public void add(Command command) {
		this.comandos.put(this.index, command);
		
		this.index++;
	}
	
	public void add(Menu menu) {
		this.submenus.add(menu);
	}
	
	public Command get(int opcao) {
		return this.comandos.get(opcao);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}

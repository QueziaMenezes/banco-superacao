package br.com.residencia.poo.principal;

import java.io.IOException;
import java.util.Scanner;

import br.com.residencia.poo.contas.Conta;
import br.com.residencia.poo.menus.Menu;
import br.com.residencia.poo.pessoas.Funcionario;

public class Principal {

	public char operacao;
	public double valor;
	public String inputCpf;
	public String inputSenha;
	public static Scanner sc = new Scanner(System.in);
	Menu menuPrincipal = new Menu();

	public void loopMenu() {
		boolean manterLoop = true;
		do {
			menuInterativo();
		} while (manterLoop == true);
	}

	public void menuInterativo() {
		try {
			imprimeLinhaHorizontal();
			imprimeLinhaHorizontal();
			System.out.print("Digite seu CPF: ");
			inputCpf = sc.next();
			System.out.print("Digite sua senha: ");
			inputSenha = sc.next();
			Funcionario funcionario = (Funcionario) Funcionario.mapaFuncionarios.get(inputCpf);
			Conta conta = (Conta) Conta.mapaContas.get(inputCpf);

			while (funcionario == null || !(funcionario.getSenha().equals(inputSenha))) {
				System.out.println("CPF e/ou Senha incorreto(s)\n\n");
				System.out.print("Digite seu CPF: ");
				inputCpf = sc.next();
				System.out.print("Digite sua senha: ");
				inputSenha = sc.next();
				funcionario = (Funcionario) Funcionario.mapaFuncionarios.get(inputCpf);
				conta = (Conta) Conta.mapaContas.get(inputCpf);
			}
			
			subMenu(funcionario, conta);
			imprimeLinhaHorizontal();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			menuInterativo();
		}
		sc.close();
	}

	// SUBMENU
	public void subMenu(Funcionario funcionario, Conta conta) throws IOException {
		try {
			imprimeLinhaHorizontal();
			System.out.println("Bem-vindo(a) ao seu Banco, " + funcionario.getNome() + "!\n");
			imprimeLinhaHorizontal();
			System.out.println("Digite o número correspondente a operação desejada:");
			Menu.menuPrincipal(funcionario, conta);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			subMenu(funcionario, conta);
		}
	
		Menu.menuPrincipal(funcionario, conta);
	}

	public void imprimeLinhaHorizontal() {
		System.out.println("--------------------------------------------------");
	}
	
}

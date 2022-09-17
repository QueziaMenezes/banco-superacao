package br.com.residencia.poo.principal;

import java.io.IOException;
import java.util.Scanner;

import br.com.residencia.poo.contas.Conta;
import br.com.residencia.poo.enums.TipoPessoa;
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
			System.out.println("Olá, seja bem vindo(a) ao Banco Super Ação!");
			imprimeLinhaHorizontal();
			System.out.println("Confirme a suas credenciais.");
			System.out.print("Digite seu CPF: ");
			inputCpf = sc.next();
			System.out.print("Digite sua senha: ");
			inputSenha = sc.next();
			Funcionario funcionario = (Funcionario) Funcionario.mapaFuncionarios.get(inputCpf);
			Conta conta = (Conta) Conta.mapaContas.get(inputCpf);

			while (funcionario == null || !(funcionario.getSenha().equals(inputSenha))) {
				imprimeLinhaHorizontal();
				System.out.println("CPF e/ou Senha incorreto(s)\n\n");
				imprimeLinhaHorizontal();
				System.out.println("Confirme a suas credenciais.");
				System.out.print("Digite seu CPF: ");
				inputCpf = sc.next();
				System.out.print("Digite sua senha: ");
				inputSenha = sc.next();
				funcionario = (Funcionario) Funcionario.mapaFuncionarios.get(inputCpf);
				conta = (Conta) Conta.mapaContas.get(inputCpf);
			}
			
			limpar();
			imprimeLinhaHorizontal();
			System.out.println("Olá, " + funcionario.getNome() + "!\n");
			
			if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.GERENTE.getTipoPessoa())) {
				System.out.println("Cargo: Gerente");
			} else if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.DIRETOR.getTipoPessoa())) {
				System.out.println("Cargo: Diretor");
			} else if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.PRESIDENTE.getTipoPessoa())) {
				System.out.println("Cargo: Presidente");
			}
			
			menu(funcionario, conta);
			imprimeLinhaHorizontal();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			menuInterativo();
		}
		sc.close();
	}

	// Menu
	public void menu(Funcionario funcionario, Conta conta) throws IOException {
		try {
			Menu.menuPrincipal(funcionario, conta);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			menu(funcionario, conta);
		}
		Menu.menuPrincipal(funcionario, conta);
	}
	
	public void imprimeLinhaHorizontal() {
		System.out.println("\n==================================================");
	}
	
	public void limpar() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}

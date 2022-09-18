package br.com.residencia.poo.principal;

import java.io.IOException;
import java.util.Scanner;

import br.com.residencia.poo.contas.Conta;
import br.com.residencia.poo.enums.TipoPessoa;
import br.com.residencia.poo.menus.MenuPrincipal;
import br.com.residencia.poo.pessoas.Funcionario;
import br.com.residencia.poo.usuarios.Cliente;

public class Principal {

	public char operacao;
	public double valor;
	public String inputCpf;
	public String inputSenha;
	public static Scanner sc = new Scanner(System.in);
	MenuPrincipal menuPrincipal = new MenuPrincipal();

	public void loopMenu() {
		boolean manterLoop = true;
		do {
			menuInterativo();
		} while (manterLoop == true);
	}

	public void menuInterativo() {
		try {
			System.out.println("--------------------------------------------------");
			System.out.println("                                                  ");
			System.out.println("            ======    ======    ======            ");
			System.out.println("           ||    ||   ||       ||    ||           ");
			System.out.println("           ||    ||   ||       ||    ||           ");
			System.out.println("           ||====||   ======   ||====||           ");
			System.out.println("           ||    ||       ||   ||    ||           ");
			System.out.println("           ||    ||       ||   ||    ||           ");
			System.out.println("            ======    ======   ||    ||           ");
			System.out.println("                                                  ");
			System.out.println("--------------------------------------------------");
			System.out.println("Olá, seja bem vindo(a) ao Banco Super Ação!");
			imprimeLinhaHorizontal();
			System.out.println("Confirme a suas credenciais.");
			System.out.print("Digite seu CPF: ");
			inputCpf = sc.next();
			System.out.print("Digite sua senha: ");
			inputSenha = sc.next();
			Funcionario funcionario = (Funcionario) Funcionario.mapaFuncionarios.get(inputCpf);
			Cliente cliente = (Cliente) Cliente.mapaCliente.get(inputCpf);
			Conta conta = (Conta) Conta.mapaContas.get(inputCpf);

			
			if (cliente != null) {
				while (cliente == null || !(cliente.getSenha().equals(inputSenha))) {
					imprimeLinhaHorizontal();
					System.out.println("CPF e/ou Senha incorreto(s)\n\n");
					imprimeLinhaHorizontal();
					System.out.println("Confirme a suas credenciais.");
					System.out.print("Digite seu CPF: ");
					inputCpf = sc.next();
					System.out.print("Digite sua senha: ");
					inputSenha = sc.next();
					cliente = (Cliente) Cliente.mapaCliente.get(inputCpf);
					conta = (Conta) Conta.mapaContas.get(inputCpf);
				}
				
				limpar();
				imprimeLinhaHorizontal();
				System.out.println("Olá, " + cliente.getNome() + "!\n");
				menuCliente(cliente, conta);
			} else if (funcionario != null) {
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
				
				menuFuncionario(funcionario, conta);
				imprimeLinhaHorizontal();
			} else {
				limpar();
				menuInterativo();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			menuInterativo();
		}
		sc.close();
	}

	// Menu
	public void menuCliente(Cliente cliente, Conta conta) throws IOException {
		try {
			MenuPrincipal.menuCliente(cliente, conta);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			menuCliente(cliente, conta);
		}
		MenuPrincipal.menuCliente(cliente, conta);
	}
	
	public void menuFuncionario(Funcionario funcionario, Conta conta) throws IOException {
		try {
			MenuPrincipal.menuPrincipal(funcionario, conta);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			menuFuncionario(funcionario, conta);
		}
		MenuPrincipal.menuPrincipal(funcionario, conta);
	}
	
	public void imprimeLinhaHorizontal() {
		System.out.println("\n==================================================");
	}
	
	public void limpar() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}

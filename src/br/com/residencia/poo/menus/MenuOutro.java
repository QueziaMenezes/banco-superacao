package br.com.residencia.poo.menus;

import java.io.IOException;

import br.com.residencia.poo.IO.*;
import br.com.residencia.poo.contas.Conta;
import br.com.residencia.poo.enums.TipoPessoa;
import br.com.residencia.poo.pessoas.Funcionario;
import br.com.residencia.poo.principal.Principal;

public class MenuOutro {
	
	String cpf;
    
	public static void menuPrincipal(Funcionario funcionario, Conta conta) throws IOException {
		//Principal principal = new Principal();
		// mostrar menu de opções		  		
	    try {
			System.out.println("Escolha uma das opções: ");
			System.out.println("1 - Saque");
			System.out.println("2 - Depósito");
			System.out.println("3 - Transferência");
			System.out.println("4 - Consulta Saldo");
			System.out.println("5 - Relatório");
			System.out.println("6 - Sair");
			System.out.print("Digite a opção desejada: ");
			int opcao = Principal.sc.nextInt();
			switch (opcao) {
			
			case 1:
				System.out.print("Digite o valor que deseja sacar: ");
				double saque = Principal.sc.nextDouble();
				conta.sacar(saque);
				LeituraEscrita.comprovanteSaque(conta, saque);
				break;
			case 2:
				System.out.print("Digite o valor que deseja depositar: ");
				double deposito = Principal.sc.nextDouble();
				conta.depositar(deposito);
				LeituraEscrita.comprovanteDeposito(conta, deposito);
				break;
			case 3:
				System.out.print("Informe o CPF da conta destino: ");
				String cpfDestino = Principal.sc.next();
				Conta contaDestino = (Conta) Conta.mapaContas.get(cpfDestino);
				System.out.print("Informe o valor a ser transferido: ");
				double transferencia = Principal.sc.nextDouble();
				System.out.printf("Valor transferido foi de: R$ %.2f%n", transferencia);
				//System.out.print("Saldo atual de " + conta.getTitular() + " ");
				conta.verificarSaldo();	
				LeituraEscrita.comprovanteTransferencia(conta, transferencia, contaDestino);
				break;
			case 4:
				//System.out.print("Saldo atual de " + conta.getTitular() + ": ");
				conta.verificarSaldo();	
				System.out.printf("Seu saldo é: R$%.2f", conta.getSaldo());
				LeituraEscrita.comprovanteSaldo(conta);
				break;
			case 5:
				if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.GERENTE.getTipoPessoa())) {
					MenuRelatorio.menuRelatorio(TipoPessoa.GERENTE.getIdTipoPessoa(), funcionario, conta);
				} else if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.DIRETOR.getTipoPessoa())) {
					MenuRelatorio.menuRelatorio(TipoPessoa.DIRETOR.getIdTipoPessoa(), funcionario, conta);
				} else if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.PRESIDENTE.getTipoPessoa())) {
					MenuRelatorio.menuRelatorio(TipoPessoa.PRESIDENTE.getIdTipoPessoa(), funcionario, conta);
				} else if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.OPERADOR_CAIXA.getTipoPessoa())) {
					MenuRelatorio.menuRelatorio(TipoPessoa.OPERADOR_CAIXA.getIdTipoPessoa(), funcionario, conta);
				}
				break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}	


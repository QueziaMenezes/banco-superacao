package br.com.residencia.poo.menus;

import java.io.IOException;

import br.com.residencia.poo.IO.LeituraEscrita;
import br.com.residencia.poo.contas.Conta;
import br.com.residencia.poo.contas.ContaPoupanca;
import br.com.residencia.poo.enums.TipoConta;
import br.com.residencia.poo.enums.TipoPessoa;
import br.com.residencia.poo.contas.ContaCorrente;
import br.com.residencia.poo.pessoas.Funcionario;
import br.com.residencia.poo.principal.Principal;
import br.com.residencia.poo.tributos.Tributo;
import br.com.residencia.poo.usuarios.Cliente;

public class MenuRelatorio {

	protected int teste;
	protected int operacao;
	protected static int enter;
	Principal principal = new Principal();
	Menu menuPrincipal = new Menu();

	public static void menuRelatorio(Integer idFuncionario, Funcionario funcionario, Conta conta) throws IOException {
		limpar();
		imprimeLinhaHorizontal();
		System.out.println("Digite o número correspondente a operação desejada.");
		System.out.println("Escolha uma das opções: ");
		if (conta.getTipoConta().equalsIgnoreCase(TipoConta.CONTA_CORRENTE.getTipoConta())) {
			System.out.println("1 - Tributação conta corrente.");
		} else {
			System.out.println("1 - Simulação de rendimento.");
		}
		
		if (idFuncionario >= 1) {
			System.out.println("2 - Número de contas da sua agencia.");
			if (idFuncionario >= 2) {
				System.out.println("3 - Informações de Nome, CPF e Agência de todos os clientes.");
				if (idFuncionario >= 2) {
					System.out.println("4 - Informações do valor total do capital armazenado no banco.");
					
				}
			}
		}
		
		System.out.println("0 - Voltar");
		imprimeLinhaHorizontal();

		System.out.print("Digite a opção desejada: ");
		int operacao = Principal.sc.nextInt();

		switch (operacao) {
		case 0:
			limpar();
			Menu.menuPrincipal(funcionario, conta);
			break;
		case 1:
			if (conta.getTipoConta().equalsIgnoreCase(TipoConta.CONTA_CORRENTE.getTipoConta())) {
				imprimeLinhaHorizontal();
				System.out.println("\nTributação conta corrente.\n");
				System.out.printf("O total gasto com operações foi de R$%.2f",
						((ContaCorrente) conta).getTotalTributado());
				System.out.printf("\nO valor cobrado para cada saque é de R$ %.2f", Tributo.SAQUE);
				System.out.println("\nTotal de saques realizados: " + ((ContaCorrente) conta).getTotalSaques());
				LeituraEscrita.relatorioTributacaoContaCorrente(conta);
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
			} else {
				int inputDias;
				double inputValor;
				imprimeLinhaHorizontal();
				System.out.println("\nSimulação de rendimento.\n");
				System.out.println("Digite o valor para a simulação: ");
				inputValor = Double.parseDouble(Principal.sc.next());
				System.out.println("Digite o número de dias para a simulação: ");
				inputDias = Principal.sc.nextInt();
				double valorRendimento = ((ContaPoupanca) conta).previsaoDeRendimento(inputValor, inputDias);
				LeituraEscrita.relatorioRendimentoPoupanca(conta, inputValor, inputDias, valorRendimento);
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
			}
			limpar();
			selecaoRelatorio(conta, funcionario);
			break;
		case 2:
			if (idFuncionario >= 1) {
				int total = 0;
				imprimeLinhaHorizontal();
				System.out.println("\nNúmero de contas da sua agencia.\n");
				for (String cpf : Conta.mapaContas.keySet()) {
					if (Conta.mapaContas.get(cpf).getAgencia().equals(conta.getAgencia())) {
						System.out.println(Conta.mapaContas.get(cpf));
						total++;
					}
				}
				System.out.println("Total de contas: " + total);
				LeituraEscrita.relatorioContasPorAgencia(conta);
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
				limpar();
			} else {
				System.out.println("\nAcesso negado!");
			}
			selecaoRelatorio(conta, funcionario);
			break;
		case 3:
			if (idFuncionario >= 2) {
				imprimeLinhaHorizontal();
				System.out.println("\nInformações de Nome, CPF e Agência de todos os clientes.\n");
				for (String nome : Funcionario.OrdenaFuncionarios.keySet()) {
					System.out.println(((Funcionario) Funcionario.OrdenaFuncionarios.get(nome)).relatorioInformacoes());
				}
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
				LeituraEscrita.relatorioTotalClientes(conta, Funcionario.OrdenaFuncionarios);
				limpar();
			} else {
				System.out.println("\nAcesso negado!");
			}
			selecaoRelatorio(conta, funcionario);
			break;
		case 4:
			if (idFuncionario >= 2) {
				imprimeLinhaHorizontal();
				double totalBanco = 0;
				System.out.println("\nValor total do capital armazenado no banco.\n");
				for(String cpf : Conta.mapaContas.keySet()) {
					totalBanco = totalBanco + ((Conta) conta).mapaContas.get(cpf).getSaldo();
				}
	
				System.out.printf("Capital total armazenado no banco: R$ %.2f", totalBanco);
				LeituraEscrita.relatorioTotalCapitalBanco(conta, totalBanco);
				System.out.println("\nDigite 1 para continuar.");
				enter = Principal.sc.nextInt();
			} else {
				System.out.println("\nAcesso negado!");
			}
			selecaoRelatorio(conta, funcionario);
			break;
		default:
			System.out.println("Opção inválida!");
			limpar();
			selecaoRelatorio(conta, funcionario);
		}
	}

	public static void selecaoRelatorio(Conta conta, Funcionario funcionario) throws IOException {
		if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.GERENTE.getTipoPessoa())) {
			MenuRelatorio.menuRelatorio(TipoPessoa.GERENTE.getIdTipoPessoa(), funcionario, conta);
		} else if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.DIRETOR.getTipoPessoa())) {
			MenuRelatorio.menuRelatorio(TipoPessoa.DIRETOR.getIdTipoPessoa(), funcionario, conta);
		} else if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.PRESIDENTE.getTipoPessoa())) {
			MenuRelatorio.menuRelatorio(TipoPessoa.PRESIDENTE.getIdTipoPessoa(), funcionario, conta);
		} else if (funcionario.getTipoPessoa().equalsIgnoreCase(TipoPessoa.OPERADOR_CAIXA.getTipoPessoa())) {
			MenuRelatorio.menuRelatorio(TipoPessoa.OPERADOR_CAIXA.getIdTipoPessoa(), funcionario, conta);
		}
	}

	public static void imprimeLinhaHorizontal() {
		System.out.println("\n==================================================");
	}
	
	public static void limpar() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}

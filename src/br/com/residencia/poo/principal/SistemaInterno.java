package br.com.residencia.poo.principal;

import java.io.IOException;

import br.com.residencia.poo.IO.LeituraEscrita;

public class SistemaInterno {

	public static void main(String[] args) throws IOException {
			LeituraEscrita.leitor("dados");

			Principal principal = new Principal();
			principal.menuInterativo();
	}
}

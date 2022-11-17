package br.com.cesarschool.poo.utils.contas;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.utils.Comparador;

public class ComparadorContaDataAbertura implements Comparador {

	@Override
	public int comparar(Object o1, Object o2) {
		Conta p1 = (Conta)o1;
		Conta p2 = (Conta)o2;
		return p1.getDataAbertura().compareTo(p2.getDataAbertura());
	}
}

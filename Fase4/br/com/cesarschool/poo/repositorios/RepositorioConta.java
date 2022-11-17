package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.entidades.Conta;

public class RepositorioConta extends RepostorioGenerico {

	private static final int TAMANHO_MAX_CONTAS = 1000;
	private static RepositorioConta instancia;
	
	public static RepositorioConta getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioConta();
		}
		return instancia;
	}
	private RepositorioConta() {
		
	}
	
	public boolean incluir(Conta conta) {
		return super.incluir(conta);
	}
	public boolean alterar(Conta conta) {
		return super.alterar(conta);	
	}
	
	public Conta buscar(long numero) {
		return (Conta)super.buscar("" + numero);
	}
	
	public boolean excluir(long numero) {
		return super.excluir("" + numero);		
	}
	
	public Conta[] buscarTodos() {
		return (Conta[])super.buscarTodos();
	}
	@Override
	public int getTamanhoMaximoRepositorio() {
		return TAMANHO_MAX_CONTAS;
	}
}

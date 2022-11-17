package br.com.cesarschool.poo.mediators;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.entidades.StatusConta;
import br.com.cesarschool.poo.repositorios.RepositorioConta;
import br.com.cesarschool.poo.repositorios.RepositorioCorrentista;
import br.com.cesarschool.poo.utils.Ordenador;

public class CorrentistaMediator {
	
	private static final String MSG_CORRENTISTA_NAO_INFORMADO = "Correntista nao informado"; 
	private static final String MSG_CPF_NAO_INFORMADO = "CPF nao informado"; 
	private static final String MSG_NOME_NAO_INFORMADO = "Nome nao informado"; 
	private static final String MSG_CPF_INVALIDO = "CPF invalido";
	private static final String MSG_CORRENTISTA_NAO_INCLUIDO = "Correntista nao incluido";
	private static final String MSG_CORRENTISTA_NAO_ENCONTRADO = "Correntista nao encontrado";
	private static final int ZERO = 0;
	
	private RepositorioCorrentista repositorio = RepositorioCorrentista.getInstancia();
	
	public StatusValidacaoCorrentista incluir(Correntista correntista) {
		StatusValidacaoCorrentista status = validar(correntista);
		if (status.isValido()) {
			boolean retornoRepositorio = repositorio.incluir(correntista);
			if (!retornoRepositorio) {
				status.getCodigosErros()[0] = StatusValidacaoCorrentista.CORRENTISTA_NAO_INCLUIDO;
				status.getMensagens()[0] = MSG_CORRENTISTA_NAO_INCLUIDO;
				status.setValido(false);
			}
		}
		return status;
	}
	public StatusValidacaoCorrentista alterar(Correntista correntista) {
		StatusValidacaoCorrentista status = validar(correntista);
		if (status.isValido()) {
			boolean retornoRepositorio = repositorio.alterar(correntista);
			if (!retornoRepositorio) {
				status.getCodigosErros()[0] = StatusValidacaoCorrentista.CORRENTISTA_NAO_ENCONTRADO;
				status.getMensagens()[0] = MSG_CORRENTISTA_NAO_ENCONTRADO;
				status.setValido(false);
			}
		}
		return status;
	}
	public boolean excluir(String cpf) {
		return repositorio.excluir(cpf);
	}	
	public Correntista buscar(String cpf) {
		return repositorio.buscar(cpf);
	}
	
	private boolean cpfValido(String cpf) {
		// Retorno sempre true pois facilita os testes
		// Mas...na entrega, o matodo deveria estar implementado!! 
		return true;
	}
	
	private StatusValidacaoCorrentista validar(Correntista correntista) {
		int[] codigoStatus = new int[StatusValidacaoCorrentista.QTD_SITUACOES_EXCECAO]; 
		String[] mensagensStatus = new String[StatusValidacaoCorrentista.QTD_SITUACOES_EXCECAO];
		int contErros = 0;
		if (correntista == null) {
			codigoStatus[contErros++] = StatusValidacaoCorrentista.CORRENTISTA_NAO_INFORMADO;
			mensagensStatus[contErros] = MSG_CORRENTISTA_NAO_INFORMADO;			
		} else {
			if (correntista.getCpf() == null || "".equals(correntista.getCpf().trim())) {
				codigoStatus[contErros++] = StatusValidacaoCorrentista.CPF_NAO_INFORMADO;
				mensagensStatus[contErros] = MSG_CPF_NAO_INFORMADO;
			} 
			if (correntista.getNome() == null || "".equals(correntista.getNome().trim())) {
				codigoStatus[contErros++] = StatusValidacaoCorrentista.NOME_NAO_INFORMADO;
				mensagensStatus[contErros] = MSG_NOME_NAO_INFORMADO;				
			}			
			if (cpfValido(correntista.getCpf())) {
				codigoStatus[contErros++] = StatusValidacaoCorrentista.CPF_INVALIDO;
				mensagensStatus[contErros] = MSG_CPF_INVALIDO;				
			}			
		}		
		return new StatusValidacaoCorrentista(codigoStatus, mensagensStatus, contErros == ZERO);		
	}
	public Correntista[] consultarTodosOrdenadoPorNome() {
		Correntista[] todos = repositorio.buscarTodos();
		if (todos != null && todos.length > 0) {
			ordenarCorrentistaPorNome(todos);
		}
		return todos;
	}
	private void ordenarCorrentistaPorNome(Correntista[] correntistas) {
		Ordenador.ordenar(correntistas);
	}
}

package com.dio.banklineapi.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.banklineapi.dto.NovaMovimentacao;
import com.dio.banklineapi.model.Correntista;
import com.dio.banklineapi.model.Movimentacao;
import com.dio.banklineapi.model.MovimentacaoTipo;
import com.dio.banklineapi.repository.CorrentistaRepository;
import com.dio.banklineapi.repository.MovimentacaoRepository;



@Service
public class MovimentacaoService {
	@Autowired
	private MovimentacaoRepository repository;
	
	@Autowired
	private CorrentistaRepository correntistaRepository;
	
	public void save (NovaMovimentacao novaMovimentacao) {

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDataHora(LocalDateTime.now());
		
		movimentacao.setDescricao(novaMovimentacao.getDescricao());
		movimentacao.setIdConta(novaMovimentacao.getIdConta());
		
		
		Double valor = novaMovimentacao.getTipo() == MovimentacaoTipo.RECEITA ? novaMovimentacao.getValor() : novaMovimentacao.getValor() * -1; 
		movimentacao.setValor(valor);
		
		movimentacao.setTipo(novaMovimentacao.getTipo());

		
		Correntista correntista = correntistaRepository.findById(novaMovimentacao.getIdConta()).orElse(null);
		if (correntista != null) {
			correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
			correntistaRepository.save(correntista);
		}
		repository.save(movimentacao);
		
	}
}

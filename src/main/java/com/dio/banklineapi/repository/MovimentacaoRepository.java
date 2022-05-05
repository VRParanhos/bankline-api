package com.dio.banklineapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dio.banklineapi.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer>{

}

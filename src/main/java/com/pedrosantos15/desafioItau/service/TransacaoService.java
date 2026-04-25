package com.pedrosantos15.desafioItau.service;

import com.pedrosantos15.desafioItau.dto.TransacaoDTO;
import com.pedrosantos15.desafioItau.mapper.TransacaoMapper;
import com.pedrosantos15.desafioItau.model.TransacaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private TransacaoModel transacao;
    private TransacaoMapper mapper;

    public TransacaoService(TransacaoModel transacao, TransacaoMapper mapper) {
        this.transacao = transacao;
        this.mapper = mapper;
    }

    public TransacaoDTO receberTransacao(TransacaoDTO transacaoDTO){
        transacao = mapper.dtoToEntity(transacaoDTO);
        TransacaoModel.getTransacoes().add(transacao);
        return transacaoDTO;
    }

    public TransacaoDTO getTransacao(){
        return mapper.entityToDto(transacao);
    }

    public void deletarTransacao(){
        transacao = null;
        TransacaoModel.getTransacoes().clear();
    }
}

package com.pedrosantos15.desafioItau.service;

import com.pedrosantos15.desafioItau.dto.EstatisticaDTO;
import com.pedrosantos15.desafioItau.dto.TransacaoDTO;
import com.pedrosantos15.desafioItau.mapper.TransacaoMapper;
import com.pedrosantos15.desafioItau.model.TransacaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

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

    public EstatisticaDTO calcularEstatisticas(){
        List<TransacaoModel> transacoesUltimoMinuto = TransacaoModel.getTransacoes()
                .stream()
                .filter(transacaoModel -> transacaoModel.getDataHora().isAfter(OffsetDateTime.now().minusMinutes(1)))
                .toList();

        DoubleSummaryStatistics dss = new DoubleSummaryStatistics();

        if (!transacoesUltimoMinuto.isEmpty()){
            for (TransacaoModel transacaoModel : transacoesUltimoMinuto) {
                dss.accept(transacaoModel.getValor().doubleValue());
            }

            return new EstatisticaDTO(
                    dss.getCount(),
                    dss.getSum(),
                    dss.getAverage(),
                    dss.getMin(),
                    dss.getMax()
                    );
        } else {

            return new EstatisticaDTO((long) 0.0, 0.0, 0.0, 0.0, 0.0);
        }

    }
}

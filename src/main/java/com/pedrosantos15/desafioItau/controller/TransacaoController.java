package com.pedrosantos15.desafioItau.controller;

import com.pedrosantos15.desafioItau.dto.EstatisticaDTO;
import com.pedrosantos15.desafioItau.dto.TransacaoDTO;
import com.pedrosantos15.desafioItau.model.TransacaoModel;
import com.pedrosantos15.desafioItau.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TransacaoController {

    private TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping("/transacao")
    public ResponseEntity<TransacaoDTO> receberTransacao(@Valid @RequestBody TransacaoDTO transacaoDTO){

        service.receberTransacao(transacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/transacao")
    public TransacaoDTO getTransacao(){
        return service.getTransacao();
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deletarTransacao(){
        service.deletarTransacao();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<EstatisticaDTO> calcularEstatistica(){
        return ResponseEntity.ok(service.calcularEstatisticas());
    }
}

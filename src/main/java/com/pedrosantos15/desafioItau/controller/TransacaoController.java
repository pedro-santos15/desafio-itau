package com.pedrosantos15.desafioItau.controller;

import com.pedrosantos15.desafioItau.dto.EstatisticaDTO;
import com.pedrosantos15.desafioItau.dto.TransacaoDTO;
import com.pedrosantos15.desafioItau.model.TransacaoModel;
import com.pedrosantos15.desafioItau.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create a new Transaction",
            description = "Using JSON as RequestBody, that POST operation create a new transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "422", description = "Validation denied the request body"),
            @ApiResponse(responseCode = "400", description = "Invalid format in the request body")
    })
    public ResponseEntity<TransacaoDTO> receberTransacao(@Valid @RequestBody @Parameter(name = "TransacaoDTO",
    description = "Json of 'TransacaoDTO' containing 'valor' and 'dataHora'") TransacaoDTO transacaoDTO){

        service.receberTransacao(transacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/transacao")
    public TransacaoDTO getTransacao(){
        return service.getTransacao();
    }

    @DeleteMapping("/transacao")
    @Operation(summary = "Delete all Transactions",
            description = "With this DELETE method, is possible to delete all the registers of transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted")
    })
    public ResponseEntity<Void> deletarTransacao(){
        service.deletarTransacao();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estatistica")
    @Operation(summary = "Return statistics of recently transactions",
            description = "With this GET method, is possible to return the statistics of the last 60 seconds transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully operation")
    })
    public ResponseEntity<EstatisticaDTO> calcularEstatistica(){
        return ResponseEntity.ok(service.calcularEstatisticas());
    }
}

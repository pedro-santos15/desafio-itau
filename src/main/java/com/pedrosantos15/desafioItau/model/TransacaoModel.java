package com.pedrosantos15.desafioItau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@Component
public class TransacaoModel {
    private BigDecimal valor;
    private OffsetDateTime dataHora;
}

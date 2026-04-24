package com.pedrosantos15.desafioItau.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoDTO(
        @NotNull(message = "A transação deve conter valor")
        @Min(value = 0, message = "A transação deve ter valor igual ou maior que 0(zero)")
        BigDecimal valor,
        @NotNull(message = "")
        @Past(message = "A transação não pode ser feita no futuro!")
        OffsetDateTime dataHora) {
}

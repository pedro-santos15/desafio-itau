package com.pedrosantos15.desafioItau.mapper;

import com.pedrosantos15.desafioItau.dto.TransacaoDTO;
import com.pedrosantos15.desafioItau.model.TransacaoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {

    TransacaoDTO entityToDto(TransacaoModel model);

    TransacaoModel dtoToEntity(TransacaoDTO dto);
}

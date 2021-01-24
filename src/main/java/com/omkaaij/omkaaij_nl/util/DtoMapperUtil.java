package com.omkaaij.omkaaij_nl.util;

import com.omkaaij.omkaaij_nl.data.dto.DTO;
import org.modelmapper.ModelMapper;


public class DtoMapperUtil {

    public static <T> T mapDtoToEntity(DTO<T> dto, Class<T> entityClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, entityClass);
    }

    public static <T, U> U mapEntityToDto (T entity, Class<U> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(entity, dtoClass);
    }
}

package com.effective_mobile.mapper;

import com.effective_mobile.dto.UserDto;
import com.effective_mobile.model.UserInfo;
import org.mapstruct.Mapper;

/**
 * Интерфейс-маппер для преобразования между объектами UserInfo и UserDto.
 *
 * @author [Ваше имя]
 */
@Mapper(componentModel = "spring")
public interface Mappers {

    /**
     * Преобразует объект UserInfo в UserDto.
     *
     * @param userInfo объект UserInfo для преобразования
     * @return эквивалентный объект UserDto
     */
    UserDto toUserDto(UserInfo userInfo);

    /**
     * Преобразует объект UserDto в UserInfo.
     *
     * @param userDto объект UserDto для преобразования
     * @return эквивалентный объект UserInfo
     */
    UserInfo toUserInfo(UserDto userDto);
}


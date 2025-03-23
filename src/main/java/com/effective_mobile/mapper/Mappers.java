package com.effective_mobile.mapper;

import com.effective_mobile.dto.UserDto;
import com.effective_mobile.model.UserInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Mappers {
    UserDto toUserDto(UserInfo userInfo);

    UserInfo toUserInfo(UserDto userDto);

}

package com.esell.esell_user_service.service.impl;

import com.esell.esell_user_service.dto.request.RequestUserDto;
import com.esell.esell_user_service.dto.request.RequestUserLoginDeto;

public interface UserService {
    public void signup(RequestUserDto requestUserDto);
    public Object login(RequestUserLoginDeto requestUserLoginDeto);
}

package com.esell.esell_user_service.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestUserDto {
    private String username;
    private String password;
    private String email;
}

package com.esell.esell_user_service.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestUserLoginDeto {
    private String password;
    private String email;
}

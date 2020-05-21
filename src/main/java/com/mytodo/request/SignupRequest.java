package com.mytodo.request;

import com.mytodo.bean.validation.ValidEmail;
import com.mytodo.bean.validation.ValidUsername;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    @ValidUsername
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @ValidEmail
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> roles;
}

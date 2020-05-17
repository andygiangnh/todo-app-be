package com.mytodo.response;

import lombok.Getter;

import java.io.Serializable;
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;

    @Getter
    private final String token;

    @Getter
    private boolean success = false;

    @Getter
    private UserResponse user;

    public JwtResponse(String jwttoken, UserResponse user) {
        this.token = jwttoken;
        this.user = user;
        this.success = true;
    }

}
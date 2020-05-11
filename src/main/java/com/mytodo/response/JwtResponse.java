package com.mytodo.response;

import java.io.Serializable;
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private boolean success = false;
    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
        this.success = true;
    }
    public String getToken() {
        return this.jwttoken;
    }

    public boolean isSuccess() {
        return this.success;
    }
}
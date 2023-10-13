package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthTokenDto
{
    public String token_type;
    public int expires_in;
    public String access_token;
    public String scope;
}

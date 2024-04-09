package com.example.relationshipJPA.Dao;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JwtAuthenticationResponse {

    private String token;

//    private String refreshToken;
}


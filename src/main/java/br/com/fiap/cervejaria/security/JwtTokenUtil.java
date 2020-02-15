package br.com.fiap.cervejaria.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expire;

    public String generateToken(String username){
        Date dataCriacao = getDateFromLocalDateTime(LocalDateTime.now());
        Date dataExpiracao = getDateFromLocalDateTime(LocalDateTime.now().plusMinutes(expire));
        //claims parte do token, header, payload and signature
        //subject tudo o que tem dentro do payload
        //issuedAt - data de criacao
        return Jwts.builder().setClaims(new HashMap<>())
                .setSubject(username)
                .setIssuedAt(dataCriacao)
                .setExpiration(dataExpiracao)
                // para não poderem descriptografar passamos a secret
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer", ""))
                .getBody();

        return claims.getSubject();
    }

    private Date getDateFromLocalDateTime(LocalDateTime now) {
        return Date.from(now.toInstant(OffsetDateTime.now().getOffset()));
    }
}

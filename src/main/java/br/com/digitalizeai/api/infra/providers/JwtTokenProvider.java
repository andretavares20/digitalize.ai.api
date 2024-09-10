package br.com.digitalizeai.api.infra.providers;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    // Gerar uma chave secreta segura
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final int jwtExpirationInMs = 604800000; // 7 dias

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        // Extrair os papéis do usuário (roles) da autenticação
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(username)
                .claim("role", roles) // Adicionar o papel do usuário no JWT
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)  // Usando a chave segura para assinar o token
                .compact();
    }
}

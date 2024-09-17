package br.com.digitalizeai.api.infra.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtTokenProvider tokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Logar a URL da requisição
        String requestUrl = request.getRequestURL().toString();
        String requestUri = request.getRequestURI();
        System.out.println("URL completa da requisição: " + requestUrl);
        System.out.println("URI da requisição: " + requestUri);

        // Obter o token do cabeçalho Authorization
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("Cabeçalho Authorization recebido: " + authorizationHeader);

        String jwtToken = tokenProvider.getJwtFromRequest(request);

        if (jwtToken != null) {
            if (tokenProvider.validateToken(jwtToken)) {
                String username = tokenProvider.getUsernameFromJWT(jwtToken);
                System.out.println("Token JWT válido. Usuário autenticado: " + username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, tokenProvider.getAuthorities(jwtToken));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("Token JWT inválido: " + jwtToken);
            }
        } else {
            System.out.println("Nenhum token JWT encontrado na requisição");
        }

        chain.doFilter(request, response);
    }
}

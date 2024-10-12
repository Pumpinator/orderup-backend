package edu.mx.utleon.orderup.api.usuarios.filtro;


import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mx.utleon.orderup.api.usuarios.servicio.UsuarioDetalles;
import edu.mx.utleon.orderup.api.usuarios.servicio.UsuarioDetallesServicio;
import edu.mx.utleon.orderup.modelo.entidad.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;


public class AutenticacionFiltro extends UsernamePasswordAuthenticationFilter {

    private final String secret;

    private final long expiration;

    public AutenticacionFiltro(AuthenticationManager authenticationManager, String secret, long expiration) {
        super(authenticationManager);
        this.secret = secret;
        this.expiration = expiration;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            UsuarioDetalles usuarioDetalles = UsuarioDetalles.builder().usuario(usuario).build();
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            usuarioDetalles.getUsername(),
                            usuarioDetalles.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UsuarioDetalles usuarioDetalles = (UsuarioDetalles) authResult.getPrincipal();

        byte[] secretBytes = Base64.getEncoder().encode(secret.getBytes());
        SecretKey key = Keys.hmacShaKeyFor(secretBytes);

        String token = Jwts.builder()
                .claim("scope", authResult.getAuthorities())
                .subject(usuarioDetalles.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(expiration)))
                .signWith(key)
                .compact();

        String tokenResponse = "{\"accessToken\": \"%s\", \"userId\": \"%s\"}";
        tokenResponse = String.format(tokenResponse, token, usuarioDetalles.getUsuario().getId());

        response.getWriter().write(tokenResponse);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}

package edu.mx.utleon.orderup.api.usuarios.filtro;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class AutorizacionFiltro extends BasicAuthenticationFilter {

    private final String header;

    private final String prefix;

    private final String secret;

    public AutorizacionFiltro(AuthenticationManager authenticationManager, String header, String prefix, String secret) {
        super(authenticationManager);
        this.header = header;
        this.prefix = prefix;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(header);

        if (authorizationHeader == null || !authorizationHeader.startsWith(prefix)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(header);

        if (authorizationHeader == null) {
            return null;
        }

        String token = authorizationHeader.replace(prefix, "");

        if (secret == null) {
            return null;
        }

        JwtFiltro jwtFiltro = new JwtFiltro(token, secret);

        String subject = jwtFiltro.getJwtSubject();

        if (subject == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(subject, null, jwtFiltro.getUserAuthorities());
    }
}
package edu.mx.utleon.orderup.api.usuarios.filtro;

import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtFiltro {

    Jwt<?, ?> jwt;

    public JwtFiltro(String jwt, String secret) {
        this.jwt = parseJwt(jwt, secret);
    }

    Jwt<?, ?> parseJwt(String jwt, String secret) {
        byte[] secretBytes = Base64.getEncoder().encode(secret.getBytes());
        SecretKey key = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS512.getJcaName());
        JwtParser jwtParser = Jwts.parser()
                .setSigningKey(key)
                .build();

        return jwtParser.parse(jwt);
    }

    public Collection<? extends GrantedAuthority> getUserAuthorities() {
        Collection<Map<String, String>> scopes = ((Claims) jwt.getBody()).get("scope", List.class);
        return scopes.stream().map(scope -> new SimpleGrantedAuthority(scope.get("authority"))).collect(Collectors.toList());
    }

    public String getJwtSubject() {
        return ((Claims) jwt.getBody()).getSubject();
    }


}

package Inventario.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//la que tiene que ver con el filtro
//OncePerRequestFilter asegura que el filtro se ejecute solo una vez por cada solicitud http
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    // este metodo es que va a realizar todos los filtros relacionados al token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            
            final String token = getTokenFromRequest(request);
            final String username;

            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            final Claims userId = jwtService.getAllClaims(token);
            if (userId == null) {
                filterChain.doFilter(request, response);
                return;
            }
            
            username = jwtService.getUsernameFromToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication()== null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.isTokenValid(token,userDetails)) {
                    
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        Long.valueOf(userId.get("id").toString()),
                        null,
                        userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }
            }

            filterChain.doFilter(request, response);
    }
    // Extrae el token de la cabecera de autorizacion de  una peticion http 
    private String getTokenFromRequest(HttpServletRequest request) {
        
        final String authHeader= request.getHeader(HttpHeaders.AUTHORIZATION); // obtiene el valor de la cabecera de 'authorization' de la solicitud http

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) { //verifica si autheader no es nullo o esta vacio y si empieza por el prefijo 'Bearer '
            return authHeader.substring(7); //retorna el token real omitiendo los prim eros 7 caracteres, los cuales serian :'Bearer '
        }
        return null;

    }
    
}
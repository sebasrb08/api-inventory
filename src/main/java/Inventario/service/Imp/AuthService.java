package Inventario.service.Imp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Inventario.Dto.AuthResponse;
import Inventario.Dto.LoginRequest;
import Inventario.Dto.RegisterRequest;
import Inventario.jwt.JwtService;
import Inventario.model.Role;
import Inventario.model.Usuario;
import Inventario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UsuarioRepository userRepository;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Optional<Usuario> user = userRepository.findByUsername(request.getUsername());
        String token = jwtService.getToken(user.get());
        return AuthResponse.builder()
        .token(token)
        .build();
    }

    public AuthResponse register(RegisterRequest request) {
       Usuario user =Usuario.builder()
       .username(request.getUsername())
       .nombre(request.getNombre())
       .apellido(request.getApellido())
       .email(request.getEmail())
       .telefono(request.getTelefono())
       .password(passwordEncoder.encode(request.getPassword()))
       .role(Role.USER)
       .build();

       userRepository.save(user); // Guardando en base de datos el usuario registrado

       return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}
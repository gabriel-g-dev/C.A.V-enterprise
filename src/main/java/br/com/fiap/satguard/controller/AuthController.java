package br.com.fiap.satguard.controller;
import br.com.fiap.satguard.dto.LoginDTO;
import br.com.fiap.satguard.dto.RegisterDTO;
import br.com.fiap.satguard.model.Usuario;
import br.com.fiap.satguard.repository.UsuarioRepository;
import br.com.fiap.satguard.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        if(this.repository.findByUsuarioEmail(data.email()).isPresent()) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario newUser = new Usuario();
        newUser.setUsuarioNome(data.nome());
        newUser.setUsuarioEmail(data.email());
        newUser.setUsuarioSenha(encryptedPassword);
        newUser.setUsuarioTipo(data.tipo());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}

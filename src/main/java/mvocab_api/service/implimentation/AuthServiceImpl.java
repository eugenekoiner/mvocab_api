package mvocab_api.service.implimentation;

import lombok.AllArgsConstructor;
import mvocab_api.dto.AuthDto;
import mvocab_api.dto.AuthResponseDto;
import mvocab_api.dto.LoginDto;
import mvocab_api.entity.RoleEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.exeption.AlreadyExistException;
import mvocab_api.exeption.DoesNotExistException;
import mvocab_api.repository.RoleRepository;
import mvocab_api.repository.UserRepository;
import mvocab_api.security.JWTGenerator;
import mvocab_api.service.AuthService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor

public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    AuthenticationManager authenticationManager;
    JWTGenerator jwtGenerator;

    @Override
    public UserEntity registerUser(AuthDto authDto) throws AlreadyExistException, DoesNotExistException {
        if (userRepository.findByEmail(authDto.getEmail()).isPresent()) {
            throw new AlreadyExistException("user");
        }
        RoleEntity role = roleRepository.findByName("USER").orElseThrow(() -> new DoesNotExistException("role"));
        UserEntity user = new UserEntity();
        user.setEmail(authDto.getEmail());
        user.setPassword(passwordEncoder.encode(authDto.getPassword()));
        user.setUserRoles(singletonList(role));
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistException("user");
        }
    }

    @Override
    public AuthResponseDto loginUser(LoginDto loginDto) throws DoesNotExistException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String  token = jwtGenerator.generateToken(authentication);
        return new AuthResponseDto(token);
    }
}

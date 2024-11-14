package com.cuna_inteligente.backend_cuna_inteligente.bl;
import com.cuna_inteligente.backend_cuna_inteligente.dao.UsuarioRepository;
import com.cuna_inteligente.backend_cuna_inteligente.dto.LoginDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.ResponseDto;
import com.cuna_inteligente.backend_cuna_inteligente.dto.TokenDto;
import com.cuna_inteligente.backend_cuna_inteligente.entity.Usuario;
import com.cuna_inteligente.backend_cuna_inteligente.utility.HashingUtility;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

import com.auth0.jwt.interfaces.DecodedJWT;


@Service
public class AuthBl {
    public static final String KEY = "SDKJCBDSC823923";
    private final UsuarioRepository usuarioRepository;

    public AuthBl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public TokenDto login(LoginDto loginDto) {
        TokenDto tokenDto = new TokenDto();
        try {
        Usuario user = usuarioRepository.findByGmail(loginDto.getGmail());
        if (user == null) {
            HashingUtility hashingUtility = new HashingUtility();
            if (hashingUtility.checkPassword(loginDto.getContrasenia(), user.getContrasenia())) {
               int idUser = user.getIdUsuario();
               tokenDto.setIdUser(user.getIdUsuario());
               tokenDto.setAuthToken(generateToken(idUser, "auth", 4 * 60 * 60));
                tokenDto.setRefreshToken(generateToken(idUser, "refresh", 7 * 24 * 60 * 60));
                tokenDto.setType(1);
            }else{
                tokenDto.setType(-1);
            }
        }else{
            tokenDto.setType(-1);
        }
        return tokenDto;
    }catch (Exception e){
        tokenDto.setType(-2);
    }
    return tokenDto;
    }

    public String validateToken(String authToken, int idPath) {
        if (authToken == null || authToken.isEmpty()) {
            return "Token invalido";// si el token es vacío o nulo
        }
        if (authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
        }
        try {
            DecodedJWT decodedJWT;
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("com.ucb.page.sistemas")
                    .build();
            decodedJWT = verifier.verify(authToken);
            if (decodedJWT.getExpiresAt().before(new Date())) {
                return "Token expirado";
            }
            if (idPath != decodedJWT.getClaim("idUser").asInt()
                    || !decodedJWT.getClaim("tokenRole").asString().equals("auth")) {
                return "Token invalido";
            }
            return "Token valido";/// Pequeño cambio, antes devolvía el rol como verificación de que estaba bien
                                  /// return decodedJWT.getClaim("userType").asString();
        } catch (Exception e) {
            return "Token invalido";
        }
    }

    public TokenDto refreshToken(TokenDto refreshTokenDto){
        TokenDto tokenDto = new TokenDto();
        String refreshToken = refreshTokenDto.getRefreshToken();
        try { 
            DecodedJWT decodedJWT;
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("com.ucb.page.cuna_inteligente")
                    .build();
            decodedJWT = verifier.verify(refreshToken);
            if (decodedJWT.getExpiresAt().before(new Date())) {
                tokenDto.setType(-3);
                return tokenDto;
            }
            if (!decodedJWT.getClaim("tokenRole").asString().equals("refresh")) {
                tokenDto.setType(-4);
                return tokenDto;
            }
            int idUser = decodedJWT.getClaim("idUser").asInt();
            if (idUser != refreshTokenDto.getIdUser()) {
                tokenDto.setType(-4);
                return tokenDto;
            }
            // String userType = decodedJWT.getClaim("userType").asString();
            tokenDto.setIdUser(idUser);
            tokenDto.setAuthToken(generateToken(idUser, "auth", 4 * 60 * 60));
            tokenDto.setRefreshToken(refreshToken);
            tokenDto.setType(1);
            return tokenDto;
        } catch (Exception e) {
            tokenDto.setType(-2);
            return tokenDto;
        }

    }

    private String generateToken(int idUser, String tokenRole, int tokenExpirationTime) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            long currentTime = System.currentTimeMillis();
            return com.auth0.jwt.JWT.create()
                    .withIssuer("com.ucb.page.cuna_inteligente")
                    .withClaim("idUser", idUser)
                    .withClaim("tokenRole", tokenRole)
                    .withIssuedAt(new Date(currentTime))
                    .withExpiresAt(new Date(currentTime + tokenExpirationTime * 1000L))// payload
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el token", e);
        }
    }

    public ResponseDto<TokenDto> gestionarRespuestaToken(TokenDto tokenDto) {
        ResponseDto<TokenDto> responseDto = new ResponseDto<>();
        switch (tokenDto.getType()) {
            case -2:
                responseDto.setCode("500");
                responseDto.setErrorMessage("Error en el servidor");
                break;
            case -1:
                responseDto.setCode("401");
                responseDto.setErrorMessage("Usuario o contraseña incorrectos");
                break;
            case -3:
                responseDto.setErrorMessage("Refresh token expirado");
                responseDto.setCode("401");
                break;
            case -4:
                responseDto.setErrorMessage("Refresh token invalido");
                responseDto.setCode("401");
                break;
            default:
                responseDto.setCode("200");
                responseDto.setResponse(tokenDto); // por default se devuelve el mismo token
                break;
        }
        return responseDto;
    }


    public <T> ResponseDto<T> gestionarRespuesta(String token, Integer idUser, String errorMessage) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        try {
            String role = validateToken(token, idUser);
            switch (role) {
                case "Token invalido", "Token expirado":
                    responseDto.setCode("401");
                    responseDto.setErrorMessage(role);
                    break;
                default:
                    responseDto.setCode("200");
                    break;
            }
        } catch (Exception e) {
            responseDto.setCode("500");
            responseDto.setErrorMessage(errorMessage);
        }
        return responseDto;
    }


    
}

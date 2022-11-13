package br.edu.ifpb.dac.guilherme.projetojpa.presentation.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifpb.dac.guilherme.projetojpa.business.service.ConverterService;
import br.edu.ifpb.dac.guilherme.projetojpa.business.service.OwnerService;
import br.edu.ifpb.dac.guilherme.projetojpa.business.service.interfaces.AuthenticationService;
import br.edu.ifpb.dac.guilherme.projetojpa.business.service.tokenService.TokenService;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;
import br.edu.ifpb.dac.guilherme.projetojpa.presentation.dto.LoginDTO;
import br.edu.ifpb.dac.guilherme.projetojpa.presentation.dto.OwnerDTO;
import br.edu.ifpb.dac.guilherme.projetojpa.presentation.dto.TokenDTO;

@RestController
@RequestMapping("/api")
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class AuthenticationController {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private ConverterService converterService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO dto){
        try {
            String token = authService.login(dto.getUsername(), dto.getPassword());
            Owner entity = ownerService.findByEmail(dto.getUsername());
            OwnerDTO userDTO = converterService.ownerToDTO(entity);

            TokenDTO tokenDTO = new TokenDTO(token,userDTO);
            
            return new ResponseEntity(tokenDTO, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/isTokenValid")
    public ResponseEntity isTokenValid(@RequestBody TokenDTO dto){
        try {
            boolean isTokenValid = tokenService.isValid(dto.getToken());
            
            return new ResponseEntity(isTokenValid, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
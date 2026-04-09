package com.example.teste.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.teste.Controller.UserController;
import com.example.teste.Service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;
    
    
    @Test
    void deveCadastrarUsuario() throws Exception {
      String json = """
       {
         "nome" : "allisson",
         "email" : "allisson@gmail.com",
         "senha" : "Senha123@"
       }              
      """;

      mockMvc.perform(post("/user/cadastrar")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk())
        .andExpect(content().string("Usuário cadastrado"));
    }

    @Test
    void naoDeveCadastrarUsuario() throws Exception {
      String json = """
       {
         "nome" : "allisson",
         "email" : "allisson.com",
         "senha" : "Senha123"
       }              
      """;

      mockMvc.perform(post("/user/cadastrar")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isBadRequest());
        
      verify(userService, never()).cadastrarUser(any());
    }

    
    @Test
    void deveAceitarEmailValido() throws Exception {
      String json = """
       {
         "nome" : "Diogo",
         "email" : "diogo@gmail.com",
         "senha" : "Senha123@"
       }              
      """;

      mockMvc.perform(post("/user/cadastrar")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk());
    }

    @Test
    void naoDeveAceitarEmailInvalido() throws Exception {

        String json = """
        {
        "nome": "Diogo",
        "email": "diogo.com",
        "senha": "Senha123@"
        }
        """;

        mockMvc.perform(post("/user/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void naoDeveAceitarSenhaCurta() throws Exception {

        String json = """
        {
        "nome": "Allisson",
        "email": "allisson@gmail.com",
        "senha": "Teste12"
        }
        """;

        mockMvc.perform(post("/user/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void naoDeveCadastrarEmailDuplicado() throws Exception {

        String json = """
        {
        "nome": "Allisson",
        "email": "thiago@gmail.com",
        "senha": "Senha123@"
        }
        """;

        doThrow(new RuntimeException("Email já cadastrado"))
                .when(userService)
                .cadastrarUser(any());

        mockMvc.perform(post("/user/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void NaoDeveCadastrarNomeVazio() throws Exception {
                String json = """
        {
        "nome": "",
        "email": "allisson@gmail.com",
        "senha": "Teste12"
        }
        """;

        mockMvc.perform(post("/user/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }
}
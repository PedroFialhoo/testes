package com.example.teste.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType; 
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers; 
import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional 
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void naoDeveCadastrarEmailDuplicado() throws Exception {

        String json = """
        {
            "nome": "Allisson",
            "email": "duplicado@gmail.com",
            "senha": "Senha123@"
        }
        """;

        String jsonEmailDiferente = """
        {
            "nome": "Allisson",
            "email": "outro@gmail.com",
            "senha": "Senha123@"
        }
        """;

        
        mockMvc.perform(post("/user/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        
        mockMvc.perform(post("/user/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email Já cadastrado"));
    }
}
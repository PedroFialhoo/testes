package com.example.teste.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank
    @NotEmpty
    @Email
    private String email;
    @NotBlank
    @NotEmpty
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "A senha deve conter letra maiúscula, minúscula e número")
    private String senha;
    
    @NotBlank()
    private String nome;
}

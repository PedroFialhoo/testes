package com.example.teste.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.teste.Dto.UserDto;
import com.example.teste.Model.User;
import com.example.teste.Repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

    public boolean cadastrarUser(UserDto userDto){
      User user = new User();
      user.setEmail(userDto.getEmail());
      user.setSenha(userDto.getSenha());
      user.setNome(userDto.getNome());
      userRepository.save(user);

      return true;
    }
}

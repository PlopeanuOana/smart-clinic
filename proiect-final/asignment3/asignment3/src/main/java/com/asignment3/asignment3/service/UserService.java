package com.asignment3.asignment3.service;


import com.asignment3.asignment3.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO register(UserDTO userDTO);

    UserDTO save(UserDTO userDTO);

    void deleteUser(Long id);

    List<UserDTO> findAllUsersDTO();

    UserDTO logIn(String username, String password);

    UserDTO findByUsername(String username);

    UserDTO findById(Long id);

}

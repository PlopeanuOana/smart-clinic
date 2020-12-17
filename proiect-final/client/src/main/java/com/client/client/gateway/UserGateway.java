package com.client.client.gateway;

import com.client.client.DTO.UserDTO;
import com.client.client.DTO.map.PatientDTOM;
import org.apache.catalina.User;

import java.util.List;

public interface UserGateway {

    UserDTO findById(Long id);

    List<UserDTO> findAll();

    Long register(UserDTO userDTO);

    UserDTO login(UserDTO userDTO);

    UserDTO findByUsername(String username);

    UserDTO save(UserDTO userDTO);

    void delete(Long id);

}

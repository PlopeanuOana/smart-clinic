package com.asignment3.asignment3.controllers;


import com.asignment3.asignment3.Assignment3ApplicationTests;
import com.asignment3.asignment3.model.dto.DrugDTO;
import com.asignment3.asignment3.model.dto.UserDTO;
import com.asignment3.asignment3.model.enumeration.UserRole;
import com.asignment3.asignment3.service.DrugService;
import com.asignment3.asignment3.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerUnitTest extends Assignment3ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private DrugService drugService;

    @ParameterizedTest
    @ValueSource(strings = {"Nume_medicament","altNume","notNUll"})
    public void insertDrugTest(String name) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        DrugDTO drugDTO=new DrugDTO();
        drugDTO.setName(name);
        drugDTO.setDescription("111");
        drugDTO.setPrice(11f);
        drugDTO.setQuantity(10);
        drugDTO.setId(1L);

        mockMvc.perform(post("/drug/save")
                .content(objectMapper.writeValueAsString(drugDTO))
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0785964123A","078569dsf04123"," "})
    public void insertUserTestFailsDueToPhoneNumber(String phoneNumber) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTO = new UserDTO();
        userDTO.setName("User full name");
        userDTO.setAddress("Somewhere");
        userDTO.setUsername("username1");
        userDTO.setPassword("password");
        userDTO.setPhone(phoneNumber);
        userDTO.setRole(UserRole.DOCTOR);

        mockMvc.perform(post("/user/save")
                .content(objectMapper.writeValueAsString(userDTO))
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }


    @ParameterizedTest
    @ValueSource(strings = {" "})
    public void insertUserTestFailsDueToNull(String name) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);

        mockMvc.perform(post("/user/save")
                .content(objectMapper.writeValueAsString(userDTO))
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

}

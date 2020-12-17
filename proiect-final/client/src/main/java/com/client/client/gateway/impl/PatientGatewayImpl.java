package com.client.client.gateway.impl;

import com.client.client.DTO.DoctorDTO;
import com.client.client.DTO.PatientDTO;
import com.client.client.DTO.map.AppointmentDTOM;
import com.client.client.DTO.map.PatientDTOM;
import com.client.client.config.RestProperties;
import com.client.client.gateway.PatientGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PatientGatewayImpl implements PatientGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGatewayImpl.class);
    private final String URL = "/patient";
    private final RestProperties restProperties;

    @Autowired
    public PatientGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public PatientDTO findById(Long id) {
        LOGGER.info("Executing find patient by id method, id=" + id);
        String url = restProperties.getUrl() + URL + "/find?id=" + id;

        RestTemplate restTemplate = new RestTemplate();
        PatientDTO patientDTO = restTemplate.getForObject(url, PatientDTO.class);
        return patientDTO;
    }

    @Override
    public PatientDTO findByUserId(Long user_id) {
        LOGGER.info("Executing find doctor by user id method, id=" + user_id);
        String url = restProperties.getUrl() + URL + "/patientData?user_id=" + user_id;

        RestTemplate restTemplate = new RestTemplate();
        PatientDTO patient = restTemplate.getForObject(url, PatientDTO.class);
        return patient;
    }

    @Override
    public PatientDTO findByUsername(String username) {
        LOGGER.info("Executing findById method, id=" + username);
        String url = restProperties.getUrl() + URL + "/patientData?username=" + username;

        RestTemplate restTemplate = new RestTemplate();
        PatientDTO patient = restTemplate.getForObject(url, PatientDTO.class);
        return patient;
    }

    @Override
    public List<PatientDTOM> findAll() {
        LOGGER.info("Executing findAll method");
        String url = restProperties.getUrl() + URL +"/all";
        RestTemplate restTemplate = new RestTemplate();
       // ResponseEntity<PatientDTOM[]> forEntity = restTemplate.getForEntity(url, PatientDTOM[].class);
        PatientDTOM[] response = restTemplate.getForObject(url, PatientDTOM[].class);
        return Arrays.asList(response);
    }

    @Override
    public PatientDTO save(PatientDTO patientDTO) {
        LOGGER.info("Executing save method for doctor");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(patientDTO);
        RestTemplate restTemplate = new RestTemplate();
        PatientDTO response = restTemplate.postForObject(url, httpEntity, PatientDTO.class);
        return response;
    }

    @Override
    public List<AppointmentDTOM> findAllApp(Long id) {
        LOGGER.info("Executing findAll method");
        String url = restProperties.getUrl() + "/appointment/listByPatientId?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        AppointmentDTOM[] response = restTemplate.getForObject(url, AppointmentDTOM[].class);
        if(response==null) return new ArrayList<>();
        return Arrays.asList(response);
    }

    @Override
    public void delete(Long id) {
        String url = restProperties.getUrl() + URL + "/delete?id="+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, id);
    }
}

package com.client.client.gateway.impl;

import com.client.client.DTO.DoctorDTO;
import com.client.client.DTO.PatientDTO;
import com.client.client.DTO.map.AppointmentDTOM;
import com.client.client.DTO.map.DoctorDTOM;
import com.client.client.config.RestProperties;
import com.client.client.gateway.DoctorGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DoctorGatewayImpl implements DoctorGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGatewayImpl.class);
    private final String URL = "/doctor";
    private final RestProperties restProperties;

    @Autowired
    public DoctorGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public DoctorDTO findById(Long id) {
        LOGGER.info("Executing find doctor by id method, id=" + id);
        String url = restProperties.getUrl() + URL + "/find?id=" + id;

        RestTemplate restTemplate = new RestTemplate();
        DoctorDTO doctorDTO = restTemplate.getForObject(url, DoctorDTO.class);
        return doctorDTO;
    }

    @Override
    public DoctorDTO findByUserId(Long user_id) {
        LOGGER.info("Executing find doctor by user id method, id=" + user_id);
        String url = restProperties.getUrl() + URL + "/doctorData?user_id=" + user_id;

        RestTemplate restTemplate = new RestTemplate();
        DoctorDTO doctorDTO = restTemplate.getForObject(url, DoctorDTO.class);
        return doctorDTO;
    }

    @Override
    public List<DoctorDTOM> findAll() {
        LOGGER.info("Executing find all doctors method");
        String url = restProperties.getUrl() + URL +"/allDTOM";
        RestTemplate restTemplate = new RestTemplate();
        DoctorDTOM[] response = restTemplate.getForObject(url, DoctorDTOM[].class);
        return Arrays.asList(response);
    }

    @Override
    public DoctorDTO save(DoctorDTO doctorDTO) {
        LOGGER.info("Executing save method for doctor");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(doctorDTO);
        RestTemplate restTemplate = new RestTemplate();
        DoctorDTO response = restTemplate.postForObject(url, httpEntity, DoctorDTO.class);
        return response;
    }

    @Override
    public List<AppointmentDTOM> findAllApp(Long id) {
        LOGGER.info("Executing find all appointments method");
        String url = restProperties.getUrl() + "/appointment/listByDoctorId?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        AppointmentDTOM[] response = restTemplate.getForObject(url, AppointmentDTOM[].class);
        if (response==null) return new ArrayList<AppointmentDTOM>();
        return Arrays.asList(response);
    }

    @Override
    public void delete(Long id) {
        String url = restProperties.getUrl() + URL + "/delete?id="+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, id);
    }
}

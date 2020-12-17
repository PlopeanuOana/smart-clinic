package com.client.client.gateway.impl;

import com.client.client.DTO.AppointmentDTO;
import com.client.client.DTO.DoctorDTO;
import com.client.client.config.RestProperties;
import com.client.client.gateway.AppointmentGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AppointmentGatewayImpl implements AppointmentGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGatewayImpl.class);
    private final String URL = "/appointment";
    private final RestProperties restProperties;

    @Autowired
    public AppointmentGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public AppointmentDTO findById(Long appointment_id) {
        LOGGER.info("Executing find doctor by id method, id=" + appointment_id);
        String url = restProperties.getUrl() + URL + "/find?id=" + appointment_id;
        RestTemplate restTemplate = new RestTemplate();
        AppointmentDTO appointmentDTO = restTemplate.getForObject(url, AppointmentDTO.class);
        return appointmentDTO;
    }

    @Override
    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        LOGGER.info("Executing save method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(appointmentDTO);
        RestTemplate restTemplate = new RestTemplate();
        AppointmentDTO response = restTemplate.postForObject(url, httpEntity, AppointmentDTO.class);
        return response;
    }

}

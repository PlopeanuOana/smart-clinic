package com.client.client.gateway.impl;

import com.client.client.DTO.BankAccountDTO;
import com.client.client.DTO.PrescriptionDTO;
import com.client.client.DTO.map.AddDrugDTO;
import com.client.client.DTO.map.CheckOutDTO;
import com.client.client.DTO.map.PrescriptionDTOM;
import com.client.client.config.RestProperties;
import com.client.client.gateway.PrescriptionGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PrescriptionGatewayImpl implements PrescriptionGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGatewayImpl.class);
    private final String URL = "/prescription";
    private final RestProperties restProperties;

    @Autowired
    public PrescriptionGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public PrescriptionDTO save(PrescriptionDTO prescriptionDTO) {
        LOGGER.info("Executing save prescription method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(prescriptionDTO);
        RestTemplate restTemplate = new RestTemplate();
        PrescriptionDTO response = restTemplate.postForObject(url, httpEntity, PrescriptionDTO.class);
        return response;
    }

    @Override
    public PrescriptionDTOM findByAppointmentId(Long id) {
        LOGGER.info("Executing find prescription by appointment id method, id=" + id);
        String url = restProperties.getUrl() + URL + "/findByApp?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        PrescriptionDTOM prescriptionDTOM = restTemplate.getForObject(url, PrescriptionDTOM.class);
        return prescriptionDTOM;
    }

    @Override
    public PrescriptionDTOM findById(Long id) {
        LOGGER.info("Executing find prescription by id method, id=" + id);
        String url = restProperties.getUrl() + URL + "/find?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        PrescriptionDTOM prescriptionDTOM = restTemplate.getForObject(url, PrescriptionDTOM.class);
        return prescriptionDTOM;
    }

    @Override
    public Boolean saveArticle(AddDrugDTO addDrugDTO) {
        LOGGER.info("Executing add drug to prescription method");
        String url = restProperties.getUrl() + URL + "/addDrug";
        HttpEntity<Object> httpEntity = new HttpEntity<>(addDrugDTO);
        RestTemplate restTemplate = new RestTemplate();
        Boolean response = restTemplate.postForObject(url, httpEntity, Boolean.class);
        return response;
    }

    @Override
    public Boolean check_out(CheckOutDTO check) {
        LOGGER.info("Executing add drug to prescription method");
        String url = restProperties.getUrl() + URL + "/check_out";
        HttpEntity<Object> httpEntity = new HttpEntity<>(check);
        RestTemplate restTemplate = new RestTemplate();
        Boolean response = restTemplate.postForObject(url, httpEntity, Boolean.class);
        return response;
    }
}

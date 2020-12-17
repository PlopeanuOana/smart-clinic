package com.client.client.gateway.impl;

import com.client.client.DTO.DrugDTO;
import com.client.client.DTO.UserDTO;
import com.client.client.config.RestProperties;
import com.client.client.gateway.DrugGateway;
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
public class DrugGatewayImpl implements DrugGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGatewayImpl.class);
    private final String URL = "/drug";
    private final RestProperties restProperties;

    @Autowired
    public DrugGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Override
    public List<DrugDTO> findAll() {
        LOGGER.info("Executing findAll method");
        String url = restProperties.getUrl() + URL + "/all";
        RestTemplate restTemplate = new RestTemplate();
        DrugDTO[] response = restTemplate.getForObject(url, DrugDTO[].class);
        if(response==null) return new ArrayList<>();
        return Arrays.asList(response);
    }

    @Override
    public Long save(DrugDTO drugDTO) {
        LOGGER.info("Executing save drug method");
        String url = restProperties.getUrl() + URL + "/save";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(drugDTO);
        DrugDTO drug = restTemplate.postForObject(url,httpEntity,DrugDTO.class);
        return drug.getId();
    }

    @Override
    public DrugDTO findById(Long drug_id) {
        LOGGER.info("Executing findById method, id=" + drug_id);
        String url = restProperties.getUrl() + URL + "/find?id=" + drug_id;
        RestTemplate restTemplate = new RestTemplate();
        DrugDTO drug = restTemplate.getForObject(url, DrugDTO.class);
        return drug;
    }

    @Override
    public void delete(Long id) {
        String url = restProperties.getUrl() + URL + "/delete?id="+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url, id);
    }

    @Override
    public void generateReport(boolean csv, boolean pdf) {
        LOGGER.info("Generate report");
        String url = restProperties.getUrl() + URL + "/report?csv="+csv+"&txt="+pdf;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, DrugDTO.class);

    }


}

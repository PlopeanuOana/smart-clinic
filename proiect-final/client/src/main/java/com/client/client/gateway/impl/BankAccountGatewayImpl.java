package com.client.client.gateway.impl;

import com.client.client.DTO.AppointmentDTO;
import com.client.client.DTO.BankAccountDTO;
import com.client.client.config.RestProperties;
import com.client.client.gateway.BankAccountGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BankAccountGatewayImpl implements BankAccountGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGatewayImpl.class);
    private final String URL = "/bank_account";
    private final RestProperties restProperties;

    @Autowired
    public BankAccountGatewayImpl(RestProperties restProperties) {
        this.restProperties = restProperties;
    }


    @Override
    public BankAccountDTO findById(Long id) {
        LOGGER.info("Executing find bank_account by id method, id=" + id);
        String url = restProperties.getUrl() + URL + "/find?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        BankAccountDTO bankAccountDTO = restTemplate.getForObject(url, BankAccountDTO.class);
        return bankAccountDTO;
    }

    @Override
    public BankAccountDTO save(BankAccountDTO bankAccountDTO) {
        LOGGER.info("Executing save bank account method");
        String url = restProperties.getUrl() + URL + "/save";
        HttpEntity<Object> httpEntity = new HttpEntity<>(bankAccountDTO);
        RestTemplate restTemplate = new RestTemplate();
        BankAccountDTO response = restTemplate.postForObject(url, httpEntity, BankAccountDTO.class);
        return response;
    }

    @Override
    public BankAccountDTO findByUserId(Long user_id) {
        LOGGER.info("Executing find bank_account by user id method, id=" + user_id);
        String url = restProperties.getUrl() + URL + "/byUser?id=" + user_id;
        RestTemplate restTemplate = new RestTemplate();
        BankAccountDTO bankAccountDTO = restTemplate.getForObject(url, BankAccountDTO.class);
        return bankAccountDTO;
    }

}

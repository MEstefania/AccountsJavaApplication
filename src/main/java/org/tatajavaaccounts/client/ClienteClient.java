package org.tatajavaaccounts.client;

import org.tatajavaaccounts.dto.respuestaBase.BaseResponseSimpleDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClienteClient {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${client.api-url}")
    private String urlCliente;

    public Object getClientById( Long clientId) {

        ResponseEntity<BaseResponseSimpleDTO> response =
                restTemplate.exchange(
                        urlCliente + "/clientes/" + clientId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<BaseResponseSimpleDTO>() {}
                );

        BaseResponseSimpleDTO body = response.getBody();

        if (body == null ) {
            throw new RuntimeException("Cliente no encontrado");
        }

        return body.getResult();
    }
}

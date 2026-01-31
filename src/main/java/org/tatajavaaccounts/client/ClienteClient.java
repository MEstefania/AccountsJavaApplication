package org.tatajavaaccounts.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.HttpClientErrorException;
import org.tatajavaaccounts.dto.ClienteDTO;
import org.tatajavaaccounts.dto.respuestaBase.BaseResponseSimpleDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
public class ClienteClient {
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${client.api-url}")
    private String urlCliente;

    @Cacheable(value = "clientes", key = "#clienteId")
    public Optional<ClienteDTO> getClientById(Long clientId) {
        try {
            ResponseEntity<BaseResponseSimpleDTO> response =
                    restTemplate.exchange(
                            urlCliente + "/clientes/" + clientId,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<BaseResponseSimpleDTO>() {}
                    );
            BaseResponseSimpleDTO body = response.getBody();
            if(!response.getStatusCode().is2xxSuccessful()){
                System.out.println("Erro al obtener el cliente {}" +response.getStatusCode() );
                return Optional.empty();
            }
            if (body == null || body.getResult() == null) {
                return Optional.empty();
            }
            ObjectMapper mapper = new ObjectMapper();
            ClienteDTO clienteDTO = mapper.convertValue(body.getResult(), ClienteDTO.class);

            return Optional.of(clienteDTO);
        }catch (HttpClientErrorException.NotFound ex){
            throw new EntityNotFoundException("No se encontro el cliente.");
        }
        catch (Exception ex) {
            System.out.println("No se pudo obtener cliente {}" + clientId + ex);
            return Optional.empty();
        }
    }
}

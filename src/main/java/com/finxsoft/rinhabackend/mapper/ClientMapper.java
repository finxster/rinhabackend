package com.finxsoft.rinhabackend.mapper;

import com.finxsoft.rinhabackend.domain.Client;
import com.finxsoft.rinhabackend.dto.ClientDTO;
import org.springframework.stereotype.Component;

/**
 * @author finx
 */
@Component
public class ClientMapper {

    public ClientDTO toDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setBalance(client.getBalance());
        clientDTO.setLimit(client.getLimit());
        return clientDTO;
    }

    public Client toEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setBalance(clientDTO.getBalance());
        client.setLimit(clientDTO.getLimit());
        return client;
    }

}

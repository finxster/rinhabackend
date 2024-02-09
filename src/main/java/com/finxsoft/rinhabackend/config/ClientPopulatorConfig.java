package com.finxsoft.rinhabackend.config;

import com.finxsoft.rinhabackend.domain.Client;
import com.finxsoft.rinhabackend.repository.ClientRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author finx
 */
@Configuration
public class ClientPopulatorConfig {

    private static final Logger log = LoggerFactory.getLogger(ClientPopulatorConfig.class);

    private final ClientRepository clientRepository;

    public ClientPopulatorConfig(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostConstruct
    public void populate() {
        log.info("Cleaning and populating clients database...");
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1L, 0, 100000));
        clients.add(new Client(2L, 0, 80000));
        clients.add(new Client(3L, 0, 1000000));
        clients.add(new Client(4L, 0, 10000000));
        clients.add(new Client(5L, 0, 500000));

        clientRepository.deleteAll().block();
        clientRepository.saveAll(clients).subscribe();
    }
}

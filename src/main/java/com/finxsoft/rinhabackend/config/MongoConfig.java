package com.finxsoft.rinhabackend.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @author finx
 */
@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.finxsoft.rinhabackend")
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${mongodb.uri}")
    String mongoUri;

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Override
    protected String getDatabaseName() {
        return "rinha";
    }
}

package com.shweta.controller;

import com.shweta.TradeEntityEnum;
import com.shweta.entity.BulkTradeEntity;
import com.shweta.entity.TradeEntity;
import com.shweta.processor.EntityConsumer;
import com.shweta.processor.EntityProducer;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ProducerConsumerHandler {
    private static final Logger LOGGER = Logger.getLogger(ProducerConsumerHandler.class.getName());

    public static void main(String[] args) throws InterruptedException {
        List<TradeEntity> entities = new ArrayList<TradeEntity>();
        ProducerConsumerHandler producerConsumerHandler = new ProducerConsumerHandler();
        producerConsumerHandler.populateEntities(entities);

try {
    // create thread for Producer
    EntityProducer entityProducer = new EntityProducer(entities);
    Thread producerThread = new Thread(entityProducer);
    LOGGER.info("Created instance of entity Producer at " + LocalTime.now() + " for thread " + producerThread.getName());
    producerThread.start();

    // create thread for consumer
    EntityConsumer entityConsumer = new EntityConsumer(entities);
    Thread consumerThread = new Thread(entityConsumer);
    LOGGER.info("Created instance of entity Consumer at " + LocalTime.now() + " for thread " + consumerThread.getName());
    consumerThread.start();
    producerThread.join();
    consumerThread.join();
} catch (Exception e) {
    LOGGER.log(Level.SEVERE, e.getMessage(), "Error while creating threads");
}

}

void populateEntities(List<TradeEntity> entities) {
        for (int i = 0; i < 10; i++) {
            TradeEntity entity = new BulkTradeEntity(TradeEntityEnum.bulkTradeEntity.name(),TradeEntityEnum.bulkTradeEntity.name()+i);
            entities.add(entity);
        }
}
}



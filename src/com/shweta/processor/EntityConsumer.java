package com.shweta.processor;

import com.shweta.entity.TradeEntity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntityConsumer implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(EntityConsumer.class.getName());
    private static List<TradeEntity> entities;

    public EntityConsumer(List<TradeEntity> entities) {
        this.entities = entities;
    }

    public List<TradeEntity> getEntities() {
        return entities;
    }

    @Override
    public void run() {
        //write logic to consume the entity
        int size = this.entities.size();
        while (true) {
            synchronized (this.entities) {
                while (this.entities.size() == 0) {
                    try {

                        LOGGER.info("Consumer thread is waiting as the placeholder has 0 entities");
                        this.entities.wait();
                    } catch (InterruptedException e) {
                        LOGGER.log(Level.SEVERE, e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
                TradeEntity entity = this.entities.get(0);
                LOGGER.info("Consuming entity " + entity.getDescription());
                this.entities.remove(entity);
                this.entities.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}

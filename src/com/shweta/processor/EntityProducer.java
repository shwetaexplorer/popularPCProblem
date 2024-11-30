package com.shweta.processor;


import com.shweta.TradeEntityEnum;
import com.shweta.entity.BulkTradeEntity;
import com.shweta.entity.TradeEntity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntityProducer implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(EntityProducer.class.getName());
    private static List<TradeEntity> entities;

    public EntityProducer(List<TradeEntity> entities) {
        this.entities = entities;
    }

    public List<TradeEntity> getEntities() {
        return entities;
    }
    @Override
    public void run() {

        int size = entities.size();
        while (true){
        synchronized (this.entities) {
            while (size == 1000) {
                try {

                    LOGGER.info("Producer thread is waiting as the placeholder has exceeded its limit");
                    this.entities.wait();
                } catch (InterruptedException e) {
                    LOGGER.log(Level.SEVERE, "Interrupted", e);
                    throw new RuntimeException(e);
                }
            }
            TradeEntity bulkTradeEntity = new BulkTradeEntity(TradeEntityEnum.bulkTradeEntity.name(), TradeEntityEnum.bulkTradeEntity.name() + entities.size() + 1);
            LOGGER.info("Added one more bulkTradeEntity in placeHolder ");
            this.entities.add(bulkTradeEntity);
            LOGGER.info("Done Adding one more bulkTradeEntity in placeHolder ");
            this.entities.notify();
            LOGGER.info("Notified other threads having lock on this object .size of list is "+ this.getEntities().size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        LOGGER.info("Out of sync producer");
    }}
}

package com.shweta.entity;

import com.shweta.TradeEntityEnum;

public class BulkTradeEntity implements TradeEntity{
    private String name;
    private String description;
    public BulkTradeEntity(String name , String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return TradeEntityEnum.bulkTradeEntity.name();
    }

    @Override
    public String getDescription() {
        return this.description;

    }
}

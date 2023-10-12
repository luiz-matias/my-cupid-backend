package com.luizmatias.findadev.api.dtos.mappers;

import com.luizmatias.findadev.domain.entities.pagination.OrderType;

public class OrderTypeMapper {

    public static OrderType toOrderType(String orderType) {
        if (orderType.equalsIgnoreCase(OrderType.ASC.getOrder())) {
            return OrderType.ASC;
        } else {
            return OrderType.DESC;
        }
    }

    public static String toOrderTypeString(OrderType orderType) {
        return orderType.getOrder();
    }

}

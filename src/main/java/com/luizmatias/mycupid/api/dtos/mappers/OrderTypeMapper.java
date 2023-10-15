package com.luizmatias.mycupid.api.dtos.mappers;

import com.luizmatias.mycupid.domain.entities.pagination.OrderType;

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

package com.luizmatias.mycupid.domain.entities.pagination;

public enum OrderType {
    ASC("ASC"),
    DESC("DESC");

    private final String order;

    OrderType(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}

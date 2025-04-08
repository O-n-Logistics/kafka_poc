package on.ssgdeal.common;

import lombok.Getter;

@Getter
public enum Topic {
    ORDER("create.order.event"),
    ORDER_ROLLBACK("rollback.order.event"),
    ;

    private final String value;

    Topic(String value) {
        this.value = value;
    }
}

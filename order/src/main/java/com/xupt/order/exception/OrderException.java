package com.xupt.order.exception;

import com.xupt.order.enums.ResultEnum;

/**
 * @author maxu
 * @date 2019/2/18
 */
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}

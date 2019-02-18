package com.xupt.product.VO;

import lombok.Data;

/**
 * http返回的最外层对象
 *
 * @author maxu
 * @date 2019/2/17
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}

package com.xupt.product.utils;

import com.xupt.product.VO.ResultVO;

/**
 * @author maxu
 * @date 2019/2/17
 */
public class ResultVOUtils {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}

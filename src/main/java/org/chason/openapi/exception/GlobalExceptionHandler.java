package org.chason.openapi.exception;

import org.chason.openapi.bean.ResultBean;
import org.chason.openapi.constant.SystemConstantEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author chason
 * @date 2018-03-10
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理应用自定义的异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public ResultBean<?> handleSystemException(HttpServletRequest request, SystemException ex) {

        logger.info("SystemException Occured:: URL=" + request.getRequestURL());
        ex.printStackTrace();
        logger.error(ex.toString());

        ResultBean resultBean = new ResultBean();

        resultBean.setCode(ex.getCode());
        resultBean.setMsg(ex.getMessage());

        return resultBean;
    }

    /**
     * 处理应用自定义的异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BuinessException.class)
    @ResponseBody
    public ResultBean<?> handleBuinessException(HttpServletRequest request, BuinessException ex) {

        logger.info("BuinessException Occured:: URL=" + request.getRequestURL());
        ex.printStackTrace();
        logger.error(ex.toString());

        ResultBean resultBean = new ResultBean();

        resultBean.setCode(ex.getCode());
        resultBean.setMsg(ex.getMessage());

        return resultBean;
    }

    /**
     * 处理其他异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultBean<?> handleOtherException(HttpServletRequest request, Exception ex) {

        logger.info("SystemException Occured:: URL=" + request.getRequestURL());
        ex.printStackTrace();
        logger.error(ex.toString());

        ResultBean resultBean = new ResultBean();
        resultBean.setCode(SystemConstantEnum.SYSTEM_ERROR.getCode());
        resultBean.setMsg(ex.getMessage());

        return resultBean;
    }
}

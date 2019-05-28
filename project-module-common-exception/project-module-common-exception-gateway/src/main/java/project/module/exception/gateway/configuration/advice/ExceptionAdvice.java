package project.module.exception.gateway.configuration.advice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.module.exception.LogicException;
import project.module.exception.gateway.configuration.common.RestResult;


@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class ExceptionAdvice {

    /**
     * logback new instance
     */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 处理业务逻辑异常
     *
     * @param e 业务逻辑异常对象实例
     * @return 逻辑异常消息内容
     */
    @ExceptionHandler(LogicException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public RestResult<String> logicException(LogicException e) {
        logger.error("遇到业务逻辑异常：【{}】", e.getErrCode());
        // 返回响应实体内容

        return new RestResult.Builder<>().errorCode(e.getErrCode()).errorMsg(e.getErrMsg()).status(RestResult.EXCEPTION_STATUS).builder();
    }
}

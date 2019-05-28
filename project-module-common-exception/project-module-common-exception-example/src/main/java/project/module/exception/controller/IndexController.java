package project.module.exception.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.module.exception.LogicException;
import project.module.exception.exception.UserException;
import project.module.exception.exception.enums.ErrorCodeEnum;
import project.module.exception.gateway.configuration.common.RestResult;


/**
 * 测试控制器

 * @author yuqiyu
 */
@RestController
public class IndexController {
    /**
     * 首页方法
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public RestResult<String> index() throws LogicException {
        /**
         * 模拟用户不存在
         * 抛出业务逻辑异常
         */
        if (true) {
            throw new UserException(ErrorCodeEnum.USER_STATUS_FAILD);
        }
        return null;
    }
}

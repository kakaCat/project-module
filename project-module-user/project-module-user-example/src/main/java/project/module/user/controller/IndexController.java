package project.module.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.module.exception.LogicException;
import project.module.exception.enums.SysCodeEnum;

import project.module.exception.gateway.configuration.common.RestResult;
import project.module.user.domain.po.LoginUserPo;
import project.module.user.service.login.user.LoginUserService;



@RestController
public class IndexController {


    @Autowired
    private LoginUserService loginUserService;

    /**
     * 首页方法
     *
     * @return
     */
    @RequestMapping(value = "/get/{id}")
    public RestResult<LoginUserPo> index(@PathVariable("id") long id) throws LogicException {
        /**
         * 模拟用户不存在
         * 抛出业务逻辑异常
         */
        LoginUserPo loginUserPo = loginUserService.selectByPrimaryKey(id);
        return RestResult.build(SysCodeEnum.SYSTEM_SUCCESS, loginUserPo);
    }

    @RequestMapping(value = "/default")
    public RestResult<String> getdefault() throws LogicException {


        return RestResult.build(SysCodeEnum.SYSTEM_SUCCESS, "hellow");
    }

}

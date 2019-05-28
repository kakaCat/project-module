package project.module.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.module.user.domain.po.LoginUserPo;
import project.module.user.service.login.user.LoginUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginUserServiceTest  {

    @Autowired
    private LoginUserService loginUserService;


    @Test
    public void selectByPrimaryKey(){
        LoginUserPo loginUserPo = loginUserService.selectByPrimaryKey(new Long(1));
        System.out.println("---");
    }


}

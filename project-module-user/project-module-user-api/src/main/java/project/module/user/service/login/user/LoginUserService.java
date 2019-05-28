package project.module.user.service.login.user;

import project.module.user.domain.po.LoginUserPo;

public interface LoginUserService {

    int inserEntity(LoginUserPo entity);


    int updateById(LoginUserPo entity);


    LoginUserPo selectByPrimaryKey(long id);

}

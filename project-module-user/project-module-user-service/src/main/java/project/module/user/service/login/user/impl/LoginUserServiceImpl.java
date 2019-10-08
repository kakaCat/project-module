package project.module.user.service.login.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.module.user.domain.po.LoginUserPo;
import project.module.user.repository.rdb.user.LoginUserMapper;
import project.module.user.service.login.user.LoginUserService;
import project.module.user.utils.SqlDateUtils;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private LoginUserMapper loginUserMapper;


    @Override
    public int inserEntity(LoginUserPo entity) {

        entity.setCreateTime(SqlDateUtils.getNowDate());
        entity.setUpdateTime(SqlDateUtils.getNowDate());
        return loginUserMapper.inserEntity(entity);
    }

    @Override
    public int updateById(LoginUserPo entity) {
        entity.setUpdateTime(SqlDateUtils.getNowDate());
        return loginUserMapper.updateById(entity);
    }

    @Override
    public LoginUserPo selectByPrimaryKey(Integer id) {
        return loginUserMapper.selectByPrimaryKey(id);
    }
}

package project.module.realm.role.service.rdb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.module.realm.role.domain.po.CoreRolePo;
import project.module.realm.role.repository.rdb.role.CodeRoleMapper;
import project.module.realm.role.service.role.CoreRoleService;
import project.module.user.domain.po.LoginUserPo;
import project.module.user.repository.rdb.user.LoginUserMapper;
import project.module.user.service.login.user.LoginUserService;
import project.module.user.utils.SqlDateUtils;

@Service
public class CodeRoleServiceImpl implements CoreRoleService {

    @Autowired
    private CodeRoleMapper codeRoleMapper;


    @Override
    public int inserEntity(CoreRolePo entity) {
        return codeRoleMapper.inserEntity(entity);
    }

    @Override
    public int updateById(CoreRolePo entity) {
        return codeRoleMapper.updateById(entity);
    }

    @Override
    public CoreRolePo selectByPrimaryKey(Integer id) {
        return codeRoleMapper.selectByPrimaryKey(id);
    }
}

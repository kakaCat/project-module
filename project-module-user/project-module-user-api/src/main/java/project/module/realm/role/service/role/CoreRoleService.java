package project.module.realm.role.service.role;

import project.module.realm.role.domain.po.CoreRolePo;

public interface CoreRoleService {

    int inserEntity(CoreRolePo entity);


    int updateById(CoreRolePo entity);


    CoreRolePo selectByPrimaryKey(Integer id);

}

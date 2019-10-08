package project.module.realm.map.service.map;

import project.module.realm.map.domain.po.CoreUserRoleOrgPo;

public interface CoreUserRoleOrgService {

    int inserEntity(CoreUserRoleOrgPo entity);


    int updateById(CoreUserRoleOrgPo entity);


    CoreUserRoleOrgPo selectByPrimaryKey(Integer id);

}

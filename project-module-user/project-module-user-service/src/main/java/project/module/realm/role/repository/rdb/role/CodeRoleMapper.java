package project.module.realm.role.repository.rdb.role;


import org.apache.ibatis.annotations.Param;
import project.module.realm.role.domain.po.CoreRolePo;


public interface CodeRoleMapper {

	int inserEntity(CoreRolePo entity);


	int updateById(CoreRolePo entity);


	CoreRolePo selectByPrimaryKey(@Param("id") Integer id);



}

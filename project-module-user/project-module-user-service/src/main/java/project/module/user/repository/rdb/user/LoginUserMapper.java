package project.module.user.repository.rdb.user;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import project.module.user.domain.po.LoginUserPo;

@Repository
public interface LoginUserMapper {

	int inserEntity(LoginUserPo entity);


	int updateById(LoginUserPo entity);


	LoginUserPo selectByPrimaryKey(@Param("id") long id);



}

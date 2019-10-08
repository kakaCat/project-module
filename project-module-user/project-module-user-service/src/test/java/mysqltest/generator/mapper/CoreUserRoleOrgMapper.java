package mysqltest.generator.mapper;

import java.util.List;
import mysqltest.generator.pojo.CoreUserRoleOrg;
import mysqltest.generator.pojo.CoreUserRoleOrgExample;
import org.apache.ibatis.annotations.Param;

public interface CoreUserRoleOrgMapper {
    int countByExample(CoreUserRoleOrgExample example);

    int deleteByExample(CoreUserRoleOrgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CoreUserRoleOrg record);

    int insertSelective(CoreUserRoleOrg record);

    List<CoreUserRoleOrg> selectByExample(CoreUserRoleOrgExample example);

    CoreUserRoleOrg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CoreUserRoleOrg record, @Param("example") CoreUserRoleOrgExample example);

    int updateByExample(@Param("record") CoreUserRoleOrg record, @Param("example") CoreUserRoleOrgExample example);

    int updateByPrimaryKeySelective(CoreUserRoleOrg record);

    int updateByPrimaryKey(CoreUserRoleOrg record);
}
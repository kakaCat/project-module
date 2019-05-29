package project.module.dict.repository.rdb.dict;


import org.apache.ibatis.annotations.Param;
import project.module.dict.domain.po.CoreDictPo;

public interface CoreDictMapper {

    int inserEntity(CoreDictPo entity);


    int updateById(CoreDictPo entity);


    CoreDictPo selectByPrimaryKey(@Param("id") Integer id);
}
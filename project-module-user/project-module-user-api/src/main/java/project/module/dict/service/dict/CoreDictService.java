package project.module.dict.service.dict;

import project.module.dict.domain.po.CoreDictPo;

public interface CoreDictService {

    int inserEntity(CoreDictPo entity);


    int updateById(CoreDictPo entity);


    CoreDictPo selectByPrimaryKey(Integer id);

}

package project.module.dict.service.dict.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.module.dict.domain.po.CoreDictPo;
import project.module.dict.repository.rdb.dict.CoreDictMapper;
import project.module.dict.service.dict.CoreDictService;

@Service
public class CoreDictServiceImpl implements CoreDictService {

    @Autowired
    private CoreDictMapper coreDictMapper;




    @Override
    public int inserEntity(CoreDictPo entity) {
        return coreDictMapper.inserEntity(entity);
    }

    @Override
    public int updateById(CoreDictPo entity) {
        return coreDictMapper.updateById(entity);
    }

    @Override
    public CoreDictPo selectByPrimaryKey(Integer id) {
        return coreDictMapper.selectByPrimaryKey(id);
    }


}

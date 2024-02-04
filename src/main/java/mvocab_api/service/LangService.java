package mvocab_api.service;

import mvocab_api.entity.LangEntity;

import java.util.List;

public interface LangService {
    List<LangEntity> findAllLangs();
    LangEntity findById(Integer id);
    LangEntity updateLang (Integer id, LangEntity langEntity);
    String deleteWord (Integer id);


}

package mvocab_api.service;

import mvocab_api.entity.LangEntity;
import mvocab_api.entity.MovieEntity;
import mvocab_api.exeption.DoesNotExistException;

import java.util.List;

public interface LangService {
    PaginationResponse findAllLangs (int page, int size);

    LangEntity findById(Integer id) throws DoesNotExistException;


}

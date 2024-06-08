package mvocab_api.service;

import mvocab_api.entity.TranslationEntity;
import mvocab_api.exeption.AlreadyExistException;
import mvocab_api.exeption.DoesNotExistException;

import java.util.List;

public interface TranslationService {
    TranslationEntity createTranslation(TranslationEntity translationEntity) throws Exception;
    TranslationEntity findTranslationEntityById(Integer id) throws DoesNotExistException;
    TranslationEntity updateTranslationById(Integer id, String translation) throws DoesNotExistException;
    String insertTranslation(String translation, Integer wordId) throws DoesNotExistException, AlreadyExistException;
    TranslationEntity updateTranslationByLangIdAndWordId(String translation, Integer langId, Integer wordId) throws DoesNotExistException;
    String deleteTranslation(Integer id) throws DoesNotExistException;
    TranslationEntity findTranslationByLangIdAndWordId(Integer langId, Integer wordId);
}

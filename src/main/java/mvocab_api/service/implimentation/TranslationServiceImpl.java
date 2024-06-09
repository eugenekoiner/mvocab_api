package mvocab_api.service.implimentation;

import lombok.AllArgsConstructor;
import mvocab_api.entity.TranslationEntity;
import mvocab_api.exeption.AlreadyExistException;
import mvocab_api.exeption.DoesNotExistException;
import mvocab_api.repository.TranslationRepository;
import mvocab_api.service.TranslationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class TranslationServiceImpl implements TranslationService {
    private final TranslationRepository translationRepository;

    @Override
    public TranslationEntity createTranslation(TranslationEntity translationEntity) throws Exception {
        try {
            return translationRepository.save(translationEntity);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Override
    public TranslationEntity findTranslationEntityById(Integer id) throws DoesNotExistException {
        return translationRepository.findById(id).orElseThrow(() -> new DoesNotExistException("translation"));
    }

    @Override
    public TranslationEntity findTranslationByLangIdAndWordId(Integer langId, Integer wordId) {
        return translationRepository.findTranslationByLangIdAndWordId(langId, wordId).orElse(null);
    }

    @Override
    public TranslationEntity updateTranslationByLangIdAndWordId(String translation, Integer langId, Integer wordId) throws DoesNotExistException {
        try {
            if (translationRepository.updateTranslationByLangIdAndWordId(translation, langId, wordId) < 1) {
                throw new DoesNotExistException("translation");
            }
        } catch (DataIntegrityViolationException | DoesNotExistException e) {
            throw new DoesNotExistException("translation");
        }
        return translationRepository.findTranslationByLangIdAndWordId(langId, wordId).orElseThrow(() -> new DoesNotExistException("translation"));
    }

    @Override
    public String insertTranslation(String translation, Integer wordId) throws AlreadyExistException {
        if (translationRepository.insertTranslation(translation, wordId) < 1) {
            throw new AlreadyExistException("translation");
        }
        return "added";
    }


    @Override
    public String deleteTranslation(Integer id) throws DoesNotExistException {
        if (translationRepository.deleteTranslationById(id) < 1) {
            throw new DoesNotExistException("translation");
        }
        return "removed";
    }


}

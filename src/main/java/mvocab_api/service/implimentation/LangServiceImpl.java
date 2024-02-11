package mvocab_api.service.implimentation;

import lombok.AllArgsConstructor;
import mvocab_api.entity.LangEntity;
import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.WordEntity;
import mvocab_api.exeption.DoesNotExistException;
import mvocab_api.model.Movie;
import mvocab_api.repository.LangRepository;
import mvocab_api.repository.MovieRepository;
import mvocab_api.service.LangService;
import mvocab_api.service.LangsResponse;
import mvocab_api.service.MovieService;
import mvocab_api.service.MoviesResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class LangServiceImpl implements LangService {
    private final LangRepository langRepository;

    @Override
    public LangsResponse findAllLangs(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<LangEntity> langs = langRepository.findAll(pageable);
        List<LangEntity> content = langs.stream().collect(Collectors.toList());
        LangsResponse langsResponse = new LangsResponse();
        langsResponse.setContent(content);
        langsResponse.setPage(langs.getNumber());
        langsResponse.setSize(langs.getSize());
        langsResponse.setTotalElements(langs.getTotalElements());
        langsResponse.setTotalPages(langs.getTotalPages());
        langsResponse.setLast(langs.isLast());
        return langsResponse;
    }

    @Override
    public LangEntity findById(Integer id) throws DoesNotExistException {
        return langRepository.findById(id).orElseThrow(() -> new DoesNotExistException("lang"));
    }
}

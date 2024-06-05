package mvocab_api.service.implimentation;

import lombok.AllArgsConstructor;
import mvocab_api.entity.LangEntity;
import mvocab_api.exeption.DoesNotExistException;
import mvocab_api.repository.LangRepository;
import mvocab_api.service.LangService;
import mvocab_api.service.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public PaginationResponse<LangEntity> findAllLangs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LangEntity> langsPage = langRepository.findAll(pageable);
        List<LangEntity> content = langsPage.stream().collect(Collectors.toList());
        return new PaginationResponse<>(new PageImpl<>(content, pageable, langsPage.getTotalElements()));

    }

    @Override
    public LangEntity findById(Integer id) throws DoesNotExistException {
        return langRepository.findById(id).orElseThrow(() -> new DoesNotExistException("lang"));
    }
}

package com.batuhan.youcontribute.service;

import com.batuhan.youcontribute.models.Repository;
import com.batuhan.youcontribute.repositories.RepositoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RepositoryService {

    private final RepositoryRepository repositoryRepository;

    public RepositoryService(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    @Transactional
    public void create(String organization, String repository) {
        Repository r = Repository.builder()
                .organization(organization).repository(repository).build();
        this.repositoryRepository.save(r);
    }

    public List<Repository> list() {
        return this.repositoryRepository.findAll();
    }
}

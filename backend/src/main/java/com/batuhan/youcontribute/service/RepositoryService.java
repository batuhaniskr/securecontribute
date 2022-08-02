package com.batuhan.youcontribute.service;

import com.batuhan.youcontribute.exceptions.DuplicatedRepositoryException;
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
        this.validate(organization, repository);
        Repository r = Repository.builder()
                .organization(organization).repository(repository).build();
        this.repositoryRepository.save(r);
    }

    private void validate(String organization, String repository) {
      this.repositoryRepository.findByOrganizationAndRepository(organization, repository)
        .ifPresent((r) -> {
          throw new DuplicatedRepositoryException(organization, repository);
        });
    }

    public List<Repository> list() {
        return this.repositoryRepository.findAll();
    }
}

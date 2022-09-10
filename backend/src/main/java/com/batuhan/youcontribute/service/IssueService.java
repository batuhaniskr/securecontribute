package com.batuhan.youcontribute.service;

import com.batuhan.youcontribute.models.Issue;
import com.batuhan.youcontribute.models.Repository;
import com.batuhan.youcontribute.repositories.IssueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final RepositoryService repositoryService;

    @Transactional
    public void saveAll(List<Issue> issues) {
      issues.forEach(
        issue -> {
          if (this.issueRepository.findByGithubIssueId(issue.getGithubIssueId()).isEmpty()) {
            this.issueRepository.save(issue);
          }
        });
      this.issueRepository.saveAll(issues);
    }

    public List<Issue> list(Integer repositoryId) {
      Repository repository = repositoryService.findById(repositoryId);
      return issueRepository.findByRepository(repository);
    }

    public Issue findRandomIssue() {
      return issueRepository.findRandomIssue()
        .orElseThrow(() -> new EntityNotFoundException("No issue not found"));
    }
}

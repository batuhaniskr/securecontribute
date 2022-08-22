package com.batuhan.youcontribute.service;

import com.batuhan.youcontribute.models.Issue;
import com.batuhan.youcontribute.models.IssueChallenge;
import com.batuhan.youcontribute.models.IssueChallengeStatus;
import com.batuhan.youcontribute.repositories.IssueChallengeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class IssueChallengeService {

  private final IssueChallengeRepository issueChallengeRepository;

  public IssueChallenge create(Issue issue) {
    IssueChallenge challenge = IssueChallenge.builder().issue(issue).status(IssueChallengeStatus.PENDING).build();

    return this.issueChallengeRepository.save(challenge);
  }

  public Boolean hasOngoingChallenge() {
    return this.issueChallengeRepository.findByStatusIn(IssueChallengeStatus.ongoingStatuses()).isPresent();
  }

  public List<IssueChallenge> list() {
    return issueChallengeRepository.findAll();
  }

  public void updateStatus(Integer id, IssueChallengeStatus status) {
    IssueChallenge issueChallenge = this.issueChallengeRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException(String.format("Issue Challenge %d not found", id)));
    issueChallenge.setStatus(status);
    this.issueChallengeRepository.save(issueChallenge);
  }
}

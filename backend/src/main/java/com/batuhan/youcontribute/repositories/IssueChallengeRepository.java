package com.batuhan.youcontribute.repositories;

import com.batuhan.youcontribute.models.IssueChallenge;
import com.batuhan.youcontribute.models.IssueChallengeStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueChallengeRepository extends PagingAndSortingRepository<IssueChallenge, Integer> {
  Optional<IssueChallenge> findByStatusIn(List<IssueChallengeStatus> statuses);
  List<IssueChallenge> findAll();
}

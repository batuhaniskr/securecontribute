package com.batuhan.youcontribute.repositories;

import com.batuhan.youcontribute.models.Issue;
import com.batuhan.youcontribute.models.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Integer> {

    List<Issue> findAll();

    List<Issue> findByRepository(Repository repository);
}

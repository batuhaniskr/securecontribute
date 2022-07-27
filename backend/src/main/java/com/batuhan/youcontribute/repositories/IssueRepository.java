package com.batuhan.youcontribute.repositories;

import com.batuhan.youcontribute.models.Issue;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Integer> {

    List<Issue> findAll();
}

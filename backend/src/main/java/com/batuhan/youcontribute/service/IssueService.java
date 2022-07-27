package com.batuhan.youcontribute.service;

import com.batuhan.youcontribute.models.Issue;
import com.batuhan.youcontribute.repositories.IssueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    @Transactional
    public void saveAll(List<Issue> issues) {
        this.issueRepository.saveAll(issues);
    }
}

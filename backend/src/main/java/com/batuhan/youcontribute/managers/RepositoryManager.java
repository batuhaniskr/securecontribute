package com.batuhan.youcontribute.managers;

import com.batuhan.youcontribute.models.Issue;
import com.batuhan.youcontribute.models.Repository;
import com.batuhan.youcontribute.service.GithubClient;
import com.batuhan.youcontribute.service.IssueService;
import com.batuhan.youcontribute.service.RepositoryService;
import com.batuhan.youcontribute.service.models.GithubIssueResponse;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepositoryManager {

    private final RepositoryService repositoryService;

    private final GithubClient githubClient;

    private final IssueService issueService;

    public void importRepository(String organization, String repository) {
        this.repositoryService.create(organization, repository);
    }


    @Async
    public void importIssues(Repository repository) {
        LocalDate sinceYesterday = LocalDate.ofInstant(Instant.now().minus(1, ChronoUnit.DAYS), ZoneId.systemDefault());
        GithubIssueResponse[] githubIssueResponses = this.githubClient
                .listIssues(repository.getOrganization(), repository.getRepository(), sinceYesterday);
        List<Issue> issues = Arrays.stream(githubIssueResponses).map(githubIssueResponse ->
                Issue.builder().title(githubIssueResponse.getTitle())
                        .body(githubIssueResponse.getBody()).build()).collect(Collectors.toList());
        this.issueService.saveAll(issues);
    }

}

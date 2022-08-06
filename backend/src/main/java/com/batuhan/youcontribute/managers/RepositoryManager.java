package com.batuhan.youcontribute.managers;

import com.batuhan.youcontribute.config.ApplicationProperties;
import com.batuhan.youcontribute.config.GithubProperties;
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
import java.time.ZoneOffset;
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

    private final ApplicationProperties applicationProperties;

    public void importRepository(String organization, String repository) {
        this.repositoryService.create(organization, repository);
    }


    @Async
    public void importIssues(Repository repository) {
        int schedulerFrequencyInMinutes = applicationProperties.getImportFrequency() / 60000;
        LocalDate since = LocalDate.ofInstant(Instant.now().minus(schedulerFrequencyInMinutes, ChronoUnit.MINUTES), ZoneOffset.UTC);
        GithubIssueResponse[] githubIssueResponses = this.githubClient
                .listIssues(repository.getOrganization(), repository.getRepository(), since);
        System.out.println(githubIssueResponses[0].body);
        List<Issue> issues = Arrays.stream(githubIssueResponses).map(githubIssueResponse ->
                Issue.builder().title(githubIssueResponse.getTitle())
                  .githubIssueId(githubIssueResponse.getId())
                        .body(githubIssueResponse.getBody()).repository(repository).build()).collect(Collectors.toList());
        this.issueService.saveAll(issues);
    }

}

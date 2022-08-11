package com.batuhan.youcontribute.controllers.resources;

import com.batuhan.youcontribute.models.Issue;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class IssueResource {

  private Integer id;

  private Long githubIssueId;

  private Integer githubIssueNumber;

  private String title;

  private String body;

  private String url;

  public static IssueResource createFor(Issue issue) {
    return IssueResource.builder()
      .id(issue.getId())
      .title(issue.getTitle())
      .body(issue.getBody())
      .url(issue.getUrl())
      .githubIssueNumber(issue.getGithubIssueNumber())
      .githubIssueId(issue.getGithubIssueId()).build();
  }

  public static List<IssueResource> createFor(List<Issue> repositories) {
    return repositories.stream().map(IssueResource::createFor).collect(Collectors.toList());
  }
}

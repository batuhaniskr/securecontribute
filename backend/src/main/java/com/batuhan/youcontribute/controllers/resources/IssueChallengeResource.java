package com.batuhan.youcontribute.controllers.resources;

import com.batuhan.youcontribute.models.IssueChallenge;
import com.batuhan.youcontribute.models.IssueChallengeStatus;
import com.batuhan.youcontribute.models.Repository;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class IssueChallengeResource {

  private Integer id;

  private Integer issueId;

  private Integer repositoryId;

  private String repositoryTitle;

  private String issueTitle;

  private Date creationDate;

  private IssueChallengeStatus status;

  public static IssueChallengeResource createFor(IssueChallenge issueChallenge) {
    Repository repository = issueChallenge.getIssue().getRepository();;
    return IssueChallengeResource.builder()
      .id(issueChallenge.getId())
      .issueId(issueChallenge.getId())
      .repositoryId(repository.getId())
      .issueTitle(issueChallenge.getIssue().getTitle())
      .repositoryTitle(repository.getRepository())
      .creationDate(issueChallenge.getCreatedAt())
      .status(issueChallenge.getStatus())
      .build();
  }

  public static List<IssueChallengeResource> createFor(List<IssueChallenge> issueChallenges) {
    return issueChallenges.stream().map(IssueChallengeResource::createFor).collect(Collectors.toList());
  }
}

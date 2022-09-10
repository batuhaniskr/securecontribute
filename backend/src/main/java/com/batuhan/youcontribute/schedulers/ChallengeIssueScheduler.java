package com.batuhan.youcontribute.schedulers;

import com.batuhan.youcontribute.clients.OneSignalClient;
import com.batuhan.youcontribute.models.Issue;
import com.batuhan.youcontribute.models.IssueChallenge;
import com.batuhan.youcontribute.service.IssueChallengeService;
import com.batuhan.youcontribute.service.IssueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ChallengeIssueScheduler {

  private final IssueService issueService;

  private final IssueChallengeService issueChallengeService;

  private final OneSignalClient oneSignalClient;

  @Scheduled(fixedRateString = "${application.challenge-frequency}")
  public void challengeIssueScheduler() {
    log.info("Challenge issue scheduler started");
    if (this.issueChallengeService.hasOngoingChallenge()) {
      log.warn("There is already opening challenge, skipping");
      return;
    }
    Issue randomIssue = this.issueService.findRandomIssue();
    log.info("Found a random issues {}", randomIssue.getId());
    IssueChallenge issueChallenge = issueChallengeService.create(randomIssue);
    oneSignalClient.sendNotification(issueChallenge.getId(),randomIssue.getTitle());
    log.info("Challange issue scheduler finished");
  }
}

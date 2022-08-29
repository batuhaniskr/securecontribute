package com.batuhan.youcontribute.controllers;

import com.batuhan.youcontribute.controllers.request.UpdateChallengeStatusRequest;
import com.batuhan.youcontribute.controllers.resources.IssueChallengeResource;
import com.batuhan.youcontribute.models.IssueChallenge;
import com.batuhan.youcontribute.service.IssueChallengeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class IssueChallengeController {

  private final IssueChallengeService issueChallengeService;

  public IssueChallengeController(IssueChallengeService issueChallengeService) {
    this.issueChallengeService = issueChallengeService;
  }

  @PatchMapping("/{id}")
  public void updateStatus(@PathVariable("id") Integer id, @RequestBody UpdateChallengeStatusRequest request) {
    this.issueChallengeService.updateStatus(id, request.getStatus());
  }

  @GetMapping
  public List<IssueChallengeResource> list() {
    return IssueChallengeResource.createFor(this.issueChallengeService.list()) ;
  }
}

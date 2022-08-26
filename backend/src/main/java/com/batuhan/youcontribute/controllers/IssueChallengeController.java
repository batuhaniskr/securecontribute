package com.batuhan.youcontribute.controllers;

import com.batuhan.youcontribute.controllers.request.UpdateChallengeStatusRequest;
import com.batuhan.youcontribute.service.IssueChallengeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/challanges")
public class IssueChallengeController {

  private final IssueChallengeService issueChallengeService;

  public IssueChallengeController(IssueChallengeService issueChallengeService) {
    this.issueChallengeService = issueChallengeService;
  }

  @PutMapping("/{id}")
  public void updateStatus(@PathVariable("id") Integer id, @RequestBody UpdateChallengeStatusRequest request) {
    this.issueChallengeService.updateStatus(id, request.getStatus());
  }
}

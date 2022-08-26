package com.batuhan.youcontribute.controllers.request;

import com.batuhan.youcontribute.models.IssueChallengeStatus;
import com.batuhan.youcontribute.service.IssueChallengeService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChallengeStatusRequest {

  private IssueChallengeStatus status;

}

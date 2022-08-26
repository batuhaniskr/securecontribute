package com.batuhan.youcontribute.clients;

import com.batuhan.youcontribute.config.ApplicationProperties;
import com.currencyfair.onesignal.OneSignal;
import com.currencyfair.onesignal.model.notification.Button;
import com.currencyfair.onesignal.model.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class OneSignalClient {

  private final ApplicationProperties applicationProperties;

  public OneSignalClient(ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
  }

  public void sendNotification(Integer challengeId, String issueTitle) {
    NotificationRequest request = new NotificationRequest();
    ApplicationProperties.OneSignalProperties oneSignal = this.applicationProperties.getOneSignal();
    request.setTemplateId(oneSignal.getNewChallengeTemplateId());
    request.setAppId(oneSignal.getAppId());
    request.setAnyWeb(true);
    request.setContents(new HashMap<>(){{
      put("en",String.format("Would you like to solve following challange?\n%s",issueTitle));
    }});
    Button acceptButton = new Button();
    acceptButton.setId("accept");
    acceptButton.setText("accept");
    acceptButton.setUrl(String.format("https://localhost:4200/challenge/%d/accept",challengeId));
    Button rejectButton = new Button();
    rejectButton.setId("reject");
    rejectButton.setText("reject");
    rejectButton.setUrl(String.format("https://localhost:4200/challenge/%d/reject",challengeId));
    request.setWebButtons(Arrays.asList(acceptButton, rejectButton));
    request.setAppId("71eeebde-4ae7-4d97-8f18-1089644ca5ec");
    request.setIncludedSegments(new ArrayList<>(){{add("Subscribed Users"); }});
    OneSignal.createNotification(oneSignal.getApiAuthKey(), request);
  }
}

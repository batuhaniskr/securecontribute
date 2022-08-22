package com.batuhan.youcontribute.clients;

import com.batuhan.youcontribute.config.ApplicationProperties;
import com.currencyfair.onesignal.OneSignal;
import com.currencyfair.onesignal.model.notification.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class OneSignalClient {

  private final ApplicationProperties applicationProperties;

  public OneSignalClient(ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
  }

  public void sendNotification() {
    NotificationRequest request = new NotificationRequest();
    ApplicationProperties.OneSignalProperties oneSignal = this.applicationProperties.getOneSignal();
    request.setTemplateId(oneSignal.getNewChallengeTemplateId());
    request.setAppId("71eeebde-4ae7-4d97-8f18-1089644ca5ec");
  }
}

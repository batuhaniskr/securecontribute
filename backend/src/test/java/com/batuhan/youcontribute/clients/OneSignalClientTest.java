package com.batuhan.youcontribute.clients;

import com.batuhan.youcontribute.config.ApplicationProperties;
import com.currencyfair.onesignal.OneSignal;
import com.currencyfair.onesignal.model.notification.NotificationRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OneSignalClientTest {

  @Test
  public void it_should_send_notification() throws Exception {
    //given

    NotificationRequest request = new NotificationRequest();
    request.setTemplateId("2316b607-fb66-40be-bb20-780048c0e934");
    request.setAppId("71eeebde-4ae7-4d97-8f18-1089644ca5ec");
    request.setAnyWeb(true);
    request.setIncludedSegments(new ArrayList<>(){{add("Subscribed Users");}});
    OneSignal.createNotification("MzZiMjIxOTMtZDZmZS00YTc3LWFlN2QtNDRlODU3MjcyYTUw",request);
    //when

    // then
  }

}

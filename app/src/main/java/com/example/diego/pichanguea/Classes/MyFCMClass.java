package com.example.diego.pichanguea.Classes;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by diego on 28-11-2017.
 */

public class MyFCMClass extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null) {
            // do with Notification payload...
            // remoteMessage.getNotification().getBody()
        }

        if (remoteMessage.getData().size() > 0) {
            // do with Data payload...
            // remoteMessage.getData()
        }
    }
}

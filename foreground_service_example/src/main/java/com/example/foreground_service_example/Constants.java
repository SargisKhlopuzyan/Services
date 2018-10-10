package com.example.foreground_service_example;

public class Constants {

    public interface ACTION {
        public static String MAIN_ACTION = "com.example.foreground_service_example.action.main";
        public static String PREV_ACTION = "com.example.foreground_service_example.action.prev";
        public static String PLAY_ACTION = "com.example.foreground_service_example.action.play";
        public static String NEXT_ACTION = "com.example.foreground_service_example.action.next";
        public static String START_FOREGROUND_ACTION = "com.example.foreground_service_example.action.startforeground";
        public static String STOP_FOREGROUND_ACTION = "com.example.foreground_service_example.action.stopforeground";
        public static String STOP_SERVICE_ACTION = "com.example.foreground_service_example.action.stopService";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }

    public interface MEDIA_PLAYER {
        public static String CHANNEL_ID = "channelId";
    }

}

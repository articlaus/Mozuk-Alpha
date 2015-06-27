package com.ui.util;

import org.zkoss.zk.ui.util.Clients;

/**
 * Created by tseegii on 8/12/14.
 */
public class NotificationUtils {

    public static void showMsgWarning(String msg) {
        String printMsg = msg.replaceAll(" ", System.lineSeparator());
        Clients.showNotification(printMsg, Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 0, true);
    }

    public static void showSelectValueMsg() {
        String str = "Та Засах утгаа Сонгон уу.";
        str.replaceAll(" ", System.lineSeparator());
        Clients.showNotification(str, Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 0, true);
    }

    public static void showSuccess() {
        String str = "Амжилттай Хадгалагдлаа!";
        str.replaceAll(" ", System.lineSeparator());
        Clients.showNotification(str, Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 0, true);
    }

    public static void showFailure() {
        String str = "Мэдээллийг хадгалахад алдаа гарлаа!";
        str.replaceAll(" ", System.lineSeparator());
        Clients.showNotification(str, Clients.NOTIFICATION_TYPE_WARNING, null, "middle_center", 0, true);
    }

    public static void showMsg(String msg) {
        String printMsg = msg.replaceAll(" ", System.lineSeparator());
        Clients.showNotification(printMsg, Clients.NOTIFICATION_TYPE_INFO, null, "middle_center", 5000, false);
    }

    public static void showMsgInfo(String msg) {
        String printMsg = msg.replaceAll(" ", System.lineSeparator());
        Clients.showNotification(printMsg, Clients.NOTIFICATION_TYPE_INFO, null, "end_after", 5000, false);
    }
}

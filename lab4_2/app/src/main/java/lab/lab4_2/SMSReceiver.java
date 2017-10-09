package lab.lab4_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;
import java.text.SimpleDateFormat;

public class SMSReceiver extends BroadcastReceiver {

    public static final String TAG = "SMSReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() 호출됨");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if (messages != null && messages.length > 0) {
            String sender = messages[0].getOriginatingAddress();
            Log.d(TAG, "SMS Sender: " + sender);

            String contents = messages[0].getMessageBody().toString();
            Log.d(TAG, "SMS Contents: " + contents);

            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.d(TAG, "SMS Received date: " + receivedDate.toString());

            sendToActivity(context, sender, contents, receivedDate);
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objects = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objects.length];

        for (int i = 0; i < objects.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                messages[i] = SmsMessage.createFromPdu((byte[]) objects[i], bundle.getString("format"));
            else
                messages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
        }

        return messages;
    }

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate) {
        Intent intent = new Intent(context, SMSActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender", sender);
        intent.putExtra("contents", contents);
        intent.putExtra("receivedDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(receivedDate));

        context.startActivity(intent);
    }
}
package com.example.caobotao.festivalmsm.biz;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.SmsManager;
import android.util.Log;

import com.example.caobotao.festivalmsm.bean.SendedMsg;
import com.example.caobotao.festivalmsm.db.SmsDbOpenHelper;
import com.example.caobotao.festivalmsm.db.SmsProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * Created by caobotao on 15/12/26.
 */
public class SmsBiz {
    private Context context;

    public SmsBiz(Context context) {
        this.context = context;
    }

    public int sendMsg(String number, String msg, PendingIntent sentPi, PendingIntent deliverPi) {
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> contents = smsManager.divideMessage(msg);
        for (String content : contents) {
            smsManager.sendTextMessage(number, null, content, sentPi, deliverPi);
        }
        return contents.size();
    }

    public int sendMsg(Set<String> numbers, SendedMsg msg, PendingIntent sentPi, PendingIntent deliverPi) {
        save(msg);
        int result = 0;
        for (String number : numbers) {
            int count = sendMsg(number, msg.getMsg(), sentPi, deliverPi);
            result += count;
        }
        return result;
    }

    private void save(SendedMsg sendedMsg) {
        Log.e("CBT","sendedMsg.getMsg()-->"+sendedMsg.getMsg());
        sendedMsg.setDate(new Date());
        ContentValues values = new ContentValues();
        values.put(SendedMsg.COLUMN_DATE, sendedMsg.getDate().getTime());
        values.put(SendedMsg.COLUMN_FES_NAME, sendedMsg.getFestivalName());
        values.put(SendedMsg.COLUMN_MSG, sendedMsg.getMsg());
        values.put(SendedMsg.COLUMN_NAMES, sendedMsg.getNames());
        values.put(SendedMsg.COLUMN_NUMBERS, sendedMsg.getNumbers());

        context.getContentResolver().insert(SmsProvider.URI_SMS_ALL, values);

    }
}

package tw.org.smsdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActMain extends AppCompatActivity {

    private View.OnClickListener btnSend_Click = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {


            if(  checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[ ]{Manifest.permission.SEND_SMS,Manifest.permission.READ_PHONE_STATE}
                        ,0);

            }


            PendingIntent pi=PendingIntent.getActivity(
                    ActMain.this,
                    0,
                    new Intent(ActMain.this, ActMain.class),
                    0);

            SmsManager sm = SmsManager.getDefault();
            sm.sendTextMessage(
                    "5554",
                    null,
                    "下周一記得交報告!",
                    pi,
                    null);
            Toast.makeText(ActMain.this,"簡訊送出成功!",Toast.LENGTH_SHORT).show();


        }
    };
    private View.OnClickListener btnVibrate_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Vibrator vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
            vib.vibrate(50);
            Toast.makeText(ActMain.this,"啟動震動功能!",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(btnSend_Click);
        Button btnVibrate = findViewById(R.id.btnVibrate);
        btnVibrate.setOnClickListener(btnVibrate_Click);
    }
}

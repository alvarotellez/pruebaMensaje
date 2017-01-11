package es.iesnervion.atellez.pruebamensaje;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Enlace de donde he sacado la info de como enviar el mensaje directamente;
    //http://stackoverflow.com/questions/26311243/sending-sms-programmatically-without-opening-message-app
    Button btnEnviarMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnviarMsg = (Button) findViewById(R.id.btnEnviar);
        btnEnviarMsg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sendSMS("656930852","Esto es una prueba");
    }
    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent to "+phoneNo,
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}

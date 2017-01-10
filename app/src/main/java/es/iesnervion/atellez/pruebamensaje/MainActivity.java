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
        int MI_PERMISO_ENVIO_SMS = 0;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            //Aqui va la accion que hace si tiene permiso
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, msg, null, null);

                Toast toastEnvio =
                        Toast.makeText(getApplicationContext(),
                                "ENVIANDO SMS"+phoneNo, Toast.LENGTH_SHORT);

                toastEnvio.show();

            } else {
                //Aqui va la accion que hace si NO tiene permiso
                //En mi caso solicita permiso
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MI_PERMISO_ENVIO_SMS);
            }
        }
    }
        /*
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    int MY_PERMISSIONS_REQUEST_READ_CONTACTS=0;

    if (ContextCompat.checkSelfPermission(this,
    Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {

        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            // Show an expanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

        } else {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }*/
}

package com.example.proy1bueno.addCompra.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.proy1bueno.R;
import com.example.proy1bueno.addCompra.ContractAddCompra;
import com.example.proy1bueno.addCompra.presenter.AddCompraPresenter;
import com.example.proy1bueno.addProduct.presenter.AddProductPresenter;
import com.example.proy1bueno.beans.Compra;
import com.example.proy1bueno.beans.Valoracion;
import com.example.proy1bueno.categoriesFilter.view.Categories;

public class AddCompra extends AppCompatActivity implements ContractAddCompra.View{
    SharedPreferences sharedPreferencesUserCFG;
    int idUserCompra;
    int idProductCompra;
    Button btnComprar;
    private ImageButton btnHome;

    private AddCompraPresenter presenter = new AddCompraPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_compra);
        initComponents();
    }

    public void initComponents(){
        sharedPreferencesUserCFG = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        idUserCompra = sharedPreferencesUserCFG.getInt("idUser",0);
        Log.e("user_id","id user preferences = " + idUserCompra);
        idProductCompra = getIntent().getIntExtra("idProduct",0);
        Log.e("idProduct","id producto comprar = " + idProductCompra);

        btnHome = findViewById(R.id.btnHome);
        btnComprar = findViewById(R.id.btnComprar);
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, Categories.class);
            startActivity(intent);
        });
        btnComprar.setOnClickListener(view->{
            Compra compra = new Compra();
            compra.setIdComprador(idUserCompra);
            compra.setIdProductoComprado(idProductCompra);
            Toast.makeText(this, compra.toString(), Toast.LENGTH_SHORT).show();
            presenter.addCompra(compra);
            Toast.makeText(this,"COMPRA CONFIRMADA", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Categories.class);
            makeNotification();
            makeEmail();
            startActivity(intent);
        });
    }


    public void makeNotification(){
        String chanelId="CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(),chanelId);
        builder.setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Compra Comfirmada")
                .setContentText("Tu compra a sido confirmada y el paquete se enviara pronto")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(getApplicationContext(), Categories.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("IDK","IDKX2");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0,intent, PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel =
                    notificationManager.getNotificationChannel(chanelId);
            if (notificationChannel == null){
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(chanelId,
                        "description", importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        notificationManager.notify(0,builder.build());
    }

    public void makeEmail(){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"a26954@svalero.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Confirmación de compra");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "¡Gracias por tu compra!");

        // Puedes agregar archivos adjuntos si es necesario
        // Uri uri = Uri.fromFile(new File("ruta_del_archivo"));
        // emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(emailIntent, "Enviar correo electrónico"));

    }
    @Override
    public void successAddCompra(Compra compra) {

    }

    @Override
    public void failureAddCompra(String err) {

    }
}
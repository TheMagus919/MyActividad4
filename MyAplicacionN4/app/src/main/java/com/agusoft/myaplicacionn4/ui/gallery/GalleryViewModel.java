package com.agusoft.myaplicacionn4.ui.gallery;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;

public class GalleryViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private Context context;

    public GalleryViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public void llamar(String numero){
        if(numero.equals(null) || numero.isEmpty()){
            Toast.makeText(context, "Porfavor Ingresar un numero de telefono.", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("tel:" + numero));
            context.startActivity(intent);
        }
    }
    public LiveData<String> getText() {
        if(mText == null){
            mText = new MutableLiveData<>();
        }
        return mText;
    }
}
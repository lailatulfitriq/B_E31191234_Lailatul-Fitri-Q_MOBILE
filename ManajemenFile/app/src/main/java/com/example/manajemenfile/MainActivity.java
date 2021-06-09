package com.example.manajemenfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment ;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.Manifest.*;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {
    EditText editiext;
    private int STORAGE_PERMISSION_CODE=23;
    private EditText editText;

    @Override
    protected  void onCreate (Bundle savedInstanceState){
        super.onCreate (savedInstanceState) ;
        setContentView (R.layout.activity_main);
    editText = (EditText) findViewById (R.id.editText2);
    }
    public void next (View view) {
        Intent intent = new Intent( MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
    public void savePublic(View view){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        String info = editText.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_DOWNLOADS));
        File myFile = new File(folder, "myData1.txt");
                writeData(myFile, info);
        editText.setText("");

    }
    private void writeData (File myFile, String data) {
        FileOutputStream FileOutputStream = null;
        try {
            System.out.println("Yes");
            FileOutputStream = new FileOutputStream(myFile);
            FileOutputStream.write(data.getBytes());
            Toast.makeText(this, "Done" + myFile.getAbsolutePath(), Toast.LENGTH_SHORT).
            show();
        } catch (Exception e){
            e.printStackTrace();
    }finally {
            if ( (FileOutputStream != null)){
                try{
                    FileOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
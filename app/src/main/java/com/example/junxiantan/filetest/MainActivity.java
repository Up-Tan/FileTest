package com.example.junxiantan.filetest;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MainActivity extends Activity {
    private Button read,write;
    private EditText readText,writeText;
    private String fileName="content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        read=(Button)findViewById(R.id.read);
        write=(Button)findViewById(R.id.write);
        readText = (EditText)findViewById(R.id.readText);
        writeText= (EditText)findViewById(R.id.withText);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readText.setText(read());
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write(writeText.getText().toString());
            }
        });
    }

    public void write(String content){
        try{
            FileOutputStream fos = openFileOutput(fileName,Context.MODE_APPEND);
            //当第二个参数为Context.MODE_PRIVATE时，后写入的内容会覆盖原有内容。
            PrintStream ps = new PrintStream(fos);
            ps.print(content);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String read(){
        StringBuilder sbBuilder = new StringBuilder("");
        try {
            FileInputStream is = openFileInput(fileName);
            byte[] buffer = new byte[64];
            int hasRead;
            while ((hasRead = is.read(buffer))!= -1){
                sbBuilder.append(new String(buffer,0,hasRead));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  sbBuilder.toString();
    }
}

package com.hauglidtech.converter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BaseConverter1 extends AppCompatActivity {
    private Button btnConvert, btnReset, btnDec, btnBin, btnOct, btnHex;
    private EditText editText;
    private TextView textViewDec,textViewHex, textViewBin, textViewOct;

    private ClipboardManager clipboard;

    private int base = 10;
    private final float solid = 1.0f;
    private final float transparent = 0.5f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseconverter1);

        initComponents();
        addOnListeners();
    }

    private void initComponents(){
        btnConvert = (Button) findViewById(R.id.button_convert);
        btnReset = (Button) findViewById(R.id.button_reset);
        btnDec = (Button) findViewById(R.id.button_dec);
        btnBin = (Button) findViewById(R.id.button_bin);
        btnOct = (Button) findViewById(R.id.button_oct);
        btnHex = (Button) findViewById(R.id.button_hex);

        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        textViewBin = (TextView) findViewById(R.id.textView_bin);
        textViewDec = (TextView) findViewById(R.id.textView_dec);
        textViewHex = (TextView) findViewById(R.id.textView_hex);
        textViewOct = (TextView) findViewById(R.id.textView_oct);

        editText = (EditText) findViewById(R.id.editText_convert);

    }

    private void addOnListeners(){

        textViewDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textViewDec.getText().toString();
                addToClipboard(text);
            }
        });

        textViewBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textViewBin.getText().toString();
                addToClipboard(text);
            }
        });

        textViewOct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textViewOct.getText().toString();
                addToClipboard(text);
            }
        });

        textViewHex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textViewHex.getText().toString();
                addToClipboard(text);
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                base = 10;
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);

                btnBin.setAlpha(transparent);
                btnOct.setAlpha(transparent);
                btnHex.setAlpha(transparent);
                btnDec.setAlpha(solid);

            }
        });

        btnBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                base = 2;
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);

                btnBin.setAlpha(solid);
                btnOct.setAlpha(transparent);
                btnHex.setAlpha(transparent);
                btnDec.setAlpha(transparent);            }
        });

        btnOct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                base = 8;
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);

                btnBin.setAlpha(transparent);
                btnOct.setAlpha(solid);
                btnHex.setAlpha(transparent);
                btnDec.setAlpha(transparent);}
        });

        btnHex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                base = 16;
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                btnBin.setAlpha(transparent);
                btnOct.setAlpha(transparent);
                btnHex.setAlpha(solid);
                btnDec.setAlpha(transparent);}
        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                textViewDec.setText("");
                textViewHex.setText("");
                textViewBin.setText("");
                textViewOct.setText("");
            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    //noinspection ConstantConditions
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception ignored) {
                }

                String toConvert = String.valueOf(editText.getText().toString()).toUpperCase().trim();
                if( Converter.isValid(toConvert, base) ){
                    switch (base) {
                        case 8:
                            doOctal(toConvert);
                            break;
                        case 16:
                            doHex(toConvert);
                            break;
                        case 10:
                            doDec(toConvert);
                            break;
                        case 2:
                            doBin(toConvert);
                            break;
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void addToClipboard(String text){
        if(text.length() > 0){
            ClipData myClip = ClipData.newPlainText("text", text);
            clipboard.setPrimaryClip(myClip);
            Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
        }
    }

    private void doOctal(String oct){
        String dec = Converter.toDecimal(oct, 8);
        String bin = Converter.decimalToBinary(dec);
        String hex = Converter.decimalToHexadecimal(dec);
        textViewDec.setText(dec);
        textViewBin.setText(bin);
        textViewOct.setText(oct);
        textViewHex.setText(hex);
    }

    private void doHex(String hex){
        String dec = Converter.toDecimal(hex, 16);
        String bin = Converter.decimalToBinary(dec);
        String oct = Converter.decimalToOctal(dec);
        textViewDec.setText(dec);
        textViewBin.setText(bin);
        textViewOct.setText(oct);
        textViewHex.setText(hex);
    }

    private void doDec(String dec){
        String hex = Converter.decimalToHexadecimal(dec);
        String bin = Converter.decimalToBinary(dec);
        String oct = Converter.decimalToOctal(dec);
        textViewDec.setText(dec);
        textViewBin.setText(bin);
        textViewOct.setText(oct);
        textViewHex.setText(hex);
    }

    private void doBin(String bin){
        String dec = Converter.toDecimal(bin, 2);
        String hex = Converter.decimalToHexadecimal(dec);
        String oct = Converter.decimalToOctal(dec);
        textViewDec.setText(dec);
        textViewBin.setText(bin);
        textViewOct.setText(oct);
        textViewHex.setText(hex);
    }


}

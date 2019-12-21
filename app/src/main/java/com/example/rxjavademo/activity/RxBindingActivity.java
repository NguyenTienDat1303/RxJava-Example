package com.example.rxjavademo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rxjavademo.R;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Unit;

public class RxBindingActivity extends AppCompatActivity {

    EditText edtInput;
    TextView tvInput;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_binding);
        edtInput = findViewById(R.id.edtInput);
        tvInput = findViewById(R.id.tvInput);
        btnClear = findViewById(R.id.btnClear);
//        edtInput.addTextChangedListener(new TextWatcher() {
////            @Override
////            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////
////            }
////
////            @Override
////            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////                tvInput.setText(charSequence.toString());
////            }
////
////            @Override
////            public void afterTextChanged(Editable editable) {
////
////            }
////        });
////        btnClear.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                edtInput.setText("");
////                tvInput.setText("");
////            }
////        });
        Disposable disposable = RxTextView.textChanges(edtInput)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        tvInput.setText(charSequence);
                    }
                });
        Disposable disposable1 = RxView.clicks(btnClear)
                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Exception {
                        edtInput.setText("");
                    }
                });
    }
}

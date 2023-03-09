package com.example.bottomsheetdialogtemp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    private Button openBottomSheetDialogClassBtn, openBottomSheetDialogActivityBtn;
    private TextView userNameTv, emailTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setReferences();

        /**
         * Difficult option. Make separate class, where all settings of BottomSheetDialog.
         * But also for back information to activity, need use interface.
         */
        openBottomSheetDialogClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyBottomSheetDialog bottomSheetDialog = new MyBottomSheetDialog(new CallBackBottomSheetDialogInterface() {
                    @Override
                    public void callBack(String userName, String email) {
                        userNameTv.setText(userName);
                        emailTv.setText(email);
                    }
                });
                bottomSheetDialog.show(getSupportFragmentManager(), "");
            }
        });

        /**
         * Easy option. Set layout, set actions, in activity, without make separate class.
         */
        openBottomSheetDialogActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheetDialog();
            }
        });
    }

    private void setReferences(){
        openBottomSheetDialogClassBtn = findViewById(R.id.open_bottom_sheet_dialog_class_btn);
        openBottomSheetDialogActivityBtn = findViewById(R.id.open_bottom_sheet_dialog_activity_btn);
        userNameTv = findViewById(R.id.user_name_tv);
        emailTv = findViewById(R.id.email_tv);
    }

    private void openBottomSheetDialog(){
        View view = getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null, false);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.customBottomSheetDialog);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        EditText userNameEt = view.findViewById(R.id.user_name_et);
        EditText emailEt = view.findViewById(R.id.email_et);

        Button sentBtn = view.findViewById(R.id.sent_btn);
        sentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameEt.getText().toString();
                String email = emailEt.getText().toString();

                userNameTv.setText(userName);
                emailTv.setText(email);

                bottomSheetDialog.dismiss();
            }
        });

    }
}
package com.example.bottomsheetdialogtemp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * @Author: naftalikomarovski
 * @Date: 2023/03/08
 *
 * https://youtu.be/LNO52tcsUA4 - learning video
 */
public class MyBottomSheetDialog extends BottomSheetDialogFragment {

    private CallBackBottomSheetDialogInterface callBack;

    private EditText userNameEt, emailEt;
    private Button sentBtn;

    public MyBottomSheetDialog(CallBackBottomSheetDialogInterface callBack) {
        this.callBack = callBack;
    }

    /**
     * Set them for make transparent background
     * NOTE: After set style, need set styles/background/colors to all items in dialog, other them
     * to be default colors by style of dialog "R.style.customBottomSheetDialog".
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.customBottomSheetDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_bottom_sheet, container, false);
        setReferences(view);

        sentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = userNameEt.getText().toString();
                String email = emailEt.getText().toString();

                callBack.callBack(userName, email);

                dismiss();
            }
        });

        return view;
    }

    private void setReferences(View view) {
        userNameEt = view.findViewById(R.id.user_name_et);
        emailEt = view.findViewById(R.id.email_et);
        sentBtn = view.findViewById(R.id.sent_btn);
    }
}

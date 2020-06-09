package com.example.assignment;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class GenericTextWatcher implements TextWatcher {
    private EditText mEditPrevious;
    private EditText mEditNext;

    public GenericTextWatcher(EditText mEditNext, EditText mEditPrevious) {
        this.mEditPrevious = mEditPrevious;
        this.mEditNext = mEditNext;
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        if (text.length() == 1)
            mEditNext.requestFocus();
        else if (text.length() == 0)
            mEditPrevious.requestFocus();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
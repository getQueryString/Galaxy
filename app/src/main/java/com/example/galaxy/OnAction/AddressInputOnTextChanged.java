package com.example.galaxy.OnAction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.galaxy.Main.Main;

public class AddressInputOnTextChanged {

    Main m = Main.getInstance();

    public AddressInputOnTextChanged() {
        m.addr_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (m.addr_input.length() > 0) {
                    m.copyOwnIP.setVisibility(View.INVISIBLE);
                } else if (m.copyOwnIP.getVisibility() == View.INVISIBLE) {
                    m.copyOwnIP.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}

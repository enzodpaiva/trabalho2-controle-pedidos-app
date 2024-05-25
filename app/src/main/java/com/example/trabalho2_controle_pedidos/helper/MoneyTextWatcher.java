package com.example.trabalho2_controle_pedidos.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyTextWatcher implements TextWatcher {
    private final EditText editText;
    private final Locale locale;

    public MoneyTextWatcher(EditText editText, Locale locale) {
        this.editText = editText;
        this.locale = locale;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // No action needed
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // No action needed
    }

    @Override
    public void afterTextChanged(Editable s) {
        editText.removeTextChangedListener(this);

        try {
            String originalString = s.toString();

            if (originalString.isEmpty()) {
                return;
            }

            String cleanString = originalString.replaceAll("[R$,.]", "").trim();

            double parsed = Double.parseDouble(cleanString) / 100;
            String formatted = NumberFormat.getCurrencyInstance(locale).format(parsed);

            editText.setText(formatted);
            editText.setSelection(formatted.length());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        editText.addTextChangedListener(this);
    }
}

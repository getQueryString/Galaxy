package com.example.galaxy.OnAction;

import android.graphics.Color;
import android.text.Html;

import com.example.galaxy.Main.Main;

public class ResetOnClick {

    Main m = Main.getInstance();

    public ResetOnClick() {
        m.reset.setOnClickListener(v -> {
            if (m.ipaddr.length() > 12 || m.addr_input.length() > 0 || m.error_ipinfo.length() > 0 || m.copyOwnIP.isChecked()) {
                m.latency.setText(Html.fromHtml("<b>Latency:</b>"));
                m.copyOwnIP.setChecked(false);
                m.ipaddr.setText(Html.fromHtml("<b>IP address:</b>"));
                m.hostname.setText(Html.fromHtml("<b>Hostname:</b>"));
                m.orga.setText(Html.fromHtml("<b>Organisation:</b>"));
                m.location.setText(Html.fromHtml("<b>Location:</b>"));
                m.longlat.setText(Html.fromHtml("<b>Long-, Latitude:</b>"));
                m.error_ipinfo.setText("");
                m.addr_input.setText("");
                m.noFunctionClicked = 0;
                m.send.setBackgroundColor(Color.rgb(128, 128, 128));
                m.fileWriter.setBackgroundColor(Color.rgb(128, 128, 128));
            }
        });
    }
}

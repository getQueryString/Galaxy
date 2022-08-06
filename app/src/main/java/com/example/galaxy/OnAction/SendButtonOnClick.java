package com.example.galaxy.OnAction;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.galaxy.IP.GetData;
import com.example.galaxy.Main.Main;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class SendButtonOnClick {

    Main m = Main.getInstance();

    @SuppressLint("SetTextI18n")
    public SendButtonOnClick() {
        m.send.setOnClickListener(v -> {
            if (m.sendDelay) return;
            m.sendDelay = true;

            m.send.setBackgroundColor(Color.rgb(128, 128, 128));

            Handler errorHandlerRemove = new Handler(Looper.getMainLooper());
            Runnable runnable_error_remove = () -> m.error_ipinfo.setText("");
            errorHandlerRemove.post(runnable_error_remove);

            new Thread(() -> {
                try {
                    m.ipaddress = m.addr_input.getText().toString();

                    m.pattern_ipv4 = Pattern.compile(m.IP_ADDRESS_REGEX_IPV4);
                    m.pattern_ipv6 = Pattern.compile(m.IP_ADDRESS_REGEX_IPV6);

                    if (validate_ipv4(m.ipaddress) || validate_ipv6(m.ipaddress)) {
                        new GetData();

                    } else if (m.ipaddress.equals("")) {
                        new GetData();
                        if (m.copyOwnIP.isChecked()) {
                            Runnable copyToClip = this::copyToClipboard;
                            m.ipInfoHandler.post(copyToClip);
                        }
                    } else {
                        m.send.setBackgroundColor(Color.rgb(255, 0, 0));
                        Handler handlerToast = new Handler(Looper.getMainLooper());
                        Runnable ipv46 = () -> Toast.makeText(m.getApplicationContext(), "IPv4/6 address not found!", Toast.LENGTH_SHORT).show();
                        Runnable ipv46_error = () -> m.error_ipinfo.setText("Error: Does not contain IPv4/6 address");
                        handlerToast.post(ipv46);
                        handlerToast.post(ipv46_error);

                        m.noFunctionClicked = 0;
                    }
                } catch (Exception ex) {

                    m.send.setBackgroundColor(Color.rgb(255, 0, 0));
                    System.out.println("ERROR: " + ex);
                    Handler errorHandler = new Handler(Looper.getMainLooper());
                    Runnable runnable_error = () -> m.error_ipinfo.setText("Error: " + ex);
                    errorHandler.post(runnable_error);
                }
            }).start();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    m.sendDelay = false;
                }
            }, m.SEND_DELAY);
        });
    }

    private boolean validate_ipv4(final String ipAddress) {
        m.matcher_ipv4 = m.pattern_ipv4.matcher(ipAddress);
        return m.matcher_ipv4.matches();
    }

    private boolean validate_ipv6(final String ipAddress) {
        m.matcher_ipv6 = m.pattern_ipv6.matcher(ipAddress);
        return m.matcher_ipv6.matches();
    }

    private void copyToClipboard() {
        ClipboardManager cb = (ClipboardManager) m.getApplicationContext().getSystemService(m.getApplicationContext().CLIPBOARD_SERVICE);
        ClipData cd = ClipData.newPlainText("", m.response.getIp());
        cb.setPrimaryClip(cd);
        Toast.makeText(m.getApplicationContext(), "Own IPv4/6 copied!", Toast.LENGTH_SHORT).show();
    }
}

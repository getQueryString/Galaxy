package com.example.galaxy.OnAction;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.example.galaxy.Main.Main;

public class NoFunctionOnClick {

    Main m = Main.getInstance();

    @SuppressLint("SetTextI18n")
    public NoFunctionOnClick() {
        m.fileWriter.setOnClickListener(v -> {

         /*try {
                FileWriter writer;
                File dat = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/text.txt");
                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test");
                if (!dir.exists()) {
                    try {
                        dir.mkdir();
                    } catch (Exception e) {
                        m.error_ipinfo.setText(e.getMessage());
                    }
                }
                writer = new FileWriter(dat, true);
                writer.write("test123");
                writer.flush();
                writer.close();
                System.out.println("Path" + Environment.getExternalStorageDirectory().getAbsolutePath());
            } catch (Exception e) {
                System.out.println(e);
                //Toast.makeText(m.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                m.error_ipinfo.setText(e.getMessage());
            }*/

            m.noFunctionClicked++;
            if (m.noFunctionClicked < 2) m.error_ipinfo.setText("No function");
            else m.error_ipinfo.setText("No function x" + m.noFunctionClicked);
            if (m.noFunctionClicked == 5)
                Toast.makeText(m.getApplicationContext(), "Are you bored? This button has no function.", Toast.LENGTH_LONG).show();
            if (m.noFunctionClicked == 15)
                Toast.makeText(m.getApplicationContext(), "I warn you...", Toast.LENGTH_SHORT).show();
            if (m.noFunctionClicked == 25)
                System.exit(0);
        });
    }
}

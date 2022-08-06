// Copyright© by Fin

package com.example.galaxy.Main;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.hardware.fingerprint.FingerprintManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.galaxy.Fingerprint.FingerPrint;
import com.example.galaxy.Fingerprint.FingerprintHandler;
import com.example.galaxy.OnAction.AddressInputOnTextChanged;
import com.example.galaxy.OnAction.GitHubImageOnClick;
import com.example.galaxy.OnAction.NoFunctionOnClick;
import com.example.galaxy.OnAction.ResetOnClick;
import com.example.galaxy.OnAction.SendButtonOnClick;
import com.example.galaxy.OnAction.StartPauseMusicOnClick;
import com.example.galaxy.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.ipinfo.api.IPinfo;
import io.ipinfo.api.model.IPResponse;

public class Main extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    private static Main instance;

    public Main() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    public boolean sendDelay = false;

    public int SEND_DELAY = 3000;
    public int noFunctionClicked = 0;

    // Check availability of IP address
    public Pattern pattern_ipv4;
    public Pattern pattern_ipv6;

    public Matcher matcher_ipv4;
    public Matcher matcher_ipv6;

    public String ipaddress;
    public final String IP_ADDRESS_REGEX_IPV4 = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    public final String IP_ADDRESS_REGEX_IPV6 = "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$";

    public Button send;
    public Button reset;
    public Button startPauseMusic;
    public Button fileWriter;

    public CheckBox copyOwnIP;

    // IP-Address output
    public TextView latency;
    public TextView ipaddr;
    public TextView hostname;
    public TextView orga;
    public TextView location;
    public TextView longlat;
    public TextView error_ipinfo;

    public EditText addr_input;

    public ImageView gitHubImage;

    // IP response
    public IPinfo ipInfo = new IPinfo.Builder().setToken("").build();
    public IPResponse response;

    // Runnable
    public Handler ipInfoHandler = new Handler(Looper.getMainLooper());

    // Fingerprint
    public FingerprintHandler fingerprintHandler;
    public FingerprintManager fingerprintManager;

    // Background music
    public MediaPlayer mediaPlayerHackerwar;

    @SuppressLint({"WrongConstant", "SetTextI18n"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(Configuration.ORIENTATION_PORTRAIT);

        // Fingerprint
        fingerprintHandler = new FingerprintHandler(this);
        fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        // Background audio
        mediaPlayerHackerwar = MediaPlayer.create(this, R.raw.hackerwarcompressed);
        mediaPlayerHackerwar.setLooping(true);
        mediaPlayerHackerwar.setVolume(100, 100);

        // Button
        send = findViewById(R.id.btn_send);
        reset = findViewById(R.id.btn_reset);
        startPauseMusic = findViewById(R.id.btn_startPauseMusic);
        fileWriter = findViewById(R.id.btn_fileWriter);

        // CheckBox
        copyOwnIP = findViewById(R.id.copyIP);

        // IP-Address input
        addr_input = findViewById(R.id.addr_input);

        // TextView: IP-Address output
        latency = findViewById(R.id.used_time);
        latency.setText(Html.fromHtml("<b>Latency: </b"));
        copyOwnIP.setText(Html.fromHtml("<b>Copy</b>"));
        ipaddr = findViewById(R.id.ipaddr_label);
        ipaddr.setText(Html.fromHtml("<b>IP address: </b>"));
        hostname = findViewById(R.id.hostname_label);
        hostname.setText(Html.fromHtml("<b>Hostname: </b>"));
        orga = findViewById(R.id.orga_label);
        orga.setText(Html.fromHtml("<b>Organisation: </b>"));
        location = findViewById(R.id.location_label);
        location.setText(Html.fromHtml("<b>Location: </b>"));
        longlat = findViewById(R.id.longlat_label);
        longlat.setText(Html.fromHtml("<b>Long-, Latitude: </b>"));
        error_ipinfo = findViewById(R.id.error_ipinfo_label);

        // Image
        gitHubImage = findViewById(R.id.img_github);

        // Input IP
        new AddressInputOnTextChanged();

        // Click on "send"
        new SendButtonOnClick();

        // Reset
        new ResetOnClick();

        // Start/pause music
        new StartPauseMusicOnClick();

        // NoFunction (FileWriter)
        new NoFunctionOnClick();

        // GitHubImage
        new GitHubImageOnClick();

        // Fingerprint
        new FingerPrint();
    }
}
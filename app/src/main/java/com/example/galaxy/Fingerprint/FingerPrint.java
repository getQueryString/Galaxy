package com.example.galaxy.Fingerprint;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import com.example.galaxy.Main.Main;

public class FingerPrint {

    Main m = Main.getInstance();

    public FingerPrint() {
        if (!m.fingerprintManager.isHardwareDetected()
                || ContextCompat.checkSelfPermission(m, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED
                || !m.fingerprintManager.hasEnrolledFingerprints()) {
            System.exit(0);
        } else {
            m.fingerprintHandler.startAuth(m.fingerprintManager, null);
        }
    }
}

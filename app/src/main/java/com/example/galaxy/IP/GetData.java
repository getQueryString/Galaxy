package com.example.galaxy.IP;

import android.annotation.SuppressLint;
import android.text.Html;
import android.text.method.LinkMovementMethod;

import com.example.galaxy.Main.Main;

import io.ipinfo.api.errors.RateLimitedException;

public class GetData {

    Main m = Main.getInstance();

    public GetData() throws RateLimitedException {

        @SuppressLint("SetTextI18n")
        long TIME = System.nanoTime();
        m.response = m.ipInfo.lookupIP(m.ipaddress);
        long USED_TIME = (System.nanoTime() - TIME) / 1000000;

        Runnable runnable_latency = () -> m.latency.setText(Html.fromHtml("<b>Latency: </b> <a>" + USED_TIME + " ms</a>"));
        Runnable runnable_ipaddr = () ->
                m.ipaddr.setText(Html.fromHtml("<b>IP address: </b> <a>" + m.response.getIp() + "</a>"));
        Runnable runnable_hostname = () -> m.hostname.setText(Html.fromHtml("<b>Hostname: </b> <a>" + m.response.getHostname() + "</a>"));
        Runnable runnable_orga = () -> m.orga.setText(Html.fromHtml("<b>Organisation: </b> <a>" + m.response.getOrg() + "</a>"));
        Runnable runnable_location = () -> m.location.setText(Html.fromHtml("<b>Location: </b> <a>" + m.response.getCountryCode() + ", " + m.response.getRegion() + "; " + m.response.getPostal() + ", " + m.response.getCity()));
        Runnable runnable_longlat = () -> m.longlat.setText(Html.fromHtml("<b>Long-, Latitude: </b><a href='https://www.google.com/maps/search/"
                + m.response.getLocation() + "'> " + m.response.getLocation() + "</a>"));
        Runnable runnable_location_maps = () -> m.longlat.setMovementMethod(LinkMovementMethod.getInstance());

        m.ipInfoHandler.post(runnable_latency);
        m.ipInfoHandler.post(runnable_ipaddr);
        m.ipInfoHandler.post(runnable_hostname);
        m.ipInfoHandler.post(runnable_orga);
        m.ipInfoHandler.post(runnable_location);
        m.ipInfoHandler.post(runnable_longlat);
        m.ipInfoHandler.post(runnable_location_maps);

        m.noFunctionClicked = 0;
    }
}

package com.example.galaxy.OnAction;

import android.content.Intent;
import android.net.Uri;

import com.example.galaxy.Main.Main;

public class GitHubImageOnClick {

    Main m = Main.getInstance();

    public GitHubImageOnClick() {
        m.gitHubImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://github.com/getQueryString/Galaxy"));
            m.startActivity(intent);
        });
    }
}

package com.example.implicit_intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_openwebsite;
    private EditText et_openlocation;
    private EditText et_shareText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        et_openwebsite = findViewById(R.id.et_openwebsite);
        et_openlocation = findViewById(R.id.et_openlocation);
        et_shareText = findViewById(R.id.et_sharetext);
    }

    public void openWebsite(View view) {
        String url = et_openwebsite.getText().toString();

        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Can't Handle this", Toast.LENGTH_LONG).show();
        }

    }

    public void openLocation(View view) {
        String loc = et_openlocation.getText().toString();
        String errorMsg = "No Activity available to Handle this";
        Uri locationUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, locationUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {

            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();

        }


    }

    public void shareText(View view) {
        String shared_text = et_shareText.getText().toString();

        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(shared_text)
                .startChooser();
    }
}

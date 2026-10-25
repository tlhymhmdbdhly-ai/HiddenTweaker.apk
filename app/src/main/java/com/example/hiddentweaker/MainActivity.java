package com.example.hiddentweaker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import rikka.shizuku.Shizuku;

public class MainActivity extends AppCompatActivity {
    private TextView tvStatus;
    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvStatus = findViewById(R.id.tvStatus);
        Button btnRequest = findViewById(R.id.btnRequestPermission);

        btnRequest.setOnClickListener(v -> {
            if (Shizuku.isPreV11() || Shizuku.checkSelfPermission() != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                Shizuku.requestPermission(REQUEST_CODE);
            } else {
                Toast.makeText(this, "الصلاحية ممنوحة بالفعل", Toast.LENGTH_SHORT).show();
                updateStatus();
            }
        });
        updateStatus();
    }

    private void updateStatus() {
        if (Shizuku.isPreV11()) {
            tvStatus.setText("الإصدار قديم من Shizuku");
        } else if (Shizuku.checkSelfPermission() == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            tvStatus.setText("حالة Shizuku: متصل وجاهز ✔");
        } else {
            tvStatus.setText("حالة Shizuku: غير مصرح");
        }
    }
}

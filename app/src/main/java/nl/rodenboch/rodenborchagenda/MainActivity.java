package nl.rodenboch.rodenborchagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button agendaButton = findViewById(R.id.button_agenda);
        agendaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AgendaActivity.class);
                startActivity(intent);
//                Toast.makeText(MainActivity.this, "op agenda geklikt", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

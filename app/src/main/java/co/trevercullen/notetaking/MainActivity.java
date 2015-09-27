package co.trevercullen.notetaking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button main_button = (Button) findViewById(R.id.main_button_id);

        main_button.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent next_activity = new Intent(MainActivity.this, ListActivity.class);
                        startActivity(next_activity);
                    }
                }
        );
    }
}

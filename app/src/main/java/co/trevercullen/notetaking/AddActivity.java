package co.trevercullen.notetaking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class AddActivity extends AppCompatActivity {

    DBHandler db = new DBHandler(this);

    EditText title_edit_text;
    EditText desc_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        final Button add_button = (Button) findViewById(R.id.add_button_id);


        add_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        title_edit_text = (EditText) findViewById(R.id.add_title_id);
                        desc_edit_text = (EditText) findViewById(R.id.add_desc_id);

                        db.open();
                        long id = db.insertRecord(title_edit_text.getText().toString(), desc_edit_text.getText().toString());
                        db.close();

                        Toast.makeText(AddActivity.this,"Note Added", Toast.LENGTH_LONG).show();

                        Intent next_activity = new Intent(AddActivity.this, ListActivity.class);
                        startActivity(next_activity);
                    }
                }
        );
    }
}

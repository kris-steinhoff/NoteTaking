package co.trevercullen.notetaking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.database.Cursor;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        ArrayList<String> notes = new ArrayList<String>();
        notes.add("Temp");



        ListAdapter notes_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notes);
        ListView notes_list_view = (ListView) findViewById(R.id.notes_list_view_id);
        notes_list_view.setAdapter(notes_adapter);

        notes_list_view.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String note = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(ListActivity.this, note, Toast.LENGTH_SHORT).show();
                    }
                }
        );


        final Button list_button = (Button) findViewById(R.id.list_button_id);

        list_button.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent next_activity = new Intent(ListActivity.this, AddActivity.class);
                        startActivity(next_activity);
                    }
                }
        );
    }
}

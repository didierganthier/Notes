package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";
    
    private Button btnSave, btnDelete;
    private EditText editable_item;
    
    DatabaseHelper databaseHelper;
    
    private String selectedName;
    private int selectedId;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        editable_item = findViewById(R.id.editable_item);
        databaseHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        
        selectedName = receivedIntent.getStringExtra("name");
        
        selectedId = receivedIntent.getIntExtra("id",-1);
        
        editable_item.setText(selectedName);
        
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    databaseHelper.updateName(item,selectedId,selectedName);
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else{
                    Toast.makeText(EditDataActivity.this, "You must enter a name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteName(selectedId,selectedName);
                editable_item.setText("");
                Toast.makeText(EditDataActivity.this, "Removed from database", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}

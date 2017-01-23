package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    int itemPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        itemPos = getIntent().getIntExtra("itemPos",0);
        String itemName = getIntent().getStringExtra("itemName");
        EditText etEditName = (EditText) findViewById(R.id.etEditName);
        etEditName.setText(itemName);
        etEditName.setSelection(etEditName.getText().length());
    }

    public void onSubmit(View v) {
        EditText etEditName = (EditText) findViewById(R.id.etEditName);
        Intent i = new Intent();
        i.putExtra("itemName", etEditName.getText().toString());
        i.putExtra("itemPos", itemPos);
        setResult(RESULT_OK, i);
        finish();
    }
}

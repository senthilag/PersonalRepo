package com.codepath.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codepath.login.adapters.ContactsAdapter;
import com.codepath.login.models.Contact;

import java.util.ArrayList;

/**
 * Created by senthilg on 1/25/17.
 */

public class UserListActivity extends AppCompatActivity {

    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        contacts = Contact.createContactsList(20);

        ContactsAdapter adapter = new ContactsAdapter(this, contacts);

        rvContacts.setAdapter(adapter);

        rvContacts.setLayoutManager(new LinearLayoutManager(this));

    }
}

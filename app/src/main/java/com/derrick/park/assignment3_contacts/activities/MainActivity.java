package com.derrick.park.assignment3_contacts.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private OverViewModel viewModel;
    private ContactAdapter adapter;

    private static final int REQUEST_CODE = 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addButton:
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            adapter.addContact(data.getStringExtra("name"), data.getStringExtra("phone"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(OverViewModel.class);
        adapter = new ContactAdapter();

        viewModel.getContactList().observe(this , new Observer<ArrayList<Contact>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Contact> contactList) {
                if(contactList != null) {
                    adapter.setContactList(contactList);
                }
            }
        });

        RecyclerView rv = findViewById(R.id.contact_list);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }
}

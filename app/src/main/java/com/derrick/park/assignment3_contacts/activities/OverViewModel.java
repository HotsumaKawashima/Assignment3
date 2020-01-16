package com.derrick.park.assignment3_contacts.activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.derrick.park.assignment3_contacts.models.Contact;
import com.derrick.park.assignment3_contacts.models.ContactList;
import com.derrick.park.assignment3_contacts.network.ContactClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Contact>> mContactList = new MutableLiveData<>();

    public LiveData<ArrayList<Contact>> getContactList() {
        return mContactList;
    }

    public OverViewModel() {
        fetchContactList();
    }

    private void fetchContactList() {
        Call<ContactList> call = ContactClient.getContacts(10);

        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if (response.isSuccessful()) {
                    ArrayList<Contact> result = response.body().getContactList();
                    mContactList.postValue(result);
                }
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
                System.out.println(t);
                // Error Handling
            }
        });
    }


}

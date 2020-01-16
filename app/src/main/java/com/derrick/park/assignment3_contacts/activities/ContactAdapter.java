package com.derrick.park.assignment3_contacts.activities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.derrick.park.assignment3_contacts.R;
import com.derrick.park.assignment3_contacts.models.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ContactAdapter extends RecyclerView.Adapter<TextItemViewHolder> {

    private ArrayList<Contact> contactList = new ArrayList<>();

    private final int VIEWTYPE_INDEX = 0;
    private final int VIEWTYPE_CONTACT = 1;

    private ArrayList<Integer> viewTypeList = new ArrayList<>();
    private ArrayList<Integer> contactIndexList = new ArrayList<>();

    public void addContact(String name, String phone) {
         contactList.add(new Contact(name, phone));
         setContactList(contactList);
    }

    public void setContactList(ArrayList<Contact> contactList) {

        Collections.sort(contactList, new Comparator<Contact>() {
            @Override
            public int compare(Contact t1, Contact t2) {
                if(t1.getName().getFirst().charAt(0) > t2.getName().getFirst().charAt(0)) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        viewTypeList.clear();
        contactIndexList.clear();

        for(int i = 0; i < contactList.size(); ++i) {
            if(viewTypeList.size() == 0 ||
                    contactList.get(i).getName().getFirst().charAt(0) != contactList.get(i - 1).getName().getFirst().charAt(0)) {
                viewTypeList.add(VIEWTYPE_INDEX);
                contactIndexList.add(-1);
            }
            viewTypeList.add(VIEWTYPE_CONTACT);
            contactIndexList.add(i);
        }

        this.contactList = contactList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TextItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TextView view = (TextView) layoutInflater.inflate(R.layout.text_item_view, parent, false);
        return new TextItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextItemViewHolder holder, int position) {
        int i = contactIndexList.get(position);

        if(i < 0) {
            int contactIndex = contactIndexList.get(position + 1);
            Contact contact = contactList.get(contactIndex);
            String text = String.valueOf(contact.getName().getFirst().charAt(0));

            holder.textView.setText(text);
        } else {
            int contactIndex = contactIndexList.get(position);
            Contact contact = contactList.get(contactIndex);
            holder.textView.setText(contact.toString());
        }
    }

    @Override
    public int getItemCount() {
        return viewTypeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypeList.get(position);
    }

}

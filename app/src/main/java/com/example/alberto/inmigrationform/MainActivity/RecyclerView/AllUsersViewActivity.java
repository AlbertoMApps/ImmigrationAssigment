package com.example.alberto.inmigrationform.MainActivity.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.example.alberto.inmigrationform.R;
import com.example.alberto.inmigrationform.MainActivity.Database.DatabaseHandler;
import com.example.alberto.inmigrationform.MainActivity.Parcelable.User;

import java.util.ArrayList;

/**
 * Created by Alberto on /12/2015.
 */
public class AllUsersViewActivity extends Activity{

    private ListView User_listview;
    private RecyclerView mRecyclerView;
    //private Contact_Adapter cAdapter;//cAdapter for the inner class in the listView previous exercise
    private User_Adapter mAdapter;
   // private ArrayList<User> user_data = new ArrayList<User>();
    private DatabaseHandler db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContent for the new xml tvAllUsers
        setContentView(R.layout.tvallusers);
//recycler view list
        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        db = new DatabaseHandler(this);
        ArrayList<User> user_array_from_db = db.Get_Users();
        db.close();
        mAdapter = new User_Adapter(user_array_from_db, R.layout.tvallusers_item, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

 //ListView referash data.
        //Set_Referash_Data();

    }
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //Discomment for the listViewData
        // Set_Referash_Data();

    }
//listView referash data.

    /**Discomment for the listview use

    public void Set_Referash_Data() {
        //this.user_data.clear();
        db = new DatabaseHandler(this);
        ArrayList<User> user_array_from_db = db.Get_Users();
        db.close();
        //Adding the userAdapter to connect the User from the db to th view
        User_listview = (ListView) findViewById(R.id.lvAllUsers);//Discomment if you are using the listView
        cAdapter = new Contact_Adapter(AllUsersViewActivity.this, R.layout.tvallusers_item,
                user_array_from_db);
        User_listview.setAdapter(cAdapter);
        cAdapter.notifyDataSetChanged();
    }
    //Inner class to adapt the  User data to the ListView
    class Contact_Adapter extends ArrayAdapter<User> {
        Activity activity;
        int layoutResourceId;
        User user;
        ArrayList<User> data = new ArrayList<User>();

        public Contact_Adapter(Activity act, int layoutResourceId,
                               ArrayList<User> data) {
            super(act, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.activity = act;
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            UserHolder holder = null;

            if (row == null) {
                LayoutInflater inflater = LayoutInflater.from(activity);

                row = inflater.inflate(layoutResourceId, parent, false);
                holder = new UserHolder();
                holder.name = (TextView) row.findViewById(R.id.tvAllName);
                holder.surname = (TextView) row.findViewById(R.id.tvAllSurName);
                /**holder.DOB = (TextView) row.findViewById(R.id.tvAllDOB);
                holder.gender = (TextView) row.findViewById(R.id.tvAllGender);
                holder.country = (TextView) row.findViewById(R.id.tvAllCountry);
                holder.address = (TextView) row.findViewById(R.id.tvAllAddress);
                holder.email = (TextView) row.findViewById(R.id.tvAllEmail);**/
    /**Discomment for the listview use
                    row.setTag(holder);
            } else {
                holder = (UserHolder) row.getTag();
            }
            user = data.get(position);
            holder.name.setText(user.getmUName());
            holder.surname.setText(user.getmULast());/**
            holder.DOB.setText(user.getmUDOB());
            holder.gender.setText(user.getmUGender());
            holder.country.setText(user.getmUCountry());
            holder.address.setText(user.getmtxtArea());
            holder.email.setText(user.getmUtxtEmail());**/
    /**Discomment for the listview use
     * return row;

        }

        class UserHolder {
            TextView name;
            TextView surname;/**
            TextView DOB;
            TextView gender;
            TextView country;
            TextView address;
            TextView email;**/
    /**Discomment for the listview use
     *  }

    }**/



}

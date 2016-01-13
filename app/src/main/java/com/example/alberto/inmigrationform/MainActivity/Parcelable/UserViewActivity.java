package com.example.alberto.inmigrationform.MainActivity.Parcelable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.alberto.inmigrationform.R;
import com.example.alberto.inmigrationform.MainActivity.Database.DatabaseHandler;

/**
 * Created by Alberto on 10/12/2015.
 */
public class UserViewActivity extends Activity{
    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContent for the new xml tvuser (Text View User)
        setContentView(R.layout.tvuser);
        //initiating the db
        db = new DatabaseHandler(this);
        // Fetching data from a parcelable object passed from MainActivity
        User uVA = getIntent().getParcelableExtra("user");
        //Getting the textViewData
        TextView tvUName = (TextView)findViewById(R.id.tvName);
        TextView tvUSurname = (TextView)findViewById(R.id.tvSurname);
        TextView tvUDOB = (TextView)findViewById(R.id.tvDOB);
        TextView tvUGender = (TextView)findViewById(R.id.tvGender);
        TextView tvUCountry = (TextView)findViewById(R.id.tvCountry);
        TextView tvUtxtArea = (TextView)findViewById(R.id.tvAddress);
        TextView tvUtxtEmail = (TextView)findViewById(R.id.tvEmail);
        //if the userViewactivity that we retrieve is not null we should show or set the text information from the intent user into the textView
        if(uVA!=null){
            tvUName.setText( uVA.getmUName());
            tvUSurname.setText(uVA.getmULast());
            tvUDOB.setText(uVA.getmUDOB());
            tvUGender.setText(uVA.getmUGender());
            tvUCountry.setText(uVA.getmUCountry());
            tvUtxtArea.setText(uVA.getmtxtArea());
            tvUtxtEmail.setText(uVA.getmUtxtEmail());
            db.Add_User(uVA);
            db.close();
        }


    }


}

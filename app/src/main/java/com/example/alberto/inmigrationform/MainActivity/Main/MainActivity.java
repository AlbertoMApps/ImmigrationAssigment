package com.example.alberto.inmigrationform.MainActivity.Main;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alberto.inmigrationform.R;
import com.example.alberto.inmigrationform.MainActivity.RecyclerView.AllUsersViewActivity;
import com.example.alberto.inmigrationform.MainActivity.Parcelable.User;
import com.example.alberto.inmigrationform.MainActivity.Parcelable.UserViewActivity;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageView mImageView;
    private EditText name;
    private EditText surname;
    private Button DOB;
    private static String DOBSelected;
    private EditText txtAddress;
    private EditText txtEmail;
    private RadioGroup gender;
    private int genderID;
    private String genderOpt;
    private Spinner spinner;
    private String itemSpinner;
    private ArrayAdapter<String> aaCountry;
    private User user;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);
        //spinner.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(this);
        //aaCountry = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, R.array.country);
        //spinner.setAdapter(aaCountry);

        //Find the editText ids
        this.name = (EditText) findViewById(R.id.txtName);
        this.surname = (EditText) findViewById(R.id.txtLast);
        this.DOB = (Button) findViewById(R.id.btnDOB);
        //gender
        this.gender = (RadioGroup) findViewById(R.id.rdGr);
        //genderID = gender.getCheckedRadioButtonId();
        this.txtAddress = (EditText) findViewById(R.id.txtArea);
        this.txtEmail = (EditText) findViewById(R.id.txtEmail);
        this.mImageView = (ImageView) findViewById(R.id.imageView);

        //return getSelected from the checkbox option male or female
        genderOpt = getSelected();
        //Toolbar
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

         /**FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show()
        }
        });**/

    }


    //submit DOB, date of birth
    public void submitDOB(View v) {
        DOBSelected = "";
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show( getFragmentManager(), "datePicker");
    }

    //submit button check if the fields are empty or the email is invalid
    //Add colors in colors.xml and call it in case it has an error
    public void submit(View v) {

        if (name.getText().toString().isEmpty()) {
            //Show_Toast("Error on First Name field");
            name.setError("Fill Name");
        } else if (surname.getText().toString().isEmpty()) {
            //Show_Toast("Error on Last Name field");
            surname.setError("Fill Last Name");
        } else if (txtAddress.getText().toString().isEmpty()) {
            //Show_Toast("Error on  Adress field");
            txtAddress.setError("Fill Adress");
        } else if (txtEmail.getText().toString().isEmpty()) {
            //Show_Toast("Error on Email field");
            txtEmail.setError("Fill email");
        } else if (!this.emailValidator(txtEmail.getText().toString().trim())) {
            //Show_Toast("Error on email field");
            txtEmail.setError("Error email format");
        } else {

            //return getSelected from the checkbox option male or female
            genderOpt = getSelected();

            //Show all the data and create and intent with the user data to wrap it
            User uAD = new User(this.name.getText().toString().trim(), this.surname.getText().toString().trim(), this.DOBSelected, this.genderOpt.toString().trim(), itemSpinner, this.txtAddress.getText().toString().trim(), this.txtEmail.getText().toString().trim());
            // Creating an intent to open the activity UserViewActivity
            Intent intent = new Intent(getBaseContext(), UserViewActivity.class);

            // Passing data as a parecelable object to UserViewActivity
            intent.putExtra("user", uAD);

            // Opening the activity
            startActivity(intent);
        }
    }

    //reset all textFields when its pressed
    public void cancel(View v) {
        name.setText("");
        surname.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
    }

    //btn view all when it is clicked, get all the information from all the users in the view
    public void viewAll(View v) {
        Intent intent = new Intent(getBaseContext(), AllUsersViewActivity.class);
        startActivity(intent);
    }

    //emailValidator function, returns true or false depending on email
    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //method to show text Toast
    public void Show_Toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.optOK) {
            Show_Toast("optOk hit");
        }else if (id==R.id.optCancel){
            Show_Toast("optCancel hit");
        }else if (id==R.id.optviewAll){
            Show_Toast("optViewAll hit");
        }

        //noinspection SimplifiableIfStatement
        else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //When you click on the spinner it will show you the spinner selected with the position in the logcat
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("MAIN", "spinner " + position);
        itemSpinner = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //get selected one of the radioButtons option
    public String getSelected (){
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdbOne:
                        genderOpt ="Male";
                        break;

                    case R.id.rdbTwo:
                        genderOpt ="Female";
                        break;

                }
            }
        });
        return genderOpt;
    }
    //take a picture option of the image
    public void dispatchTakePictureIntent(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mImageView = new ImageView(getBaseContext());
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }
    //inner class for the datepicker
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        public EditText editText;
        DatePicker dpResult;

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

             DOBSelected=(String.valueOf(day) + "/"
                    + String.valueOf(month + 1) + "/" + String.valueOf(year));
        }
    }
}
package com.example.alberto.inmigrationform.MainActivity.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alberto on 10/12/2015.
 */
public  class User implements Parcelable{
    private int mUID;
    private String mUName;
    private String mULast;
    private String mUDOB;
    private String mUGender;
    private String mUCountry;
    private String mUtxtAdress;
    private String mUtxtEmail;
//Constructors
    public User() {} //Empty constructor
    //Constructor for the db
    public User(int mUID, String mUName, String mULast, String mUDOB, String mUGender, String mUCountry, String mUtxtArea, String mUtxtEmail) {
        this.mUID = mUID;
        this.mUName = mUName;
        this.mULast = mULast;
        this.mUDOB = mUDOB;
        this.mUGender = mUGender;
        this.mUCountry = mUCountry;
        this.mUtxtAdress = mUtxtArea;
        this.mUtxtAdress = mUtxtArea;
        this.mUtxtEmail = mUtxtEmail;
    }
    //Constructor for the class
    public User(String mUName, String mULast, String mUDOB, String mUGender, String mUCountry, String mUtxtArea, String mUtxtEmail) {
        this.mUName = mUName;
        this.mULast = mULast;
        this.mUDOB = mUDOB;
        this.mUGender = mUGender;
        this.mUCountry = mUCountry;
        this.mUtxtAdress = mUtxtArea;
        this.mUtxtAdress = mUtxtArea;
        this.mUtxtEmail = mUtxtEmail;
    }
//readable parcel
    protected User(Parcel in) {
        mUName = in.readString();
        mULast = in.readString();
        mUDOB = in.readString();
        mUGender = in.readString();
        mUCountry = in.readString();
        mUtxtAdress = in.readString();
        mUtxtAdress = in.readString();
        mUtxtEmail = in.readString();
    }
//Creator from parcelable methods
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    //writeToParcel override method
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUName);
        dest.writeString(mULast);
        dest.writeString(mUDOB);
        dest.writeString(mUGender);
        dest.writeString(mUCountry);
        dest.writeString(mUtxtAdress);
        dest.writeString(mUtxtEmail);
    }
//Getters and setters
    public void setmUID(int ID) {
        this.mUID = ID;
    }
    public void setmUName(String mUName) {

        this.mUName = mUName;
    }

    public void setmULast(String mULast) {

        this.mULast = mULast;
    }
    public void setmUDOB(String mUDOB) {

        this.mUDOB = mUDOB;
    }
    public void setmUGender(String mUGender) {

        this.mUGender = mUGender;
    }
    public void setmUCountry(String mUCountry) {

        this.mUCountry = mUCountry;
    }

    public void setmUtxtAdress(String txtArea) {

        this.mUtxtAdress = txtArea;
    }
    public void setmUtxtEmail(String txtEmail) {

        this.mUtxtEmail = txtEmail;
    }

    public int getmUID(){
        return this.mUID;
    }

    public String getmULast() {

        return mULast;
    }
    public String getmUName() {

        return this.mUName;
    }
    public String getmUDOB() {

        return this.mUDOB;
    }
    public String getmUGender() {

        return this.mUGender;
    }
    public String getmUCountry(){
        return this.mUCountry;
    }
    public String getmtxtArea() {

        return this.mUtxtAdress;
    }
    public String getmUtxtEmail() {

        return this.mUtxtEmail;
    }

    @Override
    public int describeContents() {

        return 0;
    }

}

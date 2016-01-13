package com.example.alberto.inmigrationform.MainActivity.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alberto.inmigrationform.R;
import com.example.alberto.inmigrationform.MainActivity.Parcelable.User;

import java.util.List;

/**
 * Created by Alberto on 14/12/2015.
 */
public class User_Adapter extends RecyclerView.Adapter<User_Adapter.ViewHolder>{

    private List<User> allUsersData;
    private int rowLayout;
    private Context mContext;

    public User_Adapter (List<User> listUsersData, int rowLayout, Context mContext){
        this.allUsersData=listUsersData;
        this.rowLayout=rowLayout;
        this.mContext=mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        final User mAllUsers = allUsersData.get(i);
        holder.userName.setText(mAllUsers.getmUName());
        holder.userSurname.setText(mAllUsers.getmULast());
    }

    @Override
    public int getItemCount() {
        return this.allUsersData == null ? 0 : this.allUsersData.size();
    }
    //User adapter to connect the the recycler layout with the data from the database

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView userSurname;

        public ViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.tvAllName);
            userSurname = (TextView) itemView.findViewById(R.id.tvAllSurName);

        }
    }

}

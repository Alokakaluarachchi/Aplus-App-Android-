package com.example.aplusapp.adepter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplusapp.R;
import com.example.aplusapp.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private List<User> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, userRole;

        public MyViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.txtName);
            userRole = (TextView) view.findViewById(R.id.txtRole);
        }

    }

    public UserAdapter(List<User> _userList) {
        userList = _userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userName.setText(user.getUserName());
        holder.userRole.setText(user.getRole());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}

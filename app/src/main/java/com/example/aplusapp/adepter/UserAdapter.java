package com.example.aplusapp.adepter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplusapp.R;
import com.example.aplusapp.model.User;

import java.lang.ref.WeakReference;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private final ClickListener listener;
    private List<User> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView userName, userRole;
        public Button btnEdit, btnDelete;

        private WeakReference<ClickListener> listenerRef;

        public MyViewHolder(View view) {
            super(view);

            listenerRef = new WeakReference<>(listener);

            userName = (TextView) view.findViewById(R.id.txtName);
            userRole = (TextView) view.findViewById(R.id.txtRole);

            btnEdit = view.findViewById(R.id.btnUserEdit);
            btnDelete = view.findViewById(R.id.btnUserDelete);

            view.setOnClickListener(this);
            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == btnEdit.getId()) {
                Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            listenerRef.get().onPositionClicked(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public UserAdapter(List<User> _userList, ClickListener listener) {
        this.listener = listener;
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

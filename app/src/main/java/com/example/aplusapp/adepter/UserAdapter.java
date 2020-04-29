package com.example.aplusapp.adepter;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplusapp.R;
import com.example.aplusapp.activity.CircularProgressBarDialog;
import com.example.aplusapp.activity.MainActivity;
import com.example.aplusapp.activity.UserEditPopupActivity;
import com.example.aplusapp.activity.UserListActivity;
import com.example.aplusapp.db.repos.RoleRepository;
import com.example.aplusapp.db.repos.UserRepository;
import com.example.aplusapp.model.User;
import com.example.aplusapp.model.responce.UserListResult;
import com.example.aplusapp.network.APIClient;
import com.example.aplusapp.network.UserApiService;
import com.example.aplusapp.utils.SharedConst;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private final ClickListener listener;
    private List<User> userList;
    private Application application;
    private FragmentManager fragmentManager;
    private String jwtToken;

    private Retrofit retrofit;
    private UserApiService apiService;

    private CircularProgressBarDialog circularProgressBarDialog;
    UserListActivity userListActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView userName, userRole;
        public Button btnEdit, btnDelete;

        private WeakReference<ClickListener> listenerRef;
        private CallBackListener callBackListener;

        public MyViewHolder(View view) {
            super(view);

            listenerRef = new WeakReference<>(listener);

            retrofit = APIClient.getClient(); //initialize Retrofit Client
            apiService = retrofit.create(UserApiService.class); //Register the Api Service

            userName = (TextView) view.findViewById(R.id.txtName);
            userRole = (TextView) view.findViewById(R.id.txtRole);

            btnEdit = view.findViewById(R.id.btnUserEdit);
            btnDelete = view.findViewById(R.id.btnUserDelete);

            circularProgressBarDialog = new CircularProgressBarDialog();
            //callBackListener = (CallBackListener) fragmentManager.findFragmentById(R.id.fragmentContainer);

            view.setOnClickListener(this);
            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == btnEdit.getId()) {
                //Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(userName.getText().toString() ), Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();

                Bundle args = new Bundle();
                args.putString(SharedConst.SETTINGS_ID, userName.getTag().toString());

                UserEditPopupActivity userEditPopupActivity = new UserEditPopupActivity();
                //set the arguments to pass
                userEditPopupActivity.setArguments(args);
                userEditPopupActivity.show(fragmentManager,null);


                //Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }else if(v.getId() == btnDelete.getId()){
                new MaterialAlertDialogBuilder(v.getContext()).setTitle("Confirm for remove")
                        .setMessage("Are you sure want to remove user("+userName.getText().toString()+") ?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                JSONObject jObj = new JSONObject();
                                try {
                                    jObj.put("userID",userName.getTag());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                //setup the api call
                                Call<JSONObject> call = apiService.removeUser("Bearer "+jwtToken, jObj);

                                //show progress bar
                                circularProgressBarDialog.show(fragmentManager, null);

                                call.enqueue(new Callback<JSONObject>() {
                                    @Override
                                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

                                        if(response.code() == 401){
                                            Toasty.warning(v.getContext(), "Session Expired !", Toast.LENGTH_SHORT, true).show();
                                            return;
                                        }

                                        UserRepository repo = new UserRepository(application);
                                        int id = Integer.parseInt(userName.getTag().toString());
                                        repo.removeUser(id);


                                        notifyDataSetChanged();
                                        circularProgressBarDialog.dismiss();
                                        userListActivity.LoadUserList(jwtToken);
                                    }

                                    @Override
                                    public void onFailure(Call<JSONObject> call, Throwable t) {
                                        Toast.makeText(v.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        circularProgressBarDialog.dismiss();
                                    }
                                });




                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }else {
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            listenerRef.get().onPositionClicked(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    public UserAdapter(List<User> _userList, Application _application, String _jwtToken, FragmentManager _fragmentManager, UserListActivity _userListActivity, ClickListener listener) {
        this.listener = listener;
        userList = _userList;
        application = _application;
        jwtToken = _jwtToken;
        fragmentManager = _fragmentManager;
        userListActivity = _userListActivity;
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
        holder.userName.setTag(user.getId());
        holder.userRole.setText(user.getRole());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}

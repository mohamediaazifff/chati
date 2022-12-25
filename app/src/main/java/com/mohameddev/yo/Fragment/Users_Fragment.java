package com.mohameddev.yo.Fragment;

import static java.util.Objects.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mohameddev.yo.R;
import com.mohameddev.yo.adapters.UserAdapter;
import com.mohameddev.yo.models.Users;

import java.util.ArrayList;
import java.util.Objects;

public class Users_Fragment extends Fragment {

    RecyclerView recyclerView;
    View v;
    DatabaseReference databaseReference;
    UserAdapter userAdapter;
    ArrayList<Users> mlist;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_user,container,false);

        recyclerView=(RecyclerView) v.findViewById(R.id.recycler_viewSearch);

        databaseReference= FirebaseDatabase.getInstance().getReference("users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mlist=new ArrayList<>();
        userAdapter=new UserAdapter(mlist,getContext());

        recyclerView.setAdapter(userAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {

                    Users users=dataSnapshot.getValue(Users.class);
                    if (!users.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {

                        mlist.add(users);
                    }
                };
                userAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        return v;



    }
}

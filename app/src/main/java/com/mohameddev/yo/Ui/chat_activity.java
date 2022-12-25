package com.mohameddev.yo.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mohameddev.yo.R;
import com.mohameddev.yo.adapters.message_adapter;
import com.mohameddev.yo.models.Users;
import com.mohameddev.yo.models.messages;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class chat_activity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.btn_send_msg)
    MaterialButton sendmsg;

    @BindView(R.id.message_text)
    TextInputEditText msg;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.user_profile_img)
    CircleImageView user_img;


    message_adapter msgadapter;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    ArrayList<messages> mlist = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();




        sendmsg.setOnClickListener(this);



        Intent bundle = getIntent();
        String userid = bundle.getStringExtra("reciver");

        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Users users = dataSnapshot.getValue(Users.class);
                assert users != null;
                username.setText(users.getUsername());
                if (!Objects.equals(users.getUser_profile(), "default")) {
                     Glide.with(getApplicationContext()).load(users.getUser_profile()).into(user_img);

                } else

                   Glide.with(getApplicationContext()).load(R.drawable.ic_launcher_background).into(user_img);


                 readMesagges(firebaseUser.getUid(), userid, users.getUser_profile());
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_send_msg) {
            Intent bundle=getIntent();
            String userid=bundle.getStringExtra("reciver");
            String messageText = msg.getText().toString();

            sendMessage( firebaseUser.getUid(),userid,messageText);

        }


    }



    private void sendMessage(String sender, final String receiver, String message){
        Intent bundle=getIntent();
        String userid=bundle.getStringExtra("reciver");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender_id", sender);
        hashMap.put("reciver_id", receiver);
        hashMap.put("msg", message);
        hashMap.put("isseen", false);

        reference.child("Chats").push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    msg.getText().clear();
                    msg.
                            setHint("enter your message");
                }

            }
        });



        // add user to chat fragment


        final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("Chatlist")
                .child(firebaseUser.getUid())
                .child(userid);

        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    chatRef.child("id").setValue(userid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final DatabaseReference chatRefReceiver = FirebaseDatabase.getInstance().getReference("Chatlist")
                .child(userid)
                .child(firebaseUser.getUid());
        chatRefReceiver.child("id").setValue(firebaseUser.getUid());

    }


    private void readMesagges(final String myid, final String userid, final String imageurl){
        mlist = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mlist.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    messages chat = snapshot.getValue(messages.class);
                    if (chat.getReciver_id().equals(myid) && chat.getSender_id().equals(userid) ||
                            chat.getReciver_id().equals(userid) && chat.getSender_id().equals(myid)){
                        mlist.add(chat);

                        msgadapter=new message_adapter(mlist,getApplicationContext(),imageurl);

                        recyclerView.setAdapter(msgadapter);
                        msgadapter.notifyDataSetChanged();

                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


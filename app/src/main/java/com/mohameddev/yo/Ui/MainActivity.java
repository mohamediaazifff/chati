package com.mohameddev.yo.Ui;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mohameddev.yo.R;
import com.mohameddev.yo.adapters.TableAdapter;
import com.mohameddev.yo.models.Users;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.user_profile_img)
    CircleImageView user_profile;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager2 viewPager2;

    FirebaseUser firebaseUser,firebaseUser2;
    DatabaseReference reference,reference2;
    FirebaseAuth firebaseAuth;


    String check = "YES";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadtables();
        display_user_data();
    }

    private void display_user_data() {

        reference= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid());


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Users users = snapshot.getValue(Users.class);
                assert users != null;
                username.setText(users.getUsername());
                        if (!Objects.equals(users.getUser_profile(), "default")) {
                            Glide.with(getApplicationContext()).load(users.getUser_profile()).into(user_profile);

                        }
                        else  Glide.with(getApplicationContext()).load(R.drawable.ic_launcher_background).into(user_profile);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void loadtables() {
        TableAdapter tableAdapter;
        FragmentManager fragmentManager=getSupportFragmentManager();
        tableAdapter=new TableAdapter(fragmentManager,getLifecycle());
        viewPager2.setAdapter(tableAdapter);


        tabLayout.addTab(tabLayout.newTab().setText("chat"));
        tabLayout.addTab(tabLayout.newTab().setText("users"));
        tabLayout.addTab(tabLayout.newTab().setText("live"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });






    }


}

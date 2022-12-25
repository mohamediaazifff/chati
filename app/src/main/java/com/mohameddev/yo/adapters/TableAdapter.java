package com.mohameddev.yo.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mohameddev.yo.Fragment.Live_Fragement;
import com.mohameddev.yo.Fragment.Users_Fragment;
import com.mohameddev.yo.Fragment.message_Fragment;

public class TableAdapter  extends FragmentStateAdapter {


    public TableAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position==0)return new message_Fragment();


else if (position==1) return new Users_Fragment();

            else      return new Live_Fragement();




    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

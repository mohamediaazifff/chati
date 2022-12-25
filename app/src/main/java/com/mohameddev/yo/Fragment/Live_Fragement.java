package com.mohameddev.yo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mohameddev.yo.R;
import com.mohameddev.yo.utils.utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Live_Fragement  extends Fragment implements View.OnClickListener {
    View v;
    @BindView(R.id.golive)
    FloatingActionButton golive;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_live,container,false);
      ButterKnife.bind(v);
      golive=(FloatingActionButton)v.findViewById(R.id.golive);

        golive.setOnClickListener(this);





        return v;



    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.golive)
        {
        }

    }
}

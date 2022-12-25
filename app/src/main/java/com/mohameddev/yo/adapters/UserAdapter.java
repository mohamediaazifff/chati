package com.mohameddev.yo.adapters;



 import android.content.Context;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.TextView;
 import androidx.annotation.NonNull;
 import androidx.constraintlayout.widget.ConstraintLayout;
 import androidx.recyclerview.widget.RecyclerView;
 import com.bumptech.glide.Glide;
 import com.mohameddev.yo.R;
 import com.mohameddev.yo.Ui.chat_activity;
 import com.mohameddev.yo.models.Users;
 import com.mohameddev.yo.utils.utils;
 import java.util.ArrayList;
 import java.util.Objects;
 import de.hdodenhof.circleimageview.CircleImageView;





public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.viewholder> {
    View v;
    ArrayList<Users> muser;
    Context context;

    public UserAdapter(ArrayList<Users> muser, Context context) {
        this.muser = muser;
        this.context = context;
    }




    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v= LayoutInflater.from(context).inflate(R.layout.oneuser,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Users user=muser.get(position);

        holder.username.setText(user.getUsername());

                Glide.with(context).load(user.getUser_profile()).into(holder.user_profile);
        if (!Objects.equals(user.getUser_profile(), "default")) {
            Glide.with(context).load(user.getUser_profile()).into(holder.user_profile);

        }
        else  Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.user_profile);

holder.select_user.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        utils.intentWithoutClear(context, chat_activity.class,"reciver", user.getId());
    }
});




    }

    @Override
    public int getItemCount() {
        return muser.size();
    }

    public static class viewholder  extends RecyclerView.ViewHolder {

        TextView username;
        ConstraintLayout select_user;
        CircleImageView user_profile;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            username=
                    (TextView) itemView.findViewById(R.id.username);
            user_profile=(CircleImageView) itemView.findViewById(R.id.user_profile_img);
            select_user=(ConstraintLayout) itemView.findViewById(R.id.linearLayout);


        }
    }
}

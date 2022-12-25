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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mohameddev.yo.R;
import com.mohameddev.yo.models.messages;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class message_adapter extends RecyclerView.Adapter<message_adapter.viewholder> {

    View v;
    ArrayList<messages> msg;
    Context context;
    private  final static int MSG_left=0,MSG_right=1;
    FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
    String img;

    public message_adapter(ArrayList<messages> msg, Context context,String img) {
        this.msg = msg;
        this.context = context;
        this.img=img;
    }




    @NonNull
    @Override
    public message_adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType ==MSG_right){
            v= LayoutInflater.from(context).inflate(R.layout.msg_item_right,parent,false);
            return new viewholder(v);
        }
else {
            v = LayoutInflater.from(context).inflate(R.layout.msg_item_left, parent, false);
            return new viewholder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull message_adapter.viewholder holder, int position) {
        messages mmsg=msg.get(position);
        holder.msgtext.setText(mmsg.getMsg());

        if(img.equals("default")){
            Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.user_msg_img);
        }
        else {
            Glide.with(context).load(img).into(holder.user_msg_img);
        }




    }

    @Override
    public int getItemCount() {
        return msg.size();
    }

    public static class viewholder  extends RecyclerView.ViewHolder {

        TextView msgtext;

        CircleImageView user_msg_img;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            msgtext=(TextView) itemView.findViewById(R.id.msg_text_chat);
            user_msg_img=(CircleImageView) itemView.findViewById(R.id.user_msg_img);



        }
    }

    @Override
    public int getItemViewType(int position) {
        if (msg.get(position).getSender_id().equals(firebaseUser.getUid())){
            return  MSG_right;
        } else
        {
            return  MSG_left;}
    }
}


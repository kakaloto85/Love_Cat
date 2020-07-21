package com.example.myapplication;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
        import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Friend_RecyclerAdapter extends RecyclerView.Adapter<Friend_RecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<Friend_data> listData = new ArrayList<>();
    private Context mContext;
    Friend_RecyclerAdapter(Context c){
        mContext = c;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(Friend_data friend_data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(friend_data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView friend_name;
        private TextView friend_email;
        private Button button;
        ItemViewHolder(View itemView) {
            super(itemView);
            friend_email=itemView.findViewById(R.id.friend_email);
            friend_name=itemView.findViewById(R.id.friend_name);


            button = itemView.findViewById(R.id.friend_button);
            button.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {



                    //여기서 친구 sns로 접속
                    Intent intent = new Intent(mContext,searched_tab1_friend.class);
                    intent.putExtra("user_email",friend_email.getText().toString());

                    mContext.startActivity(intent);




//                    int pos = getAdapterPosition() ;
//                    if (pos != RecyclerView.NO_POSITION) {
//                        Log.d("RecyclerAdapter","good");
//                        Intent intent =new Intent(mContext,phonepopup.class);
//                        intent.putExtra("name", listData.get(pos).getName());
//                        intent.putExtra("number", listData.get(pos).getNumber());
//
//                        mContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//
//                    }


                }
            });

        }


        void onBind(Friend_data friend_data) {

            friend_email.setText(friend_data.getEmail());
            friend_name.setText(friend_data.getName());
            button = itemView.findViewById(R.id.friend_button);

        }

    }
}

package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Friend_recycler_adapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<Data> listData = new ArrayList<>();
    private Context mContext;
    Friend_recycler_adapter(Context c){
        mContext = c;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
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

    void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView community_title;
        private TextView community_number;
        private TextView community_email;
        private TextView community_content;
        private ImageView community_image;
        private Button button;
        ItemViewHolder(View itemView) {
            super(itemView);

            community_title = itemView.findViewById(R.id.community_title);
            community_number = itemView.findViewById(R.id.community_number);
            community_email = itemView.findViewById(R.id.community_email);
            community_content= itemView.findViewById(R.id.community_content);
            community_image= itemView.findViewById(R.id.community_image);
            button = itemView.findViewById(R.id.community_button);
            button.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, community_write_activity.class);

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

        void onBind(Data data) {

            community_title.setText(data.getTitle());
            community_number.setText(data.getPhone());
            community_email.setText(data.getEmail());
            community_content.setText(data.getContent());
            community_image.setImageBitmap(data.getImage());
            button = itemView.findViewById(R.id.community_button);

        }

    }
}

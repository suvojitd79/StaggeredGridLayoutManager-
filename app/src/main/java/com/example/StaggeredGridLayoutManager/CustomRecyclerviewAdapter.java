package com.example.StaggeredGridLayoutManager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomRecyclerViewHolder>{

    private List<Data> imageModels = new ArrayList<>();

    public void setImageModels(List<Data> imageModels){

        Log.d("99",imageModels.size()+"");
        this.imageModels = imageModels;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CustomRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_layout,viewGroup,false);
        return new CustomRecyclerViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewHolder customRecyclerViewHolder, int i) {

            customRecyclerViewHolder.setImageView(imageModels.get(i).getImageModel().getSmall());
            customRecyclerViewHolder.setTextView(imageModels.get(i).getAlt_description());
    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }

    class CustomRecyclerViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public CustomRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.textView);
        }



        public void setImageView(String uri) {

            Picasso.get().load(uri)
                        .into(imageView);

        }

        public void setTextView(String text) {
            this.textView.setText(text);
        }

    }

}

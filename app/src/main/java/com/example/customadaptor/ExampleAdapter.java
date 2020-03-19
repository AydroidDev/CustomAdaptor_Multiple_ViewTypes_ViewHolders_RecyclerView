package com.example.customadaptor;

import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Genjitsu on 20/03/2020.
 */

public class ExampleAdapter extends RecyclerView.Adapter<ViewHolder> {
    // ExampleItem objemizi alan bir arraylist oluşturuyoruz
    private ArrayList<ExampleItem> mExampleList;
    private Context context;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        //tıklama durumlarında hangi elemente tıklanıldıysa ona göre işlem yapmak için fonksiyonlara position parametresini gönderiyoruz
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }
    // listeleme yaparken  ne zaman hangi tipteki elementin listeye ekleneceğini ayarlamak için 0 ve 1 olarak atama yapıyoruz.
    private static final int PHOTO = 0;
    private static final int VIDEO = 1;
    int[] viewTypes;



    public ExampleAdapter(ArrayList<ExampleItem> exampleList, Context context, int[] viewTypes) {
        mExampleList = exampleList;
        this.context = context;
        this.viewTypes = viewTypes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        //viewType a gore ViewHolder ve layout belirliyoruz
        View v;
        if (viewType == PHOTO) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item_photo, parent, false);
            ViewHolder holder = new ExampleViewHolder(v,mListener);
            Log.d("holder", "holder secildi PHOTO");
            return holder;
        } else if (viewType == VIDEO) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item_video, parent, false);
            ViewHolder holder = new VideoViewHolder(v);
            Log.d("holder", "holder secildi VIDEO");
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        ExampleItem currentItem = mExampleList.get(position);
        // eğer element type PHOTO ise Picasso kullanarak objemizdeki veriyi layout umuzdaki imageView'a gönderiyoruz
        // eğer element type VIDEO ise objemizdeki veriyi layout umuzdaki VideoView'e gönderiyoruz.
        if (viewHolder.getItemViewType() == PHOTO) {
            final ExampleViewHolder holder = (ExampleViewHolder) viewHolder;
            try {
                Picasso.get().load(currentItem.getPhotoLink()).into(holder.mImageView);
            }
            catch (Exception e){
                Toast.makeText(context, "Error picasso", Toast.LENGTH_SHORT).show();
            }


            holder.mTextView1.setText(currentItem.getObjText());


        } else if (viewHolder.getItemViewType() == VIDEO) {

           final VideoViewHolder vholder = (VideoViewHolder) viewHolder;
            try {
                String link = currentItem.getVideoLink();
                MediaController mediaController = new MediaController(context);
                mediaController.setAnchorView(vholder.mVideoView);
                Uri video = Uri.parse(link);

                vholder.mVideoView.setMediaController(mediaController);
                vholder.mVideoView.setVideoURI(video);
                vholder.mVideoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vholder.mVideoView.start();
                    }
                });

                vholder.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(context, "video bitti", Toast.LENGTH_SHORT).show();

                    }
                });
            } catch (Exception e) {
                Toast.makeText(context, "Error connection", Toast.LENGTH_SHORT).show();
            }

            vholder.mTextView2.setText(currentItem.getObjText());
        }
    }


    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypes[position];
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View v) {
        super(v);
    }
}

// photo icin viewHolder
class ExampleViewHolder extends ViewHolder {
    public ImageView mImageView;
    public TextView mTextView1;

    public ExampleViewHolder(View itemView,final ExampleAdapter.OnItemClickListener listener) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.imageview);
        mTextView1 = itemView.findViewById(R.id.photo_content);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });


    }
}



// video icin viewHolder
class VideoViewHolder extends ViewHolder {

    public VideoView mVideoView;
    public TextView mTextView2;


    public VideoViewHolder(View v) {
        super(v);

        mVideoView = itemView.findViewById(R.id.videoView);
        mTextView2 = itemView.findViewById(R.id.video_content);

    }
}

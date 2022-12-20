package com.nmnews.nmnewsagency.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.OwnVideoDetailActivity;
import com.nmnews.nmnewsagency.modelclass.SearchTopSearchModel;
import com.bumptech.glide.Glide;

import java.util.List;

import vimeoextractor.OnVimeoExtractionListener;
import vimeoextractor.VimeoExtractor;
import vimeoextractor.VimeoVideo;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.MyViewHolder> {
   // private List<LocationModel> moviesList;
    private List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> moviesList;
    Context context;

    public VideosAdapter(Context context, List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView video_search;
        public ImageView image_search_video_profile;
        LinearLayout lin_videoes;

        public MyViewHolder(View view) {
            super(view);
            image_search_video_profile = (ImageView) view.findViewById(R.id.image_search_video_profile);
            video_search = (TextView) view.findViewById(R.id.video_search);
            lin_videoes = (LinearLayout) view.findViewById(R.id.lin_videoes);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_videos, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.video_search.setText(moviesList.get(position).getTitle());
        Glide.with(context)
                .load(moviesList.get(position).getAvatar())
                .placeholder(R.color.adbag)
                .centerCrop()
                /*.transition(DrawableTransitionOptions.withCrossFade(500))*/
                .into(holder.image_search_video_profile);
                holder.lin_videoes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context, OwnVideoDetailActivity.class);
                        intent.putExtra("newsid",moviesList.get(position).getId());
                        intent.putExtra("self","other");
                        context.startActivity(intent);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void getThumbnail(String vimeoUrl,ImageView imageView) {
        // Log.e("vimeothumb====","https://vimeo.com/"+vimeoUrl+".xml");
        VimeoExtractor.getInstance().fetchVideoWithURL("https://vimeo.com/api/v2/video/" + vimeoUrl, null, new OnVimeoExtractionListener() {
            @Override
            public void onSuccess(VimeoVideo video) {
                String hdStream = video.getThumbs().get("640");
                Log.e("vimeothumb====", hdStream);
                try {
                    ContextCompat.getMainExecutor(context).execute(new Runnable() {
                        @Override
                        public void run() {
                            // Utils.loadImageUsingGlidePlaceHolder(context, hdStream, holder.img_videoThumb, R.mipmap.ic_launcher_foreground);
                            Glide.with(context)
                                    .load(hdStream)
                                    .into(imageView);
                        }
                    });
                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("error", throwable.getMessage());

            }
        });
    }
}

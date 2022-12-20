package com.nmnews.nmnewsagency.adapter;

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
import com.nmnews.nmnewsagency.modelclass.GetUserSaveNewsModel;
import com.bumptech.glide.Glide;

import java.util.List;

import vimeoextractor.OnVimeoExtractionListener;
import vimeoextractor.VimeoExtractor;
import vimeoextractor.VimeoVideo;

public class GetUserSaveNewsAdapter extends RecyclerView.Adapter<GetUserSaveNewsAdapter.MyViewHolder> {
    private List<GetUserSaveNewsModel.DataBeanX.DataBean.PagedRecordBean> moviesList;
    Context context;

    public GetUserSaveNewsAdapter(Context context, List<GetUserSaveNewsModel.DataBeanX.DataBean.PagedRecordBean> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_hashtag;
        LinearLayout lin_item;
        private TextView tv_Videostatus;

        public MyViewHolder(View view) {
            super(view);
           img_hashtag = (ImageView) view.findViewById(R.id.img_hashtag);
           lin_item = (LinearLayout) view.findViewById(R.id.lin_additem);
            tv_Videostatus = view.findViewById(R.id.tv_Videostatus);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hashtag_detail, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GetUserSaveNewsModel.DataBeanX.DataBean.PagedRecordBean movie = moviesList.get(position);
      /*  Glide.with(context)
                .load(movie.getImageUrl())
                .into(holder.img_hashtag);*/
        getThumbnail(movie.getVideoId(),holder.img_hashtag);
        holder.lin_item.setTag(position);
        holder.lin_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) v.getTag();
                Intent intent = new Intent(context, OwnVideoDetailActivity.class);
                intent.putExtra("newsid", moviesList.get(pos).getNewsId());
                intent.putExtra("self", "self");
                intent.putExtra("type","save");
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            }
        });

        if (movie.getStatusName()!=null && movie.getStatusName().equals("Rejected"))
        {
            holder.tv_Videostatus.setVisibility(View.VISIBLE);
            holder.tv_Videostatus.setBackgroundColor(context.getResources().getColor(R.color.alert));
            holder.tv_Videostatus.setTextColor(context.getResources().getColor(R.color.white));

        }else
        {
            holder.tv_Videostatus.setVisibility(View.VISIBLE);
            holder.tv_Videostatus.setBackgroundColor(context.getResources().getColor(R.color.locbutonbag));
            holder.tv_Videostatus.setTextColor(context.getResources().getColor(R.color.white));
        }


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

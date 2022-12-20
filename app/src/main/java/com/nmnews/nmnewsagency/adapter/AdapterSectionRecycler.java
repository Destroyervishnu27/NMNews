package com.nmnews.nmnewsagency.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.OwnVideoDetailActivity;
import com.nmnews.nmnewsagency.holder.ChildViewHolder;
import com.nmnews.nmnewsagency.holder.SectionViewHolder;
import com.nmnews.nmnewsagency.model.Child;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;

import vimeoextractor.OnVimeoExtractionListener;
import vimeoextractor.VimeoExtractor;
import vimeoextractor.VimeoVideo;

/**
 * Created by apple on 11/7/16.
 */

public class AdapterSectionRecycler extends SectionRecyclerViewAdapter<SectionHeader, Child, SectionViewHolder, ChildViewHolder> {

    Context context;

    public AdapterSectionRecycler(Context context, List<SectionHeader> sectionHeaderItemList) {
        super(context, sectionHeaderItemList);
        this.context = context;
    }

    @Override
    public SectionViewHolder onCreateSectionViewHolder(ViewGroup sectionViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.section_item, sectionViewGroup, false);
        return new SectionViewHolder(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, childViewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder sectionViewHolder, int sectionPosition, SectionHeader sectionHeader) {
        sectionViewHolder.name.setText(sectionHeader.sectionText);
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder childViewHolder, int sectionPosition, int childPosition, Child child) {
       childViewHolder.txt_user_namenoti.setText(child.getName());
        Glide.with(context)
                .load(child.getImg(childPosition))
                .placeholder(R.color.adbag)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(childViewHolder.image_profile_noti);
        childViewHolder.rel_notichild.setTag(childPosition);
        childViewHolder.rel_notichild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int pos= (int) childViewHolder.rel_notichild.getTag();
                Intent intent4 = new Intent(context, OwnVideoDetailActivity.class);
                intent4.putExtra("newsid",Integer.parseInt(child.getnewsId(childPosition)));
                intent4.putExtra("self","other");
                context.startActivity(intent4);
            }
        });

    }
    public void getThumbnail(String vimeoUrl, ImageView imageView) {
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

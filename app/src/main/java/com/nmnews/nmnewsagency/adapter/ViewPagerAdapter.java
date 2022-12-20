package com.nmnews.nmnewsagency.adapter;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.CommentsActivity;
import com.nmnews.nmnewsagency.activity.DownloadVideoActivity;
import com.nmnews.nmnewsagency.activity.LikesActivity;
import com.nmnews.nmnewsagency.activity.UserProfileActivity;
import com.nmnews.nmnewsagency.fragment.HomeFragment;
import com.nmnews.nmnewsagency.modelclass.AddNewsModel;
import com.nmnews.nmnewsagency.modelclass.DownloadModel;
import com.nmnews.nmnewsagency.modelclass.GetNewsListModel;
import com.nmnews.nmnewsagency.modelclass.LikeModelClass;
import com.nmnews.nmnewsagency.modelclass.ReportModelClass;
import com.nmnews.nmnewsagency.modelclass.SaveModelClass;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.utils.Utils;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.interstitial.InterstitialAd;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vimeoextractor.OnVimeoExtractionListener;
import vimeoextractor.VimeoExtractor;
import vimeoextractor.VimeoVideo;


public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> implements
        View.OnClickListener, Callback<Object> {

    private List<GetNewsListModel.DataBean.PagedRecordBean> moviesList;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager2;
    Context context;
    SimpleExoPlayer exoPlayer;
    String url;
    Rest rest;
    int follow, report, coments, like, save;
    boolean show = true, isLike = false, isSave = false, autoPlay = true;
    int current_pos, total_duration;
    static ViewHolder viewHolder;
    ProgressDialog pBar;
    String outputPaTH = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + System.nanoTime() + "compress.mp4";
    String outputPaTHkk = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + System.nanoTime() + "edit.mp4";
    AdRequest adRequest;
    Activity activity;


    private int[] colorArray = new int[]{android.R.color.black, android.R.color.holo_blue_dark, android.R.color.holo_green_dark, android.R.color.holo_red_dark};

    private View.OnClickListener onClickListener;
    public ViewPagerAdapter(Context context, List<GetNewsListModel.DataBean.PagedRecordBean> data,
                            ViewPager2 viewPager, Activity activity, View.OnClickListener onClickListener) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.moviesList = data;
        this.viewPager2 = viewPager;
        this.activity = activity;
        this.onClickListener = onClickListener;
        rest = new Rest(context, this);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Toast.makeText(context, "onCreateViewHolder", Toast.LENGTH_SHORT).show();
        View view = mInflater.inflate(R.layout.item_home, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.setIsRecyclable(false);

        adRequest = new AdRequest.Builder().build();
        holder.adSize = new AdSize(300, 50);
        holder.mAdView.loadAd(adRequest);
        //holder.mAdView.setAdSize(AdSize.BANNER);
        holder.mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.e("Ad Mob- ", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                Log.e("Ad Mob- ", "onAdFailedToLoad " + adError.getMessage());
            }

            @Override
            public void onAdOpened() {
                Log.e("Ad Mob- ", "onAdOpened");
            }

            @Override
            public void onAdClicked() {
                Log.e("Ad Mob- ", "onAdClicked");
            }

            @Override
            public void onAdClosed() {
                Log.e("Ad Mob- ", "onAdClosed");
            }
        });

        url = moviesList.get(position).getVideoUrl();
        autoPlay = moviesList.get(position).isAutoPlay();
        if (autoPlay) {
            Prefrence.setSetAutoplay("true");
        } else {
            Prefrence.setSetAutoplay("false");
        }

        // }
        if (moviesList.get(position).getRecordType().equalsIgnoreCase("ADS")) {
            holder.lin_fullUI.setVisibility(View.GONE);
            holder.rel_ad.setVisibility(View.VISIBLE);
            callAddViewCount(moviesList.get(position).getNewsId());
            if (moviesList.get(position).getMediaType().equalsIgnoreCase("VIDEO")) {
                holder.ad_video.setVisibility(View.VISIBLE);
                holder.videoView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    @Override
                    public void onViewAttachedToWindow(View v) {
                        holder.initPlayerAD(url);

                        // Toast.makeText(context, "initPlayer", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onViewDetachedFromWindow(View v) {
                        try {
                            // Toast.makeText(context, "relese", Toast.LENGTH_SHORT).show();
                            holder.ad_video.getPlayer().stop();
                            holder.releasePlayer();
                        } catch (NullPointerException ex) {
                        }
                    }
                });
                holder.ad_video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                        httpIntent.setData(Uri.parse(moviesList.get(position).getRedirectUrl()));

                        context.startActivity(httpIntent);
                    }
                });
            } else {
                holder.ad_image.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(moviesList.get(position).getImageUrl())
                        .into(holder.ad_image);
                holder.ad_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                        httpIntent.setData(Uri.parse(moviesList.get(position).getRedirectUrl()));

                        context.startActivity(httpIntent);
                    }
                });
            }
        } else {
            holder.videoView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    holder.initPlayer(url, autoPlay);
                    // Toast.makeText(context, "initPlayer", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    try {
                        // Toast.makeText(context, "relese", Toast.LENGTH_SHORT).show();
                        holder.videoView.getPlayer().stop();
                        holder.releasePlayer();
                    } catch (NullPointerException ex) {
                    }
                }
            });
        }

        GetNewsListModel.DataBean.PagedRecordBean movie = moviesList.get(position);

        if (movie.getUserType() != null && movie.getUserType().toString().equalsIgnoreCase("REPORTER")) {
            holder.imgUserType.setBackground(context.getResources().getDrawable(R.drawable.ic_group_7756));
        } else
        {
            holder.imgUserType.setBackground(context.getResources().getDrawable(R.drawable.ic_check));

        }
        holder.txt_follow.setOnClickListener(this);
        holder.lin_report.setOnClickListener(this);
        holder.lin_comments.setOnClickListener(this);
        holder.lin_save.setOnClickListener(this);
        holder.lin_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFeed(movie.getNewsId(), movie.getTitle());
            }
        });
        holder.rel_userprofile.setOnClickListener(this);
        holder.img_like.setOnClickListener(this);
        holder.txt_nooflike.setOnClickListener(this);
        holder.txt_noofcoment.setText(String.valueOf(movie.getCommentCountSuffix()));
        holder.txt_nooflike.setText(String.valueOf(movie.getLikesCountSuffix()));
        //   String date = movie.getAddedOn();
        //  String[] parts = date.split("T");
        holder.rel_userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  holder.videoView.getPlayer().stop();
                int pos = (int) v.getTag();
                Intent intent1 = new Intent(context, UserProfileActivity.class);
                intent1.putExtra("userId", moviesList.get(pos).getUserId());
                context.startActivity(intent1);
            }
        });
        holder.txt_title.setText(movie.getTahsil_Name() + ": " + movie.getTitle().trim());
        holder.lin_download.setOnClickListener(onClickListener);
        holder.lin_download.setTag(R.id.lin_download,movie);
//        holder.lin_download.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ((HomeFragment)activity).downLoadVideo(movie.getVideoId());
//
//            }
//        });
        holder.txt_datetime.setText(Utils.parseDateToddMMyyyy(movie.getAddedOn()));
        holder.txt_datetime.setSelected(true);
        holder.txt_description.setText("" + movie.getAboutMe());
        holder.txt_location.setText(movie.getTahsil_Name() + ", " + movie.getCity_Name());
        holder.txt_location.setSelected(true);
        holder.txt_location.setAllCaps(false);
        holder.txt_username.setSelected(true);
        holder.txt_title.setSelected(true);
        holder.txt_username.setText(movie.getUserName());
        holder.txt_views.setText(String.valueOf(movie.getViewCountSuffix()));
        holder.txt_viewsAdds.setText(String.valueOf(movie.getViewCountSuffix()));
        if (movie.isIsFollowed()) {
            holder.txt_follow.setText("Following");
            holder.txt_follow.setTextColor(Color.parseColor("#DBDBDB"));
        } else {
            holder.txt_follow.setText("Follow");
            holder.txt_follow.setTextColor(Color.parseColor("#E5383E"));
        }
        if (movie.isIsLike()) {
            isLike = true;
            holder.img_like.setImageResource(R.drawable.ic_dislike);
        } else {
            isLike = false;
            holder.img_like.setImageResource(R.drawable.ic_like);
        }
        if (movie.isIsSaved()) {
            isSave = true;
            holder.img_save.setImageResource(R.drawable.ic_notsave);
            holder.txt_savetext.setTextColor(Color.parseColor("#FF0000"));
        } else {
            isSave = false;
            holder.img_save.setImageResource(R.drawable.ic_save);
            holder.txt_savetext.setTextColor(Color.parseColor("#333333"));
        }

        if (movie.getDownloadLink() != null && !movie.getDownloadLink().equals("")) {
            holder.lin_download.setVisibility(View.VISIBLE);
        } else {
            holder.lin_download.setVisibility(View.GONE);
        }
        // holder.exoPause.setTag(position);
        holder.rel_userprofile.setTag(position);
        //  holder.exoPlay.setTag(position);
        holder.txt_follow.setTag(position);
        holder.lin_download.setTag(position);
        holder.lin_report.setTag(position);
        holder.lin_save.setTag(position);
        holder.lin_comments.setTag(position);
        holder.img_like.setTag(position);
        holder.txt_nooflike.setTag(position);
        holder.txt_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                follow = (int) v.getTag();
                if (holder.txt_follow.getText().equals("Follow")) {
                    holder.txt_follow.setText("Following");
                    holder.txt_follow.setTextColor(Color.parseColor("#DBDBDB"));
                } else {
                    holder.txt_follow.setText("Follow");
                    holder.txt_follow.setTextColor(Color.parseColor("#E5383E"));
                }
                callServicegetFollows(String.valueOf(moviesList.get(follow).getUserId()),
                        moviesList.get(follow).isIsFollowed());

            }
        });
        holder.img_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like = (int) v.getTag();
                if (isLike) {
                    int no = Integer.parseInt(holder.txt_nooflike.getText().toString());
                    if (no > 0) {
                        int set = no - 1;
                        holder.txt_nooflike.setText(String.valueOf(set));
                        moviesList.get(like).setLikesCount(Integer.parseInt(holder.txt_nooflike.getText().toString()));
                    }
                    holder.img_like.setImageResource(R.drawable.ic_like);
                    callServicegetDisLike(moviesList.get(like).getNewsId());
                } else {
                    int no = Integer.parseInt(holder.txt_nooflike.getText().toString());

                    holder.txt_nooflike.setText(String.valueOf(no + 1));
                    moviesList.get(like).setLikesCount(Integer.parseInt(holder.txt_nooflike.getText().toString()));
                    holder.img_like.setImageResource(R.drawable.ic_dislike);
                    callServicegetLike(moviesList.get(like).getNewsId());
                }
            }
        });

        holder.txt_nooflike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LikesActivity.class);
                intent.putExtra("newsId", moviesList.get((Integer) v.getTag()).getNewsId());
                context.startActivity(intent);
            }
        });

        holder.lin_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                report = (int) v.getTag();
                openDialogBox(moviesList.get(report).getNewsId());

            }
        });
        holder.lin_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save = (int) v.getTag();
                if (isSave) {
                    holder.txt_savetext.setTextColor(Color.parseColor("#333333"));
                    holder.img_save.setImageResource(R.drawable.ic_save);
                    callServicegetDeleteSave(moviesList.get(save).getNewsId());
                } else {
                    holder.txt_savetext.setTextColor(Color.parseColor("#FF0000"));
                    holder.img_save.setImageResource(R.drawable.ic_notsave);
                    callServicegetSave(moviesList.get(save).getNewsId());
                }
            }
        });
        holder.lin_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   holder.videoView.getPlayer().stop();
                coments = (int) v.getTag();
                Intent intent = new Intent(context, CommentsActivity.class);
                intent.putExtra("newsId", moviesList.get(coments).getNewsId());
                context.startActivity(intent);
            }
        });

        //   new Handler().post(new Runnable() {
        // public void run() {
                /*Glide.with(context)
                        .load(moviesList.get(position).getImageUrl())
                        .into(holder.video_thuimbnail);*/
        if (moviesList.get(position).getImageUrl() == null) {
            getThumbnail(moviesList.get(position).getVideoId(), holder.video_thuimbnail);
        } else if (moviesList.get(position).getImageUrl().isEmpty()) {
            getThumbnail(moviesList.get(position).getVideoId(), holder.video_thuimbnail);
        } else {
            final Context context = activity.getApplication().getApplicationContext();

            if (isValidContextForGlide(context)) {
                // Load image via Glide lib using context
                Glide.with(context)
                        .load(moviesList.get(position).getImageUrl())
                        .into(holder.video_thuimbnail);
            }


        }
        // }
        // });

        new Handler().post(new Runnable() {
            public void run() {
                final Context context = activity.getApplication().getApplicationContext();

                if (isValidContextForGlide(getApplicationContext())) {
                    // Load image via Glide lib using context

                    try {
                        ContextCompat.getMainExecutor(getApplicationContext()).execute(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(getApplicationContext())
                                        .load(moviesList.get(position).getAvatar())
                                        .into(holder.image_profile);
                            }
                        });
                    } catch (Exception e) {

                    }

                }

            }
        });

    }




    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_follow:
                //  callServicegetCityList();
                break;
            case R.id.lin_share:

                break;
            case R.id.lin_comments:

                break;
            case R.id.lin_save:
                break;
            case R.id.rel_userprofile:

                break;
            case R.id.lin_report:
                // openDialogBox();
                break;
        }
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof AddNewsModel) {
                AddNewsModel loginModel = (AddNewsModel) obj;
                if (loginModel.isStatus()) {
                    if (moviesList.get(follow).isIsFollowed()) {
                        moviesList.get(follow).setIsFollowed(false);
                        Utils.showSnakBarDialog(context, viewHolder.lin_tophome, "Successfully UnFollow", R.color.msgresponce);
                    } else {
                        moviesList.get(follow).setIsFollowed(true);
                        Utils.showSnakBarDialog(context, viewHolder.lin_tophome, "Successfully Follow", R.color.msgresponce);
                    }
                    // notifyDataSetChanged();
                }
            }
            if (obj instanceof LikeModelClass) {
                LikeModelClass loginModel = (LikeModelClass) obj;
                if (loginModel.isStatus()) {
                    if (moviesList.get(like).isIsLike()) {
                        moviesList.get(like).setIsLike(false);
                        isLike = false;
                        Utils.showSnakBarDialog(context, viewHolder.lin_tophome, "Successfully UnLike", R.color.msgresponce);
                    } else {
                        moviesList.get(like).setIsLike(true);
                        isLike = true;
                        Utils.showSnakBarDialog(context, viewHolder.lin_tophome, "Successfully Like", R.color.msgresponce);
                    }
                    // notifyDataSetChanged();
                }
            }
            if (obj instanceof SaveModelClass) {
                SaveModelClass loginModel = (SaveModelClass) obj;
                if (loginModel.isStatus()) {
                    if (moviesList.get(save).isIsSaved()) {
                        moviesList.get(save).setIsSaved(false);
                        isSave = false;
                        Utils.showSnakBarDialog(getApplicationContext(), viewHolder.lin_tophome, "Successfully UnSave", R.color.msgresponce);
                    } else {
                        moviesList.get(save).setIsSaved(true);
                        isSave = true;
                        Utils.showSnakBarDialog(getApplicationContext(), viewHolder.lin_tophome, "Successfully Save", R.color.msgresponce);
                    }
                    // notifyDataSetChanged();
                }
            }
            if (obj instanceof ReportModelClass) {
                ReportModelClass loginModel = (ReportModelClass) obj;
                if (loginModel.isStatus()) {
                    // Toast.makeText(context, "Report successfully added", Toast.LENGTH_SHORT).show();
                    Utils.showSnakBarDialog(context, viewHolder.lin_tophome, "Report successfully added", R.color.msgresponce);
                }
            }
            if (obj instanceof DownloadModel) {
                DownloadModel downloadModel = (DownloadModel) obj;
                if (downloadModel.getStatus()) {
                    startDownload(downloadModel.getData());
                }
            }
        }
    }


    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        PlayerView videoView, ad_video;
        MediaPlayer audioplayer;
        AdView mAdView;
        DataSpec dataSpec;
        RelativeLayout rel_ad;
        public TextView txt_username, txt_description, txt_title, txt_views, txt_location, txt_datetime;
        public LinearLayout lin_report, lin_comments, lin_save, lin_share;
        LinearLayout rel_userprofile, lin_tophome, lin_fullUI;
        TextView txt_follow, current, total, txt_noofcoment, txt_nooflike, txt_savetext;
        ImageButton exoPlay, exoPause;
        ImageView video_thuimbnail, img_playpause, img_save, ad_image,img_like;
        CircleImageView image_profile;
        SeekBar seekbar;
        BandwidthMeter bandwidthMeter;
        TrackSelector trackSelector;
        DefaultHttpDataSourceFactory dataSourceFactory;
        ExtractorsFactory extractorsFactory;
        SimpleExoPlayer simpleExoPlayer;
        LoadControl loadControl;
        MediaSource mediaSource;
        Uri videouri;
        AdSize adSize;
        TextView txt_viewsAdds;
        LinearLayout lin_download;
        private ImageView imgUserType;

        ViewHolder(View view) {
            super(view);
            //   Toast.makeText(context, "ViewHolder", Toast.LENGTH_SHORT).show();
           /* myTextView = itemView.findViewById(R.id.tvTitle);
            relativeLayout = itemView.findViewById(R.id.container);
            button = itemView.findViewById(R.id.btnToggle);*/
            mAdView = view.findViewById(R.id.adView);
            imgUserType = view.findViewById(R.id.imgUserType);
            // mAdView.setAdUnitId("ca-app-pub-9996989418420009/6084486720");

            // mAdView.setAdSize(adSize);
            rel_userprofile = (LinearLayout) view.findViewById(R.id.rel_userprofile);
            rel_ad = (RelativeLayout) view.findViewById(R.id.rel_ad);
            lin_tophome = (LinearLayout) view.findViewById(R.id.lin_tophome);
            lin_fullUI = (LinearLayout) view.findViewById(R.id.lin_fullUI);
            lin_report = (LinearLayout) view.findViewById(R.id.lin_report);
            img_like = (ImageView) view.findViewById(R.id.img_like);
            lin_share = (LinearLayout) view.findViewById(R.id.lin_share);
            lin_save = (LinearLayout) view.findViewById(R.id.lin_save);
            lin_comments = (LinearLayout) view.findViewById(R.id.lin_comments);
            lin_download = (LinearLayout) view.findViewById(R.id.lin_download);
            //  videoView = (VideoView) view.findViewById(R.id.img_homeprofile);
            //  exoPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.idExoPlayerVIew);
            //  exoPlay = (ImageButton) view.findViewById(R.id.exo_play);
            //  exoPause = (ImageButton) view.findViewById(R.id.exo_pause);
            txt_username = (TextView) view.findViewById(R.id.txt_username);
            txt_savetext = (TextView) view.findViewById(R.id.txt_savetext);
            txt_description = (TextView) view.findViewById(R.id.txt_description);
            txt_viewsAdds = (TextView) view.findViewById(R.id.txt_viewsAdds);
            txt_title = (TextView) view.findViewById(R.id.txt_title);
            txt_views = (TextView) view.findViewById(R.id.txt_views);
            txt_location = (TextView) view.findViewById(R.id.txt_location);
            txt_datetime = (TextView) view.findViewById(R.id.txt_datetime);
            txt_follow = (TextView) view.findViewById(R.id.txt_follow);
            video_thuimbnail = (ImageView) view.findViewById(R.id.video_thuimbnail);
            img_playpause = (ImageView) view.findViewById(R.id.img_playpause);
            img_save = (ImageView) view.findViewById(R.id.img_save);
            ad_image = (ImageView) view.findViewById(R.id.ad_image);
            image_profile = (CircleImageView) view.findViewById(R.id.image_profile);
            total = (TextView) view.findViewById(R.id.total);
            current = (TextView) view.findViewById(R.id.current);
            txt_nooflike = (TextView) view.findViewById(R.id.txt_nooflike);
            txt_noofcoment = (TextView) view.findViewById(R.id.txt_noofcoment);
            seekbar = (SeekBar) view.findViewById(R.id.seekbar);
            videoView = (PlayerView) itemView.findViewById(R.id.idExoPlayerVIew);
            ad_video = (PlayerView) itemView.findViewById(R.id.ad_video);
            audioplayer = new MediaPlayer();
            //audioplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            //  simpleExoPlayer = new SimpleExoPlayer.Builder(context).build();

            bandwidthMeter = new DefaultBandwidthMeter();
            trackSelector = new DefaultTrackSelector();
            loadControl = new DefaultLoadControl.Builder()
                    .setAllocator(new DefaultAllocator(true, 10))
                    .setBufferDurationsMs(VideoPlayerConfig.MIN_BUFFER_DURATION,
                            VideoPlayerConfig.MAX_BUFFER_DURATION,
                            VideoPlayerConfig.MIN_PLAYBACK_START_BUFFER,
                            VideoPlayerConfig.MIN_PLAYBACK_RESUME_BUFFER)
                    .setTargetBufferBytes(-1)
                    .setPrioritizeTimeOverSizeThresholds(true).createDefaultLoadControl();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
            dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            extractorsFactory = new DefaultExtractorsFactory();


        }

        public void initPlayer(String url, boolean autoPlay) {
            videouri = Uri.parse(url);
            mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory,
                    null, null);
            simpleExoPlayer.prepare(mediaSource);
            videoView.setPlayer(simpleExoPlayer);
            simpleExoPlayer.setPlayWhenReady(true);
            videoView.setKeepContentOnPlayerReset(true);
            //  videoView.setFocusable(true);
            simpleExoPlayer.addListener(new Player.EventListener() {

                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

                }

                @Override
                public void onLoadingChanged(boolean isLoading) {

                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    if (playbackState == ExoPlayer.STATE_READY) {
                        // Toast.makeText(context, "STATE_READY", Toast.LENGTH_SHORT).show();
                        video_thuimbnail.setVisibility(View.GONE);
                        videoView.setControllerShowTimeoutMs(1500);

                    } else if (playbackState == ExoPlayer.STATE_BUFFERING) {
                        // Toast.makeText(context, "STATE_BUFFERING", Toast.LENGTH_SHORT).show();
                        //  video_thuimbnail.setVisibility(View.VISIBLE);
                        // exo_pause.setVisibility(View.GONE);
                        videoView.setControllerShowTimeoutMs(3000);

                    } else if (playbackState == ExoPlayer.STATE_ENDED) {
                        // Toast.makeText(context, "STATE_ENDED", Toast.LENGTH_SHORT).show();
                        if (autoPlay) {
                            simpleExoPlayer.seekTo(0);
                            videoView.setControllerShowTimeoutMs(1);
                        }
                    }
                }

                @Override
                public void onRepeatModeChanged(int repeatMode) {
                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {
                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                }
            });
           /* if (mInterstitialAd != null) {
                mInterstitialAd.show(activity);
            } else {
                Log.e("ad==", "The interstitial ad wasn't ready yet.");
            }*/
        }

        public void initPlayerAD(String url) {
            videouri = Uri.parse(url);
            mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory,
                    null, null);
            simpleExoPlayer.prepare(mediaSource);
            ad_video.setPlayer(simpleExoPlayer);
            simpleExoPlayer.setPlayWhenReady(true);
            ad_video.setKeepContentOnPlayerReset(true);
            //  videoView.setFocusable(true);
            simpleExoPlayer.addListener(new Player.EventListener() {

                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

                }

                @Override
                public void onLoadingChanged(boolean isLoading) {

                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    if (playbackState == ExoPlayer.STATE_READY) {
                        // Toast.makeText(context, "STATE_READY", Toast.LENGTH_SHORT).show();
                        // video_thuimbnail.setVisibility(View.GONE);
                        // videoView.setControllerShowTimeoutMs(1500);

                    } else if (playbackState == ExoPlayer.STATE_BUFFERING) {
                        // Toast.makeText(context, "STATE_BUFFERING", Toast.LENGTH_SHORT).show();
                        //  video_thuimbnail.setVisibility(View.VISIBLE);
                        // exo_pause.setVisibility(View.GONE);
                        videoView.setControllerShowTimeoutMs(3000);

                    } else if (playbackState == ExoPlayer.STATE_ENDED) {
                        // Toast.makeText(context, "STATE_ENDED", Toast.LENGTH_SHORT).show();
                        // if (autoPlay) {
                        //    simpleExoPlayer.seekTo(0);
                        //    videoView.setControllerShowTimeoutMs(1);
                        // }
                    }
                }

                @Override
                public void onRepeatModeChanged(int repeatMode) {
                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {
                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                }
            });
           /* if (mInterstitialAd != null) {
                mInterstitialAd.show(activity);
            } else {
                Log.e("ad==", "The interstitial ad wasn't ready yet.");
            }*/
        }

        public void releasePlayer() {
            if (simpleExoPlayer != null) {
                //  Toast.makeText(context, "releasePlayer", Toast.LENGTH_SHORT).show();
                simpleExoPlayer.setPlayWhenReady(true);
                simpleExoPlayer.stop();
                simpleExoPlayer.release();
                simpleExoPlayer = null;

                videoView.setPlayer(null);
                videoView = null;
                System.out.println("releasePlayer");
                Log.e("releasePlayer", "releasePlayer");
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    private void openDialogBox(int newsId) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_report);
        ImageView img_close_dialog = dialog.findViewById(R.id.img_close_dialog);
        TextView txt_report_subtitle = dialog.findViewById(R.id.txt_report_subtitle);
        RadioGroup radio_group = dialog.findViewById(R.id.radio_group);
        RadioButton radio_frst = dialog.findViewById(R.id.radio_frst);
        RadioButton radio_scond = dialog.findViewById(R.id.radio_scond);
        RadioButton radio_thrd = dialog.findViewById(R.id.radio_thrd);
        RadioButton radio_furth = dialog.findViewById(R.id.radio_furth);
        RadioButton radio_fifth = dialog.findViewById(R.id.radio_fifth);
        RadioButton radio_six = dialog.findViewById(R.id.radio_six);

        img_close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button but_submit = dialog.findViewById(R.id.but_submit);
        but_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectedRadioButton = null;
                if (radio_group.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(context, "Please select report reason", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // get selected radio button from radioGroup
                    int selectedId = radio_group.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    selectedRadioButton = (RadioButton) dialog.findViewById(selectedId);
                    // Toast.makeText(context, selectedRadioButton.getText().toString() + " is selected", Toast.LENGTH_SHORT).show();
                }
                if (txt_report_subtitle.getText().toString().equals("")) {
                    txt_report_subtitle.setError("Please enter sub reason!");
                    return;
                }
                if (!txt_report_subtitle.getText().toString().equals("") &&
                        !selectedRadioButton.getText().toString().equals("")) {
                    callServicegetReport(txt_report_subtitle.getText().toString(),
                            selectedRadioButton.getText().toString(), newsId);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void shareFeed(int newsId, String title) {
        try {
            Utils.shareNews(context, newsId, title);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void callServicegetFollows(String folow, boolean isfollow) {
        rest.ShowDialogue(context.getResources().getString(R.string.pleaseWait));
        if (isfollow) {
            rest.UNfollowUser(folow);
        } else {
            rest.likeUser(folow);
        }
    }

    private void callServicegetReport(String folow, String isfollow, int newid) {
        rest.ShowDialogue(context.getResources().getString(R.string.pleaseWait));
        rest.reportUser(folow, isfollow, newid);
    }

    private void callAddViewCount(int adId) {
        // rest.ShowDialogue(context.getResources().getString(R.string.pleaseWait));
        if (rest != null)
            rest.callAddViewCount(adId);
    }

    private void callServicegetLike(int newid) {
        rest.ShowDialogue(context.getResources().getString(R.string.pleaseWait));
        rest.likeUser1(newid);
    }

    private void callServicegetDisLike(int newid) {
        rest.ShowDialogue(context.getResources().getString(R.string.pleaseWait));
        rest.DislikeUser(newid);
    }

    private void callServicegetSave(int newid) {
        rest.ShowDialogue(context.getResources().getString(R.string.pleaseWait));
        rest.SaveUser(newid);
    }

    private void callServicegetDeleteSave(int newid) {
        rest.ShowDialogue(context.getResources().getString(R.string.pleaseWait));
        rest.deleteSaveUser(newid);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull @NotNull ViewPagerAdapter.ViewHolder holder) {

        //   holder.videoView.getPlayer().stop();
        super.onViewDetachedFromWindow(holder);
        //  holder.videoView.getPlayer().stop();
        // Toast.makeText(context, "onViewDetachedFromWindow", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        //Toast.makeText(context, "onViewRecycled", Toast.LENGTH_SHORT).show();
        super.onViewRecycled(holder);
        try {
            holder.videoView.getPlayer().stop();
            holder.releasePlayer();
        } catch (NullPointerException ex) {
            Log.d("recerror", ex.toString());
        }
    }

    public HomeFragment.OnActivityStateChanged registerActivityState() {
        return new HomeFragment.OnActivityStateChanged() {
            public void onResumed() {
                Log.d("SimpleTextListAdapter", "onResumed: ");
                // Toast.makeText(context,"Resumed now",Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                // Toast.makeText(context, String.valueOf(simpleExoPlayer.getPlaybackState()), Toast.LENGTH_SHORT).show();
                // viewHolder.releasePlayer();
            }

            public void onPaused() {
                // Toast.makeText(context,"Paused now",Toast.LENGTH_SHORT).show();
                // viewHolder.videoView.getPlayer().stop();
                // viewHolder.releasePlayer();
                //  View view = getCurrentFocus();
                //  simpleExoPlayer.stop();
                //  Toast.makeText(context, "onPaused", Toast.LENGTH_SHORT).show();
                //  viewHolder.videoView.getPlayer().stop();
                //viewHolder.releasePlayer();
               // notifyDataSetChanged();
                if (viewHolder.simpleExoPlayer!=null)
                {
                    viewHolder.simpleExoPlayer.stop();
                }
                Log.d("SimpleTextListAdapter", "onPaused: ");
            }

            @Override
            public void onStop() {
                // Toast.makeText(context,"Stop now",Toast.LENGTH_SHORT).show();

                if (viewHolder!=null)
                viewHolder.releasePlayer();

            }
        };
    }

    public class VideoPlayerConfig {
        //Minimum Video you want to buffer while Playing
        public static final int MIN_BUFFER_DURATION = 1000;
        //Max Video you want to buffer during PlayBack
        public static final int MAX_BUFFER_DURATION = 2000;
        //Min Video you want to buffer before start Playing it
        public static final int MIN_PLAYBACK_START_BUFFER = 1000;
        //Min video You want to buffer when user resumes video
        public static final int MIN_PLAYBACK_RESUME_BUFFER = 1000;
    }

    private byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
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
                            try{
                                if (isValidContextForGlide(context)) {
                                    Glide.with(context)
                                            .load(hdStream)
                                            .into(imageView);
                                }
                            }catch (Exception e) {
                            }


                        }
                    });
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("error", throwable.getMessage());
                rest.dismissProgressdialog();

            }
        });
    }

    public static boolean isValidContextForGlide(final Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return false;
            }
        }
        return true;
    }

    private void startDownload(String data) {
        if (Utils.checkStatus(context, DownloadManager.STATUS_RUNNING)) {
            Toast.makeText(context, "downloading is still running . Please wait..", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "downloading is started..", Toast.LENGTH_LONG).show();
            Utils.startDownload(data, context);
        }
    }


}


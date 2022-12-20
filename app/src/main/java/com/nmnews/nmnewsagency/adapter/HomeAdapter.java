package com.nmnews.nmnewsagency.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.CommentsActivity;
import com.nmnews.nmnewsagency.activity.UserProfileActivity;
import com.nmnews.nmnewsagency.modelclass.AddNewsModel;
import com.nmnews.nmnewsagency.modelclass.GetNewsListModel;
import com.nmnews.nmnewsagency.modelclass.LikeModelClass;
import com.nmnews.nmnewsagency.modelclass.ReportModelClass;
import com.nmnews.nmnewsagency.modelclass.SaveModelClass;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.utils.Utils;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.SimpleExoPlayer;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements
        View.OnClickListener, Callback<Object> {
    // private List<LocationModel> moviesList;
    private List<GetNewsListModel.DataBean.PagedRecordBean> moviesList;
    Context context;
    private RecyclerView mRecyclerView;
    SimpleExoPlayer exoPlayer;
    Rest rest;
    int follow, report, coments, like, save;
    boolean show = true, isLike = false, isSave = false;
    int current_pos, total_duration;
    /*public LocationAdapter(List<LocationModel> moviesList) {
        this.moviesList = moviesList;
    }*/

    public HomeAdapter(Context context, List<GetNewsListModel.DataBean.PagedRecordBean> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_username, txt_description, txt_title, txt_views, txt_location, txt_datetime;
        public LinearLayout lin_report, lin_comments, lin_save, lin_share, lin_like,lin_tophome;
        LinearLayout rel_userprofile;
        public VideoView videoView;
      //  SimpleExoPlayerView exoPlayerView;
        TextView txt_follow, current, total, txt_noofcoment, txt_nooflike, txt_savetext;
        ImageButton exoPlay, exoPause;

        ImageView video_thuimbnail, img_playpause, img_like, img_save;
        CircleImageView image_profile;
        SeekBar seekbar;

        public MyViewHolder(View view) {
            super(view);
            //proxy = Application.getProxy(context);
            rel_userprofile = (LinearLayout) view.findViewById(R.id.rel_userprofile);
            lin_report = (LinearLayout) view.findViewById(R.id.lin_report);
            lin_like = (LinearLayout) view.findViewById(R.id.lin_like);
            lin_tophome = (LinearLayout) view.findViewById(R.id.lin_tophome);
            lin_share = (LinearLayout) view.findViewById(R.id.lin_share);
            lin_save = (LinearLayout) view.findViewById(R.id.lin_save);
            lin_comments = (LinearLayout) view.findViewById(R.id.lin_comments);
          //  videoView = (VideoView) view.findViewById(R.id.img_homeprofile);
            //exoPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.idExoPlayerVIew);
            exoPlay = (ImageButton) view.findViewById(R.id.exo_play);
            exoPause = (ImageButton) view.findViewById(R.id.exo_pause);
            txt_username = (TextView) view.findViewById(R.id.txt_username);
            txt_savetext = (TextView) view.findViewById(R.id.txt_savetext);
            txt_description = (TextView) view.findViewById(R.id.txt_description);
            txt_title = (TextView) view.findViewById(R.id.txt_title);
            txt_views = (TextView) view.findViewById(R.id.txt_views);
            txt_location = (TextView) view.findViewById(R.id.txt_location);
            txt_datetime = (TextView) view.findViewById(R.id.txt_datetime);
            txt_follow = (TextView) view.findViewById(R.id.txt_follow);
            video_thuimbnail = (ImageView) view.findViewById(R.id.video_thuimbnail);
            img_playpause = (ImageView) view.findViewById(R.id.img_playpause);
            img_save = (ImageView) view.findViewById(R.id.img_save);
            img_like = (ImageView) view.findViewById(R.id.img_like);
            image_profile = (CircleImageView) view.findViewById(R.id.image_profile);
            total = (TextView) view.findViewById(R.id.total);
            current = (TextView) view.findViewById(R.id.current);
            txt_nooflike = (TextView) view.findViewById(R.id.txt_nooflike);
            txt_noofcoment = (TextView) view.findViewById(R.id.txt_noofcoment);
            seekbar = (SeekBar) view.findViewById(R.id.seekbar);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        rest = new Rest(context, this);
        GetNewsListModel.DataBean.PagedRecordBean movie = moviesList.get(position);
        holder.txt_follow.setOnClickListener(this);
        holder.lin_report.setOnClickListener(this);
        holder.lin_comments.setOnClickListener(this);
        holder.lin_save.setOnClickListener(this);
        holder.lin_share.setOnClickListener(this);
        holder.rel_userprofile.setOnClickListener(this);
        holder.lin_like.setOnClickListener(this);
        holder.txt_noofcoment.setText(String.valueOf(movie.getCommentCount()));
        holder.txt_nooflike.setText(String.valueOf(movie.getLikesCount()));
        //   String date = movie.getAddedOn();
        //  String[] parts = date.split("T");
        holder.rel_userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos= (int) v.getTag();
                Intent intent1 = new Intent(context, UserProfileActivity.class);
                intent1.putExtra("userId",moviesList.get(pos).getUserId());
                context.startActivity(intent1);
            }
        });
        holder.txt_title.setText(movie.getTitle());
        holder.txt_datetime.setText(Utils.parseDateToddMMyyyy(movie.getAddedOn()));
        holder.txt_description.setText(movie.getDescription());
        holder.txt_location.setText(movie.getState_Name()+" "+movie.getCountry_Name());
        holder.txt_username.setText(movie.getUserName());
        holder.txt_views.setText(String.valueOf(movie.getViewCount()));
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
        holder.exoPause.setTag(position);
        holder.rel_userprofile.setTag(position);
        holder.exoPlay.setTag(position);
        holder.txt_follow.setTag(position);
        holder.lin_report.setTag(position);
        holder.lin_save.setTag(position);
        holder.lin_comments.setTag(position);
        holder.lin_like.setTag(position);
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
        holder.lin_like.setOnClickListener(new View.OnClickListener() {
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
                save=(int)v.getTag();
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
                coments= (int) v.getTag();
                Intent intent = new Intent(context, CommentsActivity.class);
                intent.putExtra("newsId", moviesList.get(coments).getNewsId());
                context.startActivity(intent);
            }
        });

        new Handler().post(new Runnable() {
            public void run() {

                // Picasso.with(getContext()).load(imagePath).into(imageView);
                // Picasso.with(getContext()).load(imagePath) .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(imageView);

                Glide.with(context)
                        .load(moviesList.get(position).getImageUrl())
                        .into(holder.video_thuimbnail);
            }
        });
        new Handler().post(new Runnable() {
            public void run() {
                Glide.with(context)
                        .load(moviesList.get(position).getAvatar())
                        .into(holder.image_profile);
            }
        });
        /*try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
            Uri videouri = Uri.parse(movie.getVideoUrl());
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
            holder.exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
            holder.exoPlayerView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    holder.exoPlayerView.getPlayer().stop();
                }
            });
        } catch (Exception e) {
            // below line is used for
            // handling our errors.
            Log.e("TAG", "Error : " + e.toString());
        }

*/
        Uri videouri = Uri.parse(movie.getVideoUrl());

        //

        // String proxyUrl = holder.proxy.getProxyUrl(movie.getVideoUrl());
        // holder.videoView.setVideoPath(proxyUrl);
        //  holder.videoView.requestFocus();
        //   holder.videoView.start();

        holder.videoView.setVideoURI(videouri);
        holder.videoView.requestFocus();
        holder.videoView.start();

        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                holder.videoView.start();

            }
        });
        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                setVideoProgress();
                mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        Log.d("", "onInfo, what = " + what);
                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                            // video started; hide the placeholder.
                            holder.video_thuimbnail.setVisibility(View.GONE);
                            total_duration = holder.videoView.getDuration();
                            holder.seekbar.setMax((int) total_duration);
                            holder.total.setText(Utils.timeConversion((long) total_duration));
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
        holder.videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show) {
                    holder.img_playpause.setVisibility(View.VISIBLE);
                    show = false;
                } else {
                    holder.img_playpause.setVisibility(View.GONE);
                    show = true;
                }
            }
        });
        holder.img_playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.videoView.isPlaying()) {
                    holder.videoView.pause();
                    holder.img_playpause.setImageResource(R.drawable.ic_play_circle_filled_black_30dp);
                } else {
                    holder.videoView.start();
                    holder.img_playpause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                }
            }
        });


        current_pos = holder.videoView.getCurrentPosition();
        // total_duration = holder.videoView.getDuration();

        //display video duration

        holder.current.setText(Utils.timeConversion((long) current_pos));

        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    current_pos = holder.videoView.getCurrentPosition();
                    holder.current.setText(Utils.timeConversion((long) current_pos));
                    //  Log.e("pos===================",String.valueOf((int)current_pos));
                    holder.seekbar.setProgress(current_pos);
                    handler.postDelayed(this, 1000);
                } catch (IllegalStateException ed) {
                    ed.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1000);

        //seekbar change listner
        holder.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                current_pos = seekBar.getProgress();
                holder.videoView.seekTo((int) current_pos);
            }
        });


    }


    @Override
    public int getItemCount() {
        return moviesList.size();
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

    public void shareFeed() {
       // Utils.shareNews(context,newsId);

    }


    @Override
    public void onAttachedToRecyclerView(@NonNull @NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_follow:
                //  callServicegetCityList();
                break;
            case R.id.lin_share:
                shareFeed();
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
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnnhome", String.valueOf(obj));
            if (obj instanceof AddNewsModel) {
                AddNewsModel loginModel = (AddNewsModel) obj;
                if (loginModel.isStatus()) {
                    if (moviesList.get(follow).isIsFollowed()) {
                        moviesList.get(follow).setIsFollowed(false);
                        Toast.makeText(context, " Successfully UnFollow", Toast.LENGTH_SHORT).show();
                    } else {
                        moviesList.get(follow).setIsFollowed(true);
                        Toast.makeText(context, " Successfully Follow", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            if (obj instanceof LikeModelClass) {
                LikeModelClass loginModel = (LikeModelClass) obj;
                if (loginModel.isStatus()) {
                    if (moviesList.get(like).isIsLike()) {
                        moviesList.get(like).setIsLike(false);
                        isLike = false;
                        Toast.makeText(context, "News successfully UnLike", Toast.LENGTH_SHORT).show();
                    } else {
                        moviesList.get(like).setIsLike(true);
                        isLike = true;
                        Toast.makeText(context, "News successfully Like", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            if (obj instanceof SaveModelClass) {
                SaveModelClass loginModel = (SaveModelClass) obj;
                if (loginModel.isStatus()) {
                    if (moviesList.get(save).isIsSaved()) {
                        moviesList.get(save).setIsSaved(false);
                        isSave = false;
                        Toast.makeText(context, "News successfully UnSave", Toast.LENGTH_SHORT).show();
                    } else {
                        moviesList.get(save).setIsSaved(true);
                        isSave = true;
                        Toast.makeText(context, "News successfully Save", Toast.LENGTH_SHORT).show();
                    }
                    // notifyDataSetChanged();
                }
            }
            if (obj instanceof ReportModelClass) {
                ReportModelClass loginModel = (ReportModelClass) obj;
                if (loginModel.isStatus()) {
                    //Toast.makeText(context, "Report successfully added", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull @NotNull HomeAdapter.MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.videoView.pause();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull @NotNull HomeAdapter.MyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.videoView.start();
        holder.videoView.seekTo(0);
    }


    public void setVideoProgress() {
        //get the video duration

    }


}


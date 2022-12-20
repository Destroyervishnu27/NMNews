package com.nmnews.nmnewsagency.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.chat.ChatHelper;
import com.nmnews.nmnewsagency.modelclass.DeleteNewsByIdModel;
import com.nmnews.nmnewsagency.modelclass.GetNewsByIdModel;
import com.nmnews.nmnewsagency.modelclass.LikeModelClass;
import com.nmnews.nmnewsagency.modelclass.ReportModelClass;
import com.nmnews.nmnewsagency.modelclass.SaveModelClass;
import com.nmnews.nmnewsagency.modelclass.UploadNewsModel;
import com.nmnews.nmnewsagency.pref.SharedPrefsHelper;
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
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.quickblox.chat.QBRestChatService;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBDialogCustomData;
import com.quickblox.chat.model.QBDialogType;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.model.QBUser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnVideoDetailActivity extends AppCompatActivity implements Callback<Object> {
    RecyclerView recyclerView;
   LinearLayout lin_toplinb;
    List<GetNewsByIdModel.DataBean> arrayList;
    int newsId;
    String whichOne, whichType;
    Rest rest;
    public TextView title, txt_own_name, txt_own_subname, current, total, txt_owndescription, txt_ownviews, txt_ownlocation, txt_ownviewsqq,
            txt_owndatetime, txt_ownlike, txt_owncoments, txt_own_dddname;
    public LinearLayout lin_chat_qwnvideo, lin_create, lin_own_share, lin_own_comnt;
    RelativeLayout rel_ownvideo_profile;
    ImageView img_delete, image_own_profile, video_thuimbnail, img_playpause, img_ownlike;
    VideoView videoView;
    SeekBar seekbar;
    int current_pos;
    int total_duration;
    boolean show = true, isLike = false, isSave = false;
    LinearLayout lin_ownvideo_profile;
    PlayerView idExoPlayerVIew;
    SimpleExoPlayer exoPlayer = null;
    ProgressDialog pd;
    QBUser currentUser;
    private static final int REQUEST_DIALOG_ID_FOR_UPDATE = 165;


    private ImageView img_userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.item_ownvideodetail);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            newsId = (int) bundle.get("newsid");
            whichOne = (String) bundle.get("self");
            if (whichOne.equals("self")) {
                whichType = (String) bundle.get("type");
            }
        }
        rest = new Rest(this, this);
        if (!ChatHelper.getInstance().isLogged()) {
            reloginToChat();
        }
        if (ChatHelper.getCurrentUser() != null) {
            currentUser = ChatHelper.getCurrentUser();
            Log.e("currentuser======", currentUser.toString());
        } else {
            // Log.e(TAG, "Finishing " + TAG + ". Current user is null");
            // finish();
        }
        inIt();
    }

    private void inIt() {
        lin_chat_qwnvideo = (LinearLayout) findViewById(R.id.lin_chat_qwnvideo);

        lin_ownvideo_profile = (LinearLayout) findViewById(R.id.lin_ownvideo_profile);
        lin_toplinb = (LinearLayout) findViewById(R.id.lin_toplinb);
        lin_create = (LinearLayout) findViewById(R.id.lin_create);
        lin_own_share = (LinearLayout) findViewById(R.id.lin_own_share);
        lin_own_comnt = (LinearLayout) findViewById(R.id.lin_own_comnt);
        rel_ownvideo_profile = (RelativeLayout) findViewById(R.id.rel_ownvideo_profile);
        img_delete = (ImageView) findViewById(R.id.img_delete);
        image_own_profile = (ImageView) findViewById(R.id.image_own_profile);
        img_ownlike = (ImageView) findViewById(R.id.img_ownlike);
        video_thuimbnail = (ImageView) findViewById(R.id.video_thuimbnail);
        img_playpause = (ImageView) findViewById(R.id.img_playpause);
        txt_own_name = (TextView) findViewById(R.id.txt_own_name);
        txt_own_dddname = (TextView) findViewById(R.id.txt_own_dddname);
        txt_owncoments = (TextView) findViewById(R.id.txt_owncoments);
        current = (TextView) findViewById(R.id.current);
        txt_ownviewsqq = (TextView) findViewById(R.id.txt_ownviewsqq);
        txt_owndatetime = (TextView) findViewById(R.id.txt_owndatetime);
        txt_ownlike = (TextView) findViewById(R.id.txt_ownlike);
        txt_ownlocation = (TextView) findViewById(R.id.txt_ownlocation);
        txt_owndescription = (TextView) findViewById(R.id.txt_owndescription);
        img_userType = (ImageView) findViewById(R.id.img_userType);
        total = (TextView) findViewById(R.id.total);
        //videoView = (VideoView) findViewById(R.id.vid_own);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        if (whichOne.equals("self")) {
            if (whichType.equals("hash")) {
                img_delete.setVisibility(View.GONE);
            }
        }
        lin_chat_qwnvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextACtivityzzforChat();
            }
        });
        lin_ownvideo_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnVideoDetailActivity.this, UserProfileActivity.class);
                intent.putExtra("userId", arrayList.get(0).getUserId());
                startActivity(intent);
            }
        });
       /* rel_ownvideo_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnVideoDetailActivity.this, UserOwnDegtailProfileActivity.class);
                startActivity(intent);
            }
        });*/
        lin_own_comnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnVideoDetailActivity.this, CommentsActivity.class);
                intent.putExtra("newsId", arrayList.get(0).getNewsId());
                startActivity(intent);
            }
        });
        lin_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogBoxCreate();
            }
        });

        lin_own_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFeed();
            }
        });
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogBoxDelete();
            }
        });
        img_ownlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLike) {
                    int no = Integer.parseInt(txt_ownlike.getText().toString());
                    if (no > 0) {
                        int set = no - 1;
                        txt_ownlike.setText(String.valueOf(set));
                        arrayList.get(0).setLikesCount(Integer.parseInt(txt_ownlike.getText().toString()));
                    }
                    isLike = false;
                    img_ownlike.setImageResource(R.drawable.ic_like);
                    callServicegetDisLike(arrayList.get(0).getNewsId());

                } else {
                    int no = Integer.parseInt(txt_ownlike.getText().toString());

                    txt_ownlike.setText(String.valueOf(no + 1));
                    arrayList.get(0).setLikesCount(Integer.parseInt(txt_ownlike.getText().toString()));
                    img_ownlike.setImageResource(R.drawable.ic_dislike);
                    isLike = true;
                    callServicegetLike(arrayList.get(0).getNewsId());
                }
            }
        });

        txt_ownlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnVideoDetailActivity.this, LikesActivity.class);
                intent.putExtra("newsId", arrayList.get(0).getNewsId());
                startActivity(intent);
            }
        });

        callserviceaGetNewsByID();
    }

    private void callserviceaGetNewsByID() {
        rest.ShowDialogue("Please Wait ..");
        rest.getNewsById(newsId);
    }

    private void callServicegetLike(int newid) {
       // Toast.makeText(OwnVideoDetailActivity.this, "like", Toast.LENGTH_SHORT).show();
        rest.ShowDialogue("Please Wait ..");
        rest.likeUser1(newid);
    }

    private void callServicegetDisLike(int newid) {
       // Toast.makeText(OwnVideoDetailActivity.this, "dislike", Toast.LENGTH_SHORT).show();
       rest.ShowDialogue("Please Wait ..");
        rest.DislikeUser(newid);
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof GetNewsByIdModel) {
                GetNewsByIdModel loginModel = (GetNewsByIdModel) obj;
                if (loginModel.isStatus()) {
                    arrayList = loginModel.getData();
                    if (arrayList==null || arrayList.size()==0)
                    {
                        Toast.makeText(OwnVideoDetailActivity.this, "This news is Deleted by Admin", Toast.LENGTH_SHORT).show();

                        finish();
                    }
                    setDataOnViws();
                }
            }
            if (obj instanceof ReportModelClass) {
                ReportModelClass loginModel = (ReportModelClass) obj;
                if (loginModel.isStatus()) {
                   // Toast.makeText(OwnVideoDetailActivity.this, "Report successfully added", Toast.LENGTH_SHORT).show();
                    Utils.showSnakBarDialog(this,lin_toplinb,"Report successfully added",R.color.msgresponce);
                }
            }
            if (obj instanceof DeleteNewsByIdModel) {
                DeleteNewsByIdModel loginModel = (DeleteNewsByIdModel) obj;
                if (loginModel.isStatus()) {
                     Toast.makeText(OwnVideoDetailActivity.this, "News Deleted successfully ", Toast.LENGTH_SHORT).show();
                    OwnVideoDetailActivity.this.finish();
                }
            }
            if (obj instanceof SaveModelClass) {
                SaveModelClass loginModel = (SaveModelClass) obj;
                if (loginModel.isStatus()) {
                    Toast.makeText(OwnVideoDetailActivity.this, "News Unsave successfully ", Toast.LENGTH_SHORT).show();
                    OwnVideoDetailActivity.this.finish();
                    // notifyDataSetChanged();
                }
            }
            if (obj instanceof LikeModelClass) {
                LikeModelClass loginModel = (LikeModelClass) obj;
                if (loginModel.isStatus()) {
                    if(isLike) {
                        Utils.showSnakBarDialog(this, lin_toplinb, "News Like successfully ", R.color.msgresponce);
                    }else{
                        Utils.showSnakBarDialog(this, lin_toplinb, "News UnLike successfully ", R.color.msgresponce);
                    }
                }
            }
        }
    }

    private void setDataOnViws() {
        if(arrayList.size()>0) {
            if (arrayList.get(0).getUserType() != null && arrayList.get(0).getUserType().toString().equalsIgnoreCase("REPORTER")) {
                img_userType.setBackground(getResources().getDrawable(R.drawable.ic_group_7756));
            } else
            {
                img_userType.setBackground(getResources().getDrawable(R.drawable.ic_check));

            }

            if (arrayList.get(0).isIsLike()) {
                img_ownlike.setImageResource(R.drawable.ic_dislike);
                isLike = true;
            } else {
                img_ownlike.setImageResource(R.drawable.ic_like);
                isLike = false;
            }
            new Handler().post(new Runnable() {
                public void run() {
                    Glide.with(OwnVideoDetailActivity.this)
                            .load(arrayList.get(0).getImageUrl())
                            .into(video_thuimbnail);
                }
            });
            new Handler().post(new Runnable() {
                public void run() {
                    Glide.with(OwnVideoDetailActivity.this)
                            .load(arrayList.get(0).getAvatar())
                            .into(image_own_profile);
                }
            });
            txt_own_name.setText(arrayList.get(0).getUserName());
            txt_own_dddname.setText(""+arrayList.get(0).getAboutMe());
            txt_owndescription.setText(arrayList.get(0).getTitle());
            txt_ownlocation.setText(arrayList.get(0).getState_Name() + " " + arrayList.get(0).getCountry_Name());
            txt_ownviewsqq.setText(String.valueOf(arrayList.get(0).getViewCountSuffix()));
            txt_owndatetime.setText(Utils.parseDateToddMMyyyy(arrayList.get(0).getAddedOn()));
            txt_ownlike.setText(String.valueOf(arrayList.get(0).getLikesCountSuffix()));
            txt_owncoments.setText(String.valueOf(arrayList.get(0).getCommentCountSuffix()));

            Uri videouri = Uri.parse(arrayList.get(0).getVideoUrl());
            setUrlOnSimpleExoPlayer();
       /* videoView.setVideoURI(videouri);
        videoView.requestFocus();
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                videoView.start();

            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        Log.d("", "onInfo, what = " + what);
                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                            // video started; hide the placeholder.
                            video_thuimbnail.setVisibility(View.GONE);
                            total_duration = videoView.getDuration();
                            seekbar.setMax((int) total_duration);
                            total.setText(Utils.timeConversion((long) total_duration));
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show) {
                    img_playpause.setVisibility(View.VISIBLE);
                    show = false;
                } else {
                    img_playpause.setVisibility(View.GONE);
                    show = true;
                }
            }
        });*/
            img_playpause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (videoView.isPlaying()) {
                        videoView.pause();
                        img_playpause.setImageResource(R.drawable.ic_play_circle_filled_black_30dp);
                    } else {
                        videoView.start();
                        img_playpause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                    }
                }
            });


      /*  current_pos = videoView.getCurrentPosition();

        //display video duration
        current.setText(Utils.timeConversion((long) current_pos));
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    current_pos = videoView.getCurrentPosition();
                    current.setText(Utils.timeConversion((long) current_pos));
                    //  Log.e("pos===================",String.valueOf((int)current_pos));
                    seekbar.setProgress(current_pos);
                    handler.postDelayed(this, 1000);
                } catch (IllegalStateException ed) {
                    ed.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1000);
        //seekbar change listner
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                current_pos = seekBar.getProgress();
                videoView.seekTo((int) current_pos);
            }
        });*/
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }

    private void openDialogBoxCreate() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_createvideo);

        bottomSheetDialog.show();
    }

    private void openDialogBoxDelete() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_delete);
        TextView txt_delete_report = (TextView) bottomSheetDialog.findViewById(R.id.txt_delete_report);
        if (!whichOne.equals("")) {
            if (whichOne.equals("self")) {
                txt_delete_report.setText("Delete");
            } else {
                txt_delete_report.setText("Report");
            }
        }
        txt_delete_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whichOne.equals("self")) {
                    if (whichType.equals("own")) {
                        callserviceDeleteNewsById();
                    } else if (whichType.equals("save")) {
                        callServicegetDeleteSave(arrayList.get(0).getNewsId());
                    }
                } else {
                    openDialogBoxReport();
                }
                bottomSheetDialog.cancel();
            }
        });
        TextView txt_cancel = (TextView) bottomSheetDialog.findViewById(R.id.txt_cancel);
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    private void callserviceDeleteNewsById() {
        rest.ShowDialogue("Please Wait ..");
        rest.getDeleteNewsById(arrayList.get(0).getNewsId());
    }

    private void callServicegetDeleteSave(int newid) {
        rest.ShowDialogue("Please Wait ..");
        rest.deleteSaveUser(newid);
    }

    public void shareFeed() {
        Utils.shareNews(OwnVideoDetailActivity.this,newsId,arrayList.get(0).getTitle());

    }

    private void openDialogBoxReport() {
        Dialog dialog = new Dialog(OwnVideoDetailActivity.this);
        dialog.setContentView(R.layout.dialog_report);
        ImageView img_close_dialog = dialog.findViewById(R.id.img_close_dialog);
        TextView txt_report_subtitle = dialog.findViewById(R.id.txt_report_subtitle);
        RelativeLayout rel_dialogtop = dialog.findViewById(R.id.rel_dialogtop);
        RadioGroup radio_group = dialog.findViewById(R.id.radio_group);
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
                   // Toast.makeText(OwnVideoDetailActivity.this, "Please select report reason", Toast.LENGTH_SHORT).show();
                    Utils.showSnakBarDialog(OwnVideoDetailActivity.this,rel_dialogtop,"Please select report reason",R.color.alert);
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
                    //delete
                    callServicegetReport(txt_report_subtitle.getText().toString(),
                            selectedRadioButton.getText().toString(), arrayList.get(0).getNewsId());
                }
                dialog.dismiss();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private void callServicegetReport(String folow, String isfollow, int newid) {
        rest.ShowDialogue("Please Wait ..");
        rest.reportUser(folow, isfollow, newid);
    }

    public void setUrlOnSimpleExoPlayer() {
        idExoPlayerVIew = (PlayerView) findViewById(R.id.idExoPlayerVIew);
        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl.Builder()
                    .setAllocator(new DefaultAllocator(true, 10))
                    .setBufferDurationsMs(VideoPlayerConfig.MIN_BUFFER_DURATION,
                            VideoPlayerConfig.MAX_BUFFER_DURATION,
                            VideoPlayerConfig.MIN_PLAYBACK_START_BUFFER,
                            VideoPlayerConfig.MIN_PLAYBACK_RESUME_BUFFER)
                    .setTargetBufferBytes(-1)
                    .setPrioritizeTimeOverSizeThresholds(true).createDefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(OwnVideoDetailActivity.this, trackSelector, loadControl);
            Uri videouri = Uri.parse(arrayList.get(0).getVideoUrl());
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
            idExoPlayerVIew.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
            idExoPlayerVIew.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    // idExoPlayerVIew.getPlayer().stop();
                }
            });
        } catch (Exception e) {
            // below line is used for
            // handling our errors.
            Log.e("TAG", "Error : " + e.toString());
        }
        SimpleExoPlayer finalExoPlayer = exoPlayer;
        exoPlayer.addListener(new Player.EventListener() {

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
                    idExoPlayerVIew.setControllerShowTimeoutMs(1500);

                } else if (playbackState == ExoPlayer.STATE_BUFFERING) {
                    // Toast.makeText(context, "STATE_BUFFERING", Toast.LENGTH_SHORT).show();
                    //  video_thuimbnail.setVisibility(View.VISIBLE);
                    // exo_pause.setVisibility(View.GONE);
                    idExoPlayerVIew.setControllerShowTimeoutMs(3000);

                } else if (playbackState == ExoPlayer.STATE_ENDED) {
                    // Toast.makeText(context, "STATE_ENDED", Toast.LENGTH_SHORT).show();
                    finalExoPlayer.seekTo(0);
                    idExoPlayerVIew.setControllerShowTimeoutMs(1);
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


    }

    public void releasePlayer() {
        if (exoPlayer != null) {
            //  Toast.makeText(context, "releasePlayer", Toast.LENGTH_SHORT).show();
            exoPlayer.setPlayWhenReady(true);
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;

            idExoPlayerVIew.setPlayer(null);
            idExoPlayerVIew = null;
            System.out.println("releasePlayer");
            Log.e("releasePlayer", "releasePlayer");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(EventBus.getDefault().isRegistered(this)){}
        else{
            EventBus.getDefault().register(this);
        }
        setUrlOnSimpleExoPlayer();
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

    private void reloginToChat() {
        // showProgressDialog(R.string.dlg_relogin);
        pd = new ProgressDialog(this);
        pd.setMessage("Please Wait ...");
        //pd.show();
        if (SharedPrefsHelper.getInstance().hasQbUser()) {
            ChatHelper.getInstance().loginToChat(SharedPrefsHelper.getInstance().getQbUser(), new QBEntityCallback<Void>() {
                @Override
                public void onSuccess(Void aVoid, Bundle bundle) {
                    if (pd!=null && pd.isShowing())
                    pd.dismiss();
                }

                @Override
                public void onError(QBResponseException e) {
                    Log.e("error", "Relogin Failed " + e.getMessage());
                    //  hideProgressDialog();
                    if (pd!=null && pd.isShowing())
                        pd.dismiss();
                }
            });
        }
    }

    public void nextACtivityzzforChat() {
        pd = new ProgressDialog(this);
        pd.setMessage("Please Wait ...");
        pd.setCancelable(false);
        //pd.show();
        ArrayList<Integer> occupantIdsList = new ArrayList<Integer>();
        if (arrayList.get(0).getChatId() != null) {
            occupantIdsList.add(Integer.parseInt(arrayList.get(0).getChatId()));
        }
        QBDialogCustomData customData = new QBDialogCustomData();
        customData.putString("customString", "Value");
        // jsonObject.putString("userid",arrayList.get(0).getUserId());
        //jsonObject.putString("usertag",arrayList.get(0).getUserName());
        QBChatDialog dialog = new QBChatDialog();

        dialog.setPhoto(arrayList.get(0).getAvatar());
        dialog.setType(QBDialogType.PRIVATE);
        dialog.setOccupantsIds(occupantIdsList);
        dialog.setCustomData(customData);
        dialog.setName("Value");

        // or just use DialogUtils
        //QBChatDialog dialog = DialogUtils.buildPrivateDialog(recipientId);

        QBRestChatService.createChatDialog(dialog).performAsync(new QBEntityCallback<QBChatDialog>() {
            @Override
            public void onSuccess(QBChatDialog result, Bundle params) {
                pd.dismiss();
                Log.e("createChatDialog======", result.toString());
                ChatActivity.startForResult(OwnVideoDetailActivity.this, REQUEST_DIALOG_ID_FOR_UPDATE, result);
            }

            @Override
            public void onError(QBResponseException responseException) {
                if (pd!=null && pd.isShowing())
                    pd.dismiss();
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResultReceived(UploadNewsModel.DataBean dataBean) {
        Intent intent = new Intent(OwnVideoDetailActivity.this, NewVideoActivity.class);
        intent.putExtra("video", dataBean);
        startActivity(intent);
        // finish();
    }


    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}
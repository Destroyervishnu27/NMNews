package com.nmnews.nmnewsagency.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.nmnews.nmnewsagency.R;

public class RecordingPreviewActivity extends AppCompatActivity {
    VideoView vid_view;
    ImageView iamge_back_video_preview;
    String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_preview);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            videoPath = (String) bundle.get("videopath");
        }
        inIt();

    }

    private void inIt() {
        vid_view = (VideoView) findViewById(R.id.vid_view);
        iamge_back_video_preview = (ImageView) findViewById(R.id.iamge_back_video_preview);
        vid_view.setVideoPath(videoPath);
        vid_view.start();
        iamge_back_video_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        vid_view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                vid_view.start();

            }
        });
    }
}
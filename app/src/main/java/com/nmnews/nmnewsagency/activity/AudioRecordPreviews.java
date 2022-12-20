package com.nmnews.nmnewsagency.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.modelclass.UploadNewsModel;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;
import com.nmnews.nmnewsagency.service.NewsUplaodInBagroundService;
import com.nmnews.nmnewsagency.utils.Utils;
import com.github.piasy.rxandroidaudio.AudioRecorder;
import com.simform.videooperations.CallBackOfQuery;
import com.simform.videooperations.FFmpegCallBack;
import com.simform.videooperations.FFmpegQueryExtension;
import com.simform.videooperations.LogMessage;
import com.simform.videooperations.Statistics;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

import me.tankery.lib.circularseekbar.CircularSeekBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vimeoextractor.OnVimeoExtractionListener;
import vimeoextractor.VimeoExtractor;
import vimeoextractor.VimeoVideo;

public class AudioRecordPreviews extends AppCompatActivity implements Callback<Object>,
        FFmpegCallBack {
    Button button_audiocapture, button_save;
    ImageView img_thumbnail, iamge_back_audio_record;
    AudioRecorder mAudioRecorder;
    File mAudioFile;
    CircularSeekBar circularSeekBar;
    VideoView vidview_audiorecord;
    boolean startRecord = true;
    String fileofaudio, thumbanilVimeo;
    CountDownTimer countDownTimer;
    int duration;
    TextView txt_timeer;
    //private UploadNewsModel.DataBean dataBean = null;
    ProgressDialog dialog;
    File outputFile, muxingVideopath;
    LinearLayout lin_tapheare_audio;

    SeekBar seekbar;
    int current_pos;
    boolean muxing = false;
    Rest rest;
    RelativeLayout rel_audiotop;

    FFmpegQueryExtension fFmpegQueryExtension;
    CallBackOfQuery callBackOfQuery;
    FFmpegCallBack fFmpegCallBack;
    ProgressDialog progressDialog;
    private String videoUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            videoUrl = bundle.getString("videoUrl");
            Log.e("Video Url - ", videoUrl);

        }
        fFmpegQueryExtension = new FFmpegQueryExtension();
        callBackOfQuery = new CallBackOfQuery();
        fFmpegCallBack = this;
        iniIt();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void iniIt() {
        rest = new Rest(this, this);
        loadFFMpegBinary();
        File folder = getCacheDir();
        muxingVideopath = new File(folder, System.currentTimeMillis() + ".mp4");
        /*muxingVideopath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + System.nanoTime() + "muxing_video.mp4");*/
        circularSeekBar = findViewById(R.id.seek_bar);
        rel_audiotop = findViewById(R.id.rel_audiotop);
        mAudioRecorder = AudioRecorder.getInstance();
        fileofaudio = Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + System.nanoTime() + "edit.mp4";
        /*fileofVideo = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + System.nanoTime() + "editaac.mp4");*/
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        button_audiocapture = findViewById(R.id.button_audiocapture);
        txt_timeer = findViewById(R.id.txt_timeer);
        img_thumbnail = findViewById(R.id.img_thumbnail);
        lin_tapheare_audio = findViewById(R.id.lin_tapheare_audio);
        button_save = findViewById(R.id.button_save);
        iamge_back_audio_record = findViewById(R.id.iamge_back_audio_record);
        vidview_audiorecord = findViewById(R.id.vidview_audiorecord);
        vidview_audiorecord.setVideoPath(Prefrence.getVideoFile());
        vidview_audiorecord.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // mp.setVolume(0f, 0f);
                // mp.setLooping(true);
                if (!muxing) {
                    mp.setVolume(0f, 0f);
                }
            }
        });
        vidview_audiorecord.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                if (muxing) {
                    vidview_audiorecord.start();
                }
                // vidview_audiorecord.start();

            }
        });
        iamge_back_audio_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(muxing){
                    iniIt();
                }
                else {*/
                AudioRecordPreviews.this.finish();
                //  }
            }
        });
        circularSeekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                String message = String.format("Progress changed to %.2f, fromUser %s", progress, fromUser);
                //  Log.e("Main", message);
                //  Log.e("progress", String.valueOf(progress));
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {
                Log.d("Main", "onStopTrackingTouch");
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {
                Log.d("Main", "onStartTrackingTouch");
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callServiceUploadNews(muxingVideopath.getAbsolutePath());
            }
        });
        getDuration();
        circularSeekBar.setMax(duration);
        seekbar.setMax((int) duration);
        Log.e("videoduration", getDuration());
        txt_timeer.setText(getDuration());

        Glide.with(this)
                .load(Uri.fromFile(new File(videoUrl)))
                .into(img_thumbnail);

        button_audiocapture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Button is pressed
                        if (startRecord) {
                            // Toast.makeText(AudioRecordPreviews.this, "audiostart", Toast.LENGTH_SHORT).show();
                            lin_tapheare_audio.setVisibility(View.GONE);
                            vidview_audiorecord.setVisibility(View.VISIBLE);
                            // txt_timeer.setVisibility(View.VISIBLE);
                            img_thumbnail.setVisibility(View.GONE);
                            showtimerAudioRecording();
                            vidview_audiorecord.start();
                            circularSeekBar.setVisibility(View.VISIBLE);
                            File folder = getCacheDir();
                            mAudioFile = new File(folder, System.currentTimeMillis() + ".m4a");
                            /*mAudioFile = new File(
                                    Environment.getExternalStorageDirectory().getAbsolutePath() +
                                            File.separator + System.nanoTime() + ".m4a");*/
                            mAudioRecorder.prepareRecord(MediaRecorder.AudioSource.MIC,
                                    MediaRecorder.OutputFormat.DEFAULT, MediaRecorder.AudioEncoder.AAC,
                                    mAudioFile);
                            mAudioRecorder.startRecord();
                            startRecord = false;
                        } else {

                        }
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        // Button is not pressed
                        countDownTimer.cancel();
                        txt_timeer.setVisibility(View.GONE);
                        img_thumbnail.setVisibility(View.VISIBLE);
                        circularSeekBar.setVisibility(View.GONE);
                        lin_tapheare_audio.setVisibility(View.VISIBLE);
                        // stop recording and release
                        Log.e("circlevalue===", String.valueOf(circularSeekBar.getMax()));
                        // Toast.makeText(AudioRecordPreviews.this, String.valueOf(circularSeekBar.getMax()), Toast.LENGTH_SHORT).show();
                        vidview_audiorecord.stopPlayback();
                        vidview_audiorecord.setVisibility(View.GONE);
                        mAudioRecorder.stopRecord();
                        startRecord = true;
                        if (!muxing) {
                            Utils.showSnakBarDialog(AudioRecordPreviews.this, rel_audiotop,
                                    "Your audio duration is very short. Please record audio of " + getDuration() + " duration",
                                    R.color.alert);
                        }
                    }
                }
                return false;

            }

        });
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    current_pos = vidview_audiorecord.getCurrentPosition();
                    //  Log.e("pos===================",String.valueOf((int)current_pos));
                    seekbar.setProgress(current_pos);
                    handler.postDelayed(this, 1000);
                } catch (IllegalStateException ed) {
                    ed.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1000);

        vidview_audiorecord.setVisibility(View.GONE);
        img_thumbnail.setVisibility(View.VISIBLE);
        button_audiocapture.setVisibility(View.VISIBLE);
        lin_tapheare_audio.setVisibility(View.VISIBLE);
        button_save.setVisibility(View.GONE);
    }


    public void nextactivityGoing() {

        Intent intent = new Intent(AudioRecordPreviews.this, RecordingPreviewActivity.class);
        intent.putExtra("videopath", outputFile.getAbsolutePath());
        startActivity(intent);

    }

    private void callServiceUploadNews(String path) {
        if (rest.isInterentAvaliable()) {
            Prefrence.setVideoFIle(path);
            Intent intent=new Intent();
            intent.putExtra("video",path);
            setResult(RESULT_OK,intent);
            finish();
        } else {
            rest.AlertForInternet();
        }

    }

    public void setProgressSet() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.setMessage("Please Wait for a sec ..");
        dialog.setProgress(0);
        dialog.setMax(100);
        dialog.show();
        final int totalProgressTime = 95;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        sleep(2000);
                        jumpTime += 2;
                        dialog.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {

            Object obj = response.body();
            if (obj instanceof UploadNewsModel) {
                UploadNewsModel loginModel = (UploadNewsModel) obj;
                if (loginModel.isStatus()) {
                    dialog.setProgress(100);
                    dialog.dismiss();
                    nextActivityGoing(loginModel.getData());
                }
            }

        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();
    }

    private void nextActivityGoing(UploadNewsModel.DataBean data) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("video", data);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    @SuppressLint("DefaultLocale")
    public String getDuration() {
        MediaPlayer mp = MediaPlayer.create(this, Uri.parse(Prefrence.getVideoFile()));
        duration = mp.getDuration();
        mp.release();
        /*convert millis to appropriate time*/
        return String.format("0%d:%d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))

        );
        // return String.valueOf(duration);
    }

    public void showtimerAudioRecording() {
        // button_audiocapture.setVisibility(View.GONE);
        // audio_stop.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(duration, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                txt_timeer.setText(f.format(min) + ":" + f.format(sec));
                circularSeekBar.setProgress(millisUntilFinished);
            }

            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                // txt_timer_done.setVisibility(View.VISIBLE);
                txt_timeer.setVisibility(View.GONE);
                //  button_audiocapture.setVisibility(View.VISIBLE);
                // audio_stop.setVisibility(View.GONE);

                // stop recording and release
                //  Toast.makeText(AudioRecordPreviews.this, "audioend", Toast.LENGTH_SHORT).show();
                vidview_audiorecord.stopPlayback();
                vidview_audiorecord.setVisibility(View.GONE);
                img_thumbnail.setVisibility(View.VISIBLE);
                circularSeekBar.setVisibility(View.GONE);
                mAudioRecorder.stopRecord();
                startRecord = true;
                muxing = true;
                //  boolean statusEdigt=mux(Prefrence.getVideoFile(), mAudioFile.getAbsolutePath(), outputPaTH);
                //  Toast.makeText(AudioRecordActivity.this, String.valueOf(statusEdigt), Toast.LENGTH_SHORT).show();
                // mergingAUdioVideo();
                execFFmpegBinaryShortest();
            }
        }.start();
    }

    //setimage on imageview

     /* try {

        AACTrackImpl aacTrack = new AACTrackImpl(new FileDataSourceImpl(mAudioFile.toString()));
            H264TrackImpl h264Track = new H264TrackImpl(new FileDataSourceImpl(Prefrence.getVideoFile()));
        Movie movie = new Movie();
        movie.addTrack(h264Track);
        movie.addTrack(aacTrack);

            Container mp4file = new DefaultMp4Builder().build(movie);
            FileChannel fc = new FileOutputStream(new File(Environment.getExternalStorageDirectory().toString() + "/video.mp4")).getChannel();
            mp4file.writeContainer(fc);
            fc.close();
        } catch (IOException e) {
            Log.d("", "Mixer Error 1 " + e.getMessage());
        }*/


    private void execFFmpegBinaryShortest() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        //  dialog.setMessage("Proccesing ..");
        outputFile = new File(Environment.getExternalStorageDirectory().
                getAbsolutePath() + File.separator + System.nanoTime() + "ffmpegmergingvideo.mp4");

        String[] cmd = new String[]{"-i", Prefrence.getVideoFile(), "-i", mAudioFile.getAbsolutePath(),
                "-filter_complex", "[0:a][1:a]amerge=inputs=2[a]", "-map", "0:v", "-map", "[a]",
                "-c:v", "copy", "-ac", "2",
                muxingVideopath.getAbsolutePath()};
        String cmdPath = "ffmpeg -i " + Prefrence.getVideoFile() + " -i " + mAudioFile.getAbsolutePath() + " -c:v copy -c:a aac " + muxingVideopath.getAbsolutePath();
        executeFfmpegCommand(Prefrence.getVideoFile(), mAudioFile.getAbsolutePath(), muxingVideopath.getAbsolutePath());
       /* ffmpeg.execute(cmd, new ExecuteBinaryResponseHandler() {
            @Override
            public void onFailure(String s) {
                System.out.println("on failure----" + s);
            }

            @Override
            public void onSuccess(String s) {
                System.out.println("on success-----" + s);
                Log.e("succesffmpeg", "SUCCESS with output : " + s);
               // nextactivityGoing();
                afterMuxingVideoShow();
            }

            @Override
            public void onProgress(String s) {
                //Log.d(TAG, "Started command : ffmpeg "+command);
                System.out.println("Started---" + s);
                dialog.setMessage("Please Wait ...");
            }

            @Override
            public void onStart() {
               // dialog.setMessage("Start...");
                dialog.show();
                //Log.d(TAG, "Started command : ffmpeg " + command);
                System.out.println("Start----");

            }

            @Override
            public void onFinish() {
                System.out.println("Finish-----");
                dialog.dismiss();

            }
        });*/


    }

    private void loadFFMpegBinary() {
       /* try {
            if (FFprobe.getInstance(this).isSupported()) {
                ffmpeg = FFprobe.getInstance(this);
            } else {
                showUnsupportedExceptionDialog();
            }

        } catch (Exception e) {
            Log.e("ffmpegcatchexception", "EXception no controlada : " + e);
        }*/
    }

    private void showUnsupportedExceptionDialog() {
        new AlertDialog.Builder(AudioRecordPreviews.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Not Supported")
                .setMessage("Device Not Supported")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AudioRecordPreviews.this.finish();
                    }
                })
                .create()
                .show();

    }

    public void afterMuxingVideoShow() {

        vidview_audiorecord.setVisibility(View.VISIBLE);
        img_thumbnail.setVisibility(View.GONE);
        button_audiocapture.setVisibility(View.GONE);
        lin_tapheare_audio.setVisibility(View.GONE);
        button_save.setVisibility(View.VISIBLE);
        vidview_audiorecord.setVideoPath(muxingVideopath.getAbsolutePath());
        vidview_audiorecord.start();

    }



    @Override
    public void process(@NonNull LogMessage logMessage) {
        Log.e("Video processing ", logMessage.getText());
    }

    @Override
    public void success() {
        Log.e("Video processing ", "success");
        afterMuxingVideoShow();
        progressDialog.dismiss();
        Toast.makeText(AudioRecordPreviews.this, "Filter Applied", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void cancel() {
        Log.e("Video processing ", "cancel");
    }

    @Override
    public void failed() {
        Log.e("Video processing ", "failed");
    }

    @Override
    public void statisticsProcess(@NonNull Statistics statistics) {
        Log.e("Video processing ", statistics.toString());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.e("Video processing ", "hasCapture - "+hasCapture);
    }

    private void executeFfmpegCommand(String inputVideo, String inputAudio, String outputVideo) {

        //creating the progress dialog
         progressDialog = new ProgressDialog(AudioRecordPreviews.this);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String[] query = fFmpegQueryExtension.addAudioOnVideoNew(inputVideo, inputAudio, outputVideo);
        callBackOfQuery.callQuery(this, query, fFmpegCallBack);


        /*
            Here, we have used he Async task to execute our query because if we use the regular method the progress dialog
            won't be visible. This happens because the regular method and progress dialog uses the same thread to execute
            and as a result only one is a allowed to work at a time.
            By using we Async task we create a different thread which resolves the issue.
         */

       /* FFmpegKit.executeAsync(exe, new ExecuteCallback() {
            @Override
            public void apply(Session session) {
                ReturnCode returnCode = session.getReturnCode();

                AudioRecordPreviews.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (returnCode.isSuccess()) {


                            //after successful execution of ffmpeg command,
                            //again set up the video Uri in VideoView

                            Log.e("VideUrl= ",filePath);

                            progressDialog.dismiss();
                            Toast.makeText(AudioRecordPreviews.this, "Filter Applied", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            Log.d("TAG", session.getAllLogsAsString());
                            Toast.makeText(AudioRecordPreviews.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }, new LogCallback() {
            @Override
            public void apply(com.arthenica.ffmpegkit.Log log) {

                AudioRecordPreviews.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        progressDialog.setMessage("Applying Filter..\n"+log.getMessage());

                    }
                });
            }
        }, new StatisticsCallback() {
            @Override
            public void apply(Statistics statistics) {

                android.util.Log.d("STATS", statistics.toString());

            }
        });*/
    }
}
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.modelclass.UploadNewsModel;
import com.nmnews.nmnewsagency.pref.Prefrence;
import com.nmnews.nmnewsagency.rest.Rest;
import com.github.piasy.rxandroidaudio.AudioRecorder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import me.tankery.lib.circularseekbar.CircularSeekBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AudioRecordActivity extends AppCompatActivity implements Callback<Object> {
    Button button_audiocapture, button_save;
    ImageView img_thumbnail, iamge_back_audio_record;
    AudioRecorder mAudioRecorder;
    File audioFilePath, cutVideoPath, mergeVideoPath, muxingVideopath;
    CircularSeekBar circularSeekBar;
    VideoView vidview_audiorecord;
    boolean startRecord = true;
    String fileofaudio;
    CountDownTimer countDownTimer;
    int duration;
    TextView txt_timeer;
    private UploadNewsModel.DataBean dataBean = null;
    ProgressDialog dialog;
    File outputFile;
    LinearLayout lin_tapheare_audio;
    SeekBar seekbar;
    int current_pos;
    private int seconds = 0, sSec = 0;
    Handler handler;
    int repeatvideoCount, seekBarProgress, anotherValue;
    Rest rest;
    boolean muxing = false;
    int progrsValue = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            dataBean = (UploadNewsModel.DataBean) bundle.get("video");
        }
        rest = new Rest(this, this);
        iniIt();
        loadFFMpegBinary();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void iniIt() {
        circularSeekBar = findViewById(R.id.seek_bar);
        mAudioRecorder = AudioRecorder.getInstance();
        cutVideoPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + System.nanoTime() + "cut_video.mp4");
        mergeVideoPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + System.nanoTime() + "merge_video.mp4");
        muxingVideopath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + System.nanoTime() + "muxing_video.mp4");
        audioFilePath = new File(
                Environment.getExternalStorageDirectory().getAbsolutePath() +
                        File.separator + System.nanoTime() + ".m4a");
        /*fileofVideo = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + System.nanoTime() + "editaac.mp4");*/
        seekbar = findViewById(R.id.seekbar);
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
                if (!muxing) {
                    mp.setVolume(0f, 0f);
                }
                // mp.setLooping(true);

            }
        });
        vidview_audiorecord.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                vidview_audiorecord.start();
                Log.e("videocompleitiuon==", "");
                repeatvideoCount = repeatvideoCount + 1;
                anotherValue = anotherValue + 1;
                Log.e("videocompleitiuon==", String.valueOf(repeatvideoCount));
            }
        });
        iamge_back_audio_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("seekprogress==", String.valueOf(progress));
                seekBarProgress = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        circularSeekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                String message = String.format("Progress changed to %.2f, fromUser %s", progress, fromUser);
                //  Log.e("Main", message);
                Log.e("progress===", String.valueOf(progress));
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
        circularSeekBar.setMax(500000);
        seekbar.setMax((int) duration);
        Log.e("videoduration", getDuration());
        txt_timeer.setText(getDuration());
        new ImageLoadTask(dataBean.getImageFullPath(),
                img_thumbnail).execute();


        button_audiocapture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Button is pressed
                        if (startRecord) {
                            repeatvideoCount = 0;
                            // Toast.makeText(AudioRecordActivity.this, "audiostart", Toast.LENGTH_SHORT).show();
                            lin_tapheare_audio.setVisibility(View.GONE);
                            vidview_audiorecord.setVisibility(View.VISIBLE);
                            //txt_timeer.setVisibility(View.VISIBLE);
                            img_thumbnail.setVisibility(View.GONE);
                            showtimerAudioRecording();
                            vidview_audiorecord.start();
                            circularSeekBar.setVisibility(View.VISIBLE);

                            mAudioRecorder.prepareRecord(MediaRecorder.AudioSource.MIC,
                                    MediaRecorder.OutputFormat.DEFAULT, MediaRecorder.AudioEncoder.AAC,
                                    audioFilePath);
                            mAudioRecorder.startRecord();
                            startRecord = false;
                        } else {

                        }
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        Log.e("ssecduration==", sSec * 1000 + "--" + duration);
                        if (sSec * 1000 < duration) {
                            executeCutVideoCommand(0 * 1000, sSec * 1000);
                        } else if (sSec * 1000 > duration) {
                            repeatVideomerging();
                        }
                        handler.removeCallbacksAndMessages(null);
                        txt_timeer.setVisibility(View.GONE);
                        img_thumbnail.setVisibility(View.VISIBLE);
                        circularSeekBar.setVisibility(View.GONE);
                        lin_tapheare_audio.setVisibility(View.VISIBLE);
                        vidview_audiorecord.stopPlayback();
                        vidview_audiorecord.setVisibility(View.GONE);
                        mAudioRecorder.stopRecord();
                        startRecord = true;
                        sSec = 0;
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
    }

    private void callServiceUploadNews(String path) {
        Prefrence.setVideoFIle(path);
        setProgressSet();
        rest.uploadNews(path,true);

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

    public void nextactivityGoing(File outputFile) {

        Intent intent = new Intent(AudioRecordActivity.this, RecordingPreviewActivity.class);
        intent.putExtra("videopath", outputFile.getAbsolutePath());
        startActivity(intent);

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

    private void nextActivityGoing(UploadNewsModel.DataBean data) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("video", data);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();
    }


    private static class BufferedWritableFileByteChannel implements WritableByteChannel {
        private static final int BUFFER_CAPACITY = 1000000;

        private boolean isOpen = true;
        private final OutputStream outputStream;
        private final ByteBuffer byteBuffer;
        private final byte[] rawBuffer = new byte[BUFFER_CAPACITY];

        private BufferedWritableFileByteChannel(OutputStream outputStream) {
            this.outputStream = outputStream;
            this.byteBuffer = ByteBuffer.wrap(rawBuffer);
        }


        @Override
        public boolean isOpen() {
            return isOpen;
        }

        @Override
        public void close() throws IOException {
            dumpToFile();
            isOpen = false;
        }

        private void dumpToFile() {
            try {
                outputStream.write(rawBuffer, 0, byteBuffer.position());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public int write(ByteBuffer inputBuffer) throws IOException {
            int inputBytes = inputBuffer.remaining();

            if (inputBytes > byteBuffer.remaining()) {
                dumpToFile();
                byteBuffer.clear();

                if (inputBytes > byteBuffer.remaining()) {
                    throw new BufferOverflowException();
                }
            }

            byteBuffer.put(inputBuffer);

            return inputBytes;
        }
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
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int showSec = sSec;
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                txt_timeer.setText(time);
                Log.e("sec==", "" + showSec);
                circularSeekBar.setProgress(showSec * 1000);
                if (true) {
                    seconds++;
                    sSec++;
                }
                handler.postDelayed(this, 1000);
            }
        });


    }

    //setimage on imageview
    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }

    private void execFFmpegBinaryShortest(String givenOutputPath) {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // dialog.setIndeterminate(true);
        dialog.setProgress(0);
        dialog.setMax(100);
        String[] cmd = new String[]{"-i", givenOutputPath, "-i", audioFilePath.getAbsolutePath(),
                "-filter_complex", "[0:a][1:a]amerge=inputs=2[a]", "-map", "0:v", "-map", "[a]", "-c:v", "copy", "-ac", "2",
                muxingVideopath.getAbsolutePath()};


        /*ffmpeg.execute(cmd, new ExecuteBinaryResponseHandler() {
            @Override
            public void onFailure(String s) {
                System.out.println("on failure----" + s);
            }

            @Override
            public void onSuccess(String s) {
                dialog.setProgress(100);
                dialog.dismiss();
                System.out.println("on success-----" + s);
                Log.e("succesffmpegmuxing===", "SUCCESS with output : " + s);
                // nextactivityGoing(muxingVideopath);

                afterMuxingVideoShow();
            }

            @Override
            public void onProgress(String s) {
                Log.e("progressbeforemuxing", "" + progrsValue);
                if (progrsValue < 90) {
                    Log.e("progress", "" + progrsValue);
                    dialog.setProgress(changeProgress());
                }
            }

            @Override
            public void onStart() {
                dialog.setMessage("Almost Done ...");
                dialog.setProgress(10);
                dialog.show();
                //Log.d(TAG, "Started command : ffmpeg " + command);
                System.out.println("Start----");

            }

            @Override
            public void onFinish() {
                System.out.println("Finish-----");
                //  dialog.dismiss();

            }
        });*/


    }

    private void loadFFMpegBinary() {
       /* try {
            if (FFmpeg.getInstance(this).isSupported()) {
                ffmpeg = FFmpeg.getInstance(this);
            } else {
                showUnsupportedExceptionDialog();
            }

        } catch (Exception e) {
            Log.e("ffmpegcatchexception", "EXception no controlada : " + e);
        }*/
    }

    private void showUnsupportedExceptionDialog() {
        new AlertDialog.Builder(AudioRecordActivity.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Not Supported")
                .setMessage("Device Not Supported")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AudioRecordActivity.this.finish();
                    }
                })
                .create()
                .show();

    }


    private void executeCutVideoCommand(int startMs, int endMs) {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // dialog.setIndeterminate(true);
        dialog.setMax(100);
        dialog.setProgress(0);
        String[] cmd = {"-ss", "" + startMs / 1000, "-y", "-i", Prefrence.getVideoFile(), "-t", "" +
                (endMs - startMs) / 1000, "-vcodec", "mpeg4", "-b:v", "2097152", "-b:a", "48000", "-ac", "2", "-ar", "22050", cutVideoPath.getAbsolutePath()};

       /* ffmpeg.execute(cmd, new ExecuteBinaryResponseHandler() {
            @Override
            public void onFailure(String s) {
                System.out.println("on failure----" + s);
            }

            @Override
            public void onSuccess(String s) {
                dialog.setProgress(100);
                dialog.dismiss();
                progrsValue = 0;
                System.out.println("on success-----" + s);
                Log.e("succesffmpegcutvideo===", "SUCCESS with output : " + s);
                if (repeatvideoCount == 1) {
                    if (anotherValue > 1) {
                        executeMergeVideoCommand(mergeVideoPath.getAbsolutePath(), cutVideoPath.getAbsolutePath());
                    } else {
                        executeMergeVideoCommand(Prefrence.getVideoFile(), cutVideoPath.getAbsolutePath());
                    }
                } else {
                    execFFmpegBinaryShortest(cutVideoPath.getAbsolutePath());
                }
            }

            @Override
            public void onProgress(String s) {
                Log.e("progressbeforecut", "" + progrsValue);
                if (progrsValue < 90) {
                    Log.e("progresscut", "" + progrsValue);
                    dialog.setProgress(changeProgress());
                }
            }

            @Override
            public void onStart() {
                dialog.setMessage("Please Wait for a sec cut...");
                dialog.show();

                dialog.setProgress(10);
                //Log.d(TAG, "Started command : ffmpeg " + command);
                System.out.println("Start----");

            }

            @Override
            public void onFinish() {
                System.out.println("Finish-----");


            }
        });*/

    }

    public int changeProgress() {
        progrsValue = progrsValue + 1;
        return progrsValue;
    }

    public void repeatVideomerging() {
        Log.e("repercount==", String.valueOf(repeatvideoCount));
        if (repeatvideoCount == 1) {
            executeCutVideoCommand(0 * 1000, seekBarProgress);
        } else if (repeatvideoCount == 2) {
            if (anotherValue == 3) {
                executeMergeVideoCommand(mergeVideoPath.getAbsolutePath(), Prefrence.getVideoFile());
            } else {
                executeMergeVideoCommand(Prefrence.getVideoFile(), Prefrence.getVideoFile());
            }
        } else if (repeatvideoCount == 3) {
            executeMergeVideoCommand(Prefrence.getVideoFile(), Prefrence.getVideoFile());
        }
    }

    private void executeMergeVideoCommand(String path, String path1) {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //  dialog.setIndeterminate(true);
        dialog.setProgress(0);
        String[] cmd = new String[]{"-i", path, "-i", path1,
                "-filter_complex", "[0:v] [0:a:0] [1:v] [1:a:0] concat=n=2:v=1:a=1 [v] [a]", "-map", "[v]", "-map", "[a]",
                mergeVideoPath.getAbsolutePath()};
        /*ffmpeg.execute(cmd, new ExecuteBinaryResponseHandler() {
            @Override
            public void onFailure(String s) {
                System.out.println("on failure----" + s);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onSuccess(String s) {
                System.out.println("on success-----" + s);
                Log.e("succespegmergevideo==", "SUCCESS with output : " + s);
                dialog.setProgress(100);
                dialog.dismiss();
                progrsValue = 0;
                if (repeatvideoCount == 1) {
                    execFFmpegBinaryShortest(mergeVideoPath.getAbsolutePath());
                } else if (repeatvideoCount == 2) {
                    repeatVideomerging();
                } else if (repeatvideoCount == 3) {
                    repeatVideomerging();
                }
                repeatvideoCount = repeatvideoCount - 1;
            }

            @Override
            public void onProgress(String s) {
                Log.e("progressbeforemerge", "" + progrsValue);
                if (progrsValue < 90) {
                    Log.e("progressmerge", "" + progrsValue);
                    dialog.setProgress(changeProgress());
                }
            }

            @Override
            public void onStart() {
                dialog.setMessage("Please Wait for a sec merge...");
                dialog.setProgress(10);
                dialog.show();
                //Log.d(TAG, "Started command : ffmpeg " + command);
                System.out.println("Start----");

            }

            @Override
            public void onFinish() {
                System.out.println("Finish-----");


            }
        });*/

    }

    public void afterMuxingVideoShow() {
        muxing = true;
        vidview_audiorecord.setVisibility(View.VISIBLE);
        img_thumbnail.setVisibility(View.GONE);
        button_audiocapture.setVisibility(View.GONE);
        lin_tapheare_audio.setVisibility(View.GONE);
        button_save.setVisibility(View.VISIBLE);
        vidview_audiorecord.setVideoPath(muxingVideopath.getAbsolutePath());
        vidview_audiorecord.start();

    }

}
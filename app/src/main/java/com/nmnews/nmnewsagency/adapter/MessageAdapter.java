package com.nmnews.nmnewsagency.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.ChatActivity;
import com.nmnews.nmnewsagency.chat.ChatHelper;
import com.nmnews.nmnewsagency.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.model.QBUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    // private List<LocationModel> moviesList;
    private List<String> moviesList;
    private List<Long> msgtime;
    private List<String> lstmsg;
    private List<String> imagelist;
    Context context;
    ArrayList<QBChatDialog> dialogs1;
    QBUser currentUser;
    private static final int REQUEST_DIALOG_ID_FOR_UPDATE = 165;
    Activity activity;
    private static final String MAX_MESSAGES_TEXT = "99+";
    private static final int MAX_MESSAGES = 99;

    /*public LocationAdapter(List<LocationModel> moviesList) {
        this.moviesList = moviesList;
    }*/

    public MessageAdapter(Context context, List<String> moviesList, List<String> lstmsg, List<Long> msgtime,
                          ArrayList<QBChatDialog> dialogs1, QBUser currentUser, Activity activity,List<String> imagelist) {
        this.moviesList = moviesList;
        this.lstmsg = lstmsg;
        this.imagelist = imagelist;
        this.msgtime = msgtime;
        this.context = context;
        this.dialogs1 = dialogs1;
        this.currentUser = currentUser;
        this.activity = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_msg_username,txt_msg_lastmsg,txt_msg_time,text_unread_counter;
        public CircleImageView img_mesge;
        public RelativeLayout rel_messages;

        public MyViewHolder(View view) {
            super(view);
            txt_msg_username = (TextView) view.findViewById(R.id.txt_msg_username);
            text_unread_counter = (TextView) view.findViewById(R.id.text_unread_counter);
            txt_msg_time = (TextView) view.findViewById(R.id.txt_msg_time);
            txt_msg_lastmsg = (TextView) view.findViewById(R.id.txt_msg_lastmsg);
            img_mesge = (CircleImageView) view.findViewById(R.id.img_mesge);
            rel_messages = (RelativeLayout) view.findViewById(R.id.rel_messsages);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*LocationModel movie = moviesList.get(position);
        holder.title.setText(movie.getmName());*/
        holder.txt_msg_username.setText(moviesList.get(position));
        holder.txt_msg_lastmsg.setText(lstmsg.get(position));
        holder.txt_msg_time.setText(getDialogLastMessageTime(msgtime.get(position)));

        int unreadMessagesCount = getUnreadMsgCount(dialogs1.get(position));

        if (unreadMessagesCount == 0) {
            holder.text_unread_counter.setVisibility(View.INVISIBLE);
        } else {
            holder.text_unread_counter.setVisibility(View.VISIBLE);
            String messageCount = (unreadMessagesCount > MAX_MESSAGES) ? MAX_MESSAGES_TEXT : String.valueOf(unreadMessagesCount);
            holder.text_unread_counter.setText(messageCount);
        }

        Glide.with(context)
                .load(imagelist.get(position))
                .placeholder(R.color.adbag)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(holder.img_mesge);

        holder.rel_messages.setTag(position);
       holder.rel_messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = (int) v.getTag();
                Log.e("datat=======", dialogs1.get(i).toString());
                final QBChatDialog selectedDialog =dialogs1.get(i);
                //  if (ChatHelper.getInstance().isLogged()) {
                // showProgressDialog(R.string.dlg_login);
                ChatHelper.getInstance().loginToChat(currentUser, new QBEntityCallback<Void>() {
                    @Override
                    public void onSuccess(Void aVoid, Bundle bundle) {
                        //  hideProgressDialog();
                        ChatActivity.startForResult(activity, REQUEST_DIALOG_ID_FOR_UPDATE, selectedDialog);
                    }

                    @Override
                    public void onError(QBResponseException e) {
                        // hideProgressDialog();
                        ToastUtils.shortToast(R.string.login_chat_login_error);
                    }
                });
                // }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ChatHelper.getInstance().isLogged();
               // final QBChatDialog selectedDialog = (QBChatDialog) ;
               // Log.e("clicl=========",selectedDialog.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
    private String getDialogLastMessageTime(long seconds) {
        long timeInMillis = seconds * 1000;
        Calendar msgTime = Calendar.getInstance();
        msgTime.setTimeInMillis(timeInMillis);

        if (timeInMillis == 0) {
            return "";
        }

        Calendar now = Calendar.getInstance();
        SimpleDateFormat timeFormatToday = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        SimpleDateFormat dateFormatThisYear = new SimpleDateFormat("d MMM", Locale.ENGLISH);
        SimpleDateFormat lastYearFormat = new SimpleDateFormat("dd.MM.yy", Locale.ENGLISH);

        if (now.get(Calendar.DATE) == msgTime.get(Calendar.DATE) && now.get(Calendar.YEAR) == msgTime.get(Calendar.YEAR)) {
            return timeFormatToday.format(new Date(timeInMillis));
        } else if (now.get(Calendar.DAY_OF_YEAR) - msgTime.get(Calendar.DAY_OF_YEAR) == 1 && now.get(Calendar.YEAR) == msgTime.get(Calendar.YEAR)) {
            return context.getString(R.string.yesterday);
        } else if (now.get(Calendar.YEAR) == msgTime.get(Calendar.YEAR)) {
            return dateFormatThisYear.format(new Date(timeInMillis));
        } else {
            return lastYearFormat.format(new Date(timeInMillis));
        }
    }
    private int getUnreadMsgCount(QBChatDialog chatDialog) {
        Integer unreadMessageCount = chatDialog.getUnreadMessageCount();
        if (unreadMessageCount == null) {
            unreadMessageCount = 0;
        }
        return unreadMessageCount;
    }
}

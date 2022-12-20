package com.nmnews.nmnewsagency.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.MessageAdapter;
import com.nmnews.nmnewsagency.chat.ChatHelper;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.pref.SharedPrefsHelper;
import com.nmnews.nmnewsagency.utils.DialogsManager;
import com.nmnews.nmnewsagency.utils.ToastUtils;
import com.nmnews.nmnewsagency.utils.qb.QbChatDialogMessageListenerImp;
import com.nmnews.nmnewsagency.utils.qb.QbDialogHolder;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBIncomingMessagesManager;
import com.quickblox.chat.listeners.QBChatDialogMessageListener;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.request.QBRequestGetBuilder;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity implements DialogsManager.ManagingDialogsCallbacks {
    RecyclerView recyclerView;
    ImageView iamge_back_msg;
    MessageAdapter messageAdapter;
    //  List<LocationModel> arrayList;
    List<String> arrayList, arraymsg, arrayImage;
    List<Integer> arrayId;
    ArrayList<Long> arrattime;
    int totalUsers = 0;
    ProgressDialog pd;
    QBUser currentUser;
    public static final int DIALOGS_PER_PAGE = 100;
    private boolean hasMoreDialogs = true;
    ProgressDialog dialog;
    ArrayList<QBChatDialog> dialogs1;
    EditText edit_msgdetail;
    String jsonObject;
    private DialogsManager dialogsManager = new DialogsManager();;
    private QBChatDialogMessageListener allDialogsMessagesListener = new AllDialogsMessageListener();
    private QBIncomingMessagesManager incomingMessagesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        setContentView(R.layout.activity_message);
        if (ChatHelper.getCurrentUser() != null) {
            currentUser = ChatHelper.getCurrentUser();
        } else {
            // Log.e(TAG, "Finishing " + TAG + ". Current user is null");
            finish();
        }
        inIt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        arrayList = new ArrayList<>();
        arrattime = new ArrayList<>();
        arraymsg = new ArrayList<>();
        arrayImage = new ArrayList<>();
        arrayId = new ArrayList<>();
        loadDialogsFromQb(false, true);
    }

    private void inIt() {
        recyclerView = (RecyclerView) findViewById(R.id.recy_mesage);
        iamge_back_msg = (ImageView) findViewById(R.id.iamge_back_msg);
        edit_msgdetail = (EditText) findViewById(R.id.edit_msgdetail);

        iamge_back_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageActivity.this.finish();
            }
        });
        edit_msgdetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  messageAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        try {
            incomingMessagesManager = QBChatService.getInstance().getIncomingMessagesManager();
        } catch (Exception e) {
            return;
        }

        if (incomingMessagesManager == null) {
            reloginToChat();
            return;
        }

        incomingMessagesManager.addDialogMessageListener(allDialogsMessagesListener);
    }

    private void inItItemRecycle() {
        messageAdapter = new MessageAdapter(this, arrayList, arraymsg, arrattime, dialogs1, currentUser,
                MessageActivity.this, arrayImage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(messageAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void loadDialogsFromQb(final boolean silentUpdate, final boolean clearDialogHolder) {
        //isProcessingResultInProgress = true;
       /* if (silentUpdate) {
            progress.setVisibility(View.VISIBLE);
        } else {
            showProgressDialog(R.string.dlg_loading);
        }*/
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("Please Wait ...");
        dialog.show();
        QBRequestGetBuilder requestBuilder = new QBRequestGetBuilder();
        requestBuilder.setLimit(DIALOGS_PER_PAGE);
        requestBuilder.setSkip(clearDialogHolder ? 0 : QbDialogHolder.getInstance().getDialogs().size());

        ChatHelper.getInstance().getDialogs(requestBuilder, new QBEntityCallback<ArrayList<QBChatDialog>>() {
            @Override
            public void onSuccess(ArrayList<QBChatDialog> dialogs, Bundle bundle) {
                if (dialogs.size() < DIALOGS_PER_PAGE) {
                    hasMoreDialogs = false;
                }
                if (clearDialogHolder) {
                    QbDialogHolder.getInstance().clear();
                    hasMoreDialogs = true;
                }
                QbDialogHolder.getInstance().addDialogs(dialogs);
                dialogs1 = new ArrayList<>(QbDialogHolder.getInstance().getDialogs().values());
                Log.e("dialog===", dialogs.toString());
                if (dialogs.size() > 0) {
                    for (int i = 0; i < dialogs.size(); i++) {
                        arrayList.add(dialogs.get(i).getName());
                        arrattime.add(dialogs.get(i).getLastMessageDateSent());
                        arraymsg.add(dialogs.get(i).getLastMessage());
                        arrayImage.add(dialogs.get(i).getPhoto());
                       // arrayId.add(dialogs.get(i).getUserId());
                       /* jsonObject=(dialogs.get(i).getCustomData().toString());
                        Log.e("customdata",jsonObject);
                        try {
                            JSONObject object=new JSONObject(jsonObject);
                            Log.e("customdata",object.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                    }
                    if (arrayList != null && arrayList.size() > 0) {
                        dialog.dismiss();
                        inItItemRecycle();
                    }
                } else {
                    dialog.dismiss();
                    Toast.makeText(MessageActivity.this, "No user found", Toast.LENGTH_SHORT).show();
                }
               /* updateDialogsAdapter();

                DialogJoinerAsyncTask joinerTask = new DialogJoinerAsyncTask(DialogsActivity.this, dialogs);
                joinerTasksSet.add(joinerTask);
                joinerTask.execute();

                disableProgress();
                if (hasMoreDialogs) {
                    Toast.makeText(DialogsActivity.this, "loaddialiogg==", Toast.LENGTH_SHORT).show();
                    loadDialogsFromQb(true, false);
                }*/
            }

            @Override
            public void onError(QBResponseException e) {
                // disableProgress();
                ToastUtils.shortToast(e.getMessage());
            }
        });
    }

    public void getUserDetailById() {
        QBUsers.getUser(123).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser, Bundle bundle) {

            }

            @Override
            public void onError(QBResponseException e) {

            }
        });
    }

    @Override
    public void onDialogCreated(QBChatDialog chatDialog) {

    }

    @Override
    public void onDialogUpdated(String chatDialog) {

    }

    @Override
    public void onNewDialogLoaded(QBChatDialog chatDialog) {

    }

    private class AllDialogsMessageListener extends QbChatDialogMessageListenerImp {
        @Override
        public void processMessage(final String dialogId, final QBChatMessage qbChatMessage, Integer senderId) {
            Log.d("listner====", "Processing received Message: " + qbChatMessage.getBody());
            if (!senderId.equals(currentUser.getId())) {
                dialogsManager.onGlobalMessageReceived(dialogId, qbChatMessage);
            }
        }
    }
    private void reloginToChat() {
        pd=new ProgressDialog(this);
        pd.setMessage("Please Wait ..");
        pd.show();
        if (SharedPrefsHelper.getInstance().hasQbUser()) {
            ChatHelper.getInstance().loginToChat(SharedPrefsHelper.getInstance().getQbUser(), new QBEntityCallback<Void>() {
                @Override
                public void onSuccess(Void aVoid, Bundle bundle) {
                   pd.dismiss();
                }

                @Override
                public void onError(QBResponseException e) {

                }
            });
        }
    }

}
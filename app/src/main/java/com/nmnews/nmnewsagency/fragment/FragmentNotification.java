package com.nmnews.nmnewsagency.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.adapter.AdapterSectionRecycler;
import com.nmnews.nmnewsagency.adapter.SectionHeader;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.model.Child;
import com.nmnews.nmnewsagency.modelclass.NotificationModel;
import com.nmnews.nmnewsagency.rest.Rest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNotification extends Fragment implements Callback<Object> {
    View view;
    Rest rest;
    RecyclerView recyclerView;
    AdapterSectionRecycler adapterRecycler;
    List<SectionHeader> sectionHeaders;
    List<NotificationModel.DataBean> notificationModelList;
    List<Child> childListToday, childListolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification, container, false);
        rest = new Rest(getActivity(), this);
        init();
        return view;
    }

    private void init() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recy_notification);
        childListToday = new ArrayList<>();
        childListolder = new ArrayList<>();
        callServiceNotification();
    }

    private void callServiceNotification() {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getNotification();

    }

    private void inItRecycle() throws JSONException {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        JSONObject jsonObject = null;
        for (int i = 0; i < notificationModelList.size(); i++) {
            if(notificationModelList.get(i).getNotification_JSON()!=null) {
                 jsonObject = new JSONObject(notificationModelList.get(i).getNotification_JSON());
            }
            String desDate=notificationModelList.get(i).getDesireDate();
            if (desDate.endsWith("h") || desDate.endsWith("s") || desDate.endsWith("m")) {
                if(jsonObject!=null) {
                    childListToday.add(new Child(notificationModelList.get(i).getNotification_Desc()+"  "+
                            notificationModelList.get(i).getDesireDate(),
                            notificationModelList.get(i).getImageUrl(),String.valueOf(notificationModelList.get(i).getNewsId())));
                }else{
                    childListToday.add(new Child(notificationModelList.get(i).getNotification_Desc()+"  "+
                            notificationModelList.get(i).getDesireDate(),
                            "",String.valueOf(notificationModelList.get(i).getNewsId())));
                }
            } else {
                if(jsonObject!=null) {
                    childListolder.add(new Child(notificationModelList.get(i).getNotification_Desc()+"  "+
                            notificationModelList.get(i).getDesireDate(),
                            notificationModelList.get(i).getImageUrl(),String.valueOf(notificationModelList.get(i).getNewsId())));
                }else{
                    childListolder.add(new Child(notificationModelList.get(i).getNotification_Desc()+"  "+
                            notificationModelList.get(i).getDesireDate(),
                            "",String.valueOf(notificationModelList.get(i).getNewsId())));
                }
            }
        }
        sectionHeaders = new ArrayList<>();
        if (childListToday.size()>0)
        {
            sectionHeaders.add(new SectionHeader(childListToday, "Today", 6));
        }

        if (childListolder.size()>0)
        {
            sectionHeaders.add(new SectionHeader(childListolder, "This week", 2));
        }

//        sectionHeaders.add(new SectionHeader(childListToday, "Today", 6));
//        sectionHeaders.add(new SectionHeader(childListolder, "This week", 2));
        adapterRecycler = new AdapterSectionRecycler(getActivity(), sectionHeaders);
        recyclerView.setAdapter(adapterRecycler);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {

            Object obj = response.body();
            if (obj instanceof NotificationModel) {
                NotificationModel loginModel = (NotificationModel) obj;
                if (loginModel.isStatus()) {
                    notificationModelList = loginModel.getData();
                    try {
                        inItRecycle();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }

   /* public boolean compairDare(String dateFormat) throws ParseException {
        boolean date = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
        String strDate = sdf.format("2021-08-25T18:46:44.543");
        String  strDate1 = sdf.format(new Date().toString());
        if (strDate1 > strDate) {
            date = false;
            Log.e("time==", String.valueOf(date));
        }
        Log.e("time==", String.valueOf(date));
        return date;
    }*/
}

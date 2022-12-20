package com.nmnews.nmnewsagency.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.activity.SearchTopBarTabingActivity;
import com.nmnews.nmnewsagency.adapter.HashTagAdapter;
import com.nmnews.nmnewsagency.adapter.UsersAdapter;
import com.nmnews.nmnewsagency.adapter.VideosAdapter;
import com.nmnews.nmnewsagency.listner.RecyclerTouchListener;
import com.nmnews.nmnewsagency.modelclass.SearchTopSearchModel;
import com.nmnews.nmnewsagency.rest.Rest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSearch extends Fragment implements Callback<Object> {
    View view;
    RecyclerView recyclerView, recyclerView_users, recyclerView_video;
    HashTagAdapter locationAdapter;
    UsersAdapter userAdapter;
    VideosAdapter videoAdapter;
    List<SearchTopSearchModel.DataBeanX.DataBean.PagedRecordBean> arrayList, arrayList_users, arrayList_video,arrayList_hashtag;
    EditText search_edit;
    LinearLayout lin_onlysearch;
    Animation myAnim;
    Rest rest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        rest=new Rest(getActivity(),this);
        inIt();
        return view;
    }

    private void inIt() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recy_hashtag);
        recyclerView_users = (RecyclerView) view.findViewById(R.id.recy_users);
        recyclerView_video = (RecyclerView) view.findViewById(R.id.recy_video);
        lin_onlysearch = (LinearLayout) view.findViewById(R.id.lin_onlysearch);
        myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
        lin_onlysearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lin_onlysearch.startAnimation(myAnim);
                Intent intent = new Intent(getActivity(), SearchTopBarTabingActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        arrayList_users=new ArrayList<>();
        arrayList=new ArrayList<>();
        arrayList_video=new ArrayList<>();
        callServicegetHashTag("");
    }

    private void callServicegetHashTag(String query) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getNewsTopSearch(4, "");
    }
    private void callServicegetPeople(String query) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getNewsTopSearch(3, "");
    }
    private void callServicegetVideo(String query) {
        rest.ShowDialogue(getResources().getString(R.string.pleaseWait));
        rest.getNewsTopSearch(2, "");
    }

    private void inItItemRecycle() {
        locationAdapter = new HashTagAdapter(getActivity(),arrayList_hashtag);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(locationAdapter);

        userAdapter = new UsersAdapter(getActivity(), arrayList_users);
        recyclerView_users.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false));
        recyclerView_users.setItemAnimator(new DefaultItemAnimator());
        recyclerView_users.setNestedScrollingEnabled(false);
        recyclerView_users.setHasFixedSize(false);
        recyclerView_users.setAdapter(userAdapter);

        videoAdapter = new VideosAdapter(getActivity(), arrayList_video);
        recyclerView_video.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false));
        recyclerView_video.setItemAnimator(new DefaultItemAnimator());
        recyclerView_video.setNestedScrollingEnabled(false);
        recyclerView_video.setHasFixedSize(false);
        recyclerView_video.setAdapter(videoAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_loc, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        rest.dismissProgressdialog();
        if (response.isSuccessful()) {
            Object obj = response.body();
            Log.e("nmnnn", String.valueOf(obj));
            if (obj instanceof SearchTopSearchModel) {
                SearchTopSearchModel searchTopSearchModel = (SearchTopSearchModel) obj;
                if (searchTopSearchModel.getData().isStatus()) {
                    arrayList=searchTopSearchModel.getData().getData().getPagedRecord();
                    if(arrayList.size()>0){
                    if(arrayList.get(0).getSearchType().equalsIgnoreCase("hashtag")){
                        arrayList_hashtag=searchTopSearchModel.getData().getData().getPagedRecord();
                        callServicegetPeople("");
                    }else if(arrayList.get(0).getSearchType().equalsIgnoreCase("PEOPLE")){
                        arrayList_users=searchTopSearchModel.getData().getData().getPagedRecord();
                        callServicegetVideo("");
                    }else if(arrayList.get(0).getSearchType().equalsIgnoreCase("VIDEO")){
                        arrayList_video=searchTopSearchModel.getData().getData().getPagedRecord();
                    }
                    inItItemRecycle();
                    }
                   /* if (searchTopSearchModel.getData().getData().getPagedRecord().size()  > 0) {
                        for (int i = 0; i < searchTopSearchModel.getData().getData().getPagedRecord().size(); i++) {
                            if (searchTopSearchModel.getData().getData().getPagedRecord().get(i).getSearchType().equals("VIDEO")) {
                                arrayList_video.add(searchTopSearchModel.getData().getData().getPagedRecord().get(i));
                            } else if (searchTopSearchModel.getData().getData().getPagedRecord().get(i).getSearchType().equals("PEOPLE")) {
                                arrayList_users.add(searchTopSearchModel.getData().getData().getPagedRecord().get(i));
                            } else if (searchTopSearchModel.getData().getData().getPagedRecord().get(i).getSearchType().equals("HASHTAG")) {
                                arrayList.add(searchTopSearchModel.getData().getData().getPagedRecord().get(i));
                            }
                        }
                    }*/
                   // inItItemRecycle();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        rest.dismissProgressdialog();

    }
}

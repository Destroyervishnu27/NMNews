package com.nmnews.nmnewsagency.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nmnews.nmnewsagency.R;
import com.nmnews.nmnewsagency.model.CountryModel;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> implements View.OnClickListener {
    // private List<LocationModel> moviesList;
    private List<CountryModel.DataBean> moviesList;
    Context context;

    /*public LocationAdapter(List<LocationModel> moviesList) {
        this.moviesList = moviesList;
    }*/

    public LocationAdapter(Context context,List<CountryModel.DataBean> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public void onClick(View v) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public RelativeLayout relloc_location;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.loc_name);
            relloc_location = (RelativeLayout) view.findViewById(R.id.relloc_location);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_locastion, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CountryModel.DataBean movie = moviesList.get(position);
        holder.title.setText(movie.getName());

      //  holder.title.setText(moviesList.get(position).getData().get(position).getName());
        /*holder.relloc_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    notifyDataSetChanged();
                    holder.relloc_location.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.locationborderitemwithred));
                } else {
                    notifyDataSetChanged();
                    holder.relloc_location.setBackground(ContextCompat.getDrawable(context, R.drawable.locationborderitemwithred));
                }

                holder.title.setTextColor(Color.parseColor("#FFFFFF"));
              //  holder.relloc_location.setBackgroundColor(Color.parseColor("#FF0000"));
                //notifyDataSetChanged();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

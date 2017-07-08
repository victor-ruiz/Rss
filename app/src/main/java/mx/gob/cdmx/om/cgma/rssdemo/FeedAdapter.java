package mx.gob.cdmx.om.cgma.rssdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by victor on 20/01/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedHolder> {
    List<FeedItem> feedItems;
    Context context;

    public FeedAdapter(List<FeedItem> feedItems, Context context) {
        this.feedItems = feedItems;
        this.context = context;
    }

    @Override
    public FeedAdapter.FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custum_row_news_item,parent,false);
        FeedHolder holder = new FeedHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final FeedAdapter.FeedHolder holder, int position) {

        //para animar los cardview
        //YoYo.with(Techniques.RubberBand).playOn(holder.cardView);

        final FeedItem current = feedItems.get(position);

        holder.tvTitle.setText(current.getTitle());

        if ( current.getUrlImage().contentEquals("")) {
            holder.ivImage.setImageResource(R.drawable.chica);
        }else{
            //Picasso.with(context).load(current.getUrlImage()).into(holder.ivImage);
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, current.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public class FeedHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvDescription,tvDate;
        ImageView ivImage;
        CardView cardView;

        public FeedHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_noticias);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_thumb_img);
        }
    }
}

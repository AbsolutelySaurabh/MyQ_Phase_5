package dd.com.myq.Activity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import dd.com.myq.R;
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    ImageLoader imageLoader;

    private List<Movie> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
public ImageView img;

        public MyViewHolder(View view) {
            super(view);
             img = (ImageView) view.findViewById(R.id.img);
        }
    }
    public MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);

        Log.d("picasso shuru ho gaya","");

        Log.d("context=", String.valueOf(holder.img.getContext()));

        Picasso.with(holder.img.getContext())
                .load(movie.getWasteImage())
                .into(holder.img);
}

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
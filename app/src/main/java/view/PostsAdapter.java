package view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chaitali.databinding.R;
import com.example.chaitali.databinding.databinding.*;

import java.util.ArrayList;
import java.util.List;

import model.Post;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private List<Post> postList;
    private LayoutInflater layoutInflater;
    private PostsAdapterListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private PostRowItemBinding binding;
        public ViewHolder(final PostRowItemBinding itemBinding)  {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    public PostsAdapter(ArrayList<Post> postList, PostsAdapterListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        PostRowItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.post_row_item,viewGroup, false );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.binding.setPost(postList.get(i));
        viewHolder.binding.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPostClicked(postList.get(i));
            }
        });
    }


    @Override
    public int getItemCount() {
        return postList.size();
    }

    public interface PostsAdapterListener {
        void onPostClicked(Post post);
    }
}

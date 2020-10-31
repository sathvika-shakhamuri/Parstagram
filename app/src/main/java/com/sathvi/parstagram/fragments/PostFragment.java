package com.sathvi.parstagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.sathvi.parstagram.Post;
import com.sathvi.parstagram.PostsAdapter;
import com.sathvi.parstagram.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostFragment#} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {

  private RecyclerView rvPosts;
  public static final String TAG = "PostsFragment";
  protected PostsAdapter adapter;
  protected List<Post> allPosts;

    public PostFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);
        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);
        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();


    }
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
         query.setLimit(20);
         query.addDescendingOrder(Post.KEY_CREATED_KEY);
         query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e!= null){
                    Log.e(TAG, "Issue with getting posts", e);
                }
                for(Post post:posts){
                    Log.i(TAG, "Post : " + post.getDescription());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }

        });
    }
}
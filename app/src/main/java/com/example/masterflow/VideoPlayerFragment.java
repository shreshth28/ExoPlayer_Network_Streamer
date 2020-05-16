package com.example.masterflow;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoPlayerFragment extends Fragment{

    SimpleExoPlayerView mPlayerView;
    ExoPlayer mExoPlayer;
    ImageView noPlayerIV;
    static int videoIndex;
    boolean isAvailable;


    public VideoPlayerFragment()
    {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video_player, container, false);
        if(savedInstanceState==null) {

            mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.fragment_player);
            noPlayerIV = rootView.findViewById(R.id.noVideo_iv);
            String url = MainActivity.mainList.get(RecipeListFragment.indexSteps).getVideoURL().get(videoIndex);
            initializePlayer(Uri.parse(url));
        }
        return rootView;
    }


    private void initializePlayer(Uri mediaUri)
    {
        if(mediaUri.toString().equals("")) {
            noPlayerIV.setVisibility(View.VISIBLE);
            isAvailable=false;
            mPlayerView.setVisibility(View.GONE);
            Toast.makeText(getActivity(),"No Video Available", Toast.LENGTH_SHORT).show();
        }
        else {
            noPlayerIV.setVisibility(View.GONE);
            mPlayerView.setVisibility(View.VISIBLE);
            isAvailable=true;
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            String userAgent = Util.getUserAgent(getContext(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    public void releasePlayer()
    {
        if(isAvailable) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }


}

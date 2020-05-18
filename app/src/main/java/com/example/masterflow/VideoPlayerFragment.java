package com.example.masterflow;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.squareup.picasso.Picasso;

public class VideoPlayerFragment extends Fragment{

    SimpleExoPlayerView mPlayerView;
    static ExoPlayer mExoPlayer;
    static ImageView noPlayerIV;
    static int videoIndex;
    static boolean isAvailable;
    static String thumbNailUrl;
    public static long position;
    Button nextBtn;
    Button prevBtn;
    Boolean isFirstRun;
    TextView detailInstructions;
    public static final String MY_PREFS_NAME="vp";
    SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video_player, container, false);
            mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.fragment_player);
            noPlayerIV = rootView.findViewById(R.id.noVideo_iv);

            if(savedInstanceState==null) {
                initializePlayer(videoIndex);
                editor=getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_MULTI_PROCESS).edit();
                editor.putInt("videoIndex",videoIndex);
                editor.apply();
            }
            if(savedInstanceState!=null)
            {
                if(MainActivity.isTablet)
                {
                    initializePlayer(videoIndex);
                    SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_MULTI_PROCESS);
                    videoIndex = prefs.getInt("videoIndex", 0);
                    position = savedInstanceState.getLong("position", 0);
                    mExoPlayer.seekTo(position);
                }
                else {
                    SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_MULTI_PROCESS);
                    videoIndex = prefs.getInt("videoIndex", 0);
                    position = savedInstanceState.getLong("position", 0);
                    Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
                    TrackSelector trackSelector = new DefaultTrackSelector();
                    LoadControl loadControl = new DefaultLoadControl();
                    mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
                    mPlayerView.setPlayer(mExoPlayer);
                    mExoPlayer.setPlayWhenReady(true);
                }

            }
            nextBtn=getActivity().findViewById(R.id.next_btn);
            prevBtn=getActivity().findViewById(R.id.prev_btn);
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(videoIndex<MainActivity.mainList.get(RecipeListFragment.indexSteps).getStepsLength()-1) {
                        videoIndex=videoIndex+1;
                        releasePlayer();
                        initializePlayer(videoIndex);
                        editor=getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_MULTI_PROCESS).edit();
                        editor.putInt("videoIndex",videoIndex);
                        editor.apply();
                    }
                }
            });

            prevBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(videoIndex>0)
                    {

                        videoIndex=videoIndex-1;
                        releasePlayer();
                        initializePlayer(videoIndex);
                        editor=getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_MULTI_PROCESS).edit();
                        editor.putInt("videoIndex",videoIndex);
                        editor.apply();
                    }
                }
            });
        return rootView;
    }


    private void initializePlayer(int index)
    {
        executeDisplay();
        String url = MainActivity.mainList.get(RecipeListFragment.indexSteps).getVideoURL().get(index);
        Uri mediaUri=(Uri.parse(url));
        if(mediaUri.toString().equals("")) {
           thumbNailUrl=MainActivity.mainList.get(RecipeListFragment.indexSteps).getThumbnailURL().get(index);
           try{
            if(thumbNailUrl.substring(thumbNailUrl.length()-4).equalsIgnoreCase(".mp4")) {
                Picasso.get().load(R.drawable.novideo).resize(50, 50)
                        .centerCrop().into(noPlayerIV);
            }
            else{
                Picasso.get()
                        .load(thumbNailUrl)
                        .error(R.drawable.novideo)
                        .into(noPlayerIV);
            }}
           catch (Exception e)
           {
               Picasso.get().load(R.drawable.novideo).resize(50, 50)
                       .centerCrop().into(noPlayerIV);
           }
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
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            String userAgent = Util.getUserAgent(getActivity(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!MainActivity.isTablet) {
            if(mExoPlayer!=null) {
                mExoPlayer.seekTo(position);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public static void releasePlayer()
    {
        if(isAvailable) {
            mExoPlayer.stop();
            mExoPlayer.release();
        }
    }

    public void executeDisplay(){
        detailInstructions = getActivity().findViewById(R.id.detail_instruction);
        detailInstructions.setText(MainActivity.mainList.get(RecipeListFragment.indexSteps).getDescription().get(VideoPlayerFragment.videoIndex));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("position",position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mExoPlayer!=null) {
            position = mExoPlayer.getCurrentPosition();
        }
    }


}

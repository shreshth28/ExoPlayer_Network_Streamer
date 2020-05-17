package com.example.masterflow;

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

public class VideoPlayerFragment extends Fragment{

    SimpleExoPlayerView mPlayerView;
    static ExoPlayer mExoPlayer;
    static ImageView noPlayerIV;
    static int videoIndex;
    static boolean isAvailable;
    Button nextBtn;
    Button prevBtn;
    TextView detailInstructions;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video_player, container, false);
            mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.fragment_player);
            noPlayerIV = rootView.findViewById(R.id.noVideo_iv);
            initializePlayer(videoIndex);
            nextBtn=getActivity().findViewById(R.id.next_btn);
            prevBtn=getActivity().findViewById(R.id.prev_btn);
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(VideoPlayerFragment.videoIndex<MainActivity.mainList.get(RecipeListFragment.indexSteps).getStepsLength()-1) {
                        ++VideoPlayerFragment.videoIndex;
                        releasePlayer();
                        initializePlayer(videoIndex);
                    }
                }
            });

            prevBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(VideoPlayerFragment.videoIndex>0)
                    {

                        --VideoPlayerFragment.videoIndex;
                        releasePlayer();
                        initializePlayer(videoIndex);
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

    public static void releasePlayer()
    {
        if(isAvailable) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    public void executeDisplay(){
        detailInstructions = getActivity().findViewById(R.id.detail_instruction);
        detailInstructions.setText(MainActivity.mainList.get(RecipeListFragment.indexSteps).getDescription().get(VideoPlayerFragment.videoIndex));
    }


    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }


}

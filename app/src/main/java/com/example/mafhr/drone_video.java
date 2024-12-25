package com.example.mafhr;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mafhr.databinding.FragmentDroneVideoBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link drone_video#newInstance} factory method to
 * create an instance of this fragment.
 */
public class drone_video extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainActivity mainActivity;

    public drone_video() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment drone_video.
     */
    // TODO: Rename and change types and number of parameters

    private static final String TAG = "DroneVideo";
    private static String VIDEO_API_URL;
    private static VideoView videoView;
    public static drone_video newInstance(String param1, String param2) {
        drone_video fragment = new drone_video();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MainActivity");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDroneVideoBinding binding = FragmentDroneVideoBinding.inflate(inflater, container, false);

        videoView = binding.getRoot().findViewById(R.id.videoView);
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");
        if(position == 0){
            VIDEO_API_URL = "http://10.0.2.2:5000/get_video/arms_y_shape";
        }
        else{
            VIDEO_API_URL = "http://10.0.2.2:5000/get_video/arms_up";
        }
        // Download video from API
        downloadVideo(videoFile -> {
            if (videoFile != null) {
                Uri videoUri = Uri.fromFile(videoFile);
                videoView.setVideoURI(videoUri);
                videoView.start();
            } else {
                Log.e(TAG, "Failed to download video");
            }
        });

        return binding.getRoot();
    }

    private void downloadVideo(OnVideoDownloadedListener listener) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(VIDEO_API_URL).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "Video download failed: " + e.getMessage());
                listener.onDownloaded(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Save the video file locally
                    File videoFile = new File(mainActivity.getExternalFilesDir(null), "downloaded_video.mp4");
                    Log.d(TAG, "Video downloaded to: " + videoFile.getAbsolutePath());

                    try (FileOutputStream fos = new FileOutputStream(videoFile)) {
                        fos.write(response.body().bytes());
                    }

                    // Update the VideoView on the main thread
                    requireActivity().runOnUiThread(() -> {
                        Uri videoUri = Uri.fromFile(videoFile);
                        videoView.setVideoURI(videoUri);
                        videoView.start();
                    });
                } else {
                    // Handle unsuccessful response on the main thread
                    requireActivity().runOnUiThread(() -> {
                        Toast.makeText(mainActivity, "Video download failed: " + response.message(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }



    private interface OnVideoDownloadedListener {
        void onDownloaded(File videoFile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbarToolbar);

        // Attach the toolbar to the activity's action bar
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        // Configure the NavController
        NavController navController = NavHostFragment.findNavController(this);
        NavigationUI.setupWithNavController(toolbar, navController);

        // Optional: Add navigation button behavior
        toolbar.setNavigationOnClickListener(v -> {
            if (!navController.navigateUp()) {
                requireActivity().onBackPressed();
            }
        });
    }
}
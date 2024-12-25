package com.example.mafhr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mafhr.databinding.FragmentAdminDashboardBinding;
import com.example.mafhr.databinding.FragmentDroneBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link drone#newInstance} factory method to
 * create an instance of this fragment.
 */
public class drone extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public drone() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment drone.
     */
    // TODO: Rename and change types and number of parameters
    public static drone newInstance(String param1, String param2) {
        drone fragment = new drone();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Use Data Binding to inflate the layout
        FragmentDroneBinding binding = FragmentDroneBinding.inflate(inflater, container, false);

        List<String> textList = new ArrayList<>();
        textList.add("Drone 1");
        textList.add("Drone 2");
        textList.add("Drone 3");
        textList.add("Drone 4");

        List<Integer> imgList = new ArrayList<>();
        imgList.add(R.drawable.img_airplane);
        imgList.add(R.drawable.img_airplane);
        imgList.add(R.drawable.img_airplane);
        imgList.add(R.drawable.img_airplane);

        NavController navController = NavHostFragment.findNavController(this);
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.recyclerGriddronetext);
        RecyclerView.Adapter adapter = new DroneAdapter(textList,imgList,navController);
        recyclerView.setAdapter(adapter);

        setHasOptionsMenu(true);

        // Return the root view from the binding
        return binding.getRoot();
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
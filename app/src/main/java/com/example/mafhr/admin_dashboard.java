package com.example.mafhr;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mafhr.databinding.FragmentAdminDashboardBinding;

import java.util.ArrayList;
import java.util.List;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link admin_dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class admin_dashboard extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public admin_dashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment admin_dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static admin_dashboard newInstance(String param1, String param2) {
        admin_dashboard fragment = new admin_dashboard();
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
        FragmentAdminDashboardBinding binding = FragmentAdminDashboardBinding.inflate(inflater, container, false);

        List<String> textList = new ArrayList<>();
        textList.add("Drone");
        textList.add("Camera");
        textList.add("Staff");
        textList.add("Incident");

        List<Integer> imgList = new ArrayList<>();
        imgList.add(R.drawable.img_airplane);
        imgList.add(R.drawable.camera_icon);
        imgList.add(R.drawable.staff_icon);
        imgList.add(R.drawable.bell_icon);

        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.recyclerGriddronetext);
        RecyclerView.Adapter adapter = new MyAdapter(textList,imgList);
        recyclerView.setAdapter(adapter);

        setHasOptionsMenu(true);

        // Return the root view from the binding
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the Toolbar in the fragment layout
        Toolbar toolbar = view.findViewById(R.id.toolbarToolbar);

        // Get the NavController
        NavController navController = NavHostFragment.findNavController(this);

        // Set up the Toolbar with the NavController
        NavigationUI.setupWithNavController(toolbar, navController);

        // Optional: Handle custom toolbar actions (if required)
        toolbar.setNavigationOnClickListener(v -> {
            // Handle back navigation or other behavior
            navController.navigateUp();
        });

        toolbar.setNavigationOnClickListener(v -> {
            // Navigate up in the NavController stack
            if (!navController.navigateUp()) {
                // Handle custom behavior if navigation is not possible
                requireActivity().onBackPressed();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
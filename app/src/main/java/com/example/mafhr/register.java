package com.example.mafhr;

import static com.example.mafhr.MainActivity.database;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mafhr.databinding.FragmentLoginBinding;
import com.example.mafhr.databinding.FragmentRegisterBinding;
import com.example.mafhr.ui.login.LoginViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class register extends Fragment {

    private FragmentRegisterBinding binding;
    private final HashMap<String, String> userCredentials = new HashMap<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(binding.username.getText()).trim();
                String password = String.valueOf(binding.password.getText()).trim();

                if (validateInput(username, password)) {
                    User newUser = new User(username, password);

                    // Insert user in a background thread
                    Executors.newSingleThreadExecutor().execute(() -> {
                        database.userDao().insertUser(newUser);
                    });
                    showResult(R.string.register_success);
                } else {
                    showResult(R.string.register_failed);
                }
            }

            private boolean validateInput(String username, String password) {
                boolean hasError = false;

                if (username.isEmpty()) {
                    binding.username.setError("Username is required");
                    hasError = true;
                } else if (!isValidEmail(username)) {
                    binding.username.setError("Invalid email format");
                    hasError = true;
                }

                if (password.isEmpty()) {
                    binding.password.setError("Password is required");
                    hasError = true;
                } else if (password.length() < 6) {
                    binding.password.setError("Password must be at least 6 characters");
                    hasError = true;
                } else if (!hasValidPasswordStrength(password)) {
                    binding.password.setError("Password must contain an uppercase letter, a number, and a special character");
                    hasError = true;
                }

                return !hasError;
            }

            private boolean authenticateUser(String username, String password) {
                return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
            }

            private boolean isValidEmail(String email) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
            }

            private boolean hasValidPasswordStrength(String password) {
                return password.matches(".*[A-Z].*") &&
                        password.matches(".*[0-9].*") &&
                        password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
            }

            private void showResult(@StringRes int stringVal) {
                if (getContext() != null && getContext().getApplicationContext() != null) {
                    Toast.makeText(getContext().getApplicationContext(), getString(stringVal), Toast.LENGTH_LONG).show();
                }
            }


        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_register_to_loginFragment);
            }
        });

        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
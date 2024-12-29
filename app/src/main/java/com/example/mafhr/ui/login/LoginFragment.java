package  com.example.mafhr.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mafhr.MainActivity;
import com.example.mafhr.User;
import com.example.mafhr.UserViewModel;
import com.example.mafhr.databinding.FragmentLoginBinding;

import com.example.mafhr.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static com.example.mafhr.MainActivity.currentUser;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;

    private MainActivity mainActivity;

    private FragmentLoginBinding binding;
    private final HashMap<String, String> userCredentials = new HashMap<>();
    private UserViewModel userViewModel;
    private List<User> users = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

    binding = FragmentLoginBinding.inflate(inflater, container, false);
    userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

    binding.btnAdmin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Get input values from the fields
            List<String> usernames = new ArrayList<>();
            LiveData<List<User>> usersLiveData = userViewModel.getUsers();
            users = usersLiveData.getValue();
            for (int index = 0; index < users.size(); index++) {
                User user = users.get(index);
                if (user.getUsername() != null) {
                    usernames.add(user.getUsername());
                    if (binding.username.getText().toString().equals(user.getUsername())){
                        currentUser = user;
                    }
                    else{
                        showResult(R.string.login_failed);
                    }
                }
            }

            if (usernames.contains(binding.username.getText().toString())) {
                User user = currentUser;
                if (user.getPassword().equals(binding.password.getText().toString())) {
                    currentUser = user;
                    Log.i("AshLogin","Logged in");
                    Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_admin_dashboard);

                }
                else{
                    showResult(R.string.login_failed);
                }
            }

        }


    });

    binding.btnRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_register);
        }
    });

      return binding.getRoot();

    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MainActivity");
        }
    }

    private void showResult(@StringRes int stringVal) {
        if (getContext() != null && getContext().getApplicationContext() != null) {
            Toast.makeText(getContext().getApplicationContext(), getString(stringVal), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
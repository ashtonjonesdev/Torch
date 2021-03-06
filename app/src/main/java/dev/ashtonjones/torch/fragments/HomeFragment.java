package dev.ashtonjones.torch.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentHomeBinding;
import dev.ashtonjones.torch.datalayer.viewmodel.HomeFragmentViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeFragmentViewModel viewModel;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();

    }

    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);

    }


    @Override
    public void onResume() {

        super.onResume();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser == null) {

            Navigation.findNavController(getView()).navigate(R.id.action_global_sign_in_nav_graph);

        }

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            viewModel.getTorchMessageLiveData().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String torchMessage) {
                    binding.torchMessageTextView.setText(torchMessage);
                }
            });

        }

    }
}

package dev.ashtonjones.torch.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentTorchDiscoveryAnswersSummaryBinding;
import dev.ashtonjones.torch.datalayer.viewmodel.TorchDiscoveryAnswersSummaryViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TorchDiscoveryAnswersSummaryFragment extends Fragment {

    private TorchDiscoveryAnswersSummaryViewModel viewModel;
    private FragmentTorchDiscoveryAnswersSummaryBinding binding;

    public TorchDiscoveryAnswersSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTorchDiscoveryAnswersSummaryBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();
    }

    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(TorchDiscoveryAnswersSummaryViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {

            viewModel.getDiscoveryAnswerOneLiveData().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    binding.discoverySummaryAnswerOneTextView.setText(s);
                }
            });

            viewModel.getDiscoveryAnswerTwoLiveData().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    binding.discoverySummaryAnswerTwoTextView.setText(s);
                }
            });

            viewModel.getDiscoveryAnswerThreeLiveData().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    binding.discoverySummaryAnswerThreeTextView.setText(s);
                }
            });

        }

    }
}

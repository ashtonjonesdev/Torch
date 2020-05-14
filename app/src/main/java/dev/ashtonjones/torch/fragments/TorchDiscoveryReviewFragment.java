package dev.ashtonjones.torch.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentTorchDiscoveryReviewBinding;
import dev.ashtonjones.torch.datalayer.viewmodel.TorchDiscoveryReviewViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TorchDiscoveryReviewFragment extends Fragment {

    private FragmentTorchDiscoveryReviewBinding binding;
    private TorchDiscoveryReviewViewModel viewModel;

    public TorchDiscoveryReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTorchDiscoveryReviewBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();

        binding.torchDiscoverySummaryFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.set_torch_fragment_dest);
            }
        });

    }

    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(TorchDiscoveryReviewViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.getDiscoveryAnswerOneLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s != null && s.length() != 0) {

                    binding.discoveryReviewAnswerOneTextView.setText(s);

                }
            }
        });

        viewModel.getDiscoveryAnswerTwoLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s != null && s.length() != 0) {

                    binding.discoveryReviewAnswerTwoTextView.setText(s);

                }
            }
        });

        viewModel.getDiscoveryAnswerThreeLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s != null && s.length() != 0) {

                    binding.discoveryReviewAnswerThreeTextView.setText(s);

                }
            }
        });


    }
}

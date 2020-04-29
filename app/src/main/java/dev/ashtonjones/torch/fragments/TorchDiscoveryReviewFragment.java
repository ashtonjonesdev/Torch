package dev.ashtonjones.torch.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentTorchDiscoveryReviewBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class TorchDiscoveryReviewFragment extends Fragment {

    private FragmentTorchDiscoveryReviewBinding binding;

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

        binding.torchDiscoveryReviewContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).popBackStack(R.id.torch_discovery_nav_graph, true);
            }
        });

    }
}

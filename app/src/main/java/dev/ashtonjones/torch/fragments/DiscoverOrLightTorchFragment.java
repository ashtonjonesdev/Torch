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
import dev.ashtonjones.torch.databinding.FragmentDiscoverOrLightTorchBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverOrLightTorchFragment extends Fragment {

    private FragmentDiscoverOrLightTorchBinding binding;

    public DiscoverOrLightTorchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDiscoverOrLightTorchBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setOnClickListeners();
    }

    private void setOnClickListeners() {

        binding.discoverYourTorchButtonDiscoverOrLightTorch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.torch_discovery_nav_graph);
            }
        });

        binding.lightYourTorchButtonDiscoverOrLightTorch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.set_torch_fragment_dest);
            }
        });

    }
}

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
import dev.ashtonjones.torch.databinding.FragmentTorchDiscoveryIntroductionBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class TorchDiscoveryIntroductionFragment extends Fragment {

    FragmentTorchDiscoveryIntroductionBinding binding;

    public TorchDiscoveryIntroductionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTorchDiscoveryIntroductionBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.torchDiscoveryIntroductionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_torch_discovery_introduction_fragment_dest_to_torch_discovery_step_1_fragment_dest);
            }
        });

    }
}

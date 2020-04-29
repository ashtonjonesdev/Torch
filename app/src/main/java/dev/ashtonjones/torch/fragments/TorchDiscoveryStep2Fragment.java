package dev.ashtonjones.torch.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentTorchDiscoveryStep2Binding;
import dev.ashtonjones.torch.datalayer.viewmodel.TorchDiscoveryStep2ViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TorchDiscoveryStep2Fragment extends Fragment {

    private FragmentTorchDiscoveryStep2Binding binding;
    private TorchDiscoveryStep2ViewModel viewModel;

    public TorchDiscoveryStep2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTorchDiscoveryStep2Binding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();

        binding.torchDiscoveryStep2ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.textInputEditTextTorchDiscoveryStep2.getText() != null && binding.textInputEditTextTorchDiscoveryStep2.getText().toString().length() != 0) {

                    viewModel.updateTorchDiscoveryTwoAnswer(binding.textInputEditTextTorchDiscoveryStep2.getText().toString());


                    Navigation.findNavController(getView()).navigate(R.id.action_torch_discovery_step_2_fragment_dest_to_torchDiscoveryStep3Fragment);

                }

                else {

                    Navigation.findNavController(getView()).navigate(R.id.action_torch_discovery_step_2_fragment_dest_to_torchDiscoveryStep3Fragment);


                }

            }
        });
    }

    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(TorchDiscoveryStep2ViewModel.class);

    }
}

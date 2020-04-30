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
import android.widget.Toast;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentTorchDiscoveryStep1Binding;
import dev.ashtonjones.torch.datalayer.viewmodel.TorchDiscoveryStep1ViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TorchDiscoveryStep1Fragment extends Fragment {

    private FragmentTorchDiscoveryStep1Binding binding;
    private TorchDiscoveryStep1ViewModel viewModel;

    public TorchDiscoveryStep1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTorchDiscoveryStep1Binding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();

        binding.torchDiscoveryStep1ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.textInputEditTextTorchDiscoveryStep1.getText() != null && binding.textInputEditTextTorchDiscoveryStep1.getText().toString().length() != 0) {

                    // Save answer to database
                    viewModel.updateTorchDiscoveryOneAnswer(binding.textInputEditTextTorchDiscoveryStep1.getText().toString());

                    // Navigate to next screen
                    Navigation.findNavController(getView()).navigate(R.id.action_torch_discovery_step_1_fragment_dest_to_torch_discovery_step_2_fragment_dest);

                }

                // if no answer was provided, just navigate to the next screen
                else {

                    // Navigate to next screen
                    Navigation.findNavController(getView()).navigate(R.id.action_torch_discovery_step_1_fragment_dest_to_torch_discovery_step_2_fragment_dest);

                }

            }
        });
    }

    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(TorchDiscoveryStep1ViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.getDiscoveryAnswerOneLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s != null && s.length() != 0) {

                    binding.textInputEditTextTorchDiscoveryStep1.setText(s);

                }
            }
        });
    }
}

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
import dev.ashtonjones.torch.databinding.FragmentTorchDiscoveryStep3Binding;
import dev.ashtonjones.torch.datalayer.viewmodel.TorchDiscoveryStep3ViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TorchDiscoveryStep3Fragment extends Fragment {

    private FragmentTorchDiscoveryStep3Binding binding;
    private TorchDiscoveryStep3ViewModel viewModel;

    public TorchDiscoveryStep3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     binding = FragmentTorchDiscoveryStep3Binding.inflate(getLayoutInflater(), container,false);

     return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();

        binding.torchDiscoveryStep3ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.textInputEditTextTorchDiscoveryStep3.getText() != null && binding.textInputEditTextTorchDiscoveryStep3.getText().toString().length() != 0) {

                    viewModel.updateTorchDiscoveryThreeAnswer(binding.textInputEditTextTorchDiscoveryStep3.getText().toString());


                    Navigation.findNavController(getView()).navigate(R.id.action_torch_discovery_step_3_fragment_dest_to_torch_discovery_review_fragment_dest);


                }

                else {

                    Navigation.findNavController(getView()).navigate(R.id.action_torch_discovery_step_3_fragment_dest_to_torch_discovery_review_fragment_dest);

                }

            }
        });

    }

    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(TorchDiscoveryStep3ViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.getDiscoveryAnswerThreeLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s != null && s.length() != 0) {

                    binding.textInputEditTextTorchDiscoveryStep3.setText(s);

                }
            }
        });
    }
}

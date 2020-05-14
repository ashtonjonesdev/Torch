package dev.ashtonjones.torch.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentChangeTorchBinding;
import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.viewmodel.ChangeTorchViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeTorch extends Fragment {

    private FragmentChangeTorchBinding binding;
    private ChangeTorchViewModel viewModel;

    public ChangeTorch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChangeTorchBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();

        setClickListeners();
    }

    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(ChangeTorchViewModel.class);

    }

    private void setClickListeners() {

        binding.changeYourTorchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.textInputEditTextTorchChangeTorch.getText() != null && binding.textInputEditTextTorchChangeTorch.length() >0 ) {

                    // Update torchMessage in database
                    String newTorchMessage = binding.textInputEditTextTorchChangeTorch.getText().toString();

                    viewModel.updateTorchMessage(newTorchMessage);

                    // Change drawable from unlit to lit
                    binding.unlitTorchSetTorch.setImageResource(R.drawable.app_icon_torch);

                    // Change text of main text view
                    binding.changeYourTorchTextView.setText("Your torch is lit!");

                    // Navigate back to Home Fragment after a delay
                    Handler delayHandler = new Handler();

                    delayHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Navigation.findNavController(getView()).navigate(R.id.torch_discovery_answers_summary_fragment_dest);
                        }
                    }, 2000);


                }

                else {

                    Toast.makeText(getContext(), "Please set your new torch", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}

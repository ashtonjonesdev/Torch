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
import android.widget.Toast;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentCheckInBinding;
import dev.ashtonjones.torch.datalayer.viewmodel.CheckInViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckInFragment extends Fragment {

    private FragmentCheckInBinding binding;
    private boolean buttonWasSelected;
    private boolean yesButtonSelected;
    private boolean noButtonSelected;

    private CheckInViewModel viewModel;


    public CheckInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCheckInBinding.inflate(getLayoutInflater(),container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();

        setOnClickListeners();
    }

    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(CheckInViewModel.class);

    }

    private void setOnClickListeners() {

        binding.yesButtonCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.yesButtonCheckIn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                binding.yesButtonCheckIn.setTextColor(getResources().getColor(R.color.colorMediumEmphasisWhite));
                binding.noButtonCheckIn.setBackgroundColor(getResources().getColor(R.color.colorSurface));
                binding.noButtonCheckIn.setTextColor(getResources().getColor(R.color.colorPrimaryLight));


                buttonWasSelected = true;
                yesButtonSelected = true;
                noButtonSelected = false;

                binding.imageViewSmileyCheckIn.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_48dp);

            }
        });

        binding.noButtonCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.noButtonCheckIn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
                binding.noButtonCheckIn.setTextColor(getResources().getColor(R.color.colorMediumEmphasisWhite));
                binding.yesButtonCheckIn.setBackgroundColor(getResources().getColor(R.color.colorSurface));
                binding.yesButtonCheckIn.setTextColor(getResources().getColor(R.color.colorPrimaryLight));

                buttonWasSelected = true;
                noButtonSelected = true;
                yesButtonSelected = false;

                binding.imageViewSmileyCheckIn.setImageResource(R.drawable.ic_sentiment_neutral_black_48dp);
            }
        });

        binding.saveFabCheckIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!buttonWasSelected) {

                    Toast.makeText(getContext(), "Please select yes or no", Toast.LENGTH_SHORT).show();

                    return;

                }

                if (yesButtonSelected) {

                    Toast.makeText(getContext(), "Yes button was selected!", Toast.LENGTH_SHORT).show();

                    viewModel.incrementNumberOfDaysAlignedLiveData();

                }

                else {

                    Toast.makeText(getContext(), "No  button was selected!", Toast.LENGTH_SHORT).show();


                }

                Navigation.findNavController(getView()).popBackStack();
            }
        });

    }
}

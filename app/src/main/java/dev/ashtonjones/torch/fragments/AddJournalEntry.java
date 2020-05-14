package dev.ashtonjones.torch.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentAddJournalEntryBinding;
import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.viewmodel.AddJournalEntryViewModel;
import dev.ashtonjones.torch.datamodels.JournalEntry;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddJournalEntry extends Fragment {

    private static final String LOG_TAG = AddJournalEntry.class.getSimpleName();

    FragmentAddJournalEntryBinding binding;
    private AddJournalEntryViewModel viewModel;
    private boolean actionsAligned;
    private int levelOfAlignment;
    private boolean buttonWasSelected;

    private JournalEntry journalEntry;

    public AddJournalEntry() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddJournalEntryBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();

        binding.sliderLevelOfAlignment.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                int intValue = (int) value;
                switch (intValue) {

                    case 1:
                        binding.sliderFaceEmojiImageView.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_48dp);
                        levelOfAlignment = 1;
                        break;
                    case 2:
                        binding.sliderFaceEmojiImageView.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_48dp);
                        levelOfAlignment = 2;
                        break;
                    case 3:
                        binding.sliderFaceEmojiImageView.setImageResource(R.drawable.ic_sentiment_neutral_black_48dp);
                        levelOfAlignment = 3;
                        break;
                    case 4:
                        binding.sliderFaceEmojiImageView.setImageResource(R.drawable.ic_sentiment_satisfied_black_48dp);
                        levelOfAlignment = 4;
                        break;
                    case 5:
                        binding.sliderFaceEmojiImageView.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_48dp);
                        levelOfAlignment = 5;
                        break;
                    default:
                        Log.d(LOG_TAG, "Error in slider selection");
                        break;



                }
            }
        });

        binding.yesButtonAddJournalEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.yesButtonAddJournalEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary200));

                binding.noButtonAddJournalEntry.setBackgroundColor(getResources().getColor(R.color.colorSurface));

                buttonWasSelected = true;

                actionsAligned = true;

            }
        });

        binding.noButtonAddJournalEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.noButtonAddJournalEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary200));

                binding.yesButtonAddJournalEntry.setBackgroundColor(getResources().getColor(R.color.colorSurface));

                buttonWasSelected = true;

                actionsAligned = false;

            }
        });

        binding.saveJournalEntryFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!buttonWasSelected) {

                    Toast.makeText(getContext(), "Please select yes or no", Toast.LENGTH_LONG).show();

                    return;

                }

                createJournalEntry();
                saveJournalEntry();
                Navigation.findNavController(getView()).popBackStack();
            }
        });

    }


    private void createJournalEntry() {

        journalEntry = new JournalEntry();

        if(binding.textInputEditTextNoteAddJournalEntry.getText() != null&& binding.textInputEditTextNoteAddJournalEntry.getText().toString().length() != 0 ) {

            journalEntry.setJournalMessage(binding.textInputEditTextNoteAddJournalEntry.getText().toString());

        }


        journalEntry.setActionsAligned(actionsAligned);

        journalEntry.setLevelOfAlignment(levelOfAlignment);

        Calendar calendar = Calendar.getInstance();

        int monthInt = calendar.get(Calendar.MONTH);

        int dayInt = calendar.get(Calendar.DATE);

        int yearInt = calendar.get(Calendar.YEAR);

        String currentDate = monthInt + "/" + dayInt + "/" + yearInt;

        Log.d(LOG_TAG, "Current Year: " + yearInt + " Current month: " + monthInt + " Current day: " + dayInt);

        Log.d(LOG_TAG, currentDate);

        journalEntry.setDate(currentDate);








    }

    private void saveJournalEntry() {

        FirebaseRepository firebaseRepository = new FirebaseRepository();

        firebaseRepository.addJournalEntry(journalEntry);

    }


    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(AddJournalEntryViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.getTorchMessageLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.torchMessageTextViewAddJournalEntry.setText(s);
            }
        });

    }
}

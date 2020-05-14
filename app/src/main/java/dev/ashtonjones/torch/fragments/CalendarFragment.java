package dev.ashtonjones.torch.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.slider.Slider;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentAddJournalEntryBinding;
import dev.ashtonjones.torch.databinding.FragmentCalendarBinding;
import dev.ashtonjones.torch.ui.CalendarDecorator;
import dev.ashtonjones.torch.ui.EventDecorator;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment implements OnDateSelectedListener {

    private static final String LOG_TAG = CalendarFragment.class.getSimpleName();

    private FragmentCalendarBinding binding;
    private FragmentAddJournalEntryBinding bindingCustomDialogView;

    private boolean actionsAligned;
    private int levelOfAlignment;
    private boolean buttonWasSelected;

    private MaterialAlertDialogBuilder materialAlertDialogBuilder;

    private final CalendarDecorator oneDayDecorator = new CalendarDecorator();


    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentCalendarBinding.inflate(getLayoutInflater(),container,false);

       bindingCustomDialogView = FragmentAddJournalEntryBinding.inflate(getLayoutInflater(), container, false);

       return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.calendarView.setOnDateChangedListener(this);

        org.threeten.bp.LocalDate temp = LocalDate.now();


        ArrayList<CalendarDay> dates = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            final CalendarDay day = CalendarDay.from(temp);
            dates.add(day);
        }

        binding.calendarView.addDecorator(new EventDecorator(R.color.colorPrimary200, dates));


        binding.addEntryFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setUpCustomDialogComponents();

                setUpAlertDialog();

                showAlertDialog();




            }
        });



    }

    private void setUpCustomDialogComponents() {

        bindingCustomDialogView.sliderLevelOfAlignment.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                int intValue = (int) value;
                switch (intValue) {

                    case 1:
                        bindingCustomDialogView.sliderFaceEmojiImageView.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_48dp);
                        levelOfAlignment = 1;
                        break;
                    case 2:
                        bindingCustomDialogView.sliderFaceEmojiImageView.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_48dp);
                        levelOfAlignment = 2;
                        break;
                    case 3:
                        bindingCustomDialogView.sliderFaceEmojiImageView.setImageResource(R.drawable.ic_sentiment_neutral_black_48dp);
                        levelOfAlignment = 3;
                        break;
                    case 4:
                        bindingCustomDialogView.sliderFaceEmojiImageView.setImageResource(R.drawable.ic_sentiment_satisfied_black_48dp);
                        levelOfAlignment = 4;
                        break;
                    case 5:
                        bindingCustomDialogView.sliderFaceEmojiImageView.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_48dp);
                        levelOfAlignment = 5;
                        break;
                    default:
                        Log.d(LOG_TAG, "Error in slider selection");
                        break;



                }
            }
        });

        bindingCustomDialogView.yesButtonAddJournalEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindingCustomDialogView.yesButtonAddJournalEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary200));

                bindingCustomDialogView.noButtonAddJournalEntry.setBackgroundColor(getResources().getColor(R.color.colorSurface));

                buttonWasSelected = true;

                actionsAligned = true;

            }
        });

        bindingCustomDialogView.noButtonAddJournalEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindingCustomDialogView.noButtonAddJournalEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary200));

                bindingCustomDialogView.yesButtonAddJournalEntry.setBackgroundColor(getResources().getColor(R.color.colorSurface));

                buttonWasSelected = true;

                actionsAligned = false;

            }
        });

    }

    private void showAlertDialog() {

        materialAlertDialogBuilder.show();


    }

    private void setUpAlertDialog() {

        materialAlertDialogBuilder = new MaterialAlertDialogBuilder(getContext()).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();


            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        materialAlertDialogBuilder.setView(bindingCustomDialogView.getRoot());

        materialAlertDialogBuilder.setTitle("Reflect");

    }

    @Override
    public void onResume() {
        super.onResume();



    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {





    }
}

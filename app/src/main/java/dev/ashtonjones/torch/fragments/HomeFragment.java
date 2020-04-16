package dev.ashtonjones.torch.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import dev.ashtonjones.torch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ImageView torchImageView;
    MaterialTextView guidingLightTextView;
    MaterialTextView goalFocusTextView;
    SpeedDialView speedDialFAB;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();

        setUpSpeedDialFAB();
    }


    private void initViews() {

        torchImageView = getView().findViewById(R.id.torch_image_view);

        guidingLightTextView = getView().findViewById(R.id.guiding_light_text_view);

//        goalFocusTextView = getView().findViewById(R.id.goal_focus_text_view);

        speedDialFAB = getView().findViewById(R.id.speed_dial_fab);

    }


    private void setUpSpeedDialFAB() {

        speedDialFAB.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_change_guiding_light_action, R.drawable.ic_call_split_black_24dp).create());

        speedDialFAB.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_check_in_action, R.drawable.ic_format_align_left_black_24dp).create());

        speedDialFAB.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                return false;
            }

            @Override
            public void onToggleChanged(boolean isOpen) {

            }
        });


        speedDialFAB.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {

                switch (actionItem.getId()) {

                    case R.id.fab_change_guiding_light_action:

                        Toast.makeText(getContext(), "Change Guiding Light action clicked!", Toast.LENGTH_SHORT).show();

                    case R.id.fab_check_in_action:

                        Toast.makeText(getContext(), "Check in action clicked!", Toast.LENGTH_SHORT).show();

                        return false;

                    default:

                        Toast.makeText(getContext(), "Error in SpeedDial Fab action", Toast.LENGTH_SHORT).show();

                        return false;

                }

            }
        });


    }
}

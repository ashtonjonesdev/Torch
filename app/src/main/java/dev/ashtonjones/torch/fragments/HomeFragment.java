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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentHomeBinding;
import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.viewmodel.HomeFragmentViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeFragmentViewModel viewModel;

    public HomeFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();

        setUpSpeedDialFAB();
    }

    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);

    }


    private void setUpSpeedDialFAB() {

        binding.speedDialFab.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_change_torch_action, R.drawable.ic_call_split_black_24dp).setLabel("Change torch").create());

        binding.speedDialFab.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_journal_action, R.drawable.ic_format_align_left_black_24dp).setLabel("View journal").create());

        binding.speedDialFab.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                return false;
            }

            @Override
            public void onToggleChanged(boolean isOpen) {

            }
        });


        binding.speedDialFab.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {

                switch (actionItem.getId()) {

                    case R.id.fab_change_torch_action:

                        Toast.makeText(getContext(), "Change torch action clicked!", Toast.LENGTH_SHORT).show();

                        Navigation.findNavController(getView()).navigate(R.id.change_torch_fragment_dest);

                    case R.id.fab_journal_action:

                        Toast.makeText(getContext(), "Journal action clicked!", Toast.LENGTH_SHORT).show();

                        return false;

                    default:

                        Toast.makeText(getContext(), "Error in SpeedDial Fab action", Toast.LENGTH_SHORT).show();

                        Navigation.findNavController(getView()).navigate(R.id.journal_fragment_dest);

                        return false;

                }

            }
        });


    }

    @Override
    public void onResume() {

        super.onResume();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser == null) {

            Navigation.findNavController(getView()).navigate(R.id.action_global_sign_in_nav_graph);

        }

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            viewModel.getTorchMessageLiveData().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String torchMessage) {
                    binding.torchMessageTextView.setText(torchMessage);
                }
            });

        }

    }
}

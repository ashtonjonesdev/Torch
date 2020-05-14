package dev.ashtonjones.torch.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.databinding.FragmentProgressBinding;
import dev.ashtonjones.torch.datalayer.viewmodel.ProgressViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressFragment extends Fragment {

    private FragmentProgressBinding binding;
    private ProgressViewModel viewModel;

    public ProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProgressBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();

//        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_progress, container, false);
//
//        binding.setLifecycleOwner(this);
//
//        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();

        binding.checkInButtonProgressFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.check_in_fragment_dest);

            }
        });
    }

    private void setUpViewModel() {

        viewModel = new ViewModelProvider(this).get(ProgressViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.getNumberOfDaysAlignedLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                binding.daysAlignedValueTextView.setText(aLong.toString());
            }
        });
    }
}

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

                if(aLong >= 0 && aLong < 20) {

                    binding.person1DrawableProgress.setVisibility(View.VISIBLE);

                }

                if(aLong >= 20 && aLong < 40 ) {

                    binding.person1DrawableProgress.setVisibility(View.INVISIBLE);

                    binding.person2DrawableProgress.setVisibility(View.VISIBLE);

                }

                if(aLong >= 40 && aLong < 60 ) {

                    binding.person2DrawableProgress.setVisibility(View.INVISIBLE);

                    binding.person3DrawableProgress.setVisibility(View.VISIBLE);


                }

                if(aLong >= 60 && aLong < 80) {

                    binding.person3DrawableProgress.setVisibility(View.INVISIBLE);

                    binding.person4DrawableProgress.setVisibility(View.VISIBLE);

                }

                ;if(aLong >= 80 && aLong < 100) {

                    binding.person4DrawableProgress.setVisibility(View.INVISIBLE);

                    binding.person5DrawableProgress.setVisibility(View.VISIBLE);

                }

                if(aLong >= 100) {

                    binding.person5DrawableProgress.setVisibility(View.INVISIBLE);

                    binding.person6DrawableProgress.setVisibility(View.VISIBLE);

                }

            }
        });
    }
}

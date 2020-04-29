package dev.ashtonjones.torch.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.ashtonjones.torch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TorchDiscoveryAnswersSummaryFragment extends Fragment {

    public TorchDiscoveryAnswersSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_torch_discovery_answers_summary, container, false);
    }
}

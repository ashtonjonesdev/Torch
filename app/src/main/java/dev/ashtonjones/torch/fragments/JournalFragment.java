//package dev.ashtonjones.torch.fragments;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.navigation.Navigation;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//import dev.ashtonjones.torch.R;
//import dev.ashtonjones.torch.adapters.SelectableItemBinderJournalEntry;
//import dev.ashtonjones.torch.databinding.FragmentJournalBinding;
//import dev.ashtonjones.torch.datalayer.viewmodel.JournalFragmentViewModel;
//import dev.ashtonjones.torch.datamodels.JournalEntry;
//import mva2.adapter.ListSection;
//import mva2.adapter.MultiViewAdapter;
//import mva2.adapter.util.Mode;
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class JournalFragment extends Fragment {
//
//    private FragmentJournalBinding binding;
//
//    private MultiViewAdapter multiViewAdapter;
//
//    private ListSection<JournalEntry> listSection;
//
//    private JournalFragmentViewModel viewModel;
//
//    public JournalFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        binding = FragmentJournalBinding.inflate(getLayoutInflater(), container, false);
//
//        return binding.getRoot();
//
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        initViews();
//
//        setUpViewModel();
//
//        // SETUP MULTIVIEWADAPTER
//        setUpMultiViewAdapter();
//
//        // SETUP RECYCLERVIEW
//        setUpRecyclerView();
//
//        binding.addJournalEntryFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Navigation.findNavController(getView()).navigate(R.id.add_journal_entry_fragment_dest);
//
//            }
//        });
//
//    }
//
//    private void setUpViewModel() {
//
//        viewModel = new ViewModelProvider(this).get(JournalFragmentViewModel.class);
//
//    }
//
//    private void initViews() {
//    }
//
//    public void setUpRecyclerView() {
//
//        // Create a GridLayoutManager for the RecyclerView
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//
//        // Set the layout manager for the RecyclerView
//        binding.recyclerViewJournalFragment.setLayoutManager(linearLayoutManager);
//
//        // Connect the MultiViewAdapter to the RecyclerView
//        binding.recyclerViewJournalFragment.setAdapter(multiViewAdapter);
//
//
//    }
//
//    public void setUpMultiViewAdapter() {
//
//        // Initialize the MultiViewAdapter
//        multiViewAdapter = new MultiViewAdapter();
//
//        // Initialize the Sections
//        listSection = new ListSection<>();
//
//        // Initialize the Binders
//        SelectableItemBinderJournalEntry selectableItemBinderMessageCard = new SelectableItemBinderJournalEntry();
//
//        // Register the Binders with the MultiViewAdapter
//        multiViewAdapter.registerItemBinders(selectableItemBinderMessageCard);
//
//        // Add Sections to the MultiViewAdapter
//        multiViewAdapter.addSection(listSection);
//
//        listSection.setSelectionMode(Mode.SINGLE);
//
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        viewModel.getJournalEntriesLiveData().observe(this, new Observer<ArrayList<JournalEntry>>() {
//            @Override
//            public void onChanged(ArrayList<JournalEntry> journalEntries) {
//
//                listSection.set(journalEntries);
//            }
//        });
//
//    }
//}

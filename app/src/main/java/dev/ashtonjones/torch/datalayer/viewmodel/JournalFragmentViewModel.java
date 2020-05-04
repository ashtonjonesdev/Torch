package dev.ashtonjones.torch.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.repository.RepositoryInterface;
import dev.ashtonjones.torch.datamodels.JournalEntry;

public class JournalFragmentViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    private MutableLiveData<ArrayList<JournalEntry>> journalEntriesLiveData;

    public JournalFragmentViewModel() {

        firebaseRepository = new FirebaseRepository();

    }

    public MutableLiveData<ArrayList<JournalEntry>> getJournalEntriesLiveData() {

        journalEntriesLiveData = firebaseRepository.getJournalEntriesLiveData();

        return journalEntriesLiveData;
    }
}

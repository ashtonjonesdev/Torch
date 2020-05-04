package dev.ashtonjones.torch.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.repository.RepositoryInterface;

public class AddJournalEntryViewModel extends ViewModel {

    RepositoryInterface firebaseRespository;

    private MutableLiveData<String> torchMessageLiveData;

    public AddJournalEntryViewModel() {

        firebaseRespository = new FirebaseRepository();

    }

    public MutableLiveData<String> getTorchMessageLiveData() {

        torchMessageLiveData = firebaseRespository.getTorchMessageLiveData();

        return torchMessageLiveData;
    }


}

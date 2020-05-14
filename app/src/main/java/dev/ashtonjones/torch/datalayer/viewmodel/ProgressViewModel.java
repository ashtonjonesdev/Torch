package dev.ashtonjones.torch.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.repository.RepositoryInterface;

public class ProgressViewModel extends ViewModel {

    private MutableLiveData<Long> numberOfDaysAlignedLiveData;

    private RepositoryInterface firebaseRepository;

    public ProgressViewModel() {

        firebaseRepository = new FirebaseRepository();

        numberOfDaysAlignedLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<Long> getNumberOfDaysAlignedLiveData() {

        numberOfDaysAlignedLiveData = firebaseRepository.getNumberOfDaysAlignedLiveData();

        return numberOfDaysAlignedLiveData;
    }


}

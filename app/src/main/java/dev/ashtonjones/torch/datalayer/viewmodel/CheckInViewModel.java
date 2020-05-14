package dev.ashtonjones.torch.datalayer.viewmodel;

import androidx.lifecycle.ViewModel;

import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.repository.RepositoryInterface;

public class CheckInViewModel extends ViewModel {

    private static final String LOG_TAG = CheckInViewModel.class.getSimpleName();

    RepositoryInterface firebaseRepository;

    public CheckInViewModel() {

        firebaseRepository = new FirebaseRepository();

    }

    public void incrementNumberOfDaysAlignedLiveData() {

        firebaseRepository.incrementNumberOfDaysAligned();


    }

}

package dev.ashtonjones.torch.datalayer.viewmodel;

import androidx.lifecycle.ViewModel;

import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.repository.RepositoryInterface;

public class SetTorchViewModel extends ViewModel {

    public  SetTorchViewModel() {
        firebaseRespository = new FirebaseRepository();
    }

    private RepositoryInterface firebaseRespository;

    public void updateTorchMessage(String newTorchMessage) {

        firebaseRespository.updateTorchMessage(newTorchMessage);

    }

    public void resetNumberOfDaysAligned() {

        firebaseRespository.resetNumberOfDaysAligned();

    }
}

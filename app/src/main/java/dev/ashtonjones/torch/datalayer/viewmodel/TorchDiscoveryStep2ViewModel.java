package dev.ashtonjones.torch.datalayer.viewmodel;

import androidx.lifecycle.ViewModel;

import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.repository.RepositoryInterface;

public class TorchDiscoveryStep2ViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    public TorchDiscoveryStep2ViewModel() {

        firebaseRepository = new FirebaseRepository();

    }

    public void updateTorchDiscoveryTwoAnswer(String discoveryTwoAnswer) {

        firebaseRepository.updateTorchDiscoveryOneAnswer(discoveryTwoAnswer);

    }

}

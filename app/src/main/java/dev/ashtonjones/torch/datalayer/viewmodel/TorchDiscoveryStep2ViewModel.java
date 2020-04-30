package dev.ashtonjones.torch.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.repository.RepositoryInterface;

public class TorchDiscoveryStep2ViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    private MutableLiveData<String> discoveryAnswerTwoLiveData;

    public MutableLiveData<String> getDiscoveryAnswerTwoLiveData() {
        discoveryAnswerTwoLiveData = firebaseRepository.getDiscoveryAnswerTwoLiveData();
        return discoveryAnswerTwoLiveData;
    }

    public TorchDiscoveryStep2ViewModel() {

        firebaseRepository = new FirebaseRepository();

    }

    public void updateTorchDiscoveryTwoAnswer(String discoveryTwoAnswer) {

        firebaseRepository.updateTorchDiscoveryTwoAnswer(discoveryTwoAnswer);

    }

}

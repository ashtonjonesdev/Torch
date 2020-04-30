package dev.ashtonjones.torch.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.repository.RepositoryInterface;

public class TorchDiscoveryStep3ViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    private MutableLiveData<String> discoveryAnswerThreeLiveData;

    public MutableLiveData<String> getDiscoveryAnswerThreeLiveData() {
        discoveryAnswerThreeLiveData = firebaseRepository.getDiscoveryAnswerThreeLiveData();
        return discoveryAnswerThreeLiveData;
    }

    public TorchDiscoveryStep3ViewModel() {

        firebaseRepository = new FirebaseRepository();

    }

    public void updateTorchDiscoveryThreeAnswer(String discoveryThreeAnswer) {

        firebaseRepository.updateTorchDiscoveryThreeAnswer(discoveryThreeAnswer);

    }

}

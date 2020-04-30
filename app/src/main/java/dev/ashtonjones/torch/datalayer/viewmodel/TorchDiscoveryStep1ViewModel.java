package dev.ashtonjones.torch.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.repository.RepositoryInterface;

public class TorchDiscoveryStep1ViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    private MutableLiveData<String> discoveryAnswerOneLiveData;

    public MutableLiveData<String> getDiscoveryAnswerOneLiveData() {

        discoveryAnswerOneLiveData = firebaseRepository.getDiscoveryAnswerOneLiveData();

        return discoveryAnswerOneLiveData;
    }

    public TorchDiscoveryStep1ViewModel() {

        firebaseRepository = new FirebaseRepository();

    }

    public void updateTorchDiscoveryOneAnswer(String discoveryOneAnswer) {

        firebaseRepository.updateTorchDiscoveryOneAnswer(discoveryOneAnswer);

    }
}

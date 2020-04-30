package dev.ashtonjones.torch.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.repository.RepositoryInterface;

public class TorchDiscoveryReviewViewModel extends ViewModel {

    private RepositoryInterface firebaseRepository;

    private MutableLiveData<String> discoveryAnswerOneLiveData;

    private MutableLiveData<String> discoveryAnswerTwoLiveData;

    private MutableLiveData<String> discoveryAnswerThreeLiveData;

    public TorchDiscoveryReviewViewModel() {

        firebaseRepository = new FirebaseRepository();

    }

    public MutableLiveData<String> getDiscoveryAnswerOneLiveData() {

        discoveryAnswerOneLiveData = firebaseRepository.getDiscoveryAnswerOneLiveData();

        return discoveryAnswerOneLiveData;
    }

    public MutableLiveData<String> getDiscoveryAnswerTwoLiveData() {

        discoveryAnswerTwoLiveData = firebaseRepository.getDiscoveryAnswerTwoLiveData();

        return discoveryAnswerTwoLiveData;
    }

    public MutableLiveData<String> getDiscoveryAnswerThreeLiveData() {

        discoveryAnswerThreeLiveData = firebaseRepository.getDiscoveryAnswerThreeLiveData();

        return discoveryAnswerThreeLiveData;
    }
}

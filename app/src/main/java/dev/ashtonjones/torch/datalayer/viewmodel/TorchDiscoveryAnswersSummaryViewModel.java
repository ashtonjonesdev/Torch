package dev.ashtonjones.torch.datalayer.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.repository.RepositoryInterface;

public class TorchDiscoveryAnswersSummaryViewModel extends ViewModel {

    RepositoryInterface firebaseRespository;

    private MutableLiveData<String> discoveryAnswerOneLiveData;

    private MutableLiveData<String> discoveryAnswerTwoLiveData;

    private MutableLiveData<String> discoveryAnswerThreeLiveData;

    public TorchDiscoveryAnswersSummaryViewModel() {

        firebaseRespository = new FirebaseRepository();

    }

    public MutableLiveData<String> getDiscoveryAnswerOneLiveData() {

        discoveryAnswerOneLiveData = firebaseRespository.getDiscoveryAnswerOneLiveData();

        return discoveryAnswerOneLiveData;
    }

    public MutableLiveData<String> getDiscoveryAnswerTwoLiveData() {

        discoveryAnswerTwoLiveData = firebaseRespository.getDiscoveryAnswerTwoLiveData();

        return discoveryAnswerTwoLiveData;
    }

    public MutableLiveData<String> getDiscoveryAnswerThreeLiveData() {

        discoveryAnswerThreeLiveData = firebaseRespository.getDiscoveryAnswerThreeLiveData();

        return discoveryAnswerThreeLiveData;
    }
}

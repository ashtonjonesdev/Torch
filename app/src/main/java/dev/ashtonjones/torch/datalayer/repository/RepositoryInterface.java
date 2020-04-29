package dev.ashtonjones.torch.datalayer.repository;

import androidx.lifecycle.MutableLiveData;

public interface RepositoryInterface {

    MutableLiveData<String> getTorchMessageLiveData();

    MutableLiveData<String> getDiscoveryAnswerOneLiveData();

    MutableLiveData<String> getDiscoveryAnswerTwoLiveData();

    MutableLiveData<String> getDiscoveryAnswerThreeLiveData();

    void updateTorchMessage(String newTorchMessage);

    void updateTorchDiscoveryOneAnswer(String discoveryOneAnswer);

    void updateTorchDiscoveryTwoAnswer(String discoveryTwoAnswer);

    void updateTorchDiscoveryThreeAnswer(String discoveryThreeAnswer);


}

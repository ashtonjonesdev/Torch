package dev.ashtonjones.torch.datalayer.repository;

import androidx.lifecycle.MutableLiveData;

public interface RepositoryInterface {

    MutableLiveData<String> getTorchMessageLiveData();

    void updateTorchMessage(String newTorchMessage);

}

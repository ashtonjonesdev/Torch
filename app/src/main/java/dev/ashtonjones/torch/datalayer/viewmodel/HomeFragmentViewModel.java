package dev.ashtonjones.torch.datalayer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.ashtonjones.torch.datalayer.repository.FirebaseRepository;
import dev.ashtonjones.torch.datalayer.repository.RepositoryInterface;

public class HomeFragmentViewModel extends ViewModel {

    private MutableLiveData<String> torchMessageLiveData;

    private RepositoryInterface firebaseRespository;

    public HomeFragmentViewModel() {

        firebaseRespository = new FirebaseRepository();

        torchMessageLiveData = new MutableLiveData<>();

    }

    public LiveData<String> getTorchMessageLiveData() {

        torchMessageLiveData = firebaseRespository.getTorchMessageLiveData();

        return torchMessageLiveData;
    }
}

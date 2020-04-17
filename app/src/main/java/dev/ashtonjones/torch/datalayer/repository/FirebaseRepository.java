package dev.ashtonjones.torch.datalayer.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import dev.ashtonjones.torch.datamodels.User;

public class FirebaseRepository implements RepositoryInterface {

    private static final String LOG_TAG = FirebaseRepository.class.getSimpleName();

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private MutableLiveData<String> torchMessageLiveData;

    @Override
    public MutableLiveData<String> getTorchMessageLiveData() {

        torchMessageLiveData = new MutableLiveData<>();

        firebaseFirestore.collection("users").document(getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()) {

                        User user = documentSnapshot.toObject(User.class);

                        Log.d(LOG_TAG, "Torch message: " + user.getTorchMessage());

                        torchMessageLiveData.postValue(user.getTorchMessage());

                    }

                }
            }
        });

        return torchMessageLiveData;


    }

    @Override
    public void updateTorchMessage(String newTorchMessage) {

        DocumentReference documentReference  = getUserDocument(getUid());

        documentReference.update("torchMessage", newTorchMessage);


    }

    public String getUid() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userID = firebaseUser.getUid();

        return userID;

    }

    public DocumentReference getUserDocument(String uID) {

        DocumentReference userDocument = firebaseFirestore.collection("users").document(uID);

        return userDocument;

    }
}

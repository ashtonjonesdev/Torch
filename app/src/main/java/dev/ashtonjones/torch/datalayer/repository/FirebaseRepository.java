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

public class FirebaseRepository implements RepositoryInterface {

    private static final String LOG_TAG = FirebaseRepository.class.getSimpleName();

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private MutableLiveData<String> torchMessageLiveData;

    private MutableLiveData<String> discoveryAnswerOneLiveData;

    private MutableLiveData<String> discoveryAnswerTwoLiveData;

    private MutableLiveData<String> discoveryAnswerThreeLiveData;








    @Override
    public MutableLiveData<String> getTorchMessageLiveData() {

        torchMessageLiveData = new MutableLiveData<>();

        firebaseFirestore.collection("users").document(getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()) {

//                        User user = documentSnapshot.toObject(User.class);

                        String torchMessage = documentSnapshot.getString("torchMessage");

                        Log.d(LOG_TAG, "Torch message: " + torchMessage);

                        torchMessageLiveData.postValue(torchMessage);

                    }

                }
            }
        });

        return torchMessageLiveData;


    }

    public MutableLiveData<String> getDiscoveryAnswerOneLiveData() {

        discoveryAnswerOneLiveData = new MutableLiveData<>();

        firebaseFirestore.collection("users").document(getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()) {

                        String answer = documentSnapshot.getString("torchDiscoveryOneAnswer");

                        discoveryAnswerOneLiveData.postValue(answer);

                    }

                }
            }
        });

        return discoveryAnswerOneLiveData;
    }

    public MutableLiveData<String> getDiscoveryAnswerTwoLiveData() {
        discoveryAnswerTwoLiveData = new MutableLiveData<>();

        firebaseFirestore.collection("users").document(getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()) {

                        String answer = documentSnapshot.getString("torchDiscoveryTwoAnswer");

                        discoveryAnswerTwoLiveData.postValue(answer);

                    }

                }
            }
        });

        return discoveryAnswerTwoLiveData;
    }

    public MutableLiveData<String> getDiscoveryAnswerThreeLiveData() {
        discoveryAnswerThreeLiveData = new MutableLiveData<>();

        firebaseFirestore.collection("users").document(getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()) {

                        String answer = documentSnapshot.getString("torchDiscoveryThreeAnswer");

                        discoveryAnswerThreeLiveData.postValue(answer);

                    }

                }
            }
        });

        return discoveryAnswerThreeLiveData;
    }

    @Override
    public void updateTorchMessage(String newTorchMessage) {

        DocumentReference documentReference  = getUserDocument(getUid());

        documentReference.update("torchMessage", newTorchMessage);


    }

    @Override
    public void updateTorchDiscoveryOneAnswer(String discoveryOneAnswer) {

        DocumentReference documentReference  = getUserDocument(getUid());

        documentReference.update("torchDiscoveryOneAnswer", discoveryOneAnswer);

    }

    @Override
    public void updateTorchDiscoveryTwoAnswer(String discoveryTwoAnswer) {

        DocumentReference documentReference  = getUserDocument(getUid());

        documentReference.update("torchDiscoveryTwoAnswer", discoveryTwoAnswer);


    }

    @Override
    public void updateTorchDiscoveryThreeAnswer(String discoveryThreeAnswer) {

        DocumentReference documentReference  = getUserDocument(getUid());

        documentReference.update("torchDiscoveryThreeAnswer", discoveryThreeAnswer);


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

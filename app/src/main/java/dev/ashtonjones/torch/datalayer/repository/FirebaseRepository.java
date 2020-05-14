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
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Field;
import java.util.ArrayList;

import dev.ashtonjones.torch.datamodels.JournalEntry;
import dev.ashtonjones.torch.datamodels.User;

public class FirebaseRepository implements RepositoryInterface {

    private static final String LOG_TAG = FirebaseRepository.class.getSimpleName();

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private MutableLiveData<String> torchMessageLiveData;

    private MutableLiveData<String> discoveryAnswerOneLiveData;

    private MutableLiveData<String> discoveryAnswerTwoLiveData;

    private MutableLiveData<String> discoveryAnswerThreeLiveData;

    private MutableLiveData<Long> numberOfDaysAlignedLiveData;

    private MutableLiveData<ArrayList<JournalEntry>> journalEntriesLiveData;








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
    public MutableLiveData<ArrayList<JournalEntry>> getJournalEntriesLiveData() {

        journalEntriesLiveData = new MutableLiveData<>();

        firebaseFirestore.collection("users").document(getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()) {

                        User user = documentSnapshot.toObject(User.class);

                        journalEntriesLiveData.postValue(user.getJournalEntries());

                    }

                }
            }
        });

        return journalEntriesLiveData;
    }

    @Override
    public MutableLiveData<Long> getNumberOfDaysAlignedLiveData() {

        numberOfDaysAlignedLiveData = new MutableLiveData<>();

        firebaseFirestore.collection("users").document(getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {

                    DocumentSnapshot documentSnapshot = task.getResult();

                    if(documentSnapshot.exists()) {

                        Long value = (Long) documentSnapshot.get("numberOfDaysAligned");

                        Log.d(LOG_TAG, "Long value: " + value);

                        numberOfDaysAlignedLiveData.postValue(value);
                    }

                }
            }
        });

        return  numberOfDaysAlignedLiveData;
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

    @Override
    public void incrementNumberOfDaysAligned() {

        DocumentReference documentReference  = getUserDocument(getUid());

        documentReference.update("numberOfDaysAligned", FieldValue.increment(1));

    }

    @Override
    public void resetNumberOfDaysAligned() {

        DocumentReference documentReference  = getUserDocument(getUid());

        documentReference.update("numberOfDaysAligned", 0);

    }


    @Override
    public void addJournalEntry(JournalEntry newJournalEntry) {

        DocumentReference documentReference  = getUserDocument(getUid());

        documentReference.update("journalEntries", FieldValue.arrayUnion(newJournalEntry));


    }

    /**
     *
     * Utility Methods
     *
     * @return
     */

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

package dev.ashtonjones.torch.datalayer.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import dev.ashtonjones.torch.datamodels.JournalEntry;

public interface RepositoryInterface {

    /**
     *
     * GET METHODS
     *
     * @return
     */
    MutableLiveData<String> getTorchMessageLiveData();

    MutableLiveData<String> getDiscoveryAnswerOneLiveData();

    MutableLiveData<String> getDiscoveryAnswerTwoLiveData();

    MutableLiveData<String> getDiscoveryAnswerThreeLiveData();

    MutableLiveData<ArrayList<JournalEntry>> getJournalEntriesLiveData();

    /**
     *
     * UPDATE METHODS
     *
     * @param newTorchMessage
     */

    void updateTorchMessage(String newTorchMessage);

    void updateTorchDiscoveryOneAnswer(String discoveryOneAnswer);

    void updateTorchDiscoveryTwoAnswer(String discoveryTwoAnswer);

    void updateTorchDiscoveryThreeAnswer(String discoveryThreeAnswer);

    /**
     *
     * ADD METHODS
     *
     */
    void addJournalEntry(JournalEntry newJournalEntry);


}

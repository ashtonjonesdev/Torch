package dev.ashtonjones.torch.datamodels;

import java.util.Date;

public class JournalEntry {

    private String journalMessage;
    private int levelOfAlignment;
    private boolean actionsAligned;

    public JournalEntry() {
    }

    public JournalEntry(String journalMessage, int levelOfAlignment, boolean actionsAligned) {
        this.journalMessage = journalMessage;
        this.levelOfAlignment = levelOfAlignment;
        this.actionsAligned = actionsAligned;
    }

    public String getJournalMessage() {
        return journalMessage;
    }

    public int getLevelOfAlignment() {
        return levelOfAlignment;
    }

    public boolean isActionsAligned() {
        return actionsAligned;
    }

    public void setJournalMessage(String journalMessage) {
        this.journalMessage = journalMessage;
    }

    public void setLevelOfAlignment(int levelOfAlignment) {
        this.levelOfAlignment = levelOfAlignment;
    }

    public void setActionsAligned(boolean actionsAligned) {
        this.actionsAligned = actionsAligned;
    }
}

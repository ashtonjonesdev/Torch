package dev.ashtonjones.torch.datamodels;

public class User {

    private String name;
    private String uID;
    private String torchMessage;

    public User(String name, String uID) {
        this.name = name;
        this.uID = uID;
    }

    public User(String name, String uID, String torchMessage) {
        this.name = name;
        this.uID = uID;
        this.torchMessage = torchMessage;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getTorchMessage() {
        return torchMessage;
    }

    public void setTorchMessage(String torchMessage) {
        this.torchMessage = torchMessage;
    }
}

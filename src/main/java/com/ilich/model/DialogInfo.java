package com.ilich.model;

public class DialogInfo {

    private int idUser;
    private String interlocutorName;
    private Long photoId;
    private String lastMessage;
    private long dialogId;

    public DialogInfo() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getInterlocutorName() {
        return interlocutorName;
    }

    public void setInterlocutorName(String interlocutorName) {
        this.interlocutorName = interlocutorName;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public long getDialogId() {
        return dialogId;
    }

    public void setDialogId(long dialogId) {
        this.dialogId = dialogId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        DialogInfo that = (DialogInfo) object;

        if (idUser != that.idUser) return false;
        if (photoId != that.photoId) return false;
        if (dialogId != that.dialogId) return false;
        if (interlocutorName != null ? !interlocutorName.equals(that.interlocutorName) : that.interlocutorName != null)
            return false;
        return lastMessage != null ? lastMessage.equals(that.lastMessage) : that.lastMessage == null;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (interlocutorName != null ? interlocutorName.hashCode() : 0);
        result = 31 * result + (int) (photoId ^ (photoId >>> 32));
        result = 31 * result + (lastMessage != null ? lastMessage.hashCode() : 0);
        result = 31 * result + (int) (dialogId ^ (dialogId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "DialogInfo{" +
                "idUser=" + idUser +
                ", interlocutorName='" + interlocutorName + '\'' +
                ", photoId=" + photoId +
                ", lastMessage='" + lastMessage + '\'' +
                ", dialogId=" + dialogId +
                '}';
    }
}
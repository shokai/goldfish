package org.ubif.goldfish.net;

public interface LineSocketEventHandler{
    public void onMessage(String line);
    public void onOpen();
    public void onClose();
}
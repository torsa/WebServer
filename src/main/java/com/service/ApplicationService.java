package com.service;

import java.net.Socket;

public interface ApplicationService {

    public void doService(Socket connection,String request);
}

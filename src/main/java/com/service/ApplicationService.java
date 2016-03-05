package com.service;

import java.net.Socket;

/**
 * This is the interface for all Implementing services in Web Server.
 *
 * @author Torsa Das
 */
public interface ApplicationService {

    public void doService(Socket connection, String request);
}

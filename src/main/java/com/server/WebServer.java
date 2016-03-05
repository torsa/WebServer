package com.server;

        import com.factory.ServiceFactory;
        import com.service.ApplicationService;
        import com.util.PropertyHandler;
        import com.validator.GetRequestValidator;
        import org.apache.log4j.Logger;

        import java.io.*;
        import java.net.BindException;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.net.UnknownHostException;

/**
 * This is the main Web server Class
 *
 * @author Torsa Das
 */
public class WebServer {

    final static Logger logger = Logger.getLogger(WebServer.class);

    public static void main(String args[]) throws Exception {
        int port = Integer.parseInt(PropertyHandler.getInstance().getValue("port"));
        ServerSocket serverSocket = null;
        try{
            //creating new socket
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            logger.error("Could not start server: " + e);
            System.exit(-1);
        }
        logger.info("Directory Server accepting connections on port " + port);
        try{
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    logger.info("[CLIENT HOST]: " + socket.getInetAddress().toString());
                    logger.info("[CLIENT ON PORT]: " + socket.getPort());
                    implementGetService(socket);
                } catch (Exception exception) {
                    logger.error(exception);
                    socket.close();
                }
                finally{
                    socket.close();
                }
            }
        } catch (BindException B) {
            logger.error("SERVER Already Running");
            System.exit(0);
        } catch (RuntimeException exception) {
            logger.error("UnExpected Error occurred ", exception);
        }
    }

    /**
     * This method is used to implement the services deployed in the server
     *
     * @param socket
     * @throws UnknownHostException
     * @throws IOException
     */
    private static void implementGetService(Socket socket) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
            PrintStream printStream = new PrintStream(outputStream);
            String request = bufferedReader.readLine();
            if (GetRequestValidator.validateGetRequest(request, printStream, socket)) {
                ApplicationService service = ServiceFactory.getService(outputStream, printStream);
                service.doService(socket, request);
            }
        } catch (IOException ioException) {
            logger.error("UnExpected Error occurred ", ioException);
        }
    }
}

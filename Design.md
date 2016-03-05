#Web Server Design
1. Port has been made configufrable.
2. The server lists the contents of the directory in which app is running but case easily list any configurable directory.
3. The Directory Listing functionality has been decoupled from the actual server and developed as a service.
4. Scalability of the server has been considered by adding the interface ApplicationSevice.
5. Logging has been provided within the application.
6. Basic Exception handling has been provided with general web server error codes(404,403,400).
7. Basic get request validation done.
8. Junits having 42% line coverage.

#Enhancements
1. Request validation could be enhanced by checking Forbidden and UnAuthorized requests.
2. Test coverage needs to be enhanced.
3. Multithreading (maintain thread pool, pass each request to an idle thread) 
4. Read all request header (If-Modified-Since, ...) .
5. HTTP authentication 
6. Functionality to navigate back to parent folder


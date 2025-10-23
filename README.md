# Network Infra Lab VI

The Socket programming API allows application layer programs to communicate with one another using the interface provided by the transport layer. Transport layer communication comes in two flavors: Transport Control Protocol (TCP) and User Datagram Protocol (UDP). TCP and UDP are used for different purposes and both have unique constraints:

- TCP is the more reliable protocol that enables a client to establish a bidirectional connection to a server, then send and receive messages over a byte stream. When using TCP, each entity knows that its messages are received in order, or it gets a connection error.
- UDP is a connectionless protocol and is good for scenarios where you do not necessarily need every packet to be received in order at its destination, or arrive at all.

In this lab, You will learn basic building-blocks for setting up and using TCP connections with Java socket programming. Note that while our lecture covered TCP socket programming in Java and the preferred language for this lab is Java, but if you like to use Python, you are free to do so. The Python codes for a simple Socket Programming can be found in the text-book.

## Lab Tasks

To start, review the Java socket programming codes from lecture slides (the code is also posted in the D2L under Lab 6 module). You must use this code as the base file and extend it to write Program described below.

### Word Prefix Server

In this lab, you are asked to implement a server and a client program using TCP sockets in Java.

The server maintains a word repository. The words.txt file can be downloaded from the lab folder in D2L. This word file contains a list of 25143 words (a single word per line). While your server is running, you can start a client program on the same machine (you should use “127.0.0.1” or “localhost” as the hostname in client code if you are using the same machine for client and server) or on a different machine to communicate with the server (the IP address of the other machine must be used in this case in the client code to connect the server). At the prompt, the user can type any single word, such as:

“yel”

Then, the message will be sent over the network to the server. The server will find all the words that share the same prefix with the client’s word, and sent them back to the client as a comma-separated string. For example, in response to the word “yel” sent from the client, the server should send back the following list of words.

“yellow, yellowish, Yellowknife, Yellowstone”

Upon receiving the list of words that share the same prefix with the original word, the client should display the list to the client, with a preceding message as follows:

“The following words start with yel: yellow, yellowish, Yellowknife, Yellowstone”

The user can request the prefix match for other words over and over until a special character or phrase such as “exit” is entered by the user to stops the client program (you can choose a different kill word or character, just state it clearly in your documentation). The server will keep running forever, listening for incoming connection requests from other clients.

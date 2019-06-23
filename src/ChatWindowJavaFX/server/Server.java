package ChatWindowJavaFX.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(8189);
            System.out.println("Server is start");

            while (true){
                socket = server.accept();
                System.out.println("Client connect");
                clients.add(new ClientHandler(socket,this));





//                if(socket.getInputStream().read() < 0){
//                    System.out.println("Socet disconnect");
//                    socket.close();
//
//
//                }


//                for(ClientHandler clientHandler : clients){
//                    if(clientHandler.getIn().read()==1){
//                        System.out.println("Socet disconnect");
//                        clients.remove(clientHandler);
//                    }
//                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadCastMsg(String msg){
        for (ClientHandler clientHandler : clients) {
           clientHandler.sendMsg(msg);
        }

    }
}

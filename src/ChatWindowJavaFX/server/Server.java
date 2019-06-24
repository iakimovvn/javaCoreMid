package ChatWindowJavaFX.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
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

                subscribe(new ClientHandler(socket,this));
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
        for (ClientHandler clientHandler : clients ) {
            clientHandler.sendMsg(msg);
        }
//        Iterator<ClientHandler> iter = clients.iterator();
//        while (iter.hasNext()) {
//            ClientHandler clientHandler = iter.next();
//            if (clientHandler.getSocket().isClosed()) {
//                iter.remove();
//            } else {
//                clientHandler.sendMsg(msg);
//            }
//        }

    }
    public void subscribe (ClientHandler clientHandler){
        clients.add(clientHandler);
    }
    public void unsubscribe (ClientHandler clientHandler){
        clients.remove(clientHandler);
    }
}

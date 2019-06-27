package ChatWindowJavaFX.server;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() throws SQLException {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            AuthService.connect();

            server = new ServerSocket(8189);
            System.out.println("Server is start");

            while (true){
                socket = server.accept();
                System.out.println("Client connect");
                new ClientHandler(socket,this);

            }
        }
        catch (IOException e) {
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
            AuthService.disconnect();
        }
    }

    public void broadCastMsg(String msg,String nickFrom){
        boolean isPrivateMessage =false;
        String[] messageArr = msg.split(" ");;

        if(msg.startsWith("/w")){
            isPrivateMessage = true;
            msg = makeMessageFromArray(messageArr,2,messageArr.length-1);
        }
        for (ClientHandler clientHandler : clients ) {
            if(isPrivateMessage){
                if(clientHandler.getNick().equals(messageArr[1]) || clientHandler.getNick().equals(nickFrom)){
                    clientHandler.sendMsg(nickFrom+": "+msg);
                }

            }else {
                clientHandler.sendMsg(nickFrom+": "+msg);
            }
        }

    }

    private String makeMessageFromArray(String [] arr, int from, int to){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = from; i <= to; i++) {
            stringBuffer.append(arr[i]);
        }
        return stringBuffer.toString();
    }
    public void subscribe (ClientHandler clientHandler){
        clients.add(clientHandler);
    }
    public void unsubscribe (ClientHandler clientHandler){
        clients.remove(clientHandler);
    }

    public boolean isAlreadyGone(String nick){
        for (ClientHandler clientHandler: clients) {
            if(clientHandler.getNick().equals(nick)){
                return true;
            }
        }
        return false;
    }
}

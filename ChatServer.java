import java.io.IOException;
import java.net.URI;
import java.util.*; 

class Handler implements URLHandler {
    ArrayList<String> ans = new ArrayList<>(); 


    public String handleRequest(URI url) {
        String x = ""; 
        if(url.getPath().equals("/")){
            return x; 
        }
        else if(url.getPath().equals("/add-message")){
            String[] parameters = url.getQuery().split("&");
            String[] name = parameters[0].split("="); 
            String[] messages = parameters[1].split("="); 
            ans.add(name[1]+": "+messages[1]); 
            for(String y: ans){
                x+= y+"\n"; 
            }
            return x; 
        }
        return "404 Not Found!";

    }
}

class ChatServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}

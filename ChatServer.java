import java.io.IOException;
import java.net.URI;
import java.util.*; 

class Handler implements URLHandler {
    ArrayList<String> ans = new ArrayList<>(); 
    String x = ""; 

    public String handleRequest(URI url) {
        if(url.getPath().equals("/")){
            return x; 
        }
        else if(url.getPath().equals("/add-message")){
            String[] parameters = url.getQuery().split("&");
            String[] user = parameters[1].split("="); 
            String[] messages = parameters[0].split("="); 
            if(user[0]=="user"&&messages[0]=="s"){
                ans.add(user[1]+": "+messages[1]); 
            }
            x = String.join("\n", ans); 
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

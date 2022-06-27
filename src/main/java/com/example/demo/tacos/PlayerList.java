package com.example.demo.tacos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/players")
public class PlayerList {
    //This method reads data from an XML file and then returns a linkedList list "List<Player>".
    @GetMapping("all")
    private List<Player> getPlayersFromXML(){
        List<Player> players = new LinkedList<>();
        try{
            File file = new File("../capstone/src/main/resources/data.xml");
            if (file.exists()) {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse("../capstone/src/main/resources/data.xml");

                //Gets the elements "Name" and "Numbers" from the data.xml file and add them to a node list
                NodeList[] player = {document.getElementsByTagName("name"), document.getElementsByTagName("number")};

                for (int i = 0; i < player[0].getLength(); i++) {
                    //Iterate through the node list and collect the string values of both "name" and "number" then pass the as parameters to new Player
                    String name = player[0].item(i).getTextContent();
                    int number = Integer.parseInt(player[1].item(i).getTextContent());
                    Player newPlayer = new Player(name,number);
                    //Then add the new player to the players List
                    players.add(newPlayer);
                }
            }else {
                System.out.println("File not found.");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //We then return the final collection of players
        return players;
    }

    @GetMapping("sortASC")
    //This method make a copy of the list then sort the copy in ASC order and returns the copy.
    public List<Player> sortASC(){
        List<Player> tempPlayers = copyList(getPlayersFromXML());
        tempPlayers.sort((p1, p2) -> p1.getNum() - p2.getNum());
        return tempPlayers;
    }

    @GetMapping("sortDESC")
    //This method  make a copy of the list then sort the copy in DESC order and returns the copy.
    public List<Player> sortDESC(){
        List<Player> tempPlayers = copyList(getPlayersFromXML());
        tempPlayers.sort((p1, p2) -> p2.getNum() - p1.getNum());
        return tempPlayers;
    }

    //This method is for copying one LinkedList to a new LinkedList to avoid
    // making changes to the original List.
    private List<Player> copyList(List<Player> players) {
        return new LinkedList<>(players);
    }

    public void printList(List<Player> players) {
        for (Player player: players
        ) {
            System.out.println("Name: "+player.getName()+"\tNumber: "+player.getNum());
        }
    }
}

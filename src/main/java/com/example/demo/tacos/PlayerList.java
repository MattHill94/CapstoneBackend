package com.example.demo.tacos;

import com.lowagie.text.DocumentException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/players")
public class PlayerList {
    //This method reads data from an XML file and then returns a linkedList list "List<Player>".
    @GetMapping("all")
    public List<Player> getPlayersFromXML(){
        List<Player> players = new LinkedList<>();
        try{
            Path filePath = Paths.get("data.xml");
            File file = new File(String.valueOf(filePath.toAbsolutePath()));

            if (file.exists()) {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(String.valueOf(filePath.toAbsolutePath()));

                //Gets the elements "Name" and "Numbers" from the data.xml file and add them to a node list
                NodeList[] player = {document.getElementsByTagName("name"), document.getElementsByTagName("number"), document.getElementsByTagName("percentage"), };

                for (int i = 0; i < player[0].getLength(); i++) {
                    //Iterate through the node list and collect the string values of both "name" and "number" then pass the as parameters to new Player
                    String name = player[0].item(i).getTextContent();
                    int number = Integer.parseInt(player[1].item(i).getTextContent());
                    int percentage = Integer.parseInt(player[2].item(i).getTextContent());
                    Player newPlayer = new Player(name,number,percentage);
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





    @GetMapping("sortPercASC")
    //This method make a copy of the list then sort the copy in ASC order and returns the copy.
    public List<Player> sortPercASC(){
        List<Player> tempPlayers = copyList(getPlayersFromXML());
        tempPlayers.sort((p1, p2) -> p1.getPercentage() - p2.getPercentage());
        return tempPlayers;
    }

    @GetMapping("sortPercDESC")
    //This method  make a copy of the list then sort the copy in DESC order and returns the copy.
    public List<Player> sortPercDESC(){
        List<Player> tempPlayers = copyList(getPlayersFromXML());
        tempPlayers.sort((p1, p2) -> p2.getPercentage() - p1.getPercentage());
        return tempPlayers;
    }



    //This method is for copying one LinkedList to a new LinkedList to avoid
    // making changes to the original List.
    private List<Player> copyList(List<Player> players) {
        return new LinkedList<>(players);
    }

    @GetMapping("ExportOriginal")
    public void exportToPDFOriginal(HttpServletResponse response) throws IOException {
        exportToPDF(response, getPlayersFromXML());
    }
    @GetMapping("ExportSortASC")
    public void exportToPDFASC(HttpServletResponse response) throws IOException {
        exportToPDF(response, sortASC());
    }
    @GetMapping("ExportSortDESC")
    public void exportToPDFDESC(HttpServletResponse response) throws IOException {
        exportToPDF(response, sortDESC());
    }




    @GetMapping("ExportSortPercASC")
    public void exportToPDFPercASC(HttpServletResponse response) throws IOException {
        exportToPDF(response, sortPercASC());
    }

    @GetMapping("ExportSortPercDESC")
    public void ExportSortPercDESC(HttpServletResponse response) throws IOException {
        exportToPDF(response, sortPercDESC());
    }









    private void exportToPDF(HttpServletResponse response, List<Player> players) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-dd-MM_HH-mm");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Troop_Report" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Player> listUsers = players;

        DataPDFExporter exporter = new DataPDFExporter(listUsers);
        exporter.export(response);
    }
}

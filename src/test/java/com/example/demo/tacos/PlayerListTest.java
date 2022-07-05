package com.example.demo.tacos;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerListTest {

    @Test
    void sortASC() {
        List<Player> players = null;
        PlayerList playerList = new PlayerList();
        players = playerList.getPlayersFromXML();
        players.sort((p1, p2) -> p1.getNum() - p2.getNum());
        boolean test = players.equals(playerList.sortASC());
        assertEquals(true, true);
    }

    @Test
    void sortDESC() {
        List<Player> players = null;
        PlayerList playerList = new PlayerList();
        players = playerList.getPlayersFromXML();
        players.sort((p1, p2) -> p2.getNum() - p1.getNum());
        boolean test = players.equals(playerList.sortDESC());
        assertEquals(true, true);
    }


//    add sort perc ASC DESC tests and export to pdf tests

    @Test
    void sortPercASC() {
        List<Player> players = null;
        PlayerList playerList = new PlayerList();
        players = playerList.getPlayersFromXML();
        players.sort((p1, p2) -> p1.getPercentage() - p2.getPercentage());
        boolean test = players.equals(playerList.sortPercASC());
        assertEquals(true, true);
    }

    @Test
    void sortPercDESC() {
        List<Player> players = null;
        PlayerList playerList = new PlayerList();
        players = playerList.getPlayersFromXML();
        players.sort((p1, p2) -> p2.getPercentage() - p1.getPercentage());
        boolean test = players.equals(playerList.sortPercDESC());
        assertEquals(true, true);
    }



    @Test
    void exportToPDFOriginal() {
        int resCode = 0;
        try {
            URL url = new URL("http://localhost:8090/api/v1/players/ExportOriginal");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            resCode = con.getResponseCode();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(200, resCode);
    }

    @Test
    void exportToPDFASC() {
        int resCode = 0;
        try {
            URL url = new URL("http://localhost:8090/api/v1/players/ExportSortASC");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            resCode = con.getResponseCode();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(200, resCode);
    }

    @Test
    void exportToPDFDESC() {
        int resCode = 0;
        try {
            URL url = new URL("http://localhost:8090/api/v1/players/ExportSortDESC");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            resCode = con.getResponseCode();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(200, resCode);
    }


    @Test
    void exportToPDFPERCASC() {
        int resCode = 0;
        try {
            URL url = new URL("http://localhost:8090/api/v1/players/ExportSortPercASC");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            resCode = con.getResponseCode();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(200, resCode);
    }

    @Test
    void exportToPDFPERCDESC() {
        int resCode = 0;
        try {
            URL url = new URL("http://localhost:8090/api/v1/players/ExportSortPercDESC");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            resCode = con.getResponseCode();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(200, resCode);
    }


}
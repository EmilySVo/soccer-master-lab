package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    //instance variables
    Hashtable<String, SoccerPlayer> players = new Hashtable<String, SoccerPlayer>();
    Set<String> keys = players.keySet();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        String tempName = firstName + "##" + lastName;
        if(players.containsKey(tempName)) {
            Log.i("Error:", "Already contains this key");
            return false;
        }
        else {
            SoccerPlayer player = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
            players.put(tempName, player);
            Log.i("Success:","Player Added");
            return true;
        }
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String hashCode = firstName + "##" + lastName;
        if(players.containsKey(hashCode)){
            players.remove(hashCode);
            Log.i("Success:","Player Removed");
            return true;
        }
        Log.i("Error", "Player not found");
        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        String hashCode = firstName + "##" + lastName;
        if(players.containsKey(hashCode)){
            Log.i("Success:","Player Found");
            return players.get(hashCode);
        }
        Log.i("Error", "Player not found");
        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String hashCode = firstName + "##" + lastName;
        if(players.containsKey(hashCode)){
            players.get(hashCode).bumpGoals();
            Log.i("Success:","Score Bumped");
            return true;
        }
        Log.i("Error", "Player not found");
        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String hashCode = firstName + "##" + lastName;
        if(players.containsKey(hashCode)){
            players.get(hashCode).bumpAssists();
            Log.i("Success:","Assists Bumped");
            return true;
        }
        Log.i("Error", "Player not found");
        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String hashCode = firstName + "##" + lastName;
        if(players.containsKey(hashCode)){
            players.get(hashCode).bumpShots();
            Log.i("Success:","Shots Bumped");
            return true;
        }
        Log.i("Error", "Player not found");
        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String hashCode = firstName + "##" + lastName;
        if(players.containsKey(hashCode)){
            players.get(hashCode).bumpSaves();
            Log.i("Success:","Saves Bumped");
            return true;
        }
        Log.i("Error", "Player not found");
        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String hashCode = firstName + "##" + lastName;
        if(players.containsKey(hashCode)){
            players.get(hashCode).bumpFouls();
            Log.i("Success:","Fouls Bumped");
            return true;
        }
        Log.i("Error", "Player not found");
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String hashCode = firstName + "##" + lastName;
        if(players.containsKey(hashCode)){
            players.get(hashCode).bumpYellowCards();
            Log.i("Success:","Yellow Cards Bumped");
            return true;
        }
        Log.i("Error", "Player not found");
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String hashCode = firstName + "##" + lastName;
        if(players.containsKey(hashCode)){
            players.get(hashCode).bumpRedCards();
            Log.i("Success:","Red Cards Bumped");
            return true;
        }
        Log.i("Error", "Player not found");
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        int numPlayers = 0;
        if(teamName == null){
            return players.size();
        }
        else {
            for (String key : keys) {
                if(players.get(key).getTeamName().equalsIgnoreCase(teamName)){
                    numPlayers++;
                }
            }
        }
        return numPlayers;
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int counter = 0;
        if(idx > numPlayers(teamName)){
            return null;
        }
        if(teamName == null){
            for(String key : keys){
                if(counter == idx){
                    counter = 0;
                    return players.get(key);
                }
                counter++;
            }
        }
        for (String key : keys) {
            if(players.get(key).getTeamName().equalsIgnoreCase(teamName)){
                if(counter == idx){
                    counter = 0;
                    return players.get(key);
                }
                counter++;
            }
        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        try {
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                String fs = sc.next();
                Log.i("first name = ", fs);
                String ls = sc.next();
                Log.i("last name = ", ls);
                String tn = sc.next();
                Log.i("team name =", tn);
                int un = sc.nextInt();
                Log.i("uniform = ", "" + un);
                SoccerPlayer player = new SoccerPlayer(fs, ls, un, tn);
                addPlayer(fs,ls,un,tn);

                while(sc.hasNextInt()){
                    int num = sc.nextInt();
                    for(int i = 0; i < num; i++){
                        player.bumpGoals();
                    }
                    num = sc.nextInt();
                    for(int i = 0; i < num; i++){
                        player.bumpAssists();
                    }
                    num = sc.nextInt();
                    for(int i = 0; i < num; i++){
                        player.bumpShots();
                    }
                    num = sc.nextInt();
                    for(int i = 0; i < num; i++){
                        player.bumpFouls();
                    }
                    num = sc.nextInt();
                    for(int i = 0; i < num; i++){
                        player.bumpSaves();
                    }
                }
            }
            sc.close();
            return true;
        }

        catch (FileNotFoundException e) {
            Log.e("Error:", "File not found");
            return false;
        }
    }


    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        file = new File (file + "");
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);
            for (String key : keys) {
                printWriter.println(logString(players.get(key).getFirstName() + " "
                + players.get(key).getLastName()));
                printWriter.println(logString("" + players.get(key).getTeamName()));
                printWriter.println(logString("" + players.get(key).getUniform()));
                printWriter.println(logString("" + players.get(key).getGoals()));
                printWriter.println(logString("" + players.get(key).getAssists()));
                printWriter.println(logString("" + players.get(key).getShots()));
                printWriter.println(logString("" + players.get(key).getFouls()));
                printWriter.println(logString("" + players.get(key).getSaves()));
            }
            printWriter.close ();
            return true;
        }
        catch (IOException e) {
            Log.i("Error:","Could not make file");
            return false;
        }
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        HashSet<String> teamNames = new HashSet<String>();

        return teamNames;
    }

}

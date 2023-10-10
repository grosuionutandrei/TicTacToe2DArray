package tictactoe.bll.Computer;

import java.util.*;

public class ComputerPlayer {



/*
* to implement when game is not finished
* and the computer needs to find an empty cell in the grid;
*
* */
private int turn=0;
    private String[][] gridPane = new String[3][3];


    public Integer[] computerCoordinates() {
       Integer[] coordinates = {2,3};

       if(turn<=3){
            return getCoords(gridPane);
        }

        System.out.print("Computer coords " +coordinates[0] + " " + coordinates[1]);
        turn+=1;
        return coordinates;
    }

    private ArrayList<Integer[]> takenCoordinates = new ArrayList<>();

    public ArrayList<Integer[]> getTakenCoordinates() {
        return takenCoordinates;
    }

    public void addTakenCoordinates(Integer[] place) {
        this.gridPane[place[0]][place[1]] = "1";
        System.out.println(this.gridPane[place[0]][place[1]] + " taken coordinates");
        takenCoordinates.add(place);
    }

    private int randomNumber(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

    private Integer[] getCoords(String[][] strings) {
        Integer[] coords = new Integer[2];
        boolean find = false;

        while (!find) {
            int row = randomNumber(3);
            int col = randomNumber(3);
            for (int i = 0; i < strings.length; i++) {
                for (int j = 0; j < strings[0].length; j++) {
                    if (row == i) {
                        if (col == j) {
                            if (strings[i][j] == null) {
                                find = true;
                                coords[0] = row;
                                coords[1] = col;
                            }
                        }
                    }
                }
            }
        }
return coords;
    }


}

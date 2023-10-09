package tictactoe.bll.Computer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class ComputerPlayer {

    public Integer[] computerCoordinates(){
        Integer[]random = getRandomCoordinates(takenCoordinates);
        System.out.println(random[0]+" " + random[1]);
        return random;
    }
   private ArrayList<Integer[]> takenCoordinates = new ArrayList<>();
    public ArrayList<Integer[]> getTakenCoordinates() {
        return takenCoordinates;
    }
    public void addTakenCoordinates(Integer[] place) {
        takenCoordinates.add(place);
    }


    private int randomNumber(){
        Random random =new Random();
        return random.nextInt(3);
    }

    private boolean checkIfTaken(ArrayList<Integer[]> taken,Integer[] current){

        for(Integer[] coord:taken ){
            if (Objects.equals(taken, current)) {
             return true;
            }
        }
        return false;
    }

    private Integer[] getRandomCoordinates(ArrayList<Integer[]> takenCoordinates){
        int  row = randomNumber();
        int col = randomNumber();
        Integer[] current = {row,col};
        if(!checkIfTaken(takenCoordinates,current)){
            return current;
        }
        return getRandomCoordinates(takenCoordinates);
    }
}

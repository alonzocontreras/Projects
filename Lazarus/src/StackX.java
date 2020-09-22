import java.util.LinkedList;
import java.util.Observable;

public class StackX extends GameObject{
    private LinkedList<Integer> locationX = new LinkedList<>();
    private int newX;

    public StackX(){
        //create list representing columns of the pit, 0 boxes each column
        for(int i = 0; i < 12; i++){
            locationX.addLast(0);
        }
    }
    //when a box lands in that column, increment the count of boxes in that column
    public void increaseStackX(int x){
        newX = (x/40) - 2;
        int temp = locationX.get(newX) + 1;
        locationX.add(newX, temp);
    }
    //when a box lands in that column, decrement the count of boxes in that column
    public void decreaseStackX(int x){
        newX = (x/40) - 2;
        int temp = locationX.get(newX) - 1;
        locationX.add(newX, temp);
    }
    //return back the number of boxes in that column
    public int getStackSizeX(int x){
        int temp = x/40 - 2;
        int size = (locationX.get(temp)) * 40;
        return 360 - size;  //compensate since we're taking in an X-coord and translating it to a Y-coord, need to
                            //adjust for the height difference from the top of the screen to where lazarus is at
    }

    @Override
    public void collide(GameObject object) {}
    @Override
    public void collide(Player player) {}
    @Override
    public void collide(Boxes box) {}
    @Override
    public void collide(Walls wall) {}
    @Override
    public void update(Observable observable, Object o) {}
}

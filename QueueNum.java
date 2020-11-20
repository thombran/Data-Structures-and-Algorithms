package Assignment4;


/**
 * @author Brandon Thomas
 * @version 1.0
 * This class serves as the object for my Queue class which just keeps track of the int value of the Queue
 * item, as well as its position in the Queue
 */
public class QueueNum {
    int placeInQueue; //Position in the current Queue
    int num;

    public QueueNum(){}

    public QueueNum (int data){
        this.num = data;
    }

    public int getPlaceInQueue() {
        return placeInQueue;
    }

    public void setPlaceInQueue(int placeInQueue) {
        this.placeInQueue = placeInQueue;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

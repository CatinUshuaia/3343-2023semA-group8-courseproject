package demo.core;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int DistanceWithRest(){
        double sumOfSquares = Math.pow(x, 2) + Math.pow(y, 2);
        double squareRoot = Math.sqrt(sumOfSquares);
        return (int)squareRoot;
    }
    public int DistanceWithAnotherOrder(Location anotherOrder){
        int xDiff=Math.abs(anotherOrder.x-this.x);
        int yDiff=Math.abs(anotherOrder.y-this.y);
        double sumOfSquares = Math.pow(xDiff, 2) + Math.pow(yDiff, 2);
        double squareRoot = Math.sqrt(sumOfSquares);
        return (int)squareRoot;
    }
}
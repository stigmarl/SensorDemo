package stig.com.sensordemo;

/**
 * Created by stigos on 07.03.2016.
 */
public class MovingAverage {
    private double circularBuffer[];
    private double avg;
    private int circularIndex;
    private int count;

    public MovingAverage(int k) {
        circularBuffer = new double[k];
        count = 0;
        circularIndex = 0;
        avg = 0;
    }

    public double getAvg() {
        return avg;
    }

    public void pushValue(float x) {
        if (count++ == 0) {
            primeBuffer(x);
        }
        double lastValue = circularBuffer[circularIndex];
        avg += (x - lastValue)/circularBuffer.length;
        circularBuffer[circularIndex] = x;
        circularIndex = nextIndex(circularIndex);
    }

    public long getCount() {
        return count;
    }

    private void primeBuffer(double val){
        for (int i=0; i<circularBuffer.length; i++) {
            circularBuffer[i] = val;
        }

        avg = val;
    }

    private int nextIndex(int curIndex) {
        if (curIndex + 1 >= circularBuffer.length) {
            return 0;
        }
        return curIndex++;
    }
}

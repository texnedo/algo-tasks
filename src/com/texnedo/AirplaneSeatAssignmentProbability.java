package com.texnedo;

public class AirplaneSeatAssignmentProbability {
    public static void main(String[] args) {
        AirplaneSeatAssignmentProbability assignment = new AirplaneSeatAssignmentProbability();
        System.out.println(assignment.nthPersonGetsNthSeat(1));
        System.out.println(assignment.nthPersonGetsNthSeat(2));
    }

    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) {
            return 1;
        }
        return 1.0 / n + (n - 2.0) / n * nthPersonGetsNthSeat(n - 1);
    }

    private static class State {
        int countPositive = 0;
        int countNegative = 0;
    }

    public double nthPersonGetsNthSeat2(int n) {
        State state = nthPersonGetsNthSeatInternal2(n, new State());
        return (double)state.countPositive / (state.countPositive + state.countNegative);
    }

    private State nthPersonGetsNthSeatInternal2(int n, State state) {
        int current = n - state.countNegative - state.countPositive;
        if (current == 0) {
            return state;
        }
        if (current == n) {
            //there is a probability that first passenger occupied assigned place
            state.countPositive++;
        }
        if (current == 1) {
            if (n > 2) {
                state.countPositive++;
            }
            return state;
        }
        //current passenger occupied place for n-th passenger
        state.countNegative++;
        //current passenger occupied assigned place, so that n-th is still available
        return nthPersonGetsNthSeatInternal2(n, state);
    }
}

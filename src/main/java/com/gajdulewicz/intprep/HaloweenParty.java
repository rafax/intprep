package com.gajdulewicz.intprep;

public class HaloweenParty {

    public static long pieces(int cuts) {
        return cuts % 2 == 1 ? (cuts / 2) * ((cuts / 2) + 1) : cuts * cuts / 4;
    }
}

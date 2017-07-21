package com.gajdulewicz.intprep;

import com.google.common.collect.Lists;
import com.google.common.truth.Truth;
import org.junit.Test;

import java.util.HashMap;

import static com.gajdulewicz.intprep.CoinChange.giveChange;
import static org.junit.Assert.*;

/**
 * Created by gajduler on 7/21/17.
 */
public class CoinChangeTest {
    @Test
    public void simple() {
        Truth.assertThat(giveChange(4, Lists.newArrayList(1, 2, 3))).isEqualTo(4);
    }

    @Test
    public void simple2() {
        Truth.assertThat(giveChange(10, Lists.newArrayList(2, 5, 3, 6))).isEqualTo(5);
    }


    @Test
    public void simplest() {
        Truth.assertThat(giveChange(3, Lists.newArrayList(1, 2))).isEqualTo(2);
    }

    @Test
    public void oddWithEvenCoins() {
        Truth.assertThat(giveChange(5, Lists.newArrayList(2, 4))).isEqualTo(0);
    }

    @Test
    public void tooSmall() {
        Truth.assertThat(giveChange(1, Lists.newArrayList(2, 4))).isEqualTo(0);
    }

    @Test
    public void big() {
        Truth.assertThat(giveChange(166, Lists.newArrayList(5, 37, 8, 39, 33, 17, 22, 32, 13, 7, 10, 35, 40, 2, 43, 49, 46, 19, 41, 1, 12, 11, 28 ))).isEqualTo(96190959);
    }

    @Test
    public void otherBig() {
        Truth.assertThat(giveChange(250, Lists.newArrayList(8,47,13,24,25,31,32,35,3,19,40,48,1,4,17,38,22,30,33,15,44,46,36,9,20,49))).isEqualTo(3542323427L);
    }

}
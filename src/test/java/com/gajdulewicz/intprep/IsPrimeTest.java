package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.IsPrime.isPrimeFast;

public class IsPrimeTest {

    @Test
    public void isPrimeTest(){
        Truth.assertThat(isPrimeFast(Integer.MAX_VALUE)).isTrue();
    }

}
package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.RunLengthEncoding.isValid;

public class RunLengthEncodingTest {
    @Test
    public void isValidTest() {
        Truth.assertThat(isValid("10XXXXXX 10XXXXX 10XXXXXX")).isFalse();
        Truth.assertThat(isValid("1110XXXX 10XXXXXX 1110XXXX")).isFalse();

        Truth.assertThat(isValid("0XXXXXXX 0XXXXXXX 0XXXXXXX 0XXXXXXX 0XXXXXXX 0XXXXXXX")).isTrue();
        Truth.assertThat(isValid("0XXXXXXX 1110XXXX 10XXXXXX 10XXXXXX")).isTrue();
    }


}
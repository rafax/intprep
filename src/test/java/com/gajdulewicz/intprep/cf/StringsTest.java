package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.cf.Strings.*;

public class StringsTest {
  @Test
  public void classifyTest() {
    Truth.assertThat(classifyStrings("?????aa?aaa???bbb???bbb??")).isEqualTo("bad");
    Truth.assertThat(classifyStrings("aa?bbb?a?bbb?aa")).isEqualTo("bad");
    Truth.assertThat(classifyStrings("a?aa")).isEqualTo("mixed");
    Truth.assertThat(classifyStrings("aa??bbb")).isEqualTo("bad");
    Truth.assertThat(classifyStrings("?????aa?aaa???bbb???bbb??")).isEqualTo("bad");
    Truth.assertThat(classifyStrings("??aa??aa??a?aaa?aa?bbbb??????????????bbbbb????????????????"))
        .isEqualTo("bad");
    Truth.assertThat(classifyStrings("a")).isEqualTo("good");
    Truth.assertThat(classifyStrings("x?x?")).isEqualTo("good");
    Truth.assertThat(classifyStrings("?a?a")).isEqualTo("mixed");
    Truth.assertThat(classifyStrings("a?b")).isEqualTo("good");
  }

  @Test
  public void justificationTest() {
    Truth.assertThat(
            textJustification(
                new String[] {"This", "is", "an", "example", "of", "text", "justification."}, 16))
        .isEqualTo(new String[] {"This    is    an", "example  of text", "justification.  "});
    Truth.assertThat(textJustification(new String[] {"Two", "words."}, 10))
        .isEqualTo(new String[] {"Two words."});
  }

  @Test
  public void regexMatchingTest() {
    Truth.assertThat(regexMatching("^code", "codefights")).isEqualTo(true);
    Truth.assertThat(regexMatching("hts$", "codefights")).isEqualTo(true);
    Truth.assertThat(regexMatching("hello", "codefights")).isEqualTo(false);
  }

  @Test
  public void longestCommonSubstringTest() {
    Truth.assertThat(longestCommonSubstring("zxabcdezy", "yzabcdezx")).isEqualTo(6);
    Truth.assertThat(
            longestCommonSubstring(
                "NUBLMOIITNCKLEFSZBEX",
                "RAMPETVHQNDDJEQVUYGPNKAZQFRPJVOAXDPCWMJOBMSKSKFOJNEWXGXNNOFWLTWJWNNVBWJCKDMEOUUZHYVHGVWUJBQX"))
        .isEqualTo(2);
  }
}

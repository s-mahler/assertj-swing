/*
 * Created on Jul 16, 2008
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2008-2013 the original author or authors.
 */
package org.assertj.swing.core.matcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.assertj.swing.test.builder.JButtons.button;

import java.util.Collection;

import javax.swing.JButton;

import org.assertj.swing.test.core.EDTSafeTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for {@link JButtonMatcher#matches(java.awt.Component)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@RunWith(Parameterized.class)
public class JButtonMatcher_matches_byNameAndText_withNotMatch_Test extends EDTSafeTestCase {
  private final String name;
  private final String text;

  @Parameters
  public static Collection<Object[]> namesAndText() {
    return newArrayList(new Object[][] { { "someName", "text" }, { "name", "someText" }, { "name", "text" } });
  }

  public JButtonMatcher_matches_byNameAndText_withNotMatch_Test(String name, String text) {
    this.name = name;
    this.text = text;
  }

  @Test
  public void should_return_false_if_name_or_text_are_not_equal_to_expected() {
    JButtonMatcher matcher = JButtonMatcher.withName(name).andText(text);
    JButton button = button().withName("someName").withText("someText").createNew();
    assertThat(matcher.matches(button)).isFalse();
  }
}

/*
 * Created on Feb 24, 2008
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
package org.assertj.swing.driver;

import org.junit.Test;

/**
 * Tests for {@link JScrollBarDriver#scrollTo(javax.swing.JScrollBar, int)}.
 * 
 * @author Alex Ruiz
 */
public class JScrollBarDriver_scrollTo_Test extends JScrollBarDriver_TestCase {
  @Test
  public void should_scroll_to_given_position() {
    showWindow();
    driver.scrollTo(scrollBar, 68);
    assertThatScrollBarValueIs(68);
  }

  @Test
  public void should_throw_error_if_JScrollBar_is_disabled() {
    disableScrollBar();
    thrown.expectIllegalStateIsDisabledComponent();
    driver.scrollTo(scrollBar, 68);
  }

  @Test
  public void should_throw_error_if_JScrollBar_is_not_showing_on_the_screen() {
    thrown.expectIllegalStateIsNotShowingComponent();
    driver.scrollTo(scrollBar, 68);
  }

  @Test
  public void should_throw_error_if_position_is_less_than_minimum() {
    thrown.expectIllegalArgumentException("Position <0> is not within the JScrollBar bounds of <10> and <80>");
    driver.scrollTo(scrollBar, 0);
  }

  @Test
  public void should_throw_error_if_position_is_greater_than_maximum() {
    thrown.expectIllegalArgumentException("Position <90> is not within the JScrollBar bounds of <10> and <80>");
    driver.scrollTo(scrollBar, 90);
  }
}

/*
 * Created on Jul 19, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.assertj.swing.driver;

import static org.assertj.swing.test.util.StopWatch.startNewStopWatch;
import static org.assertj.swing.timing.Timeout.timeout;

import org.assertj.swing.exception.WaitTimedOutError;
import org.assertj.swing.test.util.StopWatch;
import org.junit.Test;

/**
 * Tests for {@link ComponentDriver#requireEnabled(java.awt.Component, org.assertj.swing.timing.Timeout)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ComponentDriver_requireEnabledWithTimout_Test extends ComponentDriver_TestCase {
  @Test
  public void should_pass_if_Component_is_enabled_before_timeout() {
    driver.requireEnabled(window.button, timeout(100));
  }

  @Test
  public void should_fail_if_Component_is_not_enabled_before_timeout() {
    disableButton();
    int timeout = 1000;
    StopWatch stopWatch = startNewStopWatch();
    thrown.expect(WaitTimedOutError.class, "Timed out waiting for");
    thrown.expectMessageToContain(window.button.getClass().getName(), "to be enabled");
    try {
      driver.requireEnabled(window.button, timeout(timeout));
    } finally {
      stopWatch.stop();
      assertThatWaited(stopWatch, timeout);
    }
  }
}

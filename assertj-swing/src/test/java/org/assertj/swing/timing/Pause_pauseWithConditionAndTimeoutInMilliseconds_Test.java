/*
 * Created on Jul 31, 2009
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
package org.assertj.swing.timing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.test.util.StopWatch.startNewStopWatch;

import org.assertj.swing.exception.WaitTimedOutError;
import org.assertj.swing.test.util.StopWatch;
import org.junit.Test;

/**
 * Tests for {@link Pause#pause(Condition, long)}.
 * 
 * @author Alex Ruiz
 */
public class Pause_pauseWithConditionAndTimeoutInMilliseconds_Test {
  @Test(expected = WaitTimedOutError.class)
  public void should_timeout_if_Condition_is_never_satisfied() {
    Pause.pause(new NeverSatisfiedCondition(), 1000);
  }

  @Test(expected = WaitTimedOutError.class, timeout = 1100)
  public void should_timeout_if_Condition_runs_longer_than_timeout() {
    Pause.pause(new SatisfiedCondition(10000), 1000);
  }

  @Test
  public void should_wait_till_Condition_is_satisfied() {
    int timeToWaitTillSatisfied = 1000;
    SatisfiedCondition condition = new SatisfiedCondition(timeToWaitTillSatisfied);

    StopWatch watch = startNewStopWatch();
    Pause.pause(condition, 1100);
    watch.stop();

    assertThat(watch.ellapsedTime() >= timeToWaitTillSatisfied).isTrue();
    assertThat(condition.satisfied).isTrue();
  }

  @Test(expected = NumberFormatException.class)
  public void should_throw_error_if_Condition_throws_any() {
    Pause.pause(new RuntimeExceptionCondition(new NumberFormatException("expected")), 1000);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_condition_is_null() {
    Pause.pause((Condition) null, 1000);
  }
}

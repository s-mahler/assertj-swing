/*
 * Created on Oct 18, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.assertj.swing.monitor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.awt.EventQueue;

import org.junit.Test;

/**
 * Tests for {@link WindowMonitor#eventQueueFor(java.awt.Component)}.
 * 
 * @author Alex Ruiz
 */
public class WindowMonitor_eventQueueFor_Test extends WindowMonitor_TestCase {
  @Test
  public void should_return_EventQueue_for_Component() {
    EventQueue queue = new EventQueue();
    when(context.eventQueueFor(frame)).thenReturn(queue);
    assertThat(monitor.eventQueueFor(frame)).isSameAs(queue);
  }
}

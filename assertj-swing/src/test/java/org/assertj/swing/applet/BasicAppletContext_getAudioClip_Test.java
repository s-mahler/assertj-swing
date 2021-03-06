/*
 * Created on Jul 24, 2009
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
package org.assertj.swing.applet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for {@link BasicAppletContext#getAudioClip(java.net.URL)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class BasicAppletContext_getAudioClip_Test extends BasicAppletContext_TestCase {
  @Test
  public void should_always_return_null() {
    assertThat(context.getAudioClip(null)).isNull();
  }
}

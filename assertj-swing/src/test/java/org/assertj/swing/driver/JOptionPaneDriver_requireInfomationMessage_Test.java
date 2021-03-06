/*
 * Created on Mar 11, 2008
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

import static org.assertj.swing.test.swing.JOptionPaneLauncher.pack;

import javax.swing.JOptionPane;

import org.junit.Test;

/**
 * Tests for {@link JOptionPaneDriver#requireInformationMessage(JOptionPane)}.
 * 
 * @author Alex Ruiz
 */
public class JOptionPaneDriver_requireInfomationMessage_Test extends JOptionPaneDriver_TestCase {
  @Test
  public void should_pass_if_error_type_is_equal_to_expected() {
    JOptionPane optionPane = informationMessage();
    pack(optionPane, title());
    driver.requireInformationMessage(optionPane);
  }

  @Test
  public void should_fail_if_error_type_is_not_equal_to_expected() {
    JOptionPane optionPane = errorMessage();
    pack(optionPane, title());
    thrown.expectAssertionError("messageType", "[Information] Message", "[Error] Message");
    driver.requireInformationMessage(optionPane);
  }
}

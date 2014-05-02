/*
 * Created on May 16, 2009
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
package org.assertj.swing.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.assertj.swing.util.OSFamily.LINUX;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for {@link OSIdentifier#isLinux()}.
 * 
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class OSIdentifier_isLinux_Test extends OSIdentifier_TestCase {
  private final String linux;

  @Parameters
  public static Collection<Object[]> linux() {
    return newArrayList(new Object[][] { { "linux" }, { "Linux" }, { "LINUX" } });
  }

  public OSIdentifier_isLinux_Test(String linux) {
    this.linux = linux;
  }

  @Test
  public void shouldReturnLinuxIfOSNameIsEqualToLinux() {
    returnOSName(linux);
    OSIdentifier osIdentifier = new OSIdentifier(propertyReader);
    assertThat(osIdentifier.isLinux()).isTrue();
    assertThat(osIdentifier.isX11()).isTrue();
    assertThat(osIdentifier.isHPUX()).isFalse();
    assertThat(osIdentifier.isMacintosh()).isFalse();
    assertThat(osIdentifier.isOSX()).isFalse();
    assertThat(osIdentifier.isSolaris()).isFalse();
    assertThat(osIdentifier.isWindows()).isFalse();
    assertThat(osIdentifier.isWindows9x()).isFalse();
    assertThat(osIdentifier.isWindowsXP()).isFalse();
    assertThat(osIdentifier.osFamily()).isEqualTo(LINUX);
  }
}

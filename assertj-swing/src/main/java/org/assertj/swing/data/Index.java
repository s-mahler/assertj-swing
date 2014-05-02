/*
 * Created on Feb 13, 2009
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
package org.assertj.swing.data;

import static org.assertj.core.util.Objects.HASH_CODE_PRIME;

import javax.annotation.Nonnull;

/**
 * An integer-based index.
 * 
 * @author Alex Ruiz
 */
public final class Index {
  /**
   * Creates a new {@link Index}.
   * 
   * @param value the value of the index to create.
   * @return the created index.
   */
  public static @Nonnull
  Index atIndex(int value) {
    return new Index(value);
  }

  /** The value of this index. */
  public final int value;

  private Index(int value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Index)) {
      return false;
    }
    Index other = (Index) obj;
    return value == other.value;
  }

  @Override
  public int hashCode() {
    return HASH_CODE_PRIME * 1 + value;
  }

  @Override
  public String toString() {
    return String.format("%s[value=%d]", getClass().getName(), value);
  }
}

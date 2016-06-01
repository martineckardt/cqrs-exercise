/**
 * Copyright (C) 2015 Lufthansa Industry Solutions AS GmbH. All rights reserved. 
 * <http://www.lhind.org/>
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library. If not, see <http://www.gnu.org/licenses/>.
 */
package org.lhind.reservation_mgmt.common.model.flight;

import org.fuin.objects4j.common.Contract;
import org.fuin.objects4j.vo.ValueObject;

/**
 * Represents a CAPACITY of a flight.
 */
public class Capacity implements ValueObject {
  private int value;

  /**
   * Creates an Capacity.
   * 
   * @param value
   *          must be an int between 1 and 300
   */
  public Capacity(int value) {
    // Validation
    Contract.requireArgMin("value", value, 1);
    Contract.requireArgMax("value", value, 300);

    this.value = value;
  }

  /**
   * @return the value
   */
  public int getValue() {
    return value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + value;
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Capacity other = (Capacity) obj;
    if (value != other.value) {
      return false;
    }
    return true;
  }
}

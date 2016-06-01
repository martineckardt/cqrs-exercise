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

import javax.management.InvalidAttributeValueException;

import org.fuin.objects4j.vo.ValueObject;

/**
 * @author U519643
 *
 */
public class Route implements ValueObject {
  private String originCode;
  private String destinationCode;

  public Route(String originCode, String destinationCode)
      throws InvalidAttributeValueException {
    super();

    if (originCode.equals(destinationCode)) {
      throw new InvalidAttributeValueException(
          "Origing code may not be equal to destinationCode");
    }

    this.originCode = originCode;
    this.destinationCode = destinationCode;
  }

  /**
   * @return the originCode
   */
  public String getOriginCode() {
    return originCode;
  }

  /**
   * @return the destinationCode
   */
  public String getDestinationCode() {
    return destinationCode;
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
    result = prime * result
        + ((destinationCode == null) ? 0 : destinationCode.hashCode());
    result = prime * result
        + ((originCode == null) ? 0 : originCode.hashCode());
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
    Route other = (Route) obj;
    if (destinationCode == null) {
      if (other.destinationCode != null) {
        return false;
      }
    } else if (!destinationCode.equals(other.destinationCode)) {
      return false;
    }
    if (originCode == null) {
      if (other.originCode != null) {
        return false;
      }
    } else if (!originCode.equals(other.originCode)) {
      return false;
    }
    return true;
  }

}

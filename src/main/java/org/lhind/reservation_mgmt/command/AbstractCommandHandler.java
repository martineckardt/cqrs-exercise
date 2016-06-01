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
package org.lhind.reservation_mgmt.command;

import org.fuin.cqrs4j.AbstractCommand;
import org.fuin.ddd4j.ddd.BasicEventMetaData;
import org.lhind.reservation_mgmt.common.Logger;

/**
 * Base class for concrete org.lhind.reservation_mgmt.command handlers.
 */
public abstract class AbstractCommandHandler {

  static protected Logger LOG;

  /**
   * Creates a new meta data instance.
   * 
   * @param request
   *          Request to use.
   * @param cmd
   *          Command to use.
   * 
   * @return New meta data instance.
   */
  protected BasicEventMetaData createMetaData(final AbstractCommand cmd) {

    return null;
  }

  /**
   * Returns the user name form the request.
   * 
   * @param request
   *          Request to use.
   * 
   * @return User name from the principal or <code>null</code>.
   */
  protected String getUser() {
    return "Admin";
  }

  /**
   * @param cmd
   */
  protected void checkAuthorization(AbstractCommand cmd) {
    // No authorization supported. Just for demonstration

  }

}

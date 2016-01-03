/**
 * This file is part of GreatmancodeTools.
 *
 * Copyright (c) 2013-2016, Greatman <http://github.com/greatman/>
 *
 * GreatmancodeTools is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GreatmancodeTools is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GreatmancodeTools.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.tools.events.playerEvent;

import com.greatmancode.tools.events.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Created by greatman on 16-01-03.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class PreJoinEvent extends Event {
    private final String name;
    private final UUID uuid;

    public PreJoinEvent(String name, UUID uniqueId) {
        this.name = name;
        this.uuid = uniqueId;
    }
}

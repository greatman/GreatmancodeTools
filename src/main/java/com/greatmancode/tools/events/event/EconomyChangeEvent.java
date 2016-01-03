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
package com.greatmancode.tools.events.event;

import com.greatmancode.tools.events.Event;

/**
 * Represents a change in a economy account
 */
public class EconomyChangeEvent extends Event {
    private final String account;
    private final double amount;

    /**
     * Represents a change in a economy account
     *
     * @param account The account name
     * @param amount  The balance of the account
     */
    public EconomyChangeEvent(String account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    /**
     * Retrieve the account associated with this change
     *
     * @return The account
     */
    public String getAccount() {
        return account;
    }

    /**
     * Retrieve the balance of the account associated with this change
     *
     * @return the balance of the account
     */
    public double getAmount() {
        return amount;
    }
}

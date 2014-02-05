/*
 * This file is part of GreatmancodeTools.
 *
 * Copyright (c) 2013-2014, Greatman <http://github.com/greatman/>
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
package com.greatmancode.tools.caller.spout;

import java.awt.Color;

public enum SpoutChatStyle {
	BLACK('0', Color.BLACK, Color.BLACK),
	DARK_BLUE('1', new Color(0, 0, 170), new Color(0, 0, 42)),
	DARK_GREEN('2', new Color(0, 170, 0), new Color(0, 42, 0)),
	DARK_AQUA('3', new Color(0, 170, 170), new Color(0, 42, 42)),
	DARK_RED('4', new Color(170, 0, 0), new Color(42, 0, 0)),
	PURPLE('5', new Color(170, 0, 170), new Color(42, 0, 42)),
	GOLD('6', new Color(255, 170, 0), new Color(42, 42, 0)),
	GRAY('7', new Color(170, 170, 170), new Color(42, 42, 42)),
	DARK_GRAY('8', new Color(85, 85, 85), new Color(21, 21, 21)),
	BLUE('9', new Color(85, 85, 255), new Color(21, 21, 63)),
	GREEN('a', new Color(85, 255, 85), new Color(21, 63, 21)),
	AQUA('b', new Color(85, 255, 255), new Color(21, 63, 63)),
	RED('c', new Color(255, 85, 85), new Color(63, 21, 21)),
	PINK('d', new Color(255, 85, 255), new Color(63, 21, 63)),
	YELLOW('e', new Color(255, 255, 85), new Color(63, 63, 21)),
	WHITE('f', Color.WHITE, new Color(63, 63, 63)),
	OBFUSCATED('k'),
	BOLD('l'),
	UNDERLINE('n'),
	ITALIC('o'),
	RESET('r');
	public static final char COLOR_CHAR = '\u00A7';
	private final char c;
	private final Color foregroundColor, backgroundColor;

	private SpoutChatStyle(char c) {
		this(c, null, null);
	}

	private SpoutChatStyle(char c, Color foregroundColor, Color backgroundColor) {
		this.c = c;
		this.foregroundColor = foregroundColor;
		this.backgroundColor = backgroundColor;
	}

	public char getChar() {
		return c;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	@Override
	public String toString() {
		return "" + COLOR_CHAR + c;
	}
}

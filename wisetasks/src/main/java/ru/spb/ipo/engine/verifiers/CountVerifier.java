/*
 * This file is part of Wisetasks
 *
 * Copyright (C) 2006-2008, 2012  Michael Bogdanov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.spb.ipo.engine.verifiers;

import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.task.Node;

/**
 * User: mike
 * Date: 16.10.2008
 */
public class CountVerifier extends SimpleVerifier {

    public CountVerifier(Node node) throws SystemException, TaskDeserializationException {
        super(node);
    }
}

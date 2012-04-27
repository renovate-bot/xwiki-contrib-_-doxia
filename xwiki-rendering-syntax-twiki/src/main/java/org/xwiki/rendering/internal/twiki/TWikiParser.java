/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.rendering.internal.twiki;

import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.rendering.syntax.Syntax;
import org.xwiki.rendering.internal.doxia.AbstractDoxiaParser;

/**
 * TWiki Parser.
 *
 * @version $Id$
 * @since 4.1M1
 */
@Component
@Named("twiki/1.0")
@Singleton
public class TWikiParser extends AbstractDoxiaParser
{
    @Override
    public Syntax getSyntax()
    {
        return Syntax.TWIKI_1_0;
    }

    @Override
    public org.apache.maven.doxia.parser.Parser createDoxiaParser()
    {
        return new org.apache.maven.doxia.module.twiki.TWikiParser();
    }
}

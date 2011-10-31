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
package org.xwiki.rendering.xdomxml.internal.version10.parser;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.xml.sax.SAXException;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.InstantiationStrategy;
import org.xwiki.component.descriptor.ComponentInstantiationStrategy;
import org.xwiki.rendering.parser.ParseException;
import org.xwiki.rendering.syntax.SyntaxFactory;
import org.xwiki.rendering.xdomxml.internal.parser.DefaultBlockParser;

@Component("raw_text")
@InstantiationStrategy(ComponentInstantiationStrategy.PER_LOOKUP)
public class RawTextBlockParser extends DefaultBlockParser
{
    private static final Set<String> NAMES = new HashSet<String>()
    {
        {
            add("content");
            add("syntax");
        }
    };

    @Inject
    private SyntaxFactory syntaxFactory;

    public RawTextBlockParser()
    {
        super(NAMES);
    }

    @Override
    protected void beginBlock() throws SAXException
    {
        try {
            getListener().onRawText(getParameterAsString("content", ""),
                this.syntaxFactory.createSyntaxFromIdString(getParameterAsString("syntax", null)));
        } catch (ParseException e) {
            throw new SAXException("Failed to parse [syntax] parameter in rw block", e);
        }
    }
}

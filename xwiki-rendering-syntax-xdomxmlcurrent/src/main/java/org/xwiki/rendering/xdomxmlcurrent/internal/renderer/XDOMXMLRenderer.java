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
package org.xwiki.rendering.xdomxmlcurrent.internal.renderer;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.filter.xml.serializer.XMLSerializerFactory;
import org.xwiki.rendering.renderer.xml.ContentHandlerStreamRenderer;
import org.xwiki.rendering.syntax.Syntax;
import org.xwiki.rendering.xml.internal.renderer.AbstractRenderer;

/**
 * Current version of the XDOM+XML renderer.
 *
 * @version $Id$
 * @since 3.3M1
 */
@Component
@Named("xdom+xml/current")
@Singleton
public class XDOMXMLRenderer extends AbstractRenderer
{
    /**
     * The actual XML serializer factory.
     */
    @Inject
    private XMLSerializerFactory serializerFactory;

    @Override
    public Syntax getSyntax()
    {
        return Syntax.XDOMXML_CURRENT;
    }

    @Override
    protected ContentHandlerStreamRenderer createContentHandlerStreamRenderer()
    {
        return new XDOMXMLChainingStreamRenderer(this.serializerFactory);
    }
}

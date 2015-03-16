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
package org.xwiki.rendering.xdomxml10.internal.renderer;

import org.xml.sax.ContentHandler;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.InstantiationStrategy;
import org.xwiki.component.descriptor.ComponentInstantiationStrategy;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.rendering.listener.chaining.ListenerChain;
import org.xwiki.rendering.syntax.Syntax;
import org.xwiki.rendering.xml.internal.renderer.AbstractChainingContentHandlerStreamRenderer;

@Component
@Named("xdom+xml/1.0")
@InstantiationStrategy(ComponentInstantiationStrategy.PER_LOOKUP)
public class XDOMXMLContentHandlerStreamRenderer extends AbstractChainingContentHandlerStreamRenderer implements
    Initializable
{
    @Override
    public void initialize() throws InitializationException
    {
        ListenerChain chain = new ListenerChain();
        setListenerChain(chain);

        // Construct the listener chain in the right order. Listeners early in the chain are called before listeners
        // placed later in the chain.
        chain.addListener(this);
        chain.addListener(new XDOMXMLChainingStreamRenderer(chain));
    }

    @Override
    public Syntax getSyntax()
    {
        return null;
    }
    
    @Override
    public void setContentHandler(ContentHandler contentHandler)
    {
        super.setContentHandler(contentHandler);

        ((XDOMXMLChainingStreamRenderer) getListenerChain().getListener(XDOMXMLChainingStreamRenderer.class))
            .setContentHandler(contentHandler);
    }
}

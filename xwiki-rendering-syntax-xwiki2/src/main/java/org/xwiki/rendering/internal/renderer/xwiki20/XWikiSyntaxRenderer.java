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
package org.xwiki.rendering.internal.renderer.xwiki20;

import javax.inject.Inject;
import javax.inject.Named;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.annotation.InstantiationStrategy;
import org.xwiki.component.descriptor.ComponentInstantiationStrategy;
import org.xwiki.rendering.listener.chaining.ChainingListener;
import org.xwiki.rendering.listener.chaining.ListenerChain;
import org.xwiki.rendering.renderer.reference.ResourceReferenceSerializer;

/**
 * Generates XWiki Syntax 2.0 from {@link org.xwiki.rendering.block.XDOM}. This is useful for example to convert other wiki
 * syntaxes to the XWiki syntax. It's also useful in our tests to verify that round-tripping from XWiki Syntax to the
 * DOM and back to XWiki Syntax generates the same content as the initial syntax.
 * 
 * @version $Id$
 * @since 2.0M3
 */
@Component
@Named("xwiki/2.0")
@InstantiationStrategy(ComponentInstantiationStrategy.PER_LOOKUP)
public class XWikiSyntaxRenderer extends AbstractXWikiSyntaxRenderer
{
    /**
     * Needed by XWikiSyntaxChainingRenderer to serialize wiki link references.
     */
    @Inject
    @Named("xwiki/2.0/link")
    private ResourceReferenceSerializer linkReferenceSerializer;

    /**
     * Needed by XWikiSyntaxChainingRenderer to serialize wiki image references.
     */
    @Inject
    @Named("xwiki/2.0/image")
    private ResourceReferenceSerializer imageReferenceSerializer;

    /**
     * {@inheritDoc}
     * @see AbstractXWikiSyntaxRenderer#createXWikiSyntaxChainingRenderer(ListenerChain) 
     */
    @Override
    protected ChainingListener createXWikiSyntaxChainingRenderer(ListenerChain chain)
    {
        return new XWikiSyntaxChainingRenderer(chain, this.linkReferenceSerializer, this.imageReferenceSerializer);
    }
}

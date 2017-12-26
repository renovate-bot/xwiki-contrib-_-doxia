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
package org.xwiki.rendering.internal.parser.xhtml.wikimodel;

import org.xwiki.rendering.internal.renderer.xhtml.XHTMLChainingRenderer;
import org.xwiki.rendering.wikimodel.WikiParameter;
import org.xwiki.rendering.wikimodel.WikiParameters;
import org.xwiki.rendering.wikimodel.xhtml.handler.HeaderTagHandler;
import org.xwiki.rendering.wikimodel.xhtml.impl.TagContext;
import org.xwiki.text.StringUtils;

/**
 * Override the default implementation of the WikiModel XHTML parser for handling HTML headers. We need to do this in
 * order to handle the <code>id</code> attribute that we generate in our XHTML renderer.
 *
 * @version $Id$
 * @since 1.6RC1
 */
public class XWikiHeaderTagHandler extends HeaderTagHandler implements XWikiWikiModelHandler
{
    private final static String CLASS = "class";

    private static final String ID = "id";

    @Override
    protected void begin(TagContext context)
    {
        int level = Integer.parseInt(context.getName().substring(1, 2));
        sendEmptyLines(context);

        // If there's an ID attribute then check if it corresponds to the default header ID generated by the
        // XWiki ID generator. If so remove it so that it's not displayed. We need to do this because the
        // XWiki HTML renderer automatically generates an ID for headers.
        WikiParameters params = context.getParams();
        if (params.getParameter(ID) != null && params.getParameter(CLASS) != null) {
            WikiParameter classParameter = params.getParameter(CLASS);
            if (classParameter.getValue().contains(XHTMLChainingRenderer.GENERATEDIDCLASS)) {
                params = params.remove(ID);
                // Also remove the generated id class value since we're removing the id too.
                String newClassParameterValue =
                    classParameter.getValue().replace(XHTMLChainingRenderer.GENERATEDIDCLASS, "");
                if (StringUtils.isBlank(newClassParameterValue)) {
                    params = params.remove(CLASS);
                } else {
                    params = params.setParameter(CLASS, newClassParameterValue);
                }
            }
        }

        context.getScannerContext().beginHeader(level, params);
    }
}

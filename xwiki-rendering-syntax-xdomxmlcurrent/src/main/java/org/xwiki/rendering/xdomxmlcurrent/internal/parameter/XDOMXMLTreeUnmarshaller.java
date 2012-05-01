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
package org.xwiki.rendering.xdomxmlcurrent.internal.parameter;

import java.lang.reflect.Type;

import org.xwiki.component.util.ReflectionUtils;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.converters.DataHolder;
import com.thoughtworks.xstream.core.TreeUnmarshaller;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.mapper.Mapper;

public class XDOMXMLTreeUnmarshaller extends TreeUnmarshaller
{
    public XDOMXMLTreeUnmarshaller(Object root, HierarchicalStreamReader reader, ConverterLookup converterLookup,
        Mapper mapper)
    {
        super(root, reader, converterLookup, mapper);
    }

    @Override
    public Object start(DataHolder dataHolder)
    {
        Type type = (Type) dataHolder.get("type");

        Class< ? > typeClass;
        if (type != null) {
            typeClass = ReflectionUtils.getTypeClass(type);

            if (typeClass == null) {
                throw new ConversionException("Can't find any converter for the type [" + type + "]");
            }
        } else {
            typeClass = null;
        }

        if (typeClass != null) {
            return convertAnother(null, typeClass);
        } else {
            throw new ConversionException("Can't find any converter for the type [" + type + "]");
        }
    }
}

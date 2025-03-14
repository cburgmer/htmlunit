/*
 * Copyright (c) 2002-2025 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.htmlunit.svg;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.htmlunit.SgmlPage;
import org.htmlunit.html.DomAttr;
import org.htmlunit.html.ScriptElement;
import org.htmlunit.html.ScriptElementSupport;

/**
 * Wrapper for the SVG element {@code script}.
 *
 * @author Ahmed Ashour
 */
public class SvgScript extends SvgElement implements ScriptElement {

    /** The tag represented by this element. */
    public static final String TAG_NAME = "script";
    private boolean executed_;
    private boolean createdByDomParser_;

    /**
     * Creates a new instance.
     *
     * @param namespaceURI the URI that identifies an XML namespace
     * @param qualifiedName the qualified name of the element type to instantiate
     * @param page the page that contains this element
     * @param attributes the initial attributes
     */
    SvgScript(final String namespaceURI, final String qualifiedName, final SgmlPage page,
            final Map<String, DomAttr> attributes) {
        super(namespaceURI, qualifiedName, page, attributes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExecuted() {
        return executed_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setExecuted(final boolean executed) {
        executed_ = executed;
    }

    /**
     * Returns the value of the attribute {@code src}. Refer to the
     * <a href="http://www.w3.org/TR/html401/">HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code src}
     *         or an empty string if that attribute isn't defined.
     */
    public final String getSrcAttribute() {
        return getSrcAttributeNormalized();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getScriptSource() {
        return getSrcAttributeNormalized();
    }

    /**
     * Helper for src retrieval and normalization.
     *
     * @return the value of the attribute {@code src} with all line breaks removed
     *         or an empty string if that attribute isn't defined.
     */
    protected final String getSrcAttributeNormalized() {
        // at the moment StringUtils.replaceChars returns the org string
        // if nothing to replace was found but the doc implies, that we
        // can't trust on this in the future
        final String attrib = getAttributeDirect(SRC_ATTRIBUTE);
        if (ATTRIBUTE_NOT_DEFINED == attrib) {
            return attrib;
        }

        return StringUtils.replaceChars(attrib, "\r\n", "");
    }

    /**
     * Returns the value of the attribute {@code charset}. Refer to the
     * <a href="http://www.w3.org/TR/html401/">HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code charset}
     *         or an empty string if that attribute isn't defined.
     */
    public final String getCharsetAttribute() {
        return getAttributeDirect("charset");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getScriptCharset() {
        return getAttributeDirect("charset");
    }

    /**
     * Returns the value of the attribute {@code defer}. Refer to the
     * <a href="http://www.w3.org/TR/html401/">HTML 4.01</a>
     * documentation for details on the use of this attribute.
     *
     * @return the value of the attribute {@code defer}
     *         or an empty string if that attribute isn't defined.
     */
    public final String getDeferAttribute() {
        return getAttributeDirect("defer");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDeferred() {
        return ATTRIBUTE_NOT_DEFINED != getDeferAttribute();
    }

    /**
     * Executes the <code>onreadystatechange</code> handler as well as executing
     * the script itself, if necessary. {@inheritDoc}
     */
    @Override
    public void onAllChildrenAddedToPage(final boolean postponed) {
        ScriptElementSupport.onAllChildrenAddedToPage(this, postponed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void markAsCreatedByDomParser() {
        createdByDomParser_ = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean wasCreatedByDomParser() {
        return createdByDomParser_;
    }
}

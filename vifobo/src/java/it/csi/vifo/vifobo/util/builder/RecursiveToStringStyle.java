/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.util.builder;

import java.util.Collection;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>Works with {@link ToStringBuilder} to create a "deep" <code>toString</code>.</p>
 *
 * <p>To use this class write code as follows:</p>
 *
 * <pre>
 * public class Job {
 *   String title;
 *   ...
 * }
 *
 * public class Person {
 *   String name;
 *   int age;
 *   boolean smoker;
 *   Job job;
 *
 *   ...
 *
 *   public String toString() {
 *     return new ReflectionToStringBuilder(this, new RecursiveToStringStyle()).toString();
 *   }
 * }
 * </pre>
 *
 * <p>This will produce a toString of the format:
 * <code>Person@7f54[name=Stephen,age=29,smoker=false,job=Job@43cd2[title=Manager]]</code></p>
 *
 * @since 3.2
 * @version $Id: RecursiveToStringStyle.java 1606051 2014-06-27 12:22:17Z ggregory $
 */
public class RecursiveToStringStyle extends ToStringStyle {

    /**
     * Required for serialization support.
     *
     * @see java.io.Serializable
     */
    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor.</p>
     */
    public RecursiveToStringStyle() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang3.builder.ToStringStyle#appendDetail(java.lang.StringBuffer, java.lang.String, java.lang.Object)
     */
    @Override
    public void appendDetail(final StringBuffer buffer, final String fieldName, final Object value) {
        if (! ClassUtils.isPrimitiveWrapper(value.getClass()) &&
            ! String.class.equals(value.getClass()) &&
            this.accept(value.getClass())) {
            buffer.append(ReflectionToStringBuilder.toString(value, this));
        } else {
            super.appendDetail(buffer, fieldName, value);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.lang3.builder.ToStringStyle#appendDetail(java.lang.StringBuffer, java.lang.String, java.util.Collection)
     */
    @Override
    protected void appendDetail(final StringBuffer buffer, final String fieldName, final Collection<?> coll) {
        this.appendClassName(buffer, coll);
        this.appendIdentityHashCode(buffer, coll);
        this.appendDetail(buffer, fieldName, coll.toArray());
    }

    /**
     * Returns whether or not to recursively format the given <code>Class</code>.
     * By default, this method always returns {@code true}, but may be overwritten by
     * sub-classes to filter specific classes.
     *
     * @param clazz
     *            The class to test.
     * @return Whether or not to recursively format the given <code>Class</code>.
     */
    protected boolean accept(final Class<?> clazz) {
        return true;
    }

}

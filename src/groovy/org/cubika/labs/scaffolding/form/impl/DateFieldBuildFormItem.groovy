/**
 * Copyright 2009-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cubika.labs.scaffolding.form.impl

import org.cubika.labs.scaffolding.form.BuildFormItem
import org.cubika.labs.scaffolding.utils.FlexScaffoldingUtils as FSU

/**
 * Extends AbstractBuildFormItem adding datefield building functionality.
 *
 * @author Ezequiel Martin Apfel
 */
class DateFieldBuildFormItem extends AbstractBuildFormItem {

	/**
	 * Constructor
	 */
	DateFieldBuildFormItem(property) {
		super(property)
	}

	/**
	 * @see #AbstractBuildFormItem
	 *
	 */
	protected String buildFormItemComponent(binding) {
		def sw = new StringWriter()
		def pw = new PrintWriter(sw)

		pw.println """				<cubikalabs:CBKDateField id="${getID()}" selectedDate="{${binding}}"/>"""

		sw.toString()
	}

	/**
	 * @see #AbstractBuildFormItem
	 */
	String getFormAttr() { "${getID()}.${value()}" }

	/**
	 * @see #AbstractBuildFormItem
	 */
	String getID() { "df${FSU.capitalize(property.name)}" }

	/**
	 * @see #AbstractBuildFormItem
	 */
	String value() { "selectedDate" }
}

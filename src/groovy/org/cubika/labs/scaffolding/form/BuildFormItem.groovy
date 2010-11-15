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
package org.cubika.labs.scaffolding.form

/**
 * Declares Form Item Builders functionality.
 *
 * @author Ezequiel Martin Apfel
 */
interface BuildFormItem {

	/**
	 * Builds form item
	 * @param binding - property which componente must listen
	 * @return 			- form item component as string
	 */
	String build(binding)

	/**
	 * Builds form item validator
	 * @return 			- form item validator as string
	 */
	String buildValidator()

	/**
	 * @return String - form item value representation used by templates
	 *							e.g "combo.text"
	 */
	String getFormAttr()

	/**
	 * @return String - form item child component's id
	 */
	String getID()

	/**
	 * @return String - form item id
	 */
	String getFormItemID()

	/**
	 * @return String - form item id
	 */
	String value()

	/**
	 * @return String - Removed component as string
	 */
	String getRemoveChildEditView()

	/**
	 * @return String - Created component as string
	 */
	String getRemoveChildCreateView()
}

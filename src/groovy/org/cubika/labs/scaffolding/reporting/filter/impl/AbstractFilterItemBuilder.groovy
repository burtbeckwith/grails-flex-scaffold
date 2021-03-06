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
package org.cubika.labs.scaffolding.reporting.filter.impl

import org.codehaus.groovy.grails.commons.GrailsDomainClassProperty
import org.cubika.labs.scaffolding.reporting.ReportingBuilder

/**
 * Basic definition of a Filter Builder
 * @author Gonzalo Clavell
 */
abstract class AbstractFilterItemBuilder implements ReportingBuilder {

	/**
	 * Domain Class property on which builder is based
	 */
	GrailsDomainClassProperty prop

	/**
	 * Main Label for filter component
	 */
	String mainLabel

	/**
	 * Property name, used for filter id and query key
	 */
	String propName

	/**
	 * Constructor
	 * Sets prop, propName and mainLabel based on property received as param
	 * @param GrailsDomainClassProperty
	 */
	AbstractFilterItemBuilder(property) {
		prop = property
		propName = prop.name
		mainLabel = prop.naturalName
	}
}

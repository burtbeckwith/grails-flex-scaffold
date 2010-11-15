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
package org.cubika.labs.scaffolding.validator.impl

/**
 * Credit card validator builder.
 *
 * @author Ezequiel Martin Apfel
 */
class CreditCardBuildValidator extends AbstractBuildValidator {

	/**
	 * @see org.cubika.labs.scaffolding.validator.BuildValidator
	 */
	String build(id, value, constraint) {
		"""<mx:CreditCardValidator source="{${id}}" """ +
			"""property="${value}" required="${!constraint.isBlank()}"/>"""
	}
}

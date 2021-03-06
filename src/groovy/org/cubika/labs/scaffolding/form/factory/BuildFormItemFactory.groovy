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
package org.cubika.labs.scaffolding.form.factory

import org.cubika.labs.scaffolding.form.BuildFormItem
import org.cubika.labs.scaffolding.form.FormItemConstants as FIC
import org.cubika.labs.scaffolding.form.impl.TextInputBuildFormItem
import org.cubika.labs.scaffolding.form.impl.TextAreaBuildFormItem
import org.cubika.labs.scaffolding.form.impl.RichTextEditorBuildFormItem
import org.cubika.labs.scaffolding.form.impl.ComboBuildFormItem
import org.cubika.labs.scaffolding.form.impl.AutoCompleteBuildFormItem
import org.cubika.labs.scaffolding.form.impl.NumericStepperBuildFormItem
import org.cubika.labs.scaffolding.form.impl.CheckBoxBuildFormItem
import org.cubika.labs.scaffolding.form.impl.DateFieldBuildFormItem
import org.cubika.labs.scaffolding.form.impl.OneToManyBuildFormItem
import org.cubika.labs.scaffolding.form.impl.OneToOneBuildFormItem
import org.cubika.labs.scaffolding.form.impl.ExternalOneToManyBuildFormItem
import org.cubika.labs.scaffolding.form.impl.ExternalManyToOneBuildFormItem
import org.cubika.labs.scaffolding.form.impl.ExternalDataGridManyToOneBuildFormItem
import org.cubika.labs.scaffolding.form.impl.ColorPickerBuildFormItem
import org.cubika.labs.scaffolding.form.impl.SliderBuildFormItem
import org.cubika.labs.scaffolding.form.impl.FileUploadBuildFormItem
import org.cubika.labs.scaffolding.form.impl.ImageUploadBuildFormItem
import org.cubika.labs.scaffolding.form.impl.SnapshotUploadBuildFormItem
import org.cubika.labs.scaffolding.form.impl.PasswordTextInputBuildFormItem
import org.cubika.labs.scaffolding.utils.ConstraintValueUtils as CVU

/**
 * Form item builder factory.
 *
 * @author Ezequiel Martin Apfel
 */
class BuildFormItemFactory {

	private static _classLoader

	/**
	 * Must be set from a script for builders that extend AbstractRelationBuildFormItem which creates a DefaultFlexTemplateGenerator.
	 */
	static void setClassLoader(classLoader) {
		_classLoader = classLoader
		//<% import org.cubika.labs.scaffolding.form.factory.BuildFormItemFactory as BFIF
	}

	/**
	 * Create form item based on property parameter
	 */
	static BuildFormItem createFormItem(property) {
		if (!CVU.display(property)) {
			return
		}

		def constraint = property.domainClass.constrainedProperties[property.name]

		if (constraint.widget == FIC.PASSWORD) {
			validateWidget(property.type,String, constraint.widget)
			return new PasswordTextInputBuildFormItem(property)
		}

		if (constraint.widget == FIC.IMAGE_UPLOAD) {
			validateWidget(property.type,String, constraint.widget)
			return new ImageUploadBuildFormItem(property)
		}

		if (constraint.widget == FIC.FILE_UPLOAD) {
			validateWidget(property.type,String, constraint.widget)
			return new FileUploadBuildFormItem(property)
		}

		if (constraint.widget == FIC.SNAP_UPLOAD) {
			validateWidget(property.type,String, constraint.widget)
			return new SnapshotUploadBuildFormItem(property)
		}

		if (constraint.widget == FIC.TEXT_INPUT) {
			return new TextInputBuildFormItem(property)
		}

		if (constraint.widget == FIC.TEXT_AREA) {
			return new TextAreaBuildFormItem(property)
		}

		if (CVU.richtext(property)) {
			validateWidget(property.type,String, constraint.widget)
			return new RichTextEditorBuildFormItem(property)
		}

		if (constraint.widget == FIC.COLOR_PICKER) {
			validateWidget(property.type,String, constraint.widget)
			return new ColorPickerBuildFormItem(property)
		}

		if (constraint.widget == FIC.V_SLIDER) {
			return new SliderBuildFormItem(property,"V")
		}

		if (constraint.widget == FIC.H_SLIDER) {
			return new SliderBuildFormItem(property,"H")
		}

		if (property.type == String && !constraint.inList) {
			return new TextInputBuildFormItem(property)
		}

		if (constraint.inList && constraint.widget != FIC.AUTO_COMPLETE) {
			return new ComboBuildFormItem(property)
		}

		if (constraint.inList && constraint.widget == FIC.AUTO_COMPLETE) {
			return new AutoCompleteBuildFormItem(property)
		}

		if (property.type == Integer || property.type == Long || property.type == Float ||
				property.type == Double) {
			return new NumericStepperBuildFormItem(property)
		}

		if (property.type == Boolean) {
			return new CheckBoxBuildFormItem(property)
		}

		if (property.type == Date) {
			return new DateFieldBuildFormItem(property)
		}

		if (property.isManyToMany()) {
			return
		}

		boolean inPlace = CVU.getInPlace(constraint)

		if (property.isManyToOne()) {
			if (constraint.widget == FIC.COMBO_BOX) {
				return new ExternalManyToOneBuildFormItem(property, _classLoader)
			}
			return new ExternalDataGridManyToOneBuildFormItem(property, _classLoader)
			//else {
			//return new ManyToOneBuildFormItem(property)
			//}
		}

		if (property.isOneToMany()) {
			if (inPlace) {
				return new OneToManyBuildFormItem(property, _classLoader)
			}

			return new ExternalOneToManyBuildFormItem(property, _classLoader)
		}

		if (property.isOneToOne()) {
			if (!inPlace && !property.bidirectional) { //La trato como una many-to-one inPlace:false
				if (constraint.widget == FIC.COMBO_BOX) {
					return new ExternalManyToOneBuildFormItem(property, _classLoader)
				}
				return new ExternalDataGridManyToOneBuildFormItem(property, _classLoader)
			}
			else if (inPlace) {
				return new OneToOneBuildFormItem(property, _classLoader)
			}
		}
	}

	static private void validateWidget(type, clazz, widget) {
		if (type != clazz) {
			throw new Exception("Property must be ${clazz} to use \"${widget}\"")
		}
	}
}

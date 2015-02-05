/*******************************************************************************
 * Copyright (c) 2014 Eugen Covaci.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Eugen Covaci - initial API and implementation
 ******************************************************************************/
package org.vat.validator.impl;


/**
 * French VAT number validator.
 * 
 * @author eugen covaci
 * 
 */
public class FR extends AbstractVatFormalValidator  {

	@Override
	public boolean validateDigits(String vatNumber) {

		// Checks the check digits of a French VAT number.

		if (!vatNumber.matches("^\\d{11}$"))
			return true;

		// Extract the last nine digits as an integer.
		long total = Long.parseLong(vatNumber.substring(2));

		// Establish check digit.
		total = (total * 100L + 12L) % 97;

		// Compare it with the last character of the VAT number. If it's the
		// same, then it's valid.
		return total == Integer.parseInt(vatNumber.substring(0, 2));
	}

	@Override
	public String[] getRegexArray() {
		return new String[]{"^(\\d{11})$", "^([(A-H)|(J-N)|(P-Z)]\\d{10})$", "^(\\d[(A-H)|(J-N)|(P-Z)]\\d{9})$", "^([(A-H)|(J-N)|(P-Z)]{2}\\d{9})$"};
	}

}
package pers.hsc.evats.core.query.exception;

import pers.hsc.evats.core.query.data.Condition.Operator;

/**
 * @author hsc
 *
 * Mar 29, 2018
 */
@SuppressWarnings("serial")
public final class InvlidOperatorException extends QueryException {

	public InvlidOperatorException(String property, String operatorStr) {
		this(property, operatorStr, null);
	}

	public InvlidOperatorException(String property, String operatorStr, Throwable cause) {
		super("Invalid   Operator property [" + property + "], " + "operator [" + operatorStr + "], must be one of "
				+ Operator.toStringAllOperator(), cause);
	}
}

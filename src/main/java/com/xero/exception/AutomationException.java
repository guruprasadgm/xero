package com.xero.exception;

public class AutomationException extends RuntimeException
{
    /**
         * Constructor.
         * @param failureMessage Textual description of the problem.
         */
        public AutomationException(String failureMessage)
        {
            super(failureMessage);
        }

        /**
         * Constructor.
         * @param originalException The original exception.
         */
        public AutomationException(Exception originalException)
        {
            super(originalException);
        }


}


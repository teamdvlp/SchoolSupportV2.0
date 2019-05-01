package com.teamttdvlp.goodthanbefore.schoolsupport.otherstuff.support

import timber.log.Timber

class LoggingSupporterTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        return String.format("Log: %s: %s: %s",
            element.className,
            element.methodName,
            element.lineNumber)
    }

}
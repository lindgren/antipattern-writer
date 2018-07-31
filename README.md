# Antipattern

## Overview
I came across a little code snippet which neatly sends metrics to a Graphite server. The caveat is that the code isn't
easily testable. Take a look at `uk.co.lindgrens.antipattern.MyGraphiteMessageSender` and see what you think.

### Refactoring attempt 1 (tddstyle1)
Take a look at the code in the following package `uk.co.lindgrens.tddstyle1`

Pros:
* Functionality is covered by tests

Cons:
* Code feels bloated (just a feeling!!)
* No test written for `MyGraphiteSocketFactory`. Maybe that is ok, as there isn't any behavour in this class, thoughts?

### Refactoring attempt 1 (tddstyle2)
Take a look at the code in the following package `uk.co.lindgrens.tddstyle2`

This attempt uses an old trick by giving up on the private visibility of methods and instead making certain methods
package private. Is it worth the sacrifice?

Pros:
* Original class doesn't need to be refactored other than changing the visibility on certain methods

Cons:
* Giving up on private visibility on methods
* Too much tempering with setup by overriding various methods in the class under test.
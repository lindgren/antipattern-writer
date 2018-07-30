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


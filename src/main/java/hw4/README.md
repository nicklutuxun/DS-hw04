# Discussion

**Document all error conditions you determined and why they are error
 conditions. Do this by including the inputs that you used to test your
  program and what error conditions they exposed:**

| Error condition       |       input example | output                                           | Reason                                       |
|-----------------------|--------------------:|--------------------------------------------------|----------------------------------------------|
| Bad token             |              `blah` | `ERROR: bad token`                               | Doesn't make sense for an integer calculator |
| Divide by zero        |     `1 0 /`/`1 0 %` | `ERROR: cannot divide by zero`                   | Divide by zero is undefined                  |
| Empty stack(operator) | `+`/`-`/`*`/`/`/`%` | `ERROR: operator need 2 operands but 0 is given` | Binary operator needs 2 operands             |
| Empty stack(`.`)      |                 `.` | `ERROR: empty stack`                             | `stack.top()` throws `EmptyException`        |
| Stack of one value    |   `1 +`/`1 *`/`1 %` | `ERROR: operator need 2 operands but 1 is given` | Binary operator needs 2 operands             |

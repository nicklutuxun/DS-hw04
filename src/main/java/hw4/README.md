# Discussion

**Document all error conditions you determined and why they are error
 conditions. Do this by including the inputs that you used to test your
  program and what error conditions they exposed:**

| Error condition       |   input example | Reason                                       |
|-----------------------|----------------:|----------------------------------------------|
| Bad token             |          `blah` | Doesn't make sense for an integer calculator |
| Divide by zero        | `1 0 /`/`1 0 %` | Divide by zero is undefined                  |
| Empty stack(operator) | `+`/`1 *`/`1 %` | Binary operator needs 2 operands             |
| Empty stack(`.`)      |             `.` | `stack.top()` throws `EmptyException`        |
| Stack of one value    | `+`/`1 *`/`1 %` | Binary operator needs 2 operands             |

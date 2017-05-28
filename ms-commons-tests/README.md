# ms-ws module

All the common classes for tests should be here, it is an extra module because it needs to be included with the scope: test so that test implementations can find those classes.

## What should be in THIS module:
* General stuff which can be reused by every test.

## What should NOT be in THIS module:
* Anything which requires non standard library classes.
// Test for error in ExtendJ's conditional expression type analysis.
// The type of a conditional expression with reference type result expressions
// is the intersection type of the common supertypes of the expression types.
// .result=COMPILE_PASS

interface Ownable { }
interface Thing { }
abstract class Entity implements Thing { }
class Unit extends Entity implements Ownable { }
class Resource extends Entity implements Ownable { }

class Test {
  void foo(Resource resource, Unit unit) {
    // Thing is a commmon ancestor type of Resource and Unit.
    Thing l = (resource != null) ? resource : unit;
  }
}

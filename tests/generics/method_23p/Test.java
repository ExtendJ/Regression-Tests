// https://bitbucket.org/extendj/extendj/issues/214/type-inference-regression
// .result: COMPILE_PASS
public abstract class Test {
  <T extends Note> T get(Class<T> note) {
    Note a = get(note);
    return (T) a;
  }
}

interface Note { }

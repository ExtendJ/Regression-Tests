// Test stack overflow in ExtendJ during ParseName rewrite.
// https://bitbucket.org/extendj/extendj/issues/203/stack-overflow-caused-by-parsename-rewrite
// .result: COMPILE_PASS
import java.util.function.Function;

class Token {
  public final String string;
  public Token(String token) {
    this.string = token;
  }
}

public interface Test {
  <T> String transform(T in, Function<T, String> fun);

  default void prepend(String string) {
    transform(new Token(string), token -> "x" + token.string.toString());
  }
}



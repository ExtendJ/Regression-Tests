/* Copyright (c) 2013-2018, Jesper Öqvist <jesper.oqvist@cs.lth.se>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *
 *   3. Neither the name of the copyright holder nor the names of its contributors
 *      may be used to endorse or promote products derived from this software
 *      without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tests;

/**
 * This class defines the default test sets.
 *
 * Some tests are excluded when running higher versions
 * of Java because they test features that behave slightly
 * differently than in the lower version.
 *
 * @author Jesper Öqvist <jesper.oqvist@cs.lth.se>
 */
public interface Tests {
  /**
   * Tests expected to fail.
   * These expose bugs in ExtendJ that should be fixed.
   */
  String[] FAILING = {
    "type/ambiguous_01f",
    "generics/static_02f",
    "jsr335/lambda/type_inf_06p", // https://bitbucket.org/extendj/extendj/issues/217/failure-in-lambda-return-type-inference
    "jsr335/lambda/err_01f", // https://bitbucket.org/extendj/extendj/issues/202/method-lookup-error-causes
    "jsr335/lambda/err_02f", // https://bitbucket.org/extendj/extendj/issues/202/method-lookup-error-causes
    "generics/method_20p", // https://bitbucket.org/extendj/extendj/issues/213/unused-type-variable-causes-type-inference
    "generics/method_22p", // https://bitbucket.org/extendj/extendj/issues/213/unused-type-variable-causes-type-inference
    "jsr335/Semantics/ConstructorReferenceAnalysis/ClassReferences/ShouldFail/syntax03",
    "jsr335/Semantics/ConstructorReferenceAnalysis/ClassReferences/ShouldFail/syntax04",
    "jsr335/Semantics/FunctionalInterfaces/ReturnTypeSubstitutable/ShouldCompile/syntax22",
    "jsr335/Semantics/FunctionalInterfaces/Signature/ShouldFail/syntax08",
    "jsr335/Semantics/LambdaTypeAnalysis/AssignmentContext/ShouldCompile/syntax28",
    "type/autoboxing_02f", // https://bitbucket.org/extendj/extendj/issues/225/illegal-autoboxing-conversion-is
    "type/autoboxing_05f", // https://bitbucket.org/extendj/extendj/issues/225/illegal-autoboxing-conversion-is
    "pkg/static_import_03p", // https://bitbucket.org/extendj/extendj/issues/227/error-should-not-be-generated-for-unused
    "jsr335/lambda/type_inf_09p", // True Java 8 type inference is needed.
    "curious", // Curious type inference problems in Java 8. Don't even work with javac.
    "jsr335/diamond/generics_01p", // https://bitbucket.org/extendj/extendj/issues/267/diamond-constructor-inference-fails-if
    "extendj/generics/container_01f", // Needs an error message (crash during code generation).
    "generics/method_26f", // TODO: add an issue for this.
    "jsr335/misc/error_01f", // Does not give good error messages.
  };

  /**
   * Java 6 specific tests.
   *
   * <p>These are tests that use the @Override annotaiton on interface
   * declared methods. This is not allowed in Java 5.
   */
  String[] JAVA6 = {
    "classes/super_01p",  // Overriding Runnable.run().
    "generics/override_15p",  // Overriding Map.entrySet().
    "generics/override_18p",  // Overriding custom interface method.
    "pkg/static_import_02p",  // Overriding Runnable.run().
    "method/infinite_01p",  // Overriding Runnable.run().
  };

  /**
   * Tests that should be excluded for Java 8, because they test features that
   * changed since Java 7 and no longer work the same way.
   */
  String[] EXCLUDE_JAVA7 = {
    "exception/rethrow_01f", // Rethrowing Throwable gives an error.
  };

  /**
   * Java 7 tests.
   */
  String[] JAVA7 = {
    "jsr334",
    "api/jsr334",
    "pretty-print/jsr334",
    "method/overload_04p",  // Uses @SafeVarargs annotation introduced in Java 7.
    "api/modifiers_02p",  // ACC_SYNTHETIC added for fields.
  };

  /**
   * Java 8 tests.
   */
  String[] JAVA8 = {
    "jsr335",
    "extendj/jsr335",
    "api/jsr335",
    "jsr334/diamond/diamond_24p", // https://bitbucket.org/extendj/extendj/issues/173/
  };

  /**
   * Tests that should be excluded for Java 8, because they test features that
   * changed since Java 7 and no longer work the same way.
   */
  String[] EXCLUDE_JAVA8 = {
    "jsr334/diamond/diamond_18f",
    "generics/inference_07f", // More powerful type inference in Java 8.
    "generics/method_17p",
    "generics/method_25f", // Changed error message.
  };

  /**
   * Tests that test ExtendJ-specific behaviour (error messages, pretty printing, api).
   */
  String[] EXTENDJ_ONLY = {
    "extendj",
    "api",
    "pretty-print",
    "jsr335/Parsing", // ExtendJ-specific tests.
  };
}

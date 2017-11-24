/* Copyright (c) 2013-2017, Jesper Öqvist <jesper.oqvist@cs.lth.se>
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
    "jsr335/Semantics/FunctionalInterfaces/ReturnTypeSubstitutable/ShouldFail/syntax11",
    "jsr335/Semantics/FunctionalInterfaces/ReturnTypeSubstitutable/ShouldFail/syntax12",
    "jsr335/Semantics/FunctionalInterfaces/Signature/ShouldCompile/syntax08",
    "jsr335/Semantics/FunctionalInterfaces/Signature/ShouldCompile/syntax12",
    "jsr335/Semantics/FunctionalInterfaces/Signature/ShouldFail/syntax08",
    "jsr335/Semantics/FunctionalInterfaces/TypeParameters/ShouldFail/syntax02",
    "jsr335/Semantics/FunctionalInterfaces/TypeParameters/ShouldFail/syntax03",
    "jsr335/Semantics/LambdaTypeAnalysis/AssignmentContext/ShouldCompile/syntax28",
    "jsr335/Semantics/MethodReferenceAnalysis/TypeMethodReference/ShouldCompile/syntax10",
    "jsr335/Semantics/MethodReferenceAnalysis/TypeMethodReference/ShouldCompile/syntax11",
    "jsr335/Semantics/MethodReferenceAnalysis/TypeMethodReference/ShouldCompile/syntax12",
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
   * Java 7 tests.
   */
  String[] JAVA7 = {
    "jsr334",
    "pretty-print/jsr334",
    "method/overload_04p",  // Uses @SafeVarargs annotation introduced in Java 7.
  };

  /**
   * Java 8 tests.
   */
  String[] JAVA8 = {
    "jsr335",
    "extendj/jsr335",
    "jsr334/diamond/diamond_24p", // https://bitbucket.org/extendj/extendj/issues/173/
  };

  /**
   * Tests that should be excluded for Java 8, because they test features that
   * changed since Java 7 and no longer work the same way.
   */
  String[] EXCLUDE_JAVA8 = {
    "pkg/import_06f", // Multiple static imports of the same type name.
    "jsr334/diamond/diamond_18",
    "generics/inference_07f", // More powerful type inference in Java 8.
    "generics/method_17p",
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

// Test a circular import-on-demand dependency.
// The implements clause in pkg/C.java causes a type lookup loading the on-demand
// import pkg.D.*, this in turn leads to loading the member types of pkg.D, which
// requires loading the on-demand imported types for the import declaration
// pkg.C.*, which must evaluate the member types of pkg.C and leads back to
// the original type on-demand import lookup.
// .result=COMPILE_PASS
import pkg.C;

public class Test implements C.IC {
}

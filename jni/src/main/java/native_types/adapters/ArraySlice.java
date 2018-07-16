package native_types.adapters;


import org.bytedeco.javacpp.annotation.*;
import java.lang.annotation.*;

@Documented @Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Cast({"tensorflow::gtl::ArraySlice", "&"}) @Adapter("ArraySliceAdapter")
public @interface ArraySlice { String value() default ""; }

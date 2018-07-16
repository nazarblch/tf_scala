package native_types.adapters;

import org.bytedeco.javacpp.annotation.Adapter;
import org.bytedeco.javacpp.annotation.Cast;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Cast("tensorflow::StringPiece&") @Adapter("StringPieceAdapter")
public @interface StringPiece { }
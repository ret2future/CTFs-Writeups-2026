package defpackage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.RUNTIME)
public @interface go {
    String[] alternate() default {};

    String value();
}

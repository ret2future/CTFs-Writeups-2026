package defpackage;

import java.lang.reflect.Type;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class wq {
    public final Class a;
    public final Type b;
    public final int c;

    public wq(Type type) {
        Objects.requireNonNull(type);
        Type typeE = d.e(type);
        this.b = typeE;
        this.a = d.A(typeE);
        this.c = typeE.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof wq) {
            return d.r(this.b, ((wq) obj).b);
        }
        return false;
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return d.d0(this.b);
    }
}

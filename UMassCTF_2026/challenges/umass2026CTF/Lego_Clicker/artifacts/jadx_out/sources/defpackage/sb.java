package defpackage;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class sb implements qq, Cloneable {
    public static final sb c;
    public List a;
    public List b;

    static {
        sb sbVar = new sb();
        List list = Collections.EMPTY_LIST;
        sbVar.a = list;
        sbVar.b = list;
        c = sbVar;
    }

    public static boolean c(Class cls) {
        if (Enum.class.isAssignableFrom(cls) || (cls.getModifiers() & 8) != 0) {
            return false;
        }
        return cls.isAnonymousClass() || cls.isLocalClass();
    }

    @Override // defpackage.qq
    public final pq a(re reVar, wq wqVar) {
        boolean z;
        boolean z2;
        boolean zC = c(wqVar.a);
        if (zC) {
            z = true;
        } else {
            b(true);
            z = false;
        }
        if (zC) {
            z2 = true;
        } else {
            b(false);
            z2 = false;
        }
        if (z || z2) {
            return new rb(this, z2, z, reVar, wqVar);
        }
        return null;
    }

    public final void b(boolean z) {
        Iterator it = (z ? this.a : this.b).iterator();
        if (it.hasNext()) {
            it.next().getClass();
            z6.a();
        }
    }

    public final Object clone() {
        try {
            return (sb) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

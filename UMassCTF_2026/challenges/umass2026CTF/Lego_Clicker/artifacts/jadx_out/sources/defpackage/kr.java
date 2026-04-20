package defpackage;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class kr extends mr {
    public final /* synthetic */ Method b;

    public kr(Method method) {
        this.b = method;
    }

    @Override // defpackage.mr
    public final Object a(Class cls) {
        String strC = fr.c(cls);
        if (strC != null) {
            throw new AssertionError("UnsafeAllocator is used for non-instantiable type: ".concat(strC));
        }
        return this.b.invoke(null, cls, Object.class);
    }
}

package androidx.lifecycle;

import defpackage.d7;
import defpackage.dg;
import defpackage.f7;
import defpackage.ig;
import defpackage.jg;
import defpackage.kg;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
class ReflectiveGenericLifecycleObserver implements ig {
    public final jg a;
    public final d7 b;

    public ReflectiveGenericLifecycleObserver(jg jgVar) {
        this.a = jgVar;
        f7 f7Var = f7.c;
        Class<?> cls = jgVar.getClass();
        d7 d7Var = (d7) f7Var.a.get(cls);
        this.b = d7Var == null ? f7Var.a(cls, null) : d7Var;
    }

    @Override // defpackage.ig
    public final void d(kg kgVar, dg dgVar) {
        HashMap map = this.b.a;
        List list = (List) map.get(dgVar);
        jg jgVar = this.a;
        d7.a(list, kgVar, dgVar, jgVar);
        d7.a((List) map.get(dg.ON_ANY), kgVar, dgVar, jgVar);
    }
}

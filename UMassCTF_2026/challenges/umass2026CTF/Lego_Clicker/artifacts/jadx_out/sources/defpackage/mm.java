package defpackage;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class mm {
    public final String a;
    public final Field b;
    public final String c;
    public final boolean d;
    public final boolean e;
    public final /* synthetic */ Method f;
    public final /* synthetic */ boolean g;
    public final /* synthetic */ pq h;
    public final /* synthetic */ re i;
    public final /* synthetic */ wq j;
    public final /* synthetic */ boolean k;
    public final /* synthetic */ boolean l;

    public mm(String str, Field field, boolean z, boolean z2, Method method, boolean z3, pq pqVar, re reVar, wq wqVar, boolean z4, boolean z5) {
        this.f = method;
        this.g = z3;
        this.h = pqVar;
        this.i = reVar;
        this.j = wqVar;
        this.k = z4;
        this.l = z5;
        this.a = str;
        this.b = field;
        this.c = field.getName();
        this.d = z;
        this.e = z2;
    }

    public final void a(tf tfVar, Object obj) throws IllegalAccessException {
        Object objInvoke;
        if (this.d) {
            Method method = this.f;
            if (method != null) {
                try {
                    objInvoke = method.invoke(obj, null);
                } catch (InvocationTargetException e) {
                    throw new of("Accessor " + lm.d(method, false) + " threw exception", e.getCause());
                }
            } else {
                objInvoke = this.b.get(obj);
            }
            if (objInvoke == obj) {
                return;
            }
            tfVar.g(this.a);
            boolean z = this.g;
            pq biVar = this.h;
            if (!z) {
                biVar = new bi(this.i, biVar, this.j.b);
            }
            biVar.b(tfVar, objInvoke);
        }
    }
}

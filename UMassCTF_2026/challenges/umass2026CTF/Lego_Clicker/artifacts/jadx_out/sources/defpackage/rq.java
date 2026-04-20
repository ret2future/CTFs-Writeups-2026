package defpackage;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class rq implements qq {
    public final /* synthetic */ int a;
    public final Object b;
    public final Object c;

    public rq(fr frVar, sb sbVar, h7 h7Var) {
        this.a = 2;
        List list = Collections.EMPTY_LIST;
        this.b = frVar;
        this.c = sbVar;
    }

    @Override // defpackage.qq
    public final pq a(re reVar, wq wqVar) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                if (wqVar.a == ((Class) obj)) {
                    return (pq) this.c;
                }
                return null;
            case 1:
                Class<?> cls = wqVar.a;
                if (((Class) obj).isAssignableFrom(cls)) {
                    return new y5(this, cls);
                }
                return null;
            default:
                Class cls2 = wqVar.a;
                if (!Object.class.isAssignableFrom(cls2)) {
                    return null;
                }
                List list = Collections.EMPTY_LIST;
                hb.B();
                return lm.a.L(cls2) ? new pm(cls2, b(reVar, wqVar, cls2, true)) : new om(((fr) obj).e(wqVar), b(reVar, wqVar, cls2, false));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0197 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0187 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.LinkedHashMap b(defpackage.re r27, defpackage.wq r28, java.lang.Class r29, boolean r30) {
        /*
            Method dump skipped, instruction units count: 494
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rq.b(re, wq, java.lang.Class, boolean):java.util.LinkedHashMap");
    }

    public boolean c(Field field, boolean z) {
        sb sbVar = (sb) this.c;
        Class<?> type = field.getType();
        sbVar.getClass();
        if (!sb.c(type)) {
            sbVar.b(z);
            if ((136 & field.getModifiers()) == 0 && !field.isSynthetic() && !sb.c(field.getType())) {
                List list = z ? sbVar.a : sbVar.b;
                if (list.isEmpty()) {
                    return true;
                }
                Iterator it = list.iterator();
                if (!it.hasNext()) {
                    return true;
                }
                it.next().getClass();
                z6.a();
                return false;
            }
        }
        return false;
    }

    public String toString() {
        int i = this.a;
        Object obj = this.c;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                return "Factory[type=" + ((Class) obj2).getName() + ",adapter=" + ((pq) obj) + "]";
            case 1:
                return "Factory[typeHierarchy=" + ((Class) obj2).getName() + ",adapter=" + ((pq) obj) + "]";
            default:
                return super.toString();
        }
    }

    public /* synthetic */ rq(Class cls, pq pqVar, int i) {
        this.a = i;
        this.b = cls;
        this.c = pqVar;
    }
}

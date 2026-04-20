package defpackage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class bi extends pq {
    public final /* synthetic */ int a;
    public final Object b;
    public final Object c;
    public final Object d;

    public bi(Class cls) {
        this.a = 2;
        this.b = new HashMap();
        this.c = new HashMap();
        this.d = new HashMap();
        try {
            for (Field field : (Field[]) AccessController.doPrivileged(new uq(cls))) {
                Enum r4 = (Enum) field.get(null);
                String strName = r4.name();
                String string = r4.toString();
                go goVar = (go) field.getAnnotation(go.class);
                if (goVar != null) {
                    strName = goVar.value();
                    for (String str : goVar.alternate()) {
                        ((HashMap) this.b).put(str, r4);
                    }
                }
                ((HashMap) this.b).put(strName, r4);
                ((HashMap) this.c).put(string, r4);
                ((HashMap) this.d).put(r4, strName);
            }
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    @Override // defpackage.pq
    public final Object a(sf sfVar) throws IOException {
        int i = this.a;
        Object obj = this.b;
        Object obj2 = this.c;
        switch (i) {
            case 0:
                pq pqVar = (pq) ((bi) obj2).c;
                pq pqVar2 = (pq) ((bi) obj).c;
                int iV = sfVar.v();
                if (iV == 9) {
                    sfVar.r();
                    return null;
                }
                Map map = (Map) ((wj) this.d).q();
                if (iV == 1) {
                    sfVar.a();
                    while (sfVar.i()) {
                        sfVar.a();
                        Object objA = pqVar2.a(sfVar);
                        if (map.put(objA, pqVar.a(sfVar)) != null) {
                            throw new of("duplicate key: " + objA);
                        }
                        sfVar.e();
                    }
                    sfVar.e();
                } else {
                    sfVar.b();
                    while (sfVar.i()) {
                        fr.f.getClass();
                        int iD = sfVar.h;
                        if (iD == 0) {
                            iD = sfVar.d();
                        }
                        if (iD == 13) {
                            sfVar.h = 9;
                        } else if (iD == 12) {
                            sfVar.h = 8;
                        } else {
                            if (iD != 14) {
                                z6.m(lo.d(sfVar.v()), sfVar.k(), "Expected a name but was ");
                                return null;
                            }
                            sfVar.h = 10;
                        }
                        Object objA2 = pqVar2.a(sfVar);
                        if (map.put(objA2, pqVar.a(sfVar)) != null) {
                            throw new of("duplicate key: " + objA2);
                        }
                    }
                    sfVar.f();
                }
                return map;
            case 1:
                return ((pq) obj2).a(sfVar);
            default:
                if (sfVar.v() == 9) {
                    sfVar.r();
                    return null;
                }
                String strT = sfVar.t();
                Enum r4 = (Enum) ((HashMap) obj).get(strT);
                return r4 == null ? (Enum) ((HashMap) obj2).get(strT) : r4;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x005f  */
    @Override // defpackage.pq
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(defpackage.tf r4, java.lang.Object r5) throws java.io.IOException {
        /*
            r3 = this;
            int r0 = r3.a
            java.lang.Object r1 = r3.c
            java.lang.Object r2 = r3.d
            switch(r0) {
                case 0: goto L64;
                case 1: goto L1b;
                default: goto L9;
            }
        L9:
            java.lang.Enum r5 = (java.lang.Enum) r5
            if (r5 != 0) goto Lf
            r3 = 0
            goto L17
        Lf:
            java.util.HashMap r2 = (java.util.HashMap) r2
            java.lang.Object r3 = r2.get(r5)
            java.lang.String r3 = (java.lang.String) r3
        L17:
            r4.o(r3)
            return
        L1b:
            pq r1 = (defpackage.pq) r1
            java.lang.reflect.Type r2 = (java.lang.reflect.Type) r2
            if (r5 == 0) goto L2e
            boolean r0 = r2 instanceof java.lang.Class
            if (r0 != 0) goto L29
            boolean r0 = r2 instanceof java.lang.reflect.TypeVariable
            if (r0 == 0) goto L2e
        L29:
            java.lang.Class r0 = r5.getClass()
            goto L2f
        L2e:
            r0 = r2
        L2f:
            if (r0 == r2) goto L60
            java.lang.Object r3 = r3.b
            re r3 = (defpackage.re) r3
            wq r2 = new wq
            r2.<init>(r0)
            pq r3 = r3.c(r2)
            boolean r0 = r3 instanceof defpackage.nm
            if (r0 != 0) goto L43
            goto L5f
        L43:
            r0 = r1
        L44:
            boolean r2 = r0 instanceof defpackage.qe
            if (r2 == 0) goto L5a
            r2 = r0
            qe r2 = (defpackage.qe) r2
            pq r2 = r2.a
            if (r2 == 0) goto L54
            if (r2 != r0) goto L52
            goto L5a
        L52:
            r0 = r2
            goto L44
        L54:
            java.lang.String r3 = "Adapter for type with cyclic dependency has been used before dependency has been resolved"
            defpackage.z6.o(r3)
            goto L63
        L5a:
            boolean r0 = r0 instanceof defpackage.nm
            if (r0 != 0) goto L5f
            goto L60
        L5f:
            r1 = r3
        L60:
            r1.b(r4, r5)
        L63:
            return
        L64:
            java.util.Map r5 = (java.util.Map) r5
            bi r1 = (defpackage.bi) r1
            if (r5 != 0) goto L6e
            r4.i()
            goto L9b
        L6e:
            r4.c()
            java.util.Set r3 = r5.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L79:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L98
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r0 = r5.getKey()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r4.g(r0)
            java.lang.Object r5 = r5.getValue()
            r1.b(r4, r5)
            goto L79
        L98:
            r4.f()
        L9b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bi.b(tf, java.lang.Object):void");
    }

    public bi(re reVar, pq pqVar, Type type) {
        this.a = 1;
        this.b = reVar;
        this.c = pqVar;
        this.d = type;
    }

    public bi(h7 h7Var, re reVar, Type type, pq pqVar, Type type2, pq pqVar2, wj wjVar) {
        this.a = 0;
        this.b = new bi(reVar, pqVar, type);
        this.c = new bi(reVar, pqVar2, type2);
        this.d = wjVar;
    }
}

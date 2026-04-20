package defpackage;

import java.util.LinkedHashMap;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class sh {
    public final LinkedHashMap a;
    public int b;
    public final int c;
    public int d;
    public int e;

    public sh(int i) {
        if (i <= 0) {
            z6.f("maxSize <= 0");
            throw null;
        }
        this.c = i;
        this.a = new LinkedHashMap(0, 0.75f, true);
    }

    public final Object a(Object obj) {
        synchronized (this) {
            try {
                Object obj2 = this.a.get(obj);
                if (obj2 != null) {
                    this.d++;
                    return obj2;
                }
                this.e++;
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0080, code lost:
    
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object b(java.lang.Object r3, java.lang.Object r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            int r0 = r2.b     // Catch: java.lang.Throwable -> L16
            int r0 = r0 + 1
            r2.b = r0     // Catch: java.lang.Throwable -> L16
            java.util.LinkedHashMap r0 = r2.a     // Catch: java.lang.Throwable -> L16
            java.lang.Object r3 = r0.put(r3, r4)     // Catch: java.lang.Throwable -> L16
            if (r3 == 0) goto L18
            int r4 = r2.b     // Catch: java.lang.Throwable -> L16
            int r4 = r4 + (-1)
            r2.b = r4     // Catch: java.lang.Throwable -> L16
            goto L18
        L16:
            r3 = move-exception
            goto L83
        L18:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L16
            int r4 = r2.c
        L1b:
            monitor-enter(r2)
            int r0 = r2.b     // Catch: java.lang.Throwable -> L2d
            if (r0 < 0) goto L62
            java.util.LinkedHashMap r0 = r2.a     // Catch: java.lang.Throwable -> L2d
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L2d
            if (r0 == 0) goto L2f
            int r0 = r2.b     // Catch: java.lang.Throwable -> L2d
            if (r0 != 0) goto L62
            goto L2f
        L2d:
            r3 = move-exception
            goto L81
        L2f:
            int r0 = r2.b     // Catch: java.lang.Throwable -> L2d
            if (r0 <= r4) goto L60
            java.util.LinkedHashMap r0 = r2.a     // Catch: java.lang.Throwable -> L2d
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L2d
            if (r0 == 0) goto L3c
            goto L60
        L3c:
            java.util.LinkedHashMap r0 = r2.a     // Catch: java.lang.Throwable -> L2d
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L2d
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L2d
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L2d
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: java.lang.Throwable -> L2d
            java.lang.Object r1 = r0.getKey()     // Catch: java.lang.Throwable -> L2d
            r0.getValue()     // Catch: java.lang.Throwable -> L2d
            java.util.LinkedHashMap r0 = r2.a     // Catch: java.lang.Throwable -> L2d
            r0.remove(r1)     // Catch: java.lang.Throwable -> L2d
            int r0 = r2.b     // Catch: java.lang.Throwable -> L2d
            int r0 = r0 + (-1)
            r2.b = r0     // Catch: java.lang.Throwable -> L2d
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2d
            goto L1b
        L60:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2d
            return r3
        L62:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2d
            r4.<init>()     // Catch: java.lang.Throwable -> L2d
            java.lang.Class r0 = r2.getClass()     // Catch: java.lang.Throwable -> L2d
            java.lang.String r0 = r0.getName()     // Catch: java.lang.Throwable -> L2d
            r4.append(r0)     // Catch: java.lang.Throwable -> L2d
            java.lang.String r0 = ".sizeOf() is reporting inconsistent results!"
            r4.append(r0)     // Catch: java.lang.Throwable -> L2d
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L2d
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L2d
            throw r3     // Catch: java.lang.Throwable -> L2d
        L81:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2d
            throw r3
        L83:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L16
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sh.b(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final synchronized String toString() {
        int i;
        int i2;
        int i3;
        try {
            i = this.d;
            i2 = this.e;
            int i4 = i + i2;
            i3 = i4 != 0 ? (i * 100) / i4 : 0;
            Locale locale = Locale.US;
        } catch (Throwable th) {
            throw th;
        }
        return "LruCache[maxSize=" + this.c + ",hits=" + i + ",misses=" + i2 + ",hitRate=" + i3 + "%]";
    }
}

package defpackage;

import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ke implements Comparator {
    public final /* synthetic */ int a;

    public /* synthetic */ ke(int i) {
        this.a = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x002e, code lost:
    
        if (r4 == null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0037, code lost:
    
        if (r4 != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:?, code lost:
    
        return 1;
     */
    @Override // java.util.Comparator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int compare(java.lang.Object r5, java.lang.Object r6) {
        /*
            r4 = this;
            int r4 = r4.a
            switch(r4) {
                case 0: goto L18;
                case 1: goto Lf;
                default: goto L5;
            }
        L5:
            mo r5 = (defpackage.mo) r5
            mo r6 = (defpackage.mo) r6
            int r4 = r5.b
            int r5 = r6.b
            int r4 = r4 - r5
            return r4
        Lf:
            java.lang.Comparable r5 = (java.lang.Comparable) r5
            java.lang.Comparable r6 = (java.lang.Comparable) r6
            int r4 = r5.compareTo(r6)
            return r4
        L18:
            me r5 = (defpackage.me) r5
            me r6 = (defpackage.me) r6
            androidx.recyclerview.widget.RecyclerView r4 = r5.d
            r0 = 0
            r1 = 1
            if (r4 != 0) goto L24
            r2 = r1
            goto L25
        L24:
            r2 = r0
        L25:
            androidx.recyclerview.widget.RecyclerView r3 = r6.d
            if (r3 != 0) goto L2b
            r3 = r1
            goto L2c
        L2b:
            r3 = r0
        L2c:
            if (r2 == r3) goto L31
            if (r4 != 0) goto L39
            goto L3b
        L31:
            boolean r4 = r5.a
            boolean r2 = r6.a
            if (r4 == r2) goto L3d
            if (r4 == 0) goto L3b
        L39:
            r0 = -1
            goto L4e
        L3b:
            r0 = r1
            goto L4e
        L3d:
            int r4 = r6.b
            int r1 = r5.b
            int r4 = r4 - r1
            if (r4 == 0) goto L46
        L44:
            r0 = r4
            goto L4e
        L46:
            int r4 = r5.c
            int r5 = r6.c
            int r4 = r4 - r5
            if (r4 == 0) goto L4e
            goto L44
        L4e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ke.compare(java.lang.Object, java.lang.Object):int");
    }
}

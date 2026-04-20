package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class nl {
    public il a;
    public ArrayList b;
    public long c;
    public long d;
    public long e;
    public long f;

    public static void b(em emVar) {
        RecyclerView recyclerView;
        int i = emVar.i;
        if (emVar.e() || (i & 4) != 0 || (recyclerView = emVar.q) == null) {
            return;
        }
        recyclerView.F(emVar);
    }

    public abstract boolean a(em emVar, em emVar2, tj tjVar, tj tjVar2);

    /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(defpackage.em r10) {
        /*
            r9 = this;
            il r9 = r9.a
            if (r9 == 0) goto Laa
            androidx.recyclerview.widget.RecyclerView r9 = r9.a
            r0 = 1
            r10.m(r0)
            android.view.View r1 = r10.a
            em r2 = r10.g
            r3 = 0
            if (r2 == 0) goto L17
            em r2 = r10.h
            if (r2 != 0) goto L17
            r10.g = r3
        L17:
            r10.h = r3
            int r2 = r10.i
            r2 = r2 & 16
            if (r2 == 0) goto L21
            goto Laa
        L21:
            wl r2 = r9.c
            r9.b0()
            b7 r3 = r9.f
            a7 r4 = r3.b
            il r5 = r3.a
            int r6 = r3.d
            r7 = 0
            if (r6 != r0) goto L3d
            android.view.View r0 = r3.e
            if (r0 != r1) goto L37
        L35:
            r0 = r7
            goto L66
        L37:
            java.lang.String r9 = "Cannot call removeViewIfHidden within removeView(At) for a different view"
            defpackage.z6.o(r9)
            return
        L3d:
            r8 = 2
            if (r6 == r8) goto La5
            r3.d = r8     // Catch: java.lang.Throwable -> L51
            androidx.recyclerview.widget.RecyclerView r6 = r5.a     // Catch: java.lang.Throwable -> L51
            int r6 = r6.indexOfChild(r1)     // Catch: java.lang.Throwable -> L51
            r8 = -1
            if (r6 != r8) goto L53
            r3.j(r1)     // Catch: java.lang.Throwable -> L51
        L4e:
            r3.d = r7
            goto L66
        L51:
            r9 = move-exception
            goto La2
        L53:
            boolean r8 = r4.d(r6)     // Catch: java.lang.Throwable -> L51
            if (r8 == 0) goto L63
            r4.f(r6)     // Catch: java.lang.Throwable -> L51
            r3.j(r1)     // Catch: java.lang.Throwable -> L51
            r5.h(r6)     // Catch: java.lang.Throwable -> L51
            goto L4e
        L63:
            r3.d = r7
            goto L35
        L66:
            if (r0 == 0) goto L91
            em r3 = androidx.recyclerview.widget.RecyclerView.H(r1)
            r2.l(r3)
            r2.i(r3)
            boolean r2 = androidx.recyclerview.widget.RecyclerView.x0
            if (r2 == 0) goto L91
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "after removing animated view: "
            r2.<init>(r3)
            r2.append(r1)
            java.lang.String r3 = ", "
            r2.append(r3)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "RecyclerView"
            android.util.Log.d(r3, r2)
        L91:
            r2 = r0 ^ 1
            r9.c0(r2)
            if (r0 != 0) goto Laa
            boolean r10 = r10.i()
            if (r10 == 0) goto Laa
            r9.removeDetachedView(r1, r7)
            return
        La2:
            r3.d = r7
            throw r9
        La5:
            java.lang.String r9 = "Cannot call removeViewIfHidden within removeViewIfHidden"
            defpackage.z6.o(r9)
        Laa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nl.c(em):void");
    }

    public abstract void d(em emVar);

    public abstract void e();

    public abstract boolean f();
}

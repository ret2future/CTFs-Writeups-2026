package defpackage;

import android.os.Trace;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class ne implements Runnable {
    public static final ThreadLocal e = new ThreadLocal();
    public static final ke f = new ke(0);
    public ArrayList a;
    public long b;
    public long c;
    public ArrayList d;

    public static em c(RecyclerView recyclerView, int i, long j) {
        int iH = recyclerView.f.h();
        for (int i2 = 0; i2 < iH; i2++) {
            em emVarH = RecyclerView.H(recyclerView.f.g(i2));
            if (emVarH.c == i && !emVarH.e()) {
                return null;
            }
        }
        wl wlVar = recyclerView.c;
        try {
            recyclerView.N();
            em emVarK = wlVar.k(i, j);
            if (emVarK != null) {
                if (!emVarK.d() || emVarK.e()) {
                    wlVar.a(emVarK, false);
                } else {
                    wlVar.h(emVarK.a);
                }
            }
            recyclerView.O(false);
            return emVarK;
        } catch (Throwable th) {
            recyclerView.O(false);
            throw th;
        }
    }

    public final void a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.r) {
            if (RecyclerView.w0 && !this.a.contains(recyclerView)) {
                z6.o("attempting to post unregistered view!");
                return;
            } else if (this.b == 0) {
                this.b = recyclerView.getNanoTime();
                recyclerView.post(this);
            }
        }
        le leVar = recyclerView.c0;
        leVar.a = i;
        leVar.b = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(long r15) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ne.b(long):void");
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList = this.a;
        try {
            int i = lq.a;
            Trace.beginSection("RV Prefetch");
            if (!arrayList.isEmpty()) {
                int size = arrayList.size();
                long jMax = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    RecyclerView recyclerView = (RecyclerView) arrayList.get(i2);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    b(TimeUnit.MILLISECONDS.toNanos(jMax) + this.c);
                }
            }
            this.b = 0L;
            Trace.endSection();
        } catch (Throwable th) {
            this.b = 0L;
            int i3 = lq.a;
            Trace.endSection();
            throw th;
        }
    }
}

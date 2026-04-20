package defpackage;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class wl {
    public final ArrayList a;
    public ArrayList b;
    public final ArrayList c;
    public final List d;
    public int e;
    public int f;
    public vl g;
    public final /* synthetic */ RecyclerView h;

    public wl(RecyclerView recyclerView) {
        this.h = recyclerView;
        ArrayList arrayList = new ArrayList();
        this.a = arrayList;
        this.b = null;
        this.c = new ArrayList();
        this.d = Collections.unmodifiableList(arrayList);
        this.e = 2;
        this.f = 2;
    }

    public final void a(em emVar, boolean z) {
        RecyclerView.i(emVar);
        View view = emVar.a;
        RecyclerView recyclerView = this.h;
        gm gmVar = recyclerView.k0;
        if (gmVar != null) {
            fm fmVar = gmVar.e;
            os.h(view, fmVar != null ? (r) fmVar.e.remove(view) : null);
        }
        if (z) {
            ArrayList arrayList = recyclerView.n;
            if (arrayList.size() > 0) {
                arrayList.get(0).getClass();
                z6.a();
                return;
            }
            if (recyclerView.d0 != null) {
                recyclerView.g.w(emVar);
            }
            if (RecyclerView.x0) {
                Log.d("RecyclerView", "dispatchViewRecycled: " + emVar);
            }
        }
        emVar.r = null;
        emVar.q = null;
        vl vlVarC = c();
        vlVarC.getClass();
        int i = emVar.e;
        ArrayList arrayList2 = vlVarC.a(i).a;
        if (((ul) vlVarC.a.get(i)).b <= arrayList2.size()) {
            hb.h(view);
        } else if (RecyclerView.w0 && arrayList2.contains(emVar)) {
            z6.f("this scrap item already exists");
        } else {
            emVar.l();
            arrayList2.add(emVar);
        }
    }

    public final int b(int i) {
        RecyclerView recyclerView = this.h;
        am amVar = recyclerView.d0;
        if (i >= 0 && i < amVar.b()) {
            return !amVar.f ? i : recyclerView.e.e(i, 0);
        }
        throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + amVar.b() + recyclerView.y());
    }

    public final vl c() {
        if (this.g == null) {
            vl vlVar = new vl();
            vlVar.a = new SparseArray();
            vlVar.b = 0;
            vlVar.c = Collections.newSetFromMap(new IdentityHashMap());
            this.g = vlVar;
            d();
        }
        return this.g;
    }

    public final void d() {
        RecyclerView recyclerView;
        jl jlVar;
        vl vlVar = this.g;
        if (vlVar == null || (jlVar = (recyclerView = this.h).l) == null || !recyclerView.r) {
            return;
        }
        vlVar.c.add(jlVar);
    }

    public final void e(jl jlVar, boolean z) {
        vl vlVar = this.g;
        if (vlVar != null) {
            SparseArray sparseArray = vlVar.a;
            Set set = vlVar.c;
            set.remove(jlVar);
            if (set.size() != 0 || z) {
                return;
            }
            for (int i = 0; i < sparseArray.size(); i++) {
                ArrayList arrayList = ((ul) sparseArray.get(sparseArray.keyAt(i))).a;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    hb.h(((em) arrayList.get(i2)).a);
                }
            }
        }
    }

    public final void f() {
        ArrayList arrayList = this.c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            g(size);
        }
        arrayList.clear();
        if (RecyclerView.B0) {
            le leVar = this.h.c0;
            int[] iArr = leVar.c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            leVar.d = 0;
        }
    }

    public final void g(int i) {
        if (RecyclerView.x0) {
            Log.d("RecyclerView", "Recycling cached view at index " + i);
        }
        ArrayList arrayList = this.c;
        em emVar = (em) arrayList.get(i);
        if (RecyclerView.x0) {
            Log.d("RecyclerView", "CachedViewHolder to be recycled: " + emVar);
        }
        a(emVar, true);
        arrayList.remove(i);
    }

    public final void h(View view) {
        em emVarH = RecyclerView.H(view);
        boolean zI = emVarH.i();
        RecyclerView recyclerView = this.h;
        if (zI) {
            recyclerView.removeDetachedView(view, false);
        }
        if (emVarH.h()) {
            emVarH.m.l(emVarH);
        } else if (emVarH.o()) {
            emVarH.i &= -33;
        }
        i(emVarH);
        if (recyclerView.J == null || emVarH.f()) {
            return;
        }
        recyclerView.J.d(emVarH);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x00ac, code lost:
    
        r6 = r6 - 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void i(defpackage.em r13) {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wl.i(em):void");
    }

    public final void j(View view) {
        nl nlVar;
        em emVarH = RecyclerView.H(view);
        int i = emVarH.i & 12;
        RecyclerView recyclerView = this.h;
        if (i == 0 && emVarH.j() && (nlVar = recyclerView.J) != null) {
            p9 p9Var = (p9) nlVar;
            if (emVarH.c().isEmpty() && p9Var.g && !emVarH.e()) {
                if (this.b == null) {
                    this.b = new ArrayList();
                }
                emVarH.m = this;
                emVarH.n = true;
                this.b.add(emVarH);
                return;
            }
        }
        if (emVarH.e() && !emVarH.g()) {
            recyclerView.l.getClass();
            z6.f("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.".concat(recyclerView.y()));
        } else {
            emVarH.m = this;
            emVarH.n = false;
            this.a.add(emVarH);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0392  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x03f2  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0538  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0542  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0558 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:318:0x055c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final defpackage.em k(int r25, long r26) {
        /*
            Method dump skipped, instruction units count: 1423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wl.k(int, long):em");
    }

    public final void l(em emVar) {
        if (emVar.n) {
            this.b.remove(emVar);
        } else {
            this.a.remove(emVar);
        }
        emVar.m = null;
        emVar.n = false;
        emVar.i &= -33;
    }

    public final void m() {
        ql qlVar = this.h.m;
        this.f = this.e + (qlVar != null ? qlVar.i : 0);
        ArrayList arrayList = this.c;
        for (int size = arrayList.size() - 1; size >= 0 && arrayList.size() > this.f; size--) {
            g(size);
        }
    }
}

package defpackage;

import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class il {
    public final /* synthetic */ RecyclerView a;

    public /* synthetic */ il(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public void a(z1 z1Var) {
        int i = z1Var.a;
        RecyclerView recyclerView = this.a;
        if (i == 1) {
            recyclerView.m.S(z1Var.b, z1Var.c);
            return;
        }
        if (i == 2) {
            recyclerView.m.V(z1Var.b, z1Var.c);
        } else if (i == 4) {
            recyclerView.m.W(z1Var.b, z1Var.c);
        } else {
            if (i != 8) {
                return;
            }
            recyclerView.m.U(z1Var.b, z1Var.c);
        }
    }

    public em b(int i) {
        RecyclerView recyclerView = this.a;
        int iH = recyclerView.f.h();
        int i2 = 0;
        em emVar = null;
        while (true) {
            if (i2 >= iH) {
                break;
            }
            em emVarH = RecyclerView.H(recyclerView.f.g(i2));
            if (emVarH != null && !emVarH.g() && emVarH.c == i) {
                if (!recyclerView.f.c.contains(emVarH.a)) {
                    emVar = emVarH;
                    break;
                }
                emVar = emVarH;
            }
            i2++;
        }
        if (emVar != null) {
            if (!recyclerView.f.c.contains(emVar.a)) {
                return emVar;
            }
            if (RecyclerView.x0) {
                Log.d("RecyclerView", "assuming view holder cannot be find because it is hidden");
            }
        }
        return null;
    }

    public void c(int i, int i2) {
        int i3;
        int i4;
        RecyclerView recyclerView = this.a;
        int iH = recyclerView.f.h();
        int i5 = i2 + i;
        for (int i6 = 0; i6 < iH; i6++) {
            View viewG = recyclerView.f.g(i6);
            em emVarH = RecyclerView.H(viewG);
            if (emVarH != null && !emVarH.n() && (i4 = emVarH.c) >= i && i4 < i5) {
                emVarH.a(2);
                emVarH.a(1024);
                ((rl) viewG.getLayoutParams()).c = true;
            }
        }
        wl wlVar = recyclerView.c;
        ArrayList arrayList = wlVar.c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            em emVar = (em) arrayList.get(size);
            if (emVar != null && (i3 = emVar.c) >= i && i3 < i5) {
                emVar.a(2);
                wlVar.g(size);
            }
        }
        recyclerView.h0 = true;
    }

    public void d(int i, int i2) {
        RecyclerView recyclerView = this.a;
        int iH = recyclerView.f.h();
        for (int i3 = 0; i3 < iH; i3++) {
            em emVarH = RecyclerView.H(recyclerView.f.g(i3));
            if (emVarH != null && !emVarH.n() && emVarH.c >= i) {
                if (RecyclerView.x0) {
                    Log.d("RecyclerView", "offsetPositionRecordsForInsert attached child " + i3 + " holder " + emVarH + " now at position " + (emVarH.c + i2));
                }
                emVarH.k(i2, false);
                recyclerView.d0.e = true;
            }
        }
        ArrayList arrayList = recyclerView.c.c;
        int size = arrayList.size();
        for (int i4 = 0; i4 < size; i4++) {
            em emVar = (em) arrayList.get(i4);
            if (emVar != null && emVar.c >= i) {
                if (RecyclerView.x0) {
                    Log.d("RecyclerView", "offsetPositionRecordsForInsert cached " + i4 + " holder " + emVar + " now at position " + (emVar.c + i2));
                }
                emVar.k(i2, false);
            }
        }
        recyclerView.requestLayout();
        recyclerView.g0 = true;
    }

    public void e(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        RecyclerView recyclerView = this.a;
        int iH = recyclerView.f.h();
        int i10 = -1;
        if (i < i2) {
            i4 = i;
            i3 = i2;
            i5 = -1;
        } else {
            i3 = i;
            i4 = i2;
            i5 = 1;
        }
        for (int i11 = 0; i11 < iH; i11++) {
            em emVarH = RecyclerView.H(recyclerView.f.g(i11));
            if (emVarH != null && (i9 = emVarH.c) >= i4 && i9 <= i3) {
                if (RecyclerView.x0) {
                    Log.d("RecyclerView", "offsetPositionRecordsForMove attached child " + i11 + " holder " + emVarH);
                }
                if (emVarH.c == i) {
                    emVarH.k(i2 - i, false);
                } else {
                    emVarH.k(i5, false);
                }
                recyclerView.d0.e = true;
            }
        }
        ArrayList arrayList = recyclerView.c.c;
        if (i < i2) {
            i7 = i;
            i6 = i2;
        } else {
            i6 = i;
            i7 = i2;
            i10 = 1;
        }
        int size = arrayList.size();
        for (int i12 = 0; i12 < size; i12++) {
            em emVar = (em) arrayList.get(i12);
            if (emVar != null && (i8 = emVar.c) >= i7 && i8 <= i6) {
                if (i8 == i) {
                    emVar.k(i2 - i, false);
                } else {
                    emVar.k(i10, false);
                }
                if (RecyclerView.x0) {
                    Log.d("RecyclerView", "offsetPositionRecordsForMove cached child " + i12 + " holder " + emVar);
                }
            }
        }
        recyclerView.requestLayout();
        recyclerView.g0 = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void f(defpackage.em r8, defpackage.tj r9, defpackage.tj r10) {
        /*
            r7 = this;
            r0 = 0
            r8.m(r0)
            androidx.recyclerview.widget.RecyclerView r7 = r7.a
            nl r0 = r7.J
            r1 = r0
            p9 r1 = (defpackage.p9) r1
            if (r9 == 0) goto L1d
            r1.getClass()
            int r3 = r9.a
            int r5 = r10.a
            if (r3 != r5) goto L1f
            int r0 = r9.b
            int r2 = r10.b
            if (r0 == r2) goto L1d
            goto L1f
        L1d:
            r2 = r8
            goto L29
        L1f:
            int r4 = r9.b
            int r6 = r10.b
            r2 = r8
            boolean r8 = r1.g(r2, r3, r4, r5, r6)
            goto L38
        L29:
            r1.l(r2)
            android.view.View r8 = r2.a
            r9 = 0
            r8.setAlpha(r9)
            java.util.ArrayList r8 = r1.i
            r8.add(r2)
            r8 = 1
        L38:
            if (r8 == 0) goto L3d
            r7.Q()
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.il.f(em, tj, tj):void");
    }

    public void g(em emVar, tj tjVar, tj tjVar2) {
        boolean zG;
        RecyclerView recyclerView = this.a;
        recyclerView.c.l(emVar);
        recyclerView.g(emVar);
        emVar.m(false);
        p9 p9Var = (p9) recyclerView.J;
        p9Var.getClass();
        int i = tjVar.a;
        int i2 = tjVar.b;
        View view = emVar.a;
        int left = tjVar2 == null ? view.getLeft() : tjVar2.a;
        int top = tjVar2 == null ? view.getTop() : tjVar2.b;
        if (emVar.g() || (i == left && i2 == top)) {
            p9Var.l(emVar);
            p9Var.h.add(emVar);
            zG = true;
        } else {
            view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
            zG = p9Var.g(emVar, i, i2, left, top);
        }
        if (zG) {
            recyclerView.Q();
        }
    }

    public void h(int i) {
        RecyclerView recyclerView = this.a;
        View childAt = recyclerView.getChildAt(i);
        if (childAt != null) {
            RecyclerView.H(childAt);
            jl jlVar = recyclerView.l;
            childAt.clearAnimation();
        }
        recyclerView.removeViewAt(i);
    }
}

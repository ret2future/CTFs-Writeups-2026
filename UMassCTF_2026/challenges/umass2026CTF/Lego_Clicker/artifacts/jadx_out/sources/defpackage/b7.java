package defpackage;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class b7 {
    public final il a;
    public View e;
    public int d = 0;
    public final a7 b = new a7();
    public final ArrayList c = new ArrayList();

    public b7(il ilVar) {
        this.a = ilVar;
    }

    public final void a(View view, int i, boolean z) {
        RecyclerView recyclerView = this.a.a;
        int childCount = i < 0 ? recyclerView.getChildCount() : f(i);
        this.b.e(childCount, z);
        if (z) {
            i(view);
        }
        recyclerView.addView(view, childCount);
        RecyclerView.H(view);
    }

    public final void b(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        RecyclerView recyclerView = this.a.a;
        int childCount = i < 0 ? recyclerView.getChildCount() : f(i);
        this.b.e(childCount, z);
        if (z) {
            i(view);
        }
        em emVarH = RecyclerView.H(view);
        if (emVarH != null) {
            if (!emVarH.i() && !emVarH.n()) {
                StringBuilder sb = new StringBuilder("Called attach on a child which is not detached: ");
                sb.append(emVarH);
                z6.k(sb, recyclerView.y());
                return;
            } else {
                if (RecyclerView.x0) {
                    Log.d("RecyclerView", "reAttach " + emVarH);
                }
                emVarH.i &= -257;
            }
        } else if (RecyclerView.w0) {
            StringBuilder sb2 = new StringBuilder("No ViewHolder found for child: ");
            sb2.append(view);
            String strY = recyclerView.y();
            sb2.append(", index: ");
            sb2.append(childCount);
            sb2.append(strY);
            throw new IllegalArgumentException(sb2.toString());
        }
        recyclerView.attachViewToParent(view, childCount, layoutParams);
    }

    public final void c(int i) {
        int iF = f(i);
        this.b.f(iF);
        RecyclerView recyclerView = this.a.a;
        View childAt = recyclerView.getChildAt(iF);
        if (childAt != null) {
            em emVarH = RecyclerView.H(childAt);
            if (emVarH != null) {
                if (emVarH.i() && !emVarH.n()) {
                    StringBuilder sb = new StringBuilder("called detach on an already detached child ");
                    sb.append(emVarH);
                    z6.k(sb, recyclerView.y());
                    return;
                } else {
                    if (RecyclerView.x0) {
                        Log.d("RecyclerView", "tmpDetach " + emVarH);
                    }
                    emVarH.a(256);
                }
            }
        } else if (RecyclerView.w0) {
            throw new IllegalArgumentException("No view at offset " + iF + recyclerView.y());
        }
        recyclerView.detachViewFromParent(iF);
    }

    public final View d(int i) {
        return this.a.a.getChildAt(f(i));
    }

    public final int e() {
        return this.a.a.getChildCount() - this.c.size();
    }

    public final int f(int i) {
        if (i < 0) {
            return -1;
        }
        int childCount = this.a.a.getChildCount();
        int i2 = i;
        while (i2 < childCount) {
            a7 a7Var = this.b;
            int iB = i - (i2 - a7Var.b(i2));
            if (iB == 0) {
                while (a7Var.d(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += iB;
        }
        return -1;
    }

    public final View g(int i) {
        return this.a.a.getChildAt(i);
    }

    public final int h() {
        return this.a.a.getChildCount();
    }

    public final void i(View view) {
        this.c.add(view);
        em emVarH = RecyclerView.H(view);
        if (emVarH != null) {
            View view2 = emVarH.a;
            RecyclerView recyclerView = this.a.a;
            int i = emVarH.p;
            if (i != -1) {
                emVarH.o = i;
            } else {
                WeakHashMap weakHashMap = os.a;
                emVarH.o = view2.getImportantForAccessibility();
            }
            if (recyclerView.K()) {
                emVarH.p = 4;
                recyclerView.q0.add(emVarH);
            } else {
                WeakHashMap weakHashMap2 = os.a;
                view2.setImportantForAccessibility(4);
            }
        }
    }

    public final void j(View view) {
        em emVarH;
        if (!this.c.remove(view) || (emVarH = RecyclerView.H(view)) == null) {
            return;
        }
        RecyclerView recyclerView = this.a.a;
        int i = emVarH.o;
        if (recyclerView.K()) {
            emVarH.p = i;
            recyclerView.q0.add(emVarH);
        } else {
            View view2 = emVarH.a;
            WeakHashMap weakHashMap = os.a;
            view2.setImportantForAccessibility(i);
        }
        emVarH.o = 0;
    }

    public final String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }
}

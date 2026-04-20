package defpackage;

import android.view.View;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class rg {
    public boolean a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public List k;
    public boolean l;

    public final void a(View view) {
        int iB;
        int size = this.k.size();
        View view2 = null;
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < size; i2++) {
            View view3 = ((em) this.k.get(i2)).a;
            rl rlVar = (rl) view3.getLayoutParams();
            if (view3 != view && !rlVar.a.g() && (iB = (rlVar.a.b() - this.d) * this.e) >= 0 && iB < i) {
                view2 = view3;
                if (iB == 0) {
                    break;
                } else {
                    i = iB;
                }
            }
        }
        if (view2 == null) {
            this.d = -1;
        } else {
            this.d = ((rl) view2.getLayoutParams()).a.b();
        }
    }

    public final View b(wl wlVar) {
        List list = this.k;
        if (list == null) {
            View view = wlVar.k(this.d, Long.MAX_VALUE).a;
            this.d += this.e;
            return view;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            View view2 = ((em) this.k.get(i)).a;
            rl rlVar = (rl) view2.getLayoutParams();
            if (!rlVar.a.g() && this.d == rlVar.a.b()) {
                a(view2);
                return view2;
            }
        }
        return null;
    }
}

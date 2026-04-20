package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class vb extends tl {
    public final /* synthetic */ yb a;

    public vb(yb ybVar) {
        this.a = ybVar;
    }

    @Override // defpackage.tl
    public final void a(RecyclerView recyclerView) {
        int iComputeHorizontalScrollOffset = recyclerView.computeHorizontalScrollOffset();
        int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        yb ybVar = this.a;
        int i = ybVar.a;
        int iComputeVerticalScrollRange = ybVar.s.computeVerticalScrollRange();
        int i2 = ybVar.r;
        ybVar.t = iComputeVerticalScrollRange - i2 > 0 && i2 >= i;
        int iComputeHorizontalScrollRange = ybVar.s.computeHorizontalScrollRange();
        int i3 = ybVar.q;
        boolean z = iComputeHorizontalScrollRange - i3 > 0 && i3 >= i;
        ybVar.u = z;
        boolean z2 = ybVar.t;
        if (!z2 && !z) {
            if (ybVar.v != 0) {
                ybVar.d(0);
                return;
            }
            return;
        }
        if (z2) {
            float f = i2;
            ybVar.l = (int) ((((f / 2.0f) + iComputeVerticalScrollOffset) * f) / iComputeVerticalScrollRange);
            ybVar.k = Math.min(i2, (i2 * i2) / iComputeVerticalScrollRange);
        }
        if (ybVar.u) {
            float f2 = iComputeHorizontalScrollOffset;
            float f3 = i3;
            ybVar.o = (int) ((((f3 / 2.0f) + f2) * f3) / iComputeHorizontalScrollRange);
            ybVar.n = Math.min(i3, (i3 * i3) / iComputeHorizontalScrollRange);
        }
        int i4 = ybVar.v;
        if (i4 == 0 || i4 == 1) {
            ybVar.d(1);
        }
    }
}

package defpackage;

import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class dm implements Runnable {
    public int a;
    public int b;
    public OverScroller c;
    public Interpolator d;
    public boolean e;
    public boolean f;
    public final /* synthetic */ RecyclerView g;

    public dm(RecyclerView recyclerView) {
        this.g = recyclerView;
        hl hlVar = RecyclerView.D0;
        this.d = hlVar;
        this.e = false;
        this.f = false;
        this.c = new OverScroller(recyclerView.getContext(), hlVar);
    }

    public final void a(int i, int i2) {
        RecyclerView recyclerView = this.g;
        recyclerView.setScrollState(2);
        this.b = 0;
        this.a = 0;
        Interpolator interpolator = this.d;
        hl hlVar = RecyclerView.D0;
        if (interpolator != hlVar) {
            this.d = hlVar;
            this.c = new OverScroller(recyclerView.getContext(), hlVar);
        }
        this.c.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.e) {
            this.f = true;
            return;
        }
        recyclerView.removeCallbacks(this);
        WeakHashMap weakHashMap = os.a;
        recyclerView.postOnAnimation(this);
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        int i2;
        int i3;
        int i4;
        RecyclerView recyclerView = this.g;
        int[] iArr = recyclerView.p0;
        if (recyclerView.m == null) {
            recyclerView.removeCallbacks(this);
            this.c.abortAnimation();
            return;
        }
        this.f = false;
        this.e = true;
        recyclerView.m();
        OverScroller overScroller = this.c;
        if (overScroller.computeScrollOffset()) {
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int i5 = currX - this.a;
            int i6 = currY - this.b;
            this.a = currX;
            this.b = currY;
            int iL = RecyclerView.l(i5, recyclerView.F, recyclerView.H, recyclerView.getWidth());
            int iL2 = RecyclerView.l(i6, recyclerView.G, recyclerView.I, recyclerView.getHeight());
            int[] iArr2 = recyclerView.p0;
            iArr2[0] = 0;
            iArr2[1] = 0;
            if (recyclerView.r(iL, iL2, iArr2, null, 1)) {
                iL -= iArr[0];
                iL2 -= iArr[1];
            }
            if (recyclerView.getOverScrollMode() != 2) {
                recyclerView.k(iL, iL2);
            }
            if (recyclerView.l != null) {
                iArr[0] = 0;
                iArr[1] = 0;
                recyclerView.Y(iL, iL2, iArr);
                int i7 = iArr[0];
                int i8 = iArr[1];
                recyclerView.m.getClass();
                i = iL - i7;
                i3 = i7;
                i2 = iL2 - i8;
                i4 = i8;
            } else {
                i = iL;
                i2 = iL2;
                i3 = 0;
                i4 = 0;
            }
            if (!recyclerView.o.isEmpty()) {
                recyclerView.invalidate();
            }
            int[] iArr3 = recyclerView.p0;
            iArr3[0] = 0;
            iArr3[1] = 0;
            recyclerView.s(i3, i4, i, i2, null, 1, iArr3);
            int i9 = i - iArr[0];
            int i10 = i2 - iArr[1];
            if (i3 != 0 || i4 != 0) {
                recyclerView.t(i3, i4);
            }
            if (!recyclerView.awakenScrollBars()) {
                recyclerView.invalidate();
            }
            boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i9 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i10 != 0));
            recyclerView.m.getClass();
            if (z) {
                if (recyclerView.getOverScrollMode() != 2) {
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    int i11 = i9 < 0 ? -currVelocity : i9 > 0 ? currVelocity : 0;
                    if (i10 < 0) {
                        currVelocity = -currVelocity;
                    } else if (i10 <= 0) {
                        currVelocity = 0;
                    }
                    if (i11 < 0) {
                        recyclerView.v();
                        if (recyclerView.F.isFinished()) {
                            recyclerView.F.onAbsorb(-i11);
                        }
                    } else if (i11 > 0) {
                        recyclerView.w();
                        if (recyclerView.H.isFinished()) {
                            recyclerView.H.onAbsorb(i11);
                        }
                    }
                    if (currVelocity < 0) {
                        recyclerView.x();
                        if (recyclerView.G.isFinished()) {
                            recyclerView.G.onAbsorb(-currVelocity);
                        }
                    } else if (currVelocity > 0) {
                        recyclerView.u();
                        if (recyclerView.I.isFinished()) {
                            recyclerView.I.onAbsorb(currVelocity);
                        }
                    }
                    if (i11 != 0 || currVelocity != 0) {
                        WeakHashMap weakHashMap = os.a;
                        recyclerView.postInvalidateOnAnimation();
                    }
                }
                if (RecyclerView.B0) {
                    le leVar = recyclerView.c0;
                    int[] iArr4 = leVar.c;
                    if (iArr4 != null) {
                        Arrays.fill(iArr4, -1);
                    }
                    leVar.d = 0;
                }
            } else {
                if (this.e) {
                    this.f = true;
                } else {
                    recyclerView.removeCallbacks(this);
                    WeakHashMap weakHashMap2 = os.a;
                    recyclerView.postOnAnimation(this);
                }
                ne neVar = recyclerView.b0;
                if (neVar != null) {
                    neVar.a(recyclerView, i3, i4);
                }
            }
        }
        recyclerView.m.getClass();
        this.e = false;
        if (!this.f) {
            recyclerView.setScrollState(0);
            recyclerView.d0(1);
        } else {
            recyclerView.removeCallbacks(this);
            WeakHashMap weakHashMap3 = os.a;
            recyclerView.postOnAnimation(this);
        }
    }
}

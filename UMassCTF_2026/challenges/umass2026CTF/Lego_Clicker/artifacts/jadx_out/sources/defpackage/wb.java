package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class wb extends AnimatorListenerAdapter {
    public boolean a = false;
    public final /* synthetic */ yb b;

    public wb(yb ybVar) {
        this.b = ybVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.a = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (this.a) {
            this.a = false;
            return;
        }
        yb ybVar = this.b;
        if (((Float) ybVar.z.getAnimatedValue()).floatValue() == 0.0f) {
            ybVar.A = 0;
            ybVar.d(0);
        } else {
            ybVar.A = 2;
            ybVar.s.invalidate();
        }
    }
}

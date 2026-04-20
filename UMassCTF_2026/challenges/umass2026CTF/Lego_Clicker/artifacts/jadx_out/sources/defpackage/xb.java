package defpackage;

import android.animation.ValueAnimator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class xb implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ yb a;

    public xb(yb ybVar) {
        this.a = ybVar;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
        yb ybVar = this.a;
        ybVar.c.setAlpha(iFloatValue);
        ybVar.d.setAlpha(iFloatValue);
        ybVar.s.invalidate();
    }
}

package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class se extends View {
    public boolean a;

    public se(Context context) {
        super(context);
        this.a = true;
        super.setVisibility(8);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setFilterRedundantCalls(boolean z) {
        this.a = z;
    }

    public void setGuidelineBegin(int i) {
        z7 z7Var = (z7) getLayoutParams();
        if (this.a && z7Var.a == i) {
            return;
        }
        z7Var.a = i;
        setLayoutParams(z7Var);
    }

    public void setGuidelineEnd(int i) {
        z7 z7Var = (z7) getLayoutParams();
        if (this.a && z7Var.b == i) {
            return;
        }
        z7Var.b = i;
        setLayoutParams(z7Var);
    }

    public void setGuidelinePercent(float f) {
        z7 z7Var = (z7) getLayoutParams();
        if (this.a && z7Var.c == f) {
            return;
        }
        z7Var.c = f;
        setLayoutParams(z7Var);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
    }

    @Override // android.view.View
    public void setVisibility(int i) {
    }
}

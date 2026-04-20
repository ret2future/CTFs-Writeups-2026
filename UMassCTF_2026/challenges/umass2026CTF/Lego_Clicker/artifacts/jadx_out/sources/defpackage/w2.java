package defpackage;

import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.util.Property;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.LegoClicker.R;
import java.util.Random;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class w2 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ w2(Object obj, int i, Object obj2) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.a;
        Object obj = this.c;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                x2 x2Var = (x2) obj2;
                try {
                    ((Runnable) obj).run();
                    return;
                } finally {
                    x2Var.a();
                }
            case 1:
                wh whVar = (wh) obj2;
                ConstraintLayout constraintLayout = (ConstraintLayout) obj;
                Random random = whVar.d;
                try {
                    int[] iArr = new int[2];
                    int[] iArr2 = new int[2];
                    whVar.a.g.getLocationInWindow(iArr);
                    whVar.a.f.getLocationInWindow(new int[2]);
                    constraintLayout.getLocationInWindow(iArr2);
                    float height = (iArr[1] - iArr2[1]) + whVar.a.g.getHeight();
                    float f = (r4[1] - iArr2[1]) - height;
                    float width = constraintLayout.getWidth();
                    if (f <= 0.0f) {
                        return;
                    }
                    int iRound = Math.round(54.0f * whVar.getResources().getDisplayMetrics().density);
                    int iRound2 = Math.round(33.0f * whVar.getResources().getDisplayMetrics().density);
                    float fNextFloat = random.nextFloat() * Math.max(1.0f, width - iRound);
                    float fNextFloat2 = (random.nextFloat() * Math.max(1.0f, f - iRound2)) + height;
                    ImageView imageView = new ImageView(whVar);
                    imageView.setImageResource(R.drawable.lego_brick);
                    imageView.setAlpha(0.2f);
                    imageView.setRotation(random.nextFloat() * 360.0f);
                    constraintLayout.addView(imageView, new z7(iRound, iRound2));
                    imageView.setX(fNextFloat);
                    imageView.setY(fNextFloat2);
                    whVar.k++;
                    ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageView, (Property<ImageView, Float>) View.ROTATION, imageView.getRotation(), imageView.getRotation() + 360.0f);
                    objectAnimatorOfFloat.setDuration(random.nextInt(8000) + 10000);
                    objectAnimatorOfFloat.setRepeatCount(-1);
                    objectAnimatorOfFloat.setInterpolator(new LinearInterpolator());
                    objectAnimatorOfFloat.start();
                    ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(imageView, (Property<ImageView, Float>) View.SCALE_X, 0.82f, 1.18f);
                    objectAnimatorOfFloat2.setDuration(random.nextInt(2000) + 2500);
                    objectAnimatorOfFloat2.setRepeatCount(-1);
                    objectAnimatorOfFloat2.setRepeatMode(2);
                    objectAnimatorOfFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
                    objectAnimatorOfFloat2.start();
                    ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(imageView, (Property<ImageView, Float>) View.SCALE_Y, 0.82f, 1.18f);
                    objectAnimatorOfFloat3.setDuration(objectAnimatorOfFloat2.getDuration());
                    objectAnimatorOfFloat3.setRepeatCount(-1);
                    objectAnimatorOfFloat3.setRepeatMode(2);
                    objectAnimatorOfFloat3.setInterpolator(new AccelerateDecelerateInterpolator());
                    objectAnimatorOfFloat3.start();
                    return;
                } catch (Exception unused) {
                    return;
                }
            default:
                ((r4) obj2).b((Typeface) obj);
                return;
        }
    }
}

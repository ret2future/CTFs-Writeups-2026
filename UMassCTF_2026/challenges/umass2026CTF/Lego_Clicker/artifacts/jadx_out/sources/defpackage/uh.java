package defpackage;

import android.view.View;
import com.example.LegoClicker.R;
import com.example.LegoClicker.RA;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class uh implements View.OnClickListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ l2 b;

    public /* synthetic */ uh(l2 l2Var, int i) {
        this.a = i;
        this.b = l2Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.a;
        l2 l2Var = this.b;
        switch (i) {
            case 0:
                wh whVar = (wh) l2Var;
                try {
                    je jeVar = whVar.b;
                    double d = jeVar.c;
                    jeVar.a += d;
                    jeVar.b += d;
                    wh whVar2 = jeVar.j;
                    if (whVar2 != null) {
                        whVar2.e();
                    }
                    view.performHapticFeedback(1);
                    whVar.d();
                    whVar.n(d);
                } catch (Exception unused) {
                    return;
                }
                break;
            case 1:
                ((wh) l2Var).k();
                break;
            case 2:
                wh whVar3 = (wh) l2Var;
                whVar3.g = false;
                whVar3.a.b.setTextColor(whVar3.getResources().getColor(R.color.lego_white, null));
                whVar3.a.a.setTextColor(whVar3.getResources().getColor(R.color.lego_yellow, null));
                whVar3.f();
                whVar3.a.e.setAdapter(whVar3.f);
                break;
            default:
                int i2 = RA.a;
                ((RA) l2Var).finish();
                break;
        }
    }
}

package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class dh implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ hh b;

    public /* synthetic */ dh(hh hhVar, int i) {
        this.a = i;
        this.b = hhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.a;
        hh hhVar = this.b;
        switch (i) {
            case 0:
                ka kaVar = hhVar.c;
                if (kaVar != null) {
                    kaVar.setListSelectionHidden(true);
                    kaVar.requestLayout();
                }
                break;
            default:
                ka kaVar2 = hhVar.c;
                if (kaVar2 != null && kaVar2.isAttachedToWindow() && hhVar.c.getCount() > hhVar.c.getChildCount() && hhVar.c.getChildCount() <= hhVar.m) {
                    hhVar.y.setInputMethodMode(2);
                    hhVar.f();
                    break;
                }
                break;
        }
    }
}

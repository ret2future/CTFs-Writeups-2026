package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class g6 extends x7 {
    public int h;
    public int i;
    public h6 j;

    public boolean getAllowsGoneWidget() {
        return this.j.s0;
    }

    public int getMargin() {
        return this.j.t0;
    }

    public int getType() {
        return this.h;
    }

    public void setAllowsGoneWidget(boolean z) {
        this.j.s0 = z;
    }

    public void setDpMargin(int i) {
        this.j.t0 = (int) ((i * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setMargin(int i) {
        this.j.t0 = i;
    }

    public void setType(int i) {
        this.h = i;
    }
}

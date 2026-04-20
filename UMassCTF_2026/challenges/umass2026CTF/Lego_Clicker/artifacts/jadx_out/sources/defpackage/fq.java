package defpackage;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class fq extends hb {
    public final /* synthetic */ int o;
    public boolean p;
    public int q;
    public final /* synthetic */ Object r;

    public fq(et etVar) {
        this.o = 1;
        this.r = etVar;
        this.p = false;
        this.q = 0;
    }

    @Override // defpackage.ft
    public final void b() {
        int i = this.o;
        Object obj = this.r;
        switch (i) {
            case 0:
                if (!this.p) {
                    ((gq) obj).a.setVisibility(this.q);
                }
                break;
            default:
                int i2 = this.q + 1;
                this.q = i2;
                et etVar = (et) obj;
                if (i2 == etVar.a.size()) {
                    ft ftVar = etVar.d;
                    if (ftVar != null) {
                        ftVar.b();
                    }
                    this.q = 0;
                    this.p = false;
                    etVar.e = false;
                }
                break;
        }
    }

    @Override // defpackage.hb, defpackage.ft
    public void c() {
        switch (this.o) {
            case 0:
                this.p = true;
                break;
        }
    }

    @Override // defpackage.hb, defpackage.ft
    public final void e() {
        int i = this.o;
        Object obj = this.r;
        switch (i) {
            case 0:
                ((gq) obj).a.setVisibility(0);
                break;
            default:
                if (!this.p) {
                    this.p = true;
                    ft ftVar = ((et) obj).d;
                    if (ftVar != null) {
                        ftVar.e();
                    }
                    break;
                }
                break;
        }
    }

    public fq(gq gqVar, int i) {
        this.o = 0;
        this.r = gqVar;
        this.q = i;
        this.p = false;
    }
}

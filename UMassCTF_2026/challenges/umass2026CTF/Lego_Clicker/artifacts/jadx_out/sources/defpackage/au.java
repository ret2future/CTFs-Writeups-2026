package defpackage;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class au extends zt {
    public au(eu euVar, WindowInsets windowInsets) {
        super(euVar, windowInsets);
    }

    @Override // defpackage.du
    public eu a() {
        return eu.c(this.c.consumeDisplayCutout(), null);
    }

    @Override // defpackage.du
    public ba e() {
        DisplayCutout displayCutout = this.c.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new ba(displayCutout);
    }

    @Override // defpackage.yt, defpackage.du
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof au)) {
            return false;
        }
        au auVar = (au) obj;
        return Objects.equals(this.c, auVar.c) && Objects.equals(this.e, auVar.e);
    }

    @Override // defpackage.du
    public int hashCode() {
        return this.c.hashCode();
    }
}

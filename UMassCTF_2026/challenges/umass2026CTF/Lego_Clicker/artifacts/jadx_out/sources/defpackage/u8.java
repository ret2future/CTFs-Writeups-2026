package defpackage;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class u8 implements t8, v8 {
    public final /* synthetic */ int a = 0;
    public ClipData b;
    public int c;
    public int d;
    public Uri e;
    public Bundle f;

    public u8(u8 u8Var) {
        ClipData clipData = u8Var.b;
        clipData.getClass();
        this.b = clipData;
        int i = u8Var.c;
        if (i < 0) {
            Locale locale = Locale.US;
            z6.f("source is out of range of [0, 5] (too low)");
            throw null;
        }
        if (i > 5) {
            Locale locale2 = Locale.US;
            z6.f("source is out of range of [0, 5] (too high)");
            throw null;
        }
        this.c = i;
        int i2 = u8Var.d;
        if ((i2 & 1) == i2) {
            this.d = i2;
            this.e = u8Var.e;
            this.f = u8Var.f;
            return;
        }
        throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i2) + ", but only 0x" + Integer.toHexString(1) + " are allowed");
    }

    @Override // defpackage.t8
    public w8 build() {
        return new w8(new u8(this));
    }

    @Override // defpackage.v8
    public ClipData e() {
        return this.b;
    }

    @Override // defpackage.v8
    public int f() {
        return this.d;
    }

    @Override // defpackage.v8
    public ContentInfo h() {
        return null;
    }

    @Override // defpackage.t8
    public void m(Uri uri) {
        this.e = uri;
    }

    @Override // defpackage.v8
    public int o() {
        return this.c;
    }

    @Override // defpackage.t8
    public void setExtras(Bundle bundle) {
        this.f = bundle;
    }

    public String toString() {
        String str;
        switch (this.a) {
            case 1:
                Uri uri = this.e;
                StringBuilder sb = new StringBuilder("ContentInfoCompat{clip=");
                sb.append(this.b.getDescription());
                sb.append(", source=");
                int i = this.c;
                sb.append(i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? String.valueOf(i) : "SOURCE_PROCESS_TEXT" : "SOURCE_AUTOFILL" : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP");
                sb.append(", flags=");
                int i2 = this.d;
                sb.append((i2 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i2));
                if (uri == null) {
                    str = "";
                } else {
                    str = ", hasLinkUri(" + uri.toString().length() + ")";
                }
                sb.append(str);
                sb.append(this.f != null ? ", hasExtras" : "");
                sb.append("}");
                return sb.toString();
            default:
                return super.toString();
        }
    }

    @Override // defpackage.t8
    public void u(int i) {
        this.d = i;
    }

    public /* synthetic */ u8() {
    }
}

package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.tr;
import defpackage.ur;
import defpackage.z6;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static IconCompat read(tr trVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.a = -1;
        iconCompat.c = null;
        iconCompat.d = null;
        iconCompat.e = 0;
        iconCompat.f = 0;
        iconCompat.g = null;
        iconCompat.h = IconCompat.k;
        iconCompat.i = null;
        iconCompat.a = !trVar.e(1) ? -1 : ((ur) trVar).e.readInt();
        byte[] bArr = iconCompat.c;
        if (trVar.e(2)) {
            Parcel parcel = ((ur) trVar).e;
            int i = parcel.readInt();
            if (i < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[i];
                parcel.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.c = bArr;
        iconCompat.d = trVar.f(iconCompat.d, 3);
        int i2 = iconCompat.e;
        if (trVar.e(4)) {
            i2 = ((ur) trVar).e.readInt();
        }
        iconCompat.e = i2;
        int i3 = iconCompat.f;
        if (trVar.e(5)) {
            i3 = ((ur) trVar).e.readInt();
        }
        iconCompat.f = i3;
        iconCompat.g = (ColorStateList) trVar.f(iconCompat.g, 6);
        String string = iconCompat.i;
        if (trVar.e(7)) {
            string = ((ur) trVar).e.readString();
        }
        iconCompat.i = string;
        String string2 = iconCompat.j;
        if (trVar.e(8)) {
            string2 = ((ur) trVar).e.readString();
        }
        iconCompat.j = string2;
        iconCompat.h = PorterDuff.Mode.valueOf(iconCompat.i);
        switch (iconCompat.a) {
            case -1:
                Parcelable parcelable = iconCompat.d;
                if (parcelable != null) {
                    iconCompat.b = parcelable;
                    return iconCompat;
                }
                z6.f("Invalid icon");
                return null;
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.d;
                if (parcelable2 != null) {
                    iconCompat.b = parcelable2;
                    return iconCompat;
                }
                byte[] bArr3 = iconCompat.c;
                iconCompat.b = bArr3;
                iconCompat.a = 3;
                iconCompat.e = 0;
                iconCompat.f = bArr3.length;
                return iconCompat;
            case 2:
            case 4:
            case 6:
                String str = new String(iconCompat.c, Charset.forName("UTF-16"));
                iconCompat.b = str;
                if (iconCompat.a == 2 && iconCompat.j == null) {
                    iconCompat.j = str.split(":", -1)[0];
                }
                return iconCompat;
            case 3:
                iconCompat.b = iconCompat.c;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, tr trVar) {
        trVar.getClass();
        iconCompat.i = iconCompat.h.name();
        switch (iconCompat.a) {
            case -1:
                iconCompat.d = (Parcelable) iconCompat.b;
                break;
            case 1:
            case 5:
                iconCompat.d = (Parcelable) iconCompat.b;
                break;
            case 2:
                iconCompat.c = ((String) iconCompat.b).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.c = (byte[]) iconCompat.b;
                break;
            case 4:
            case 6:
                iconCompat.c = iconCompat.b.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i = iconCompat.a;
        if (-1 != i) {
            trVar.h(1);
            ((ur) trVar).e.writeInt(i);
        }
        byte[] bArr = iconCompat.c;
        if (bArr != null) {
            trVar.h(2);
            Parcel parcel = ((ur) trVar).e;
            parcel.writeInt(bArr.length);
            parcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.d;
        if (parcelable != null) {
            trVar.h(3);
            ((ur) trVar).e.writeParcelable(parcelable, 0);
        }
        int i2 = iconCompat.e;
        if (i2 != 0) {
            trVar.h(4);
            ((ur) trVar).e.writeInt(i2);
        }
        int i3 = iconCompat.f;
        if (i3 != 0) {
            trVar.h(5);
            ((ur) trVar).e.writeInt(i3);
        }
        ColorStateList colorStateList = iconCompat.g;
        if (colorStateList != null) {
            trVar.h(6);
            ((ur) trVar).e.writeParcelable(colorStateList, 0);
        }
        String str = iconCompat.i;
        if (str != null) {
            trVar.h(7);
            ((ur) trVar).e.writeString(str);
        }
        String str2 = iconCompat.j;
        if (str2 != null) {
            trVar.h(8);
            ((ur) trVar).e.writeString(str2);
        }
    }
}

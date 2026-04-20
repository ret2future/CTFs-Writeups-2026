package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import defpackage.tr;
import defpackage.ur;
import defpackage.vr;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(tr trVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        vr vrVarG = remoteActionCompat.a;
        boolean z = true;
        if (trVar.e(1)) {
            vrVarG = trVar.g();
        }
        remoteActionCompat.a = (IconCompat) vrVarG;
        CharSequence charSequence = remoteActionCompat.b;
        if (trVar.e(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((ur) trVar).e);
        }
        remoteActionCompat.b = charSequence;
        CharSequence charSequence2 = remoteActionCompat.c;
        if (trVar.e(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((ur) trVar).e);
        }
        remoteActionCompat.c = charSequence2;
        remoteActionCompat.d = (PendingIntent) trVar.f(remoteActionCompat.d, 4);
        boolean z2 = remoteActionCompat.e;
        if (trVar.e(5)) {
            z2 = ((ur) trVar).e.readInt() != 0;
        }
        remoteActionCompat.e = z2;
        boolean z3 = remoteActionCompat.f;
        if (!trVar.e(6)) {
            z = z3;
        } else if (((ur) trVar).e.readInt() == 0) {
            z = false;
        }
        remoteActionCompat.f = z;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, tr trVar) {
        trVar.getClass();
        IconCompat iconCompat = remoteActionCompat.a;
        trVar.h(1);
        trVar.i(iconCompat);
        CharSequence charSequence = remoteActionCompat.b;
        trVar.h(2);
        Parcel parcel = ((ur) trVar).e;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.c;
        trVar.h(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        PendingIntent pendingIntent = remoteActionCompat.d;
        trVar.h(4);
        parcel.writeParcelable(pendingIntent, 0);
        boolean z = remoteActionCompat.e;
        trVar.h(5);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = remoteActionCompat.f;
        trVar.h(6);
        parcel.writeInt(z2 ? 1 : 0);
    }
}

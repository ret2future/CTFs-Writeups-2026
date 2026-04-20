package defpackage;

import android.view.ContentInfo;
import android.view.View;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ks {
    public static String[] a(View view) {
        return view.getReceiveContentMimeTypes();
    }

    public static w8 b(View view, w8 w8Var) {
        ContentInfo contentInfoH = w8Var.a.h();
        Objects.requireNonNull(contentInfoH);
        ContentInfo contentInfoE = s8.e(contentInfoH);
        ContentInfo contentInfoPerformReceiveContent = view.performReceiveContent(contentInfoE);
        if (contentInfoPerformReceiveContent == null) {
            return null;
        }
        return contentInfoPerformReceiveContent == contentInfoE ? w8Var : new w8(new e0(contentInfoPerformReceiveContent));
    }

    public static void c(View view, String[] strArr, ek ekVar) {
        if (ekVar == null) {
            view.setOnReceiveContentListener(strArr, null);
        } else {
            view.setOnReceiveContentListener(strArr, new ls(ekVar));
        }
    }
}

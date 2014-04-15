package com.warnappmobile.gcm.util;

import android.content.Context;
import android.util.Log;

import com.google.android.gcm.GCMRegistrar;

public class GCM {
    
    /**
     * M�todo respons�vel por ativar o uso do GCM.
     * @param context
     */
    public static void ativa(Context context) {
        GCMRegistrar.checkDevice(context);
        GCMRegistrar.checkManifest(context);
        final String regId = GCMRegistrar
                .getRegistrationId(context);
        if (regId.equals("")) {
            GCMRegistrar.register(context, Constantes.SENDER_ID);
            Log.i(Constantes.TAG, "Servi�o GCM ativado.");
        } else {
            Log.i(Constantes.TAG, "O servi�o GCM j� est� ativo. ID: " + regId);
        }
    }
     
    /**
     * M�todo respons�vel por desativar o uso do GCM.
     * @param context
     */
    public static void desativa(Context context) {
            GCMRegistrar.unregister(context);
            Log.i(Constantes.TAG, "Servi�o GCM desativado.");
    }
     
    /**
     * M�todo respons�vel por verificar se o aplicativo 
     * est� ou n�o registrado para uso do GCM.
     * @param context
     * @return
     */
    public static boolean isAtivo(Context context) {
        return GCMRegistrar.isRegistered(context);
    }
 
}
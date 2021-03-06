package com.warnappmobile.gcm.util;

import com.warnappmobile.activity.MostraMensagemActivity;
import com.warnappmobile.activity.WarnAppActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.appcompat.R;

public class Notificacao {

	/**
	 * Método responsável por disparar uma notificação na barra de status.
	 * 

	 */
	public static void mostraNotificacao(String titulo, String mensagem,
			Context context) {

		// Tempo em que a Notificação será disparada
		long tempoDefinido = System.currentTimeMillis();

		// Objeto Notification
		Notification notification = new Notification(R.drawable.abc_ab_share_pack_holo_dark,
				titulo, tempoDefinido);

		// Intent que será disparada quando o usuário clicar sobre a Notificação
		Intent intent = new Intent(context, MostraMensagemActivity.class);
		intent.putExtra("mensagem_recebida", mensagem);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// Configurando os dados da Notificação
		notification.setLatestEventInfo(context, titulo, mensagem,
				pendingIntent);

		// Oculta a notificação após o usuário clicar sobre ela
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		
		// Define alertas de acordo com o padrão definido no dispositivo
		notification.defaults = Notification.DEFAULT_ALL;

		// Agenda a Notificação
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);
	}

}

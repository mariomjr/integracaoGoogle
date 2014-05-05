package com.warnappmobile.activity;

import com.warnappmobile.gcm.util.*;
import com.example.warnappmobile.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

public class WarnAppActivity extends ActionBarActivity {

	private Button botaoAtivarDesativar;
	private boolean gcmAtivo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_warn_app);

		botaoAtivarDesativar = (Button) findViewById(R.id.botao_ativar_desativar);
		/*
		 *  Verifica se o GCM está ativo ou não para definir a Label 
		 *  do botão na tela
		 */
		gcmAtivo = GCM.isAtivo(getApplicationContext());
		// Define a Label do botão
		defineLabelBotao();
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.warn_app, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_warn_app,
					container, false);
			return rootView;
		}
	}
	
	public void ativaDesativaGCM(View view) {
		if (GCM.isAtivo(getApplicationContext())) {
			GCM.desativa(getApplicationContext());
			gcmAtivo = false;
			Toast.makeText(getApplicationContext(), "GCM desativado!", Toast.LENGTH_LONG).show();
		} else {
			GCM.ativa(getApplicationContext());
			gcmAtivo = true;
			Toast.makeText(getApplicationContext(), "GCM ativado!", Toast.LENGTH_LONG).show();
		}
		defineLabelBotao();
	}
	
	private void defineLabelBotao() {
		if (gcmAtivo) {
			botaoAtivarDesativar.setText("Desativar");
		} else {
			botaoAtivarDesativar.setText("Ativar");
		}
	}

}

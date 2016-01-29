/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdsf3fstockwatcher;

import javafx.application.Platform;

/**
 *
 * @author Michael
 * 
 * Created based upon github project here: https://github.com/victorlaerte/javafx-asynctask
 */
public abstract class AsyncTask {

	public abstract void onPreExecute();

	public abstract void doInBackground();

	public abstract void onPostExecute();


	private final Thread backGroundThread = new Thread(new Runnable() {

		@Override
		public void run() {

			doInBackground();

			Platform.runLater(new Runnable() {

				@Override
				public void run() {

					onPostExecute();
				}
			});
		}
	});

	public void execute() {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				onPreExecute();

				backGroundThread.start();
			}
		});
	}


	public void interrupt() {

		this.backGroundThread.interrupt();
	}
}

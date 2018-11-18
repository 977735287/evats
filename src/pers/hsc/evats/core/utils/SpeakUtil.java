package pers.hsc.evats.core.utils;

import java.io.FileInputStream;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * @author hsc
 *
 * May 30, 2018
 */
public class SpeakUtil {

	public static void speak(String content) {
		ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");

		Dispatch sapo = sap.getObject();
		try {

			// 音量 0-100
			sap.setProperty("Volume", new Variant(100));
			// 语音朗读速度 -10 到 +10
			sap.setProperty("Rate", new Variant(-2));

			// 执行朗读
			Dispatch.call(sapo, "Speak", new Variant(content));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sapo.safeRelease();
			sap.safeRelease();
		}
	}
	
	public static void sound(String path) {
		try {
			FileInputStream fileau = new FileInputStream(path);
			AudioStream as = new AudioStream(fileau);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

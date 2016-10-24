
import java.applet.Applet;
/*
 * Sound class that contains all the sound objects in the game
 * 
 *  Class that contains all the audio clips to be used by the game.
 *	Credits to http://timewarpgamer.com and http://themushroomkingdom.net/ for the free sound files used in this game.
 * 	Made by Rayven N. Ingles
 *	All wrongs reversed 2016
 *
 */
public class Sound {
	public static final java.applet.AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("ball.wav"));
	public static final java.applet.AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("gameover.wav"));
	public static final java.applet.AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("back.wav"));
}
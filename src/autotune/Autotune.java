package autotune;

import java.io.*;
import javazoom.jl.decoder.*;
import javazoom.jl.converter.*;
import autotune.PitchShift;

public class Autotune {
	private double[] data;
	private SampleBuffer sample;
	private Header head;
	private int pitchToShift;

	public Autotune(Header h, SampleBuffer samp) {
		//pitchToShift = determinePitchToShift(h, samp);
		pitchToShift = 1;
		sample = samp;
		data = samp.getBuffer();
		head = h;
	}
/*	private determinePitchToShift (Header head, SampleBuffer samples) {
		/* Here is where the algorithm determining 
		 * how much to shift each pitch will be.
		 * there are multiple ways we can implement this...
		 *
		 * We can either shift each pitch to the nearest semitone or
		 * shift it in the context of a certain key. The problem with
		 * shifting it to the nearest semitone is that the output would
		 * most likely be atonal, which is not likely to be desirable for
		 * someone needing autotune. If we shift in the context of a
		 * certain key, then we can match it to some predetermined
		 * key inputted by the user or by the first (or first several) notes
		 * of the inputted file. We could determine what semitone to 
		 * match to by comparing it to an array of notes (their Hz values)
		 * in that key and finding the closest one.
		     
	} */
	public SampleBuffer getTuned () {
		PitchShift shifty = new PitchShift(Obuffer.OBUFFERSIZE);
		shifty.setPitchShift(this.pitchToShift);
		shifty.smbPitchShift(data, data, pitchToShift, sample.getBufferLength());
		SampleBuffer rewritten = new SampleBuffer(sample.getSampleFrequency(),sample.getChannelCount());
		return rewritten;
		//shifty.setOversampling(4);
		//shifty.setSampleRate(sampleRate);
		//shifty.setFftFrameSize(2048);
		//	shifty./*setAllTheRelevantParameters�*/
	//	shifty.smbPitchShift(/*relevant data from sample and header */);
	//	return (/*relevant data from sample and header now changed */);
	}
}
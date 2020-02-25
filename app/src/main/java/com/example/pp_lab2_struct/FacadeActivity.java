package com.example.pp_lab2_struct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class FacadeActivity extends AppCompatActivity {

    //-----------------------
    public class VideoFile {
        private String name;
        private String codecType;

        public VideoFile(String name) {
            this.name = name;
            this.codecType = name.substring(name.indexOf(".") + 1);
        }

        public String getCodecType() {
            return codecType;
        }

        public String getName() {
            return name;
        }
    }
    //-----------------------
    public interface Codec {
    }
    //-----------------------
    public static class MPEG4CompressionCodec implements Codec {
        public String type = "mp4";

    }
    //-----------------------
    public static class OggCompressionCodec implements Codec {
        public String type = "ogg";
    }
    //-----------------------
    public static class CodecFactory {
        public static Codec extract(VideoFile file) {
            String type = file.getCodecType();
            if (type.equals("mp4")) {
                string+="\n"+"CodecFactory: extracting mpeg audio...";
                //System.out.println("CodecFactory: extracting mpeg audio...");
                return new MPEG4CompressionCodec();
            }
            else {
                string+="\n"+"CodecFactory: extracting ogg audio...";
                //System.out.println("CodecFactory: extracting ogg audio...");
                return new OggCompressionCodec();
            }
        }
    }
    //-----------------------
    public static class BitrateReader {
        public static VideoFile read(VideoFile file, Codec codec) {
            string+="\n"+"BitrateReader: reading file...";
            //System.out.println("BitrateReader: reading file...");
            return file;
        }

        public static VideoFile convert(VideoFile buffer, Codec codec) {
            string+="\n"+"BitrateReader: writing file...";
            //System.out.println("BitrateReader: writing file...");
            return buffer;
        }
    }
    //-----------------------
    public class AudioMixer {
        public File fix(VideoFile result){
            string+="\n"+"AudioMixer: fixing audio...";
            //System.out.println("AudioMixer: fixing audio...");
            return new File("tmp");
        }
    }
    //-----------------------
    public class VideoConversionFacade {
        public File convertVideo(String fileName, String format) {
            string+="\n"+"VideoConversionFacade: conversion started.";
            //System.out.println("VideoConversionFacade: conversion started.");
            VideoFile file = new VideoFile(fileName);
            Codec sourceCodec = CodecFactory.extract(file);
            Codec destinationCodec;
            if (format.equals("mp4")) {
                destinationCodec = new OggCompressionCodec();
            } else {
                destinationCodec = new MPEG4CompressionCodec();
            }
            VideoFile buffer = BitrateReader.read(file, sourceCodec);
            VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
            File result = (new AudioMixer()).fix(intermediateResult);
            string+="\n"+"VideoConversionFacade: conversion completed.";
            //System.out.println("VideoConversionFacade: conversion completed.");
            return result;
        }
    }
    //-----------------------
    //-----------------------
    //-----------------------
    //-----------------------


    public static String string="";
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facade);
        tv=(TextView)findViewById(R.id.tv);
    }

    public void create(View view){
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
        tv.setText(string);
    }

    public void clear(View view){
        tv.setText("");
    }

}

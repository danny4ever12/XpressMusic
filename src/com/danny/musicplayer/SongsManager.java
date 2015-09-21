package com.danny.musicplayer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Environment;
import android.util.Log;



public class SongsManager {
	
	
	private ArrayList<HashMap<String,String>> songsList = new ArrayList<HashMap<String,String>>();
	
	// Constructor
	public SongsManager(){
		
	}
	
	
	public ArrayList<HashMap<String,String>> getPlayList(){
		
		
		InputStream inputStream;
		try {
			
			//fetching document.json file from sdcard/internal memory
			File setfile=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/document.json");
			 inputStream = new  FileInputStream(setfile);
		   
			
			
			 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			
			 int ctr;
			 try {
			    ctr = inputStream.read();
			    while (ctr != -1) {
			        byteArrayOutputStream.write(ctr);
			        ctr = inputStream.read();
			    }
			    inputStream.close();
			 } catch (IOException e) {
			    e.printStackTrace();
			 }
			
			 try {
			    // Parse the data into jsonobject to get original data in form of json.
			   JSONArray Data,subdata;
				JSONObject jObject = new JSONObject(
			            byteArrayOutputStream.toString());
			  
		
				
			    Data = jObject.getJSONArray("categories");
			    
                for (int i = 0; i < Data.length(); i++) {       
                        JSONObject jsonObj2 = Data.getJSONObject(i);
                        
                       String cat_name = jsonObj2.getString("name");
                       // String cat_img = jsonObj2.getString("categoryImage");
                       
  			            
                       Log.v("cat", cat_name); 
  			           
                       subdata=jsonObj2.getJSONArray("songlist");
  			        
                        for(int k=0;k<subdata.length();k++)
                        {
                        	JSONObject subjo=subdata.getJSONObject(k);
                            
                        	//fetching each song data from array within                       	
                    		HashMap<String,String> song = new HashMap<String,String>();
                        	 String sng_name=subjo.getString("songName");
                        	 String sng_url=subjo.getString("songUrl");
                        	 String sng_img=subjo.getString("songImage");
                             
                        	 song.put("songTitle",sng_name+":"+cat_name );
                        	 song.put("songPath",sng_url );
                        	 song.put("songImage",sng_img );
                        	  
                        	 songsList.add(song);
                                           
                        
                        }
          
                }
			} catch (Exception e) {
			    e.printStackTrace();
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
		}
	
		// return songs list array
		return songsList;
	}
	
	/**
	 * Class to filter files which are having .mp3 extension
	 * */
	class FileExtensionFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return (name.endsWith(".mp3") || name.endsWith(".MP3"));
		}
	}
}

package com.example.andre.clientquizzoneandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	private Socket s;
	private String ip;
	private int porta;

	public Client(String ip)
	{
		this.ip = ip;
		porta = 9999;
	}

	public Client(String ip, int porta)
	{
		this.ip = ip;
		this.porta = porta;
	}
	
	public void creaConnessione()
	{
		System.out.println("Ci provo");
		try {
			s= new Socket ("192.168.56.1", porta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Fatto");
	}
	
	public void attendi() throws IOException{
		boolean flag=false;
		while(!flag){
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String t = new String (in.readLine());
			if(t.equals("$Iniziamo!$")){
				flag=true;
			}
		}
	}
	
	public String getText() throws IOException{
		String t="";
		boolean flag=false;
		while(!flag){
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			t = new String (in.readLine());
			if(t.indexOf("$")==0){
				flag=true;
			}
		}
		return t;
	}
	
	public void invio(String n) throws IOException
	{
		PrintWriter out = new PrintWriter (s.getOutputStream(), true);
		out.println(n);
	}
	
	public void close() {
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
